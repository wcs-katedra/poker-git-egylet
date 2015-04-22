/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.leanpoker.player.checkCards;

/**
 *
 * @author poker10
 */

public class RankInfo {
    private String highRank1;
    private String highRank2;
    private int myCardsOfHand; 

    public String getHighRank1() {
        return highRank1;
    }

    public String getHighRank2() {
        return highRank2;
    }

    public int getMyCardsOfHand() {
        return myCardsOfHand;
    }


    public RankInfo(String highRank1, String highRank2, int myCardsOfHand) {
        this.highRank1 = highRank1;
        this.highRank2 = highRank2;
        this.myCardsOfHand = myCardsOfHand;
    }
    
    
}
