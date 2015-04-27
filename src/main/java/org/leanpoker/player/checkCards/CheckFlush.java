package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import org.git_egylet.tools.Tools;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckFlush extends Check {

    private String flushSuit = "";

    @Override
    protected void check() {
        for (String suit : suits) {
            if (countSuit(suit) >= 5) {
                handRank = HandRank.FLUSH;
                flushSuit = suit;
            }
        }
        //legmagasabb lap kiszámítása flush esetén
        if (handRank == handRank.FLUSH) {
            for (String rank : ranks) {
                if (countRank(rank) > 0) {
                    highRank1 = rank;
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
            if (card.isInMyHand() && card.getSuit().equals(flushSuit)) {
                myCardsOfHand++;
            }
        }
    }

    @Override
    protected void makeOrderedCardList() {
        for (Card card : cards) {
            if (card.isEqualSuit(flushSuit)) {
                orderedCardList.add(card);
            }
        }
        orderedCardList = Tools.orderCards(orderedCardList);
    }

}
