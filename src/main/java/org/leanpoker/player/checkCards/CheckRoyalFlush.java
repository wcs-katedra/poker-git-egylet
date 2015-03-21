package org.leanpoker.player.checkCards;

import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckRoyalFlush extends Check {

    @Override
    protected void check() {
        CheckResult straightFlushCheckResult=new CheckStraightFlush().getResult(cards);
        if(straightFlushCheckResult!=null){
            if(straightFlushCheckResult.getHighRank1().equals("A")){
                hand=Hand.ROYAL_FLUSH;
            }
        }
    }

}
