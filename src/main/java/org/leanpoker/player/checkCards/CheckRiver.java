/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrik
 */
public class CheckRiver {
    private GameState gState;
    private CheckOnePair cPair=new CheckOnePair();
    private String[] rankArray={"A","K","Q","J","10"};
    private String[] suitArray={"spades","hearts","diamonds","clubs"};
    private int count=0;

    public CheckRiver(GameState gState) {
        this.gState = gState;
    }
    
    
    
    public HandRank mayCombination(){
        HandRank combination=null;
        CheckResult result=cPair.getResult(gState.getCommunityCards());
        
        if (result!=null) {
            combination=HandRank.DRILL;
        }
        
        
        for (String suit : suitArray) {
            for (String rank : rankArray) {
                for (Card communityCard : gState.getCommunityCards()) {
                    if (communityCard.equals(rank, suit)){
                        count++;
                    }
                }
            }
            if (count==4) {
                combination=HandRank.ROYAL_FLUSH;
            }
            count=0;
        }
        
        return combination;
    
}
}
