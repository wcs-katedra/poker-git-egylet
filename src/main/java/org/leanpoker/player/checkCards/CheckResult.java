package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author imate
 */
public class CheckResult {

    private HandRank hand;
    private String highRank1;
    private String highRank2;
    private int myCardsOfHand;
    private final Collection<Card> cards;

    public CheckResult(HandRank hand, List<Card> cards, String highRank1, String highRank2, int myCardsOfHand) {
        this.hand = hand;
        this.highRank1 = highRank1;
        this.highRank2 = highRank2;
        this.myCardsOfHand = myCardsOfHand;
        this.cards = Collections.unmodifiableCollection(cards);
    }

    public HandRank getHand() {
        return hand;
    }

    public Collection<Card> getCards() {
        return cards;
    }

    public String getHighRank1() {
        return highRank1;
    }

    public String getHighRank2() {
        return highRank2;
    }

    public int getMyCardsOfHand() {
        return myCardsOfHand;
    }

    @Override
    public String toString() {
        return "CheckResult{" + "hand=" + hand + ", highRank1=" + highRank1 + ", highRank2=" + highRank2 + '}';
    }
}
