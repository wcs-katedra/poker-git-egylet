package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import org.git_egylet.tools.Tools;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckOnePair extends Check {

    @Override
    protected void check() {
        for (String rank : ranks) {
            if (countRank(rank) == 2) {
                handRank = HandRank.ONE_PAIR;
                highRank1 = rank;
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
            if (card.isInMyHand() && card.getRank().equals(highRank1)) {
                myCardsOfHand++;
            }
        }
    }

    @Override
    protected void makeOrderedCardList() {
        cards = Tools.orderCards(cards);
        if (cards.size() == 2) {
            orderedCardList.addAll(cards);
        } else {
            for (Card card : cards) {
                if (card.isEqualRank(highRank1)) {
                    orderedCardList.add(card);
                }
            }
            int i = 0;
            Card actCard;
            while (orderedCardList.size() < 5) {
                actCard = cards.get(i);
                if (!actCard.isEqualRank(highRank1)) {
                    orderedCardList.add(actCard);
                }
                i++;
            }
        }
    }
}
