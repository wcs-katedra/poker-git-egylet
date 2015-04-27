package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckStraight extends Check {

    private String[] ranksOfStraight;

    @Override
    protected void check() {
        int straightCounter = 0;
        boolean isStraight = false;

        if (countRank("A") > 0) {
            straightCounter++;
        }

        for (String rank : ranks) {
            if (countRank(rank) > 0) {
                straightCounter++;
            } else {
                straightCounter = 0;
            }
            if (straightCounter >= 5) {
                isStraight = true;
                highRank1 = rank;
            }
        }

        if (isStraight) {
            handRank = HandRank.STRAIGHT;
        }

        if (handRank != null) {
            calcMyCardsOfHand();
            makeOrderedCardList();
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
        ranksOfStraight = getRanksOfStraight(ranks.indexOf(highRank1));
        for (Card card : cards) {
            for (String rank : ranksOfStraight) {
                if (card.isInMyHand() && card.isEqualRank(rank)) {
                    myCardsOfHand++;
                }
            }
        }
    }

    @Override
    protected void makeOrderedCardList() {
        String rank;
        for (int i = ranksOfStraight.length - 1; i >= 0; i--) {
            rank = ranksOfStraight[i];
            for (Card card : cards) {
                if (card.isEqualRank(rank)) {
                    orderedCardList.add(card);
                    break;
                }
            }
        }
    }

}
