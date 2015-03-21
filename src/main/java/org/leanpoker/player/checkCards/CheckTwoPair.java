package org.leanpoker.player.checkCards;

import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckTwoPair extends Check {

    @Override
    protected void check() {
        int numberOfPairs = 0;

        for (String rank : ranks) {
            if (countRank(rank) == 2) {
                numberOfPairs++;
                highRank2 = highRank1;
                highRank1 = rank;
            }
        }
        if (numberOfPairs >= 2) {
            hand = Hand.TWO_PAIR;
        }
    }

}
