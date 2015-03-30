package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckOnePair extends Check {

    @Override
    protected void check() {
        for (String rank : ranks) {
            if (countRank(rank) == 2) {
                hand = Hand.ONE_PAIR;
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
