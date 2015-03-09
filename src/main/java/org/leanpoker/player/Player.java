package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.jsonconverter.JsonConverter;
import java.util.List;

public class Player {

    private static com.wcs.poker.gamestate.Player actPlayer;
    private static Integer bigBlind;
    private static List<Card> actHoleCards;

    static final String VERSION = "Default Java folding player";
    JsonConverter<Object> json;
    static int actualGamerId;
    static com.wcs.poker.gamestate.Player actualGamer;
    static List<com.wcs.poker.gamestate.Player> gamers;

    public static com.wcs.poker.gamestate.Player actualGamer(GameState gameState) {
        actualGamerId = gameState.getInAction();
        gamers = gameState.getPlayers();
        actualGamer = gamers.get(actualGamerId);
        return actualGamer;
    }

    public static int betRequest(GameState gameState) {
        return 0;
    }

    public static void showdown(GameState gameState) {

    }

    public static Integer requestBigBlind(GameState gameState) {
        bigBlind = 2 * gameState.getSmallBlind();
        return bigBlind;
    }

    public static List<Card> requestActHoleCards(GameState gameState) {
        actHoleCards = gameState.getPlayers().get(gameState.getInAction()).getHoleCards();
        return actHoleCards;
    }

}
