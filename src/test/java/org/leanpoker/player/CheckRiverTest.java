/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;
import org.git_egylet.tools.Tools;
import org.junit.Test;
import static org.junit.Assert.*;
import org.leanpoker.player.checkCards.CheckRiver;
import org.leanpoker.player.checkCards.HandRank;

/**
 *
 * @author Patrik
 */
public class CheckRiverTest {
    
    @Test
    public void testMayPair(){
        GameState gameState=new GameState(10, 320, 400, 241, 1, 7, 1);
        gameState.setCommunityCards(Tools.makeCardList("2,hearts", "2,clubs", "Q,diamonds", "6,spades", "7,diamonds"));
        
        CheckRiver cRiver=new CheckRiver(gameState);
        
        assertTrue(cRiver.mayCombination()==HandRank.DRILL);
    }
    
    @Test
    public void testMayRoyalFlush(){
        GameState gameState=new GameState(10, 320, 400, 241, 1, 7, 1);
        gameState.setCommunityCards(Tools.makeCardList("10,hearts", "J,hearts", "Q,hearts", "K,hearts", "7,diamonds"));
        
        CheckRiver cRiver=new CheckRiver(gameState);
        
        assertTrue(cRiver.mayCombination()==HandRank.ROYAL_FLUSH);
    }
    
}
