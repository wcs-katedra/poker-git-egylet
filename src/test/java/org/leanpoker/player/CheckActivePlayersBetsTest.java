/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.player.checkPlayers.CheckActivePlayersBets;
import java.util.Arrays;
import org.git_egylet.tools.Tools;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrik
 */
public class CheckActivePlayersBetsTest {
  
    @Test
    public void testCollectActivePlayers(){
        GameState gameState=new GameState(10, 320, 400, 241, 1, 7, 1);
        CheckActivePlayersBets bets=new CheckActivePlayersBets(gameState);
        com.wcs.poker.gamestate.Player player1 = new com.wcs.poker.gamestate.Player(1, "Albert", "active", "Default random player", 1590, 80);
        com.wcs.poker.gamestate.Player player2 = new com.wcs.poker.gamestate.Player(2, "Máté", "active", "Default random player2", 1200, 70);
        com.wcs.poker.gamestate.Player player3 = new com.wcs.poker.gamestate.Player(3, "Peti", "out", "Default random player3", 0, 0);
        com.wcs.poker.gamestate.Player player4 = new com.wcs.poker.gamestate.Player(4, "Gabi", "active", "Default random player4", 1200, 70);
        com.wcs.poker.gamestate.Player player5 = new com.wcs.poker.gamestate.Player(5, "Zsiga", "out", "Default random player5", 0, 0);
        com.wcs.poker.gamestate.Player player6 = new com.wcs.poker.gamestate.Player(6, "Thomas", "out", "Default random player6", 0, 0);
        
        player1.setHoleCards(Tools.makeCardList("A,hearts", "A,clubs"));
        player2.setHoleCards(Tools.makeCardList("K,hearts", "4,clubs"));
        player3.setHoleCards(Tools.makeCardList("2,hearts", "2,clubs"));
        player4.setHoleCards(Tools.makeCardList("3,hearts", "3,clubs"));
        player5.setHoleCards(Tools.makeCardList("4,hearts", "4,clubs"));
        player6.setHoleCards(Tools.makeCardList("5,hearts", "5,clubs"));
        
        gameState.setPlayers(Arrays.asList(player1, player2,player3,player4,player5,player6));
        
        for (com.wcs.poker.gamestate.Player p : bets.collectActivePlayers(gameState)) {
            System.out.println(p.getName());
        }
        
    }
    
    @Test
    public void testEnemyBets(){
        GameState gameState=new GameState(10, 320, 400, 241, 1, 7, 1);
        CheckActivePlayersBets bets=new CheckActivePlayersBets(gameState);
        com.wcs.poker.gamestate.Player player1 = new com.wcs.poker.gamestate.Player(1, "Albert", "active", "Default random player", 1590, 500);
        com.wcs.poker.gamestate.Player player2 = new com.wcs.poker.gamestate.Player(2, "Máté", "active", "Default random player2", 1200, 70);
        com.wcs.poker.gamestate.Player Iam=new com.wcs.poker.gamestate.Player(3, "Iam", "active", "Zeusz 1.0", 1000, 50);
        
        player1.setHoleCards(Tools.makeCardList("A,hearts", "A,clubs"));
        player2.setHoleCards(Tools.makeCardList("K,hearts", "4,clubs"));
        Iam.setHoleCards(Tools.makeCardList("2,hearts", "3,clubs"));
        
        gameState.setPlayers(Arrays.asList(player1, player2,Iam));
        
        System.out.println(bets.myBetModify());
        int result=0;
        assertTrue(result==bets.myBetModify());
        
    }
  
}
