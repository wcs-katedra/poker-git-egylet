package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author imate
 */
public class HandChecker {

    private static final List<Check> checkers = Arrays.asList(
            new CheckRoyalFlush(),
            new CheckStraightFlush(),
            new CheckPoker(),
            new CheckFull(),
            new CheckFlush(),
            new CheckStraight(),
            new CheckDrill(),
            new CheckTwoPair(),
            new CheckOnePair(),
            new CheckHighCard());

    public CheckResult getResult(List<Card> cards) {
        if (cards.size() < 5 || cards.size() > 7) {
            throw new IllegalArgumentException();
        } else {
            CheckResult result = null;
            for (Check checker : checkers) {
                if (checker.isApplicable(cards)) {
                    result = checker.getResult(cards);
                    break;
                }
            }
            return result;
        }
    }
}
