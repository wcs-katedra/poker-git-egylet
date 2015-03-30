package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckFlush extends Check {

    @Override
    protected void check() {
        String flushSuit = "";
        for (String suit : suits) {
            if (countSuit(suit) >= 5) {
                hand = Hand.FLUSH;
                flushSuit = suit;
            }
        }
        //legmagasabb lap kiszámítása flush esetén
        if (hand == hand.FLUSH) {
            for (String rank : ranks) {
                if (countRank(rank) > 0) {
                    highRank1 = rank;
                }
            }
        }

        //myCardsOfHand kiszámítása:
        if (hand != null) {
            for (Card card : cards) {
                if (card.isInMyHand() && card.getSuit().equals(flushSuit)) {
                    myCardsOfHand++;
                }
            }
        }
    }

}
