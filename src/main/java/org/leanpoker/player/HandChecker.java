package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author imate
 */
public class HandChecker {

    private List<Card> cards;
    private List<String> ranks = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
    private List<String> suits = Arrays.asList("clubs", "diamonds", "hearts", "spades");

    public HandChecker(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * Megvizsgálja a beaddolt kártyákat, és egy számmal tér vissza, ami a
     * kombinációkra utal.
     *
     * @param cards
     * @return 0 - magas lap 1 - egy pár 2 - két pár 3 - drill 4 - flush 5 -
     * full 6 - poker
     */
    public int checkCards() {
        boolean onePair, twoPair, drill = false, flush = false, full, poker = false;
        int result = 0;
        String rank, suit;
        int[] suitCounter = {0, 0, 0, 0};
        int[] rankCounter = new int[13];
        for (int r : rankCounter) {
            r = 0;
        }

        for (Card card : cards) {
            rank = card.getRank();
            suit = card.getSuit();
            if (ranks.contains(rank)) {
                rankCounter[ranks.indexOf(rank)]++;
            }
            if (suits.contains(suit)) {
                suitCounter[suits.indexOf(suit)]++;
            }
        }

        int pairSum = 0;
        for (int r : rankCounter) {
            if (r == 2) {
                pairSum++;
            }
            if (r == 3 && !drill) {
                drill = true;
            }
            if (r == 4 && !poker) {
                poker = true;
            }
        }
        onePair = pairSum == 1;
        twoPair = pairSum > 1;
        full = drill && (pairSum > 0);

        for (int s : suitCounter) {
            if (s > 4 && !flush) {
                flush = true;
            }
        }

        if (onePair) {
            result = 1;
        }
        if (twoPair) {
            result = 2;
        }
        if (drill) {
            result = 3;
        }
        if (flush) {
            result = 4;
        }
        if (full) {
            result = 5;
        }
        if (poker) {
            result = 6;
        }

        return result;
    }

}
