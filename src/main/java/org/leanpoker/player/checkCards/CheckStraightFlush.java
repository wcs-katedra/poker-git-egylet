package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import java.util.Arrays;
import org.git_egylet.tools.Tools;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckStraightFlush extends Check {

    private String flushSuit = "";
    private String[] ranksOfStraight = null;

    @Override
    protected void check() {
        CheckResult straightCheckResult = new CheckStraight().getResult(cards);
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
                handRank = HandRank.STRAIGH_FLUSH;
            }

            if (handRank != null) {
                calcMyCardsOfHand();
                makeOrderedCardList();
            }
        }
    }

    private String[] getRanksOfStraight(int highRankIndex) {
        ranksOfStraight = new String[5];
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

    @Override
    protected void calcMyCardsOfHand() {
        for (Card card : cards) {
            if (card.isInMyHand() && card.isEqualSuit(flushSuit) && Arrays.asList(ranksOfStraight).contains(card.getRank())) {
                myCardsOfHand++;
            }
        }
    }

    @Override
    protected void makeOrderedCardList() {
        String rank;
        for (int i = ranksOfStraight.length - 1; i >= 0; i--) {
            rank = ranksOfStraight[i];
            for (Card card : cards) {
                if (card.isEqualRank(rank) && card.isEqualSuit(flushSuit)) {
                    orderedCardList.add(card);
                    break;
                }
            }
        }
    }
}
