package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import org.junit.Test;
import com.wcs.poker.gamestate.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testBetRequest() throws Exception {
        final GameState gameState = new GameState(10, 320, 400, 240, 1, 7, 1);
        Player player1 = new Player(1, "Albert", "active", "Default random player", 1590, 80);
        Player player2 = new Player(2, "Máté", "active", "Default random player2", 1200, 70);
        gameState.setCommunityCards(makeCardList("J,hearts","K,clubs","2,diamonds","5,spades","7,diamonds"));
        player1.setHoleCards(makeCardList("A,hearts", "A,clubs"));
        player2.setHoleCards(makeCardList("K,hearts", "4,clubs"));
        gameState.setPlayers(Arrays.asList(player1, player2));

        assertTrue(org.leanpoker.player.Player.betRequest(gameState) >= 0);
    }

    /**
     * A beaddolt stringeket kártyákká alakítja, majd listaként adja vissza, de
     * kajakra!
     *
     * @param cards Kártyák adatai stringben, vesszővel elválasztva, pl.:
     * "A,hearts","6,spades".
     * @return Egy Card tíípúsúúú lista.
     */
    private List<Card> makeCardList(String... cards) {
        List<Card> holeCards = new ArrayList<>();
        String rank, suit;
        for (String card : cards) {
            rank = card.split(",")[0];
            suit = card.split(",")[1];
            holeCards.add(new Card(rank, suit));
        }
        return holeCards;
    }
}
