package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;

public class Player {

    private static com.wcs.poker.gamestate.Player actPlayer;
    private static Integer bigBlind;
    

    static final String VERSION = "Default Java folding player";

    public static int betRequest(GameState gameState) {
        return 0;
    }

    public static void showdown(GameState gameState) {

    }


    
  
    
    public static Integer requestBigBlind(GameState gameState) {
        bigBlind=2*gameState.getSmallBlind();
        return bigBlind;
    }
    
    
    
    
    
    
    
    
}
