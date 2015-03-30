/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrik
 */
public class CardTest {

    @Test
    public void testIsEqualRank() {
        Card cardOne = new Card("A", "hearts");
        Card cardTwo = new Card("A", "spades");
        Card cardThree = new Card("2", "hearts");

        assertTrue(cardOne.isEqualRank(cardTwo));
        assertFalse(cardOne.isEqualRank(cardThree));
    }

    @Test
    public void testIsEqualSuit() {
        Card cardOne = new Card("A", "hearts");
        Card cardTwo = new Card("3", "hearts");
        Card cardThree = new Card("2", "diamonds");

        assertTrue(cardOne.isEqualSuit(cardTwo));
        assertFalse(cardOne.isEqualSuit(cardThree));
    }

    @Test
    public void testIsBigCard() {
        Card cardOne = new Card("A", "hearts");
        Card cardTwo = new Card("3", "hearts");

        assertTrue(cardOne.isBigCard());
        assertFalse(cardTwo.isBigCard());
    }

    @Test
    public void testEquals1() {
        Card card1 = new Card("K", "diamonds");
        Card card2 = new Card("A", "diamonds");
        Card card3 = new Card("K", "clubs");

        assertTrue(card1.equals("K", "diamonds"));
        assertFalse(card2.equals("K", "diamonds") && card3.equals("K", "diamonds"));
    }

    @Test
    public void testEquals2() {
        Card card1 = new Card("K", "diamonds");
        Card card2 = new Card("K", "diamonds");
        Card card3 = new Card("A", "clubs");

        assertTrue(card1.equals(card2));
        assertFalse(card1.equals(card3));
    }

}
