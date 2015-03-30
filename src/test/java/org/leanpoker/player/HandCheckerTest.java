package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;
import com.wcs.poker.gamestate.Player;
import org.git_egylet.tools.Tools;
import org.leanpoker.player.checkCards.CheckResult;
import org.leanpoker.player.checkCards.Hand;
import org.leanpoker.player.checkCards.HandChecker;

/**
 *
 * @author imate
 */
public class HandCheckerTest {

    final GameState gameState = new GameState(10, 320, 400, 241, 0, 7, 0);
    Player player1 = new Player(1, "Albert", "active", "Default random player", 1590, 80);
    HandChecker handChecker = new HandChecker();

    @Test
    public void testHighCard() {
        gameState.setCommunityCards(Tools.makeCardList("2,hearts", "3,clubs", "Q,diamonds", "6,spades", "7,diamonds"));
        player1.setHoleCards(Tools.makeCardList("4,hearts", "J,clubs"));
        gameState.setPlayers(Arrays.asList(player1));

        CheckResult result = handChecker.getResult(gameState);
        assertEquals(result.getHand(), Hand.HIGH_CARD);
        assertEquals(result.getHighRank1(), "J");
        assertEquals(result.getHighRank2(), "");
    }

    @Test
    public void testOnePair() {
        gameState.setCommunityCards(Tools.makeCardList("2,hearts", "3,clubs", "4,diamonds", "6,spades", "7,diamonds"));
        player1.setHoleCards(Tools.makeCardList("4,hearts", "A,clubs"));
        gameState.setPlayers(Arrays.asList(player1));

        CheckResult result = handChecker.getResult(gameState);
        assertEquals(result.getHand(), Hand.ONE_PAIR);
        assertEquals(result.getHighRank1(), "4");
        assertEquals(result.getHighRank2(), "");
        assertEquals(result.getMyCardsOfHand(), 1);
    }

    @Test
    public void testTwoPair() {
        gameState.setCommunityCards(Tools.makeCardList("2,hearts", "3,clubs", "4,diamonds", "6,spades", "7,diamonds"));
        player1.setHoleCards(Tools.makeCardList("2,hearts", "6,clubs"));
        gameState.setPlayers(Arrays.asList(player1));

        CheckResult result = handChecker.getResult(gameState);
        assertEquals(result.getHand(), Hand.TWO_PAIR);
        assertEquals(result.getHighRank1(), "6");
        assertEquals(result.getHighRank2(), "2");
        assertEquals(result.getMyCardsOfHand(), 2);

    }

    @Test
    public void testDrill() {
        gameState.setCommunityCards(Tools.makeCardList("J,hearts", "3,clubs", "3,diamonds", "3,spades", "7,diamonds"));
        player1.setHoleCards(Tools.makeCardList("J,hearts", "J,clubs"));
        gameState.setPlayers(Arrays.asList(player1));

        CheckResult result = handChecker.getResult(gameState);
        assertEquals(result.getHand(), Hand.DRILL);
        assertEquals(result.getHighRank1(), "J");
        assertEquals(result.getHighRank2(), "");
        assertEquals(result.getMyCardsOfHand(), 2);
    }

    @Test
    public void testStraight() {
        gameState.setCommunityCards(Tools.makeCardList("6,hearts", "7,clubs", "8,diamonds", "9,spades", "A,diamonds"));
        player1.setHoleCards(Tools.makeCardList("4,hearts", "5,clubs"));
        gameState.setPlayers(Arrays.asList(player1));

        CheckResult result = handChecker.getResult(gameState);
        assertEquals(result.getHand(), Hand.STRAIGHT);
        assertEquals(result.getHighRank1(), "9");
        assertEquals(result.getHighRank2(), "");
        assertEquals(result.getMyCardsOfHand(), 1);
    }

    @Test
    public void testFlush() {
        gameState.setCommunityCards(Tools.makeCardList("10,hearts", "5,hearts", "3,hearts", "4,spades", "7,hearts"));
        player1.setHoleCards(Tools.makeCardList("J,hearts", "J,clubs"));
        gameState.setPlayers(Arrays.asList(player1));

        CheckResult result = handChecker.getResult(gameState);
        assertEquals(result.getHand(), Hand.FLUSH);
        assertEquals(result.getHighRank1(), "J");
        assertEquals(result.getHighRank2(), "");
        assertEquals(result.getMyCardsOfHand(), 1);
    }

    @Test
    public void testFull() {
        gameState.setCommunityCards(Tools.makeCardList("J,spades", "5,diamonds", "5,hearts", "J,spades", "7,hearts"));
        player1.setHoleCards(Tools.makeCardList("J,hearts", "4,clubs"));
        gameState.setPlayers(Arrays.asList(player1));

        CheckResult result = handChecker.getResult(gameState);
        assertEquals(result.getHand(), Hand.FULL);
        assertEquals(result.getHighRank1(), "J");
        assertEquals(result.getHighRank2(), "5");
        assertEquals(result.getMyCardsOfHand(), 1);

    }

    @Test
    public void testPoker() {
        gameState.setCommunityCards(Tools.makeCardList("8,spades", "8,diamonds", "5,hearts", "4,spades", "7,hearts"));
        player1.setHoleCards(Tools.makeCardList("8,hearts", "8,clubs"));
        gameState.setPlayers(Arrays.asList(player1));

        CheckResult result = handChecker.getResult(gameState);
        assertEquals(result.getHand(), Hand.POKER);
        assertEquals(result.getHighRank1(), "8");
        assertEquals(result.getHighRank2(), "");
        assertEquals(result.getMyCardsOfHand(), 2);
    }

    @Test
    public void testStraightFlush() {
        gameState.setCommunityCards(Tools.makeCardList("3,hearts", "4,hearts", "5,hearts", "J,diamonds", "3,diamonds"));
        player1.setHoleCards(Tools.makeCardList("6,hearts", "7,hearts"));
        gameState.setPlayers(Arrays.asList(player1));

        CheckResult result = handChecker.getResult(gameState);
        assertEquals(result.getHand(), Hand.STRAIGH_FLUSH);
        assertEquals(result.getHighRank1(), "7");
        assertEquals(result.getHighRank2(), "");
        assertEquals(result.getMyCardsOfHand(), 2);
    }

    @Test
    public void testRoyalFlush() {
        gameState.setCommunityCards(Tools.makeCardList("Q,hearts", "K,hearts", "10,hearts", "J,hearts", "3,diamonds"));
        player1.setHoleCards(Tools.makeCardList("J,spades", "A,hearts"));
        gameState.setPlayers(Arrays.asList(player1));

        CheckResult result = handChecker.getResult(gameState);
        assertEquals(result.getHand(), Hand.ROYAL_FLUSH);
        assertEquals(result.getHighRank1(), "");
        assertEquals(result.getHighRank2(), "");
        assertEquals(result.getMyCardsOfHand(), 1);
    }

}
