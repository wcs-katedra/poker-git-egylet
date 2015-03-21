package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckStraight extends Check {

    @Override
    protected void check() {
        int straightCounter = 0;
        boolean isStraight = false;

        if (countRank("A") > 0) {
            straightCounter++;
        }

        for (int i = 0; i < ranks.size(); i++) {
            if (countRank(ranks.get(i)) > 0) {
                straightCounter++;
            } else {
                straightCounter = 0;
            }
            if (straightCounter >= 5) {
                isStraight = true;
                highRank1 = ranks.get(i);
            }
        }

        if (isStraight) {
            hand = Hand.STRAIGHT;
        }
    }

}
