package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;
import org.junit.Test;
import com.wcs.poker.gamestate.Player;
import java.util.Arrays;
import org.git_egylet.tools.Tools;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testBetRequest() throws Exception {
        final GameState gameState = new GameState(10, 320, 400, 241, 1, 7, 1);
        Player player1 = new Player(1, "Albert", "active", "Default random player", 1590, 80);
        Player player2 = new Player(2, "Máté", "active", "Default random player2", 1200, 70);
        gameState.setCommunityCards(Tools.makeCardList("J,hearts", "K,clubs", "2,diamonds", "5,spades", "7,diamonds"));
        player1.setHoleCards(Tools.makeCardList("A,hearts", "A,clubs"));
        player2.setHoleCards(Tools.makeCardList("K,hearts", "4,clubs"));
        gameState.setPlayers(Arrays.asList(player1, player2));
        assertTrue(org.leanpoker.player.Player.betRequest(gameState) >= 0);
    }
}
