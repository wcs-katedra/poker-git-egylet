package org.leanpoker.player.checkCards;

import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckHighCard extends Check {

    @Override
    protected void check() {
        for (String rank : ranks) {
            if (countRank(rank) > 0) {
                hand = Hand.HIGH_CARD;
                highRank1 = rank;
            }
        }
    }

}
