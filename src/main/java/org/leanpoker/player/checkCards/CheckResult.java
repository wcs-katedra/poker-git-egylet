package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author imate
 */
public class CheckResult {

    private HandRank hand;
    private final Collection<Card> cards;
    private RankInfo rankInfo;

    public CheckResult(HandRank hand, List<Card> cards, String highRank1, String highRank2, int myCardsOfHand) {
        this.hand = hand;
        rankInfo=new RankInfo(highRank1,highRank2,myCardsOfHand);
        this.cards = cards;
    }

    public HandRank getHand() {
        return hand;
    }

    public Collection<Card> getCards() {
        return cards;
    }

    public String getHighRank1() {
        return rankInfo.getHighRank1();
    }

    public String getHighRank2() {
        return rankInfo.getHighRank2();
    }

    public int getMyCardsOfHand() {
        return rankInfo.getMyCardsOfHand();
    }

    public RankInfo getRankInfo() {
        return rankInfo;
    }
    
}
