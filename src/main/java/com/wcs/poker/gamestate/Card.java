package com.wcs.poker.gamestate;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Generated("org.jsonschema2pojo")
public class Card {

    private boolean inMyHand = false;

    @Expose
    private String rank;

    public static List<String> getBigRank() {
        return bigRank;
    }
    @Expose
    private String suit;

    private static final List<String> bigRank = Arrays.asList("9", "10", "J", "Q", "K", "A");

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     *
     * @return The rank
     */
    public String getRank() {
        return rank;
    }

    /**
     *
     * @param rank The rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     *
     * @return The suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     *
     * @param suit The suit
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    public boolean isInMyHand() {
        return inMyHand;
    }

    public void setInMyHand(boolean inMyHand) {
        this.inMyHand = inMyHand;
    }

    @Override
    public String toString() {
        return rank + "," + suit;
    }

    //Saját kód
    public boolean isEqualRank(Card other) {
        return this.rank.equals(other.getRank());
    }

    public boolean isEqualRank(String otherRank) {
        return this.rank.equals(otherRank);
    }

    public boolean isEqualSuit(Card other) {
        return this.suit.equals(other.getSuit());
    }

    public boolean isEqualSuit(String otherSuit) {
        return this.suit.equals(otherSuit);
    }

    public boolean isBigCard() {
        return bigRank.contains(this.rank);
    }

    public boolean equals(String rank, String suit) {
        return this.rank.equals(rank) && this.suit.equals(suit);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (!Objects.equals(this.rank, other.rank)) {
            return false;
        }
        if (!Objects.equals(this.suit, other.suit)) {
            return false;
        }
        return true;
    }

    public boolean equals(Card card) {
        return this.rank.equals(card.getRank()) && this.suit.equals(card.getSuit());
    }
}
