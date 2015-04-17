package org.git_egylet.tools;

import com.wcs.poker.gamestate.Card;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author imate
 */
public class Tools {

    /**
     * A beaddolt stringeket kártyákká alakítja, majd listaként adja vissza, de
     * kajakra!
     *
     * @param cards Kártyák adatai stringben, vesszővel elválasztva, pl.:
     * "A,hearts","6,spades".
     * @return Egy Card tíípúsúúú lista.
     */
    public static List<Card> makeCardList(String... cards) {
        List<Card> holeCards = new ArrayList<>();
        if (!Arrays.asList(cards).isEmpty()) {
            String rank, suit;
            for (String card : cards) {
                rank = card.split(",")[0];
                suit = card.split(",")[1];
                holeCards.add(new Card(rank, suit));
            }
        }
        return holeCards;
    }
    
    public static List<Card> orderCards(List<Card> cards) {
        List<Card> orderedCards = new ArrayList<>();
        orderedCards.addAll(cards);
        Collections.sort(orderedCards,new RankComparator());
        return orderedCards;
    }
}
