package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author imate
 */
public abstract class Check {

    protected static final List<String> ranks = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
    protected static final List<String> suits = Arrays.asList("clubs", "diamonds", "hearts", "spades");
    protected List<Card> cards;
    protected Hand hand;
    protected String highRank1;
    protected String highRank2;
    protected int myCardsOfHand;

    public CheckResult getResult(List<Card> cards) {
        hand = null;
        highRank1 = "";
        highRank2 = "";
        myCardsOfHand = 0;
        this.cards = cards;
        check();
        if (hand == null) {
            return null;
        } else {
            return new CheckResult(hand, highRank1, highRank2, myCardsOfHand);
        }
    }

    protected void check() {
    }

    protected int countRank(String rank) {
        int i = 0;
        for (Card card : cards) {
            if (card.getRank().equals(rank)) {
                i++;
            }
        }
        return i;
    }

    protected int countSuit(String suit) {
        int i = 0;
        for (Card card : cards) {
            if (card.getSuit().equals(suit)) {
                i++;
            }
        }
        return i;
    }

    protected boolean haveCardAs(String rank, String suit) {
        boolean have = false;
        for (Card card : cards) {
            if (card.equals(rank, suit)) {
                have = true;
            }
        }
        return have;
    }
}
