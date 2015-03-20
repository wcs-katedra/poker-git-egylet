package com.wcs.poker.gamestate;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class GameState {

    @SerializedName("small_blind")
    @Expose
    private Integer smallBlind;
    @SerializedName("current_buy_in")
    @Expose
    private Integer currentBuyIn;
    @Expose
    private Integer pot;
    @SerializedName("minimum_raise")
    @Expose
    private Integer minimumRaise;
    @Expose
    private Integer dealer;
    @Expose
    private Integer orbits;
    @SerializedName("in_action")
    @Expose
    private Integer inAction;
    @Expose
    private List<Player> players = new ArrayList<Player>();
    @SerializedName("community_cards")
    @Expose
    private List<Card> communityCards = new ArrayList<Card>();

    /**
     *
     * @return The smallBlind
     */
        public GameState(Integer smallBlind, Integer currentBuyIn, Integer pot, Integer minimumRaise, Integer dealer, Integer orbits, Integer inAction) {
        this.smallBlind = smallBlind;
        this.currentBuyIn = currentBuyIn;
        this.pot = pot;
        this.minimumRaise = minimumRaise;
        this.dealer = dealer;
        this.orbits = orbits;
        this.inAction = inAction;
    }
    public Integer getSmallBlind() {
        return smallBlind;
    }



    /**
     *
     * @param smallBlind The small_blind
     */
    public void setSmallBlind(Integer smallBlind) {
        this.smallBlind = smallBlind;
    }

    /**
     *
     * @return The currentBuyIn
     */
    public Integer getCurrentBuyIn() {
        return currentBuyIn;
    }

    /**
     *
     * @param currentBuyIn The current_buy_in
     */
    public void setCurrentBuyIn(Integer currentBuyIn) {
        this.currentBuyIn = currentBuyIn;
    }

    /**
     *
     * @return The pot
     */
    public Integer getPot() {
        return pot;
    }

    /**
     *
     * @param pot The pot
     */
    public void setPot(Integer pot) {
        this.pot = pot;
    }

    /**
     *
     * @return The minimumRaise
     */
    public Integer getMinimumRaise() {
        return minimumRaise;
    }

    /**
     *
     * @param minimumRaise The minimum_raise
     */
    public void setMinimumRaise(Integer minimumRaise) {
        this.minimumRaise = minimumRaise;
    }

    /**
     *
     * @return The dealer
     */
    public Integer getDealer() {
        return dealer;
    }

    /**
     *
     * @param dealer The dealer
     */
    public void setDealer(Integer dealer) {
        this.dealer = dealer;
    }

    /**
     *
     * @return The orbits
     */
    public Integer getOrbits() {
        return orbits;
    }

    /**
     *
     * @param orbits The orbits
     */
    public void setOrbits(Integer orbits) {
        this.orbits = orbits;
    }

    /**
     *
     * @return The inAction
     */
    public Integer getInAction() {
        return inAction;
    }

    /**
     *
     * @param inAction The in_action
     */
    public void setInAction(Integer inAction) {
        this.inAction = inAction;
    }

    /**
     *
     * @return The players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @param players The players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     *
     * @return The communityCards
     */
    public List<Card> getCommunityCards() {
        return communityCards;
    }

    /**
     *
     * @param communityCards The community_cards
     */
    public void setCommunityCards(List<Card> communityCards) {
        this.communityCards = communityCards;
    }

    //Saját kód
    public com.wcs.poker.gamestate.Player getCurrentPlayer() {
        return players.get(inAction);
    }

    public Integer requestBigBlind() {
        return 2 * smallBlind;
    }

    public List<Card> requestActHoleCards() {
        return getCurrentPlayer().getHoleCards();
    }

    public Integer getCall() {
        return currentBuyIn - players.get(inAction).getBet();
    }

    public Integer getMinimumBet() {
        return currentBuyIn - players.get(inAction).getBet() + minimumRaise;
    }
    
    public boolean areWeBlind(){
    return ((dealer+1)%players.size()==inAction
          ||(dealer+2)%players.size()==inAction);
    }
    
    
    public int activePlayers(){
    int i=0;
        for (Player player : players) {
            if (player.getStatus().equals("active")) {
                i++;
            }
        }
        return i;
    }
}
