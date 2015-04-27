package org.leanpoker.player;

import com.wcs.poker.gamestate.GameState;

public class Player {

    static final String VERSION = "Zeusz Final";

    public static int betRequest(GameState gameState) {
        CheckConditions checkConditions = new CheckConditions(gameState);
        return checkConditions.check();
    }

    public static void showdown(GameState gameState) {

    }
}
