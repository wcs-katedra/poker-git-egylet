package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import java.util.Arrays;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckStraightFlush extends Check {

    @Override
    protected void check() {
        CheckResult straightCheckResult = new CheckStraight().getResult(cards);
        String[] ranksOfStraight = null;
        String flushSuit = "";
        if (straightCheckResult != null) {
            boolean isFlush = false;
            int highRankIndex = ranks.indexOf(straightCheckResult.getHighRank1());
            ranksOfStraight = getRanksOfStraight(highRankIndex);
            for (String suit : suits) {
                if (!isFlush) {
                    flushSuit = suit;
                    isFlush = true;

                    for (String rank : ranksOfStraight) {
                        if (!haveCardAs(rank, suit)) {
                            isFlush = false;
                            break;
                        }
                    }
                }
            }
            if (isFlush) {
                highRank1 = straightCheckResult.getHighRank1();
                hand = Hand.STRAIGH_FLUSH;
            }

            //myCardsOfHand kiszámítása:
            if (hand != null) {
                for (Card card : cards) {
                    if (card.isInMyHand() && card.isEqualSuit(flushSuit) && Arrays.asList(ranksOfStraight).contains(card.getRank())) {
                        myCardsOfHand++;
                    }
                }
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
