package org.leanpoker.player.checkCards;

import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckFlush extends Check {

    @Override
    protected void check() {
        for (String suit : suits) {
            if (countSuit(suit) >= 5) {
                hand = Hand.FLUSH;
            }
        }
        //legmagasabb lap kiszámítása fluzsh esetén
        if (hand == hand.FLUSH) {
            for (String rank : ranks) {
                if (countRank(rank) > 0) {
                    highRank1 = rank;
                }
            }
        }
    }

}
