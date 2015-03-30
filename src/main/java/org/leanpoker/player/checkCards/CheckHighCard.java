package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckHighCard extends Check {

    @Override
    protected void check() {
        for (String rank : ranks) {
            for (Card card : cards) {
                if (card.isInMyHand() && card.isEqualRank(rank)) {
                    highRank1 = rank;
                    hand = Hand.HIGH_CARD;
                    myCardsOfHand = 1;
                }
            }
        }
    }

}
