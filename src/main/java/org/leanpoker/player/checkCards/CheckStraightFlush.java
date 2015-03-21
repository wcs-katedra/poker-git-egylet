package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckStraightFlush extends Check {

    @Override
    protected void check() {
        CheckResult straightCheckResult = new CheckStraight().getResult(cards);
        if (straightCheckResult != null) {
            boolean isFlush = true;
            int highRankIndex = ranks.indexOf(straightCheckResult.getHighRank1());
            String[] ranksOfStraight = getRanksOfStraight(highRankIndex);
            for (String suit : suits) {
                isFlush = true;
                for (String rank : ranksOfStraight) {
                    if (countRank(rank) > 0 && countSuit(suit) > 0) {
                        isFlush = false;
                        break;
                    }
                }
                if (isFlush) {
                    break;
                }
            }
            if (isFlush) {
                highRank1 = straightCheckResult.getHighRank1();
                hand = Hand.STRAIGH_FLUSH;
            }
        }
    }

    private String[] getRanksOfStraight(int highRankIndex) {
        String[] ranksOfStraight = new String[5];
        ranksOfStraight[4] = ranks.get(highRankIndex);
        ranksOfStraight[3] = ranks.get(highRankIndex - 1);
        ranksOfStraight[2] = ranks.get(highRankIndex - 2);
        ranksOfStraight[1] = ranks.get(highRankIndex - 3);
        if (highRankIndex - 4 >= 0) {
            ranksOfStraight[0] = ranks.get(highRankIndex - 4);
        } else {
            ranksOfStraight[0] = "A";
        }
        return ranksOfStraight;
    }

}
