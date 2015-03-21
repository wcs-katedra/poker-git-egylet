package org.leanpoker.player.checkCards;

import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckDrill extends Check {

    @Override
    protected void check() {
        for (String rank : ranks) {
            if (countRank(rank) == 3) {
                hand = Hand.DRILL;
                highRank1 = rank;
            }
        }
    }

}
