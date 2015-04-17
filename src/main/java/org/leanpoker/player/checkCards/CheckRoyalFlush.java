package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckRoyalFlush extends Check {

    private String[] royalRanks = {"10", "J", "Q", "K", "A"};
    private String flushSuit = "";

    @Override
    protected void check() {
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
                handRank = HandRank.ROYAL_FLUSH;
                break;
            }
        }

        if (handRank != null) {
            calcMyCardsOfHand();
            makeOrderedCardList();
        }
    }

    @Override
    protected void calcMyCardsOfHand() {
        if (handRank != null) {
            for (Card card : cards) {
                for (String rank : royalRanks) {
                    if (card.isInMyHand() && card.equals(rank, flushSuit)) {
                        myCardsOfHand++;
                    }
                }
            }
        }
    }

    @Override
    protected void makeOrderedCardList() {
        String rank;
        for (int i = royalRanks.length - 1; i >= 0; i--) {
            rank = royalRanks[i];
            for (Card card : cards) {
                if (card.isEqualRank(rank)&& card.isEqualSuit(flushSuit)) {
                    orderedCardList.add(card);
                }
            }
        }
    }
}