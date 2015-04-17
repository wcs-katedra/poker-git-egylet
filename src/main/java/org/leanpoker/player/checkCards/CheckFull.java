package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import java.util.ArrayList;
import java.util.List;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckFull extends Check {

    @Override
    protected void check() {
        boolean haveDrill = false;
        //először megnézem, hogy van-e drill
        for (String rank : ranks) {
            if (countRank(rank) == 3) {
                haveDrill = true;
                highRank1 = rank;
            }
        }
        //ha van drill, párt keresek, ha az is van, akkor megvan a full
        if (haveDrill) {
            for (String rank : ranks) {
                if (countRank(rank) == 2) {
                    highRank2 = rank;
                    handRank = HandRank.FULL;
                }
            }
        }

        if (handRank != null) {
            calcMyCardsOfHand();
            makeOrderedCardList();
        }
    }

    @Override
    protected void calcMyCardsOfHand() {
        for (Card card : cards) {
            if (card.isInMyHand() && (card.getRank().equals(highRank1) || card.getRank().equals(highRank2))) {
                myCardsOfHand++;
            }
        }
    }

    @Override
    protected void makeOrderedCardList() {
        List<Card> drillCards = new ArrayList<>();
        List<Card> pairCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.isEqualRank(highRank1)) {
                drillCards.add(card);
            }
            if (card.isEqualRank(highRank2)) {
                pairCards.add(card);
            }
        }
        orderedCardList.addAll(drillCards);
        orderedCardList.addAll(pairCards);
    }

}
