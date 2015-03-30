package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckPoker extends Check {

    @Override
    protected void check() {
        for (String rank : ranks) {
            if (countRank(rank) == 4) {
                hand = Hand.POKER;
                highRank1 = rank;
            }
        }

        //myCardsOfHand kiszámítása:
        if (hand != null) {
            for (Card card : cards) {
                if (card.isInMyHand() && card.getRank().equals(highRank1)) {
                    myCardsOfHand++;
                }
            }
        }
    }

}
