package com.wcs.poker.hand;

import com.wcs.poker.gamestate.Card;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author thomas
 */
public class Hand {

    private final HandRank rank;
    private final Collection<Card> cards;

    public Hand(HandRank rank, List<Card> cards) {
        this.rank = rank;
        this.cards = cards;
    }

    public HandRank getRank() {
        return rank;
    }

    public Collection<Card> getCards() {
        return cards;
    }
}
