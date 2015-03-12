package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testBetRequest() throws Exception {
        final GameState gameState = new GameState();

        assertEquals(0, Player.betRequest(gameState));
    }
}
