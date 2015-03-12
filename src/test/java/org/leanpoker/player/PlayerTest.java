package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import org.junit.Test;
import com.wcs.poker.gamestate.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testBetRequest() throws Exception {
        final GameState gameState = new GameState(10, 320, 400, 240, 1, 7, 1);
        Player player1 = new Player(1, "Albert", "active", "Default random player", 1590, 80);
        Player player2 = new Player(2, "Máté", "active", "Default random player2", 1200, 80);
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        Card card1 = new Card("4", "hearts");
        Card card2 = new Card("A", "spades");
        Card card3 = new Card("6", "clubs");
        Card card4 = new Card("8", "clubs");
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        player1.setHoleCards(cards);
        cards.add(card3);
        cards.add(card4);
        player2.setHoleCards(cards);
        gameState.setPlayers(players);

        assertTrue(org.leanpoker.player.Player.betRequest(gameState)>=0);
    }
}
