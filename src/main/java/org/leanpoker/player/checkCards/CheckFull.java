package org.leanpoker.player.checkCards;

import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckFull extends Check {

    @Override
    protected void check() {
        boolean haveDrill = false;
        //először megnézem, hogy van-e drill
        for (String rank : ranks) {
            if (countRank(rank) == 3) {
                haveDrill = true;
                highRank1 = rank;
            }
        }
        //ha van drill, párt keresek, ha az is van, akkor megvan a full
        if (haveDrill) {
            for (String rank : ranks) {
                if (countRank(rank) == 2) {
                    highRank2 = rank;
                    hand = Hand.FULL;
                }
            }
        }
    }

}
