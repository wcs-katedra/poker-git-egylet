package org.leanpoker.player.checkCards;

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
    }

}