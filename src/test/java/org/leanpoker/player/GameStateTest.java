/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;
import java.util.Arrays;
import org.git_egylet.tools.Tools;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author poker07
 */
public class GameStateTest {

    @Test
    public void testAreWeBlind() {
        final GameState gameState = new GameState(10, 320, 400, 241, 1, 7, 1);
        com.wcs.poker.gamestate.Player player1 = new com.wcs.poker.gamestate.Player(1, "Albert", "active", "Default random player", 1590, 80);
        com.wcs.poker.gamestate.Player player2 = new com.wcs.poker.gamestate.Player(2, "Máté", "active", "Default random player2", 1200, 70);
        
        gameState.setCommunityCards(Tools.makeCardList("J,hearts", "K,clubs", "2,diamonds", "5,spades", "7,diamonds"));
        player1.setHoleCards(Tools.makeCardList("A,hearts", "A,clubs"));
        player2.setHoleCards(Tools.makeCardList("K,hearts", "4,clubs"));
        gameState.setPlayers(Arrays.asList(player1, player2));
        assertEquals(false, gameState.areWeBlind());
        gameState.setDealer(0);
        assertEquals(false, gameState.areWeBlind());
         com.wcs.poker.gamestate.Player player3 = new com.wcs.poker.gamestate.Player(3, "sanyi", "active", "Default random player3", 1200, 70);
         gameState.setPlayers(Arrays.asList(player1, player2,player3));
         gameState.setInAction(2);
         assertTrue(gameState.areWeBlind());

    }

}
