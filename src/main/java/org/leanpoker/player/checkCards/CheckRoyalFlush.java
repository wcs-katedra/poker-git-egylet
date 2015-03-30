package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckRoyalFlush extends Check {
    
    @Override
    protected void check() {
        String[] royalRanks = {"10", "J", "Q", "K", "A"};
        String flushSuit = "";
        boolean royalFlush;
        for (String suit : suits) {
            royalFlush = true;
            for (String rank : royalRanks) {
                if (!haveCardAs(rank, suit)) {
                    royalFlush = false;
                    break;
                }
            }
            if (royalFlush) {
                flushSuit = suit;
                hand = Hand.ROYAL_FLUSH;
                break;
            }
        }
        
        //myCardsOfHand kiszámítása:
        if (hand != null) {
            for (Card card : cards) {
                for (String rank : royalRanks) {
                    if (card.isInMyHand() && card.equals(rank, flushSuit)) {
                        myCardsOfHand++;
                    }
                }
            }
        }
    }
}
