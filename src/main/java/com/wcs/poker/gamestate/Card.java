
package com.wcs.poker.gamestate;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import java.util.Arrays;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class Card {

    @Expose
    private String rank;
    @Expose
    private String suit;
   
    private static final List<String> bigRank = Arrays.asList("10", "J", "Q", "K", "A");
    

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * 
     * @return
     *     The rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * 
     * @param rank
     *     The rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 
     * @return
     *     The suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * 
     * @param suit
     *     The suit
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" + "rank=" + rank + ", suit=" + suit + '}';
    }
    
    //Saját kód
    
    public boolean isEqualRank(Card other){
        return this.rank.equals(other.getRank());
    }

    public boolean isEqualSuit(Card other){
        return this.suit.equals(other.getSuit());
    }
    
    public boolean isBigCard(){
        return bigRank.contains(this.rank);
    }
}
