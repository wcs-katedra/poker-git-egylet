package org.leanpoker.player.checkCards;

/**
 *
 * @author imate
 */
public class CheckResult {

    private Hand hand;
    private String highRank1;
    private String highRank2;
    private int myCardsOfHand;

    public CheckResult(Hand hand, String highRank1, String highRank2, int myCardsOfHand) {
        this.hand = hand;
        this.highRank1 = highRank1;
        this.highRank2 = highRank2;
        this.myCardsOfHand = myCardsOfHand;
    }

    public Hand getHand() {
        return hand;
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

    public void setMyCardsOfHand(int myCardsOfHand) {
        this.myCardsOfHand = myCardsOfHand;
    }

    @Override
    public String toString() {
        return "CheckResult{" + "hand=" + hand + ", highRank1=" + highRank1 + ", highRank2=" + highRank2 + '}';
    }
}
