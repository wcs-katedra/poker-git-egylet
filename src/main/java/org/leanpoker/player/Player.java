package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;

public class Player {

    static final String VERSION = "Zeusz 1.0";

    public static int betRequest(GameState gameState) {
        CheckRandomNumber checkRandomNumber = new CheckRandomNumber();
        return checkRandomNumber.check();
    }

    public static void showdown(GameState gameState) {

    }
}
