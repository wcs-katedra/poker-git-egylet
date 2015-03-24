package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author imate
 */
public class HandChecker {

    private List<Card> allCards=new ArrayList<>();
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

    public CheckResult getResult(GameState gameState) {
        allCards.clear();
        allCards.addAll(gameState.requestActHoleCards());
        allCards.addAll(gameState.getCommunityCards());
        CheckResult result = null;
        for (Check checker : checkers) {
            if (checker.getResult(allCards) != null) {
                result = checker.getResult(allCards);
                break;
            }
        }
        return result;
    }
}
