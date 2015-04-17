package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import org.git_egylet.tools.Tools;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckPoker extends Check {

    @Override
    protected void check() {
        for (String rank : ranks) {
            if (countRank(rank) == 4) {
                handRank = HandRank.POKER;
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
        for (Card card : cards) {
            if (card.isEqualRank(highRank1)) {
                orderedCardList.add(card);
            }
        }
        cards = Tools.orderCards(cards);
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
