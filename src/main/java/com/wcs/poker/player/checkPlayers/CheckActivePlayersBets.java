/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wcs.poker.player.checkPlayers;

import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.gamestate.Player;
import java.util.ArrayList;
import java.util.List;
import org.leanpoker.player.CheckConditions;

/**
 *
 * @author Patrik
 */
public class CheckActivePlayersBets {

   private GameState gState;
   private CheckConditions cConditions;

    public CheckActivePlayersBets(GameState gState) {
        this.gState = gState;
        cConditions=new CheckConditions(gState);
    }
   
    
    
    
    //active játékosok kigyűjtése
    public List<Player> collectActivePlayers(GameState gameState){
        List<Player> activePlayers=new ArrayList<>();
        
        for (Player player : gameState.getPlayers()) {
            if (player.getStatus().equals("active")) {
                activePlayers.add(player);
            }
        }
 
        return activePlayers;
    }
    
    //activ játékosok tétjeinek vizsgálata
    public int myBetModify(){
        int myBet=0;
        boolean thereIs=false;
        for (Player p : collectActivePlayers(gState)) {
            if (p.getBet()>200) {
                thereIs=true;
            }
        }
        
        if (thereIs) {
            myBet=0;
        }
        else{
            myBet=cConditions.check();
        }
        return myBet;
    }
    
}
