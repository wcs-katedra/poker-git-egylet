/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author poker10
 */
public class CheckConditions {

    private double randNumber;
    private int bet;
    private GameState gameState;
    private List<String> bigRank = Arrays.asList("10", "J", "Q", "K", "A");

    public CheckConditions(GameState gameState) {
        this.gameState = gameState;
    }

    public int check() {
        if (gameState.getCommunityCards().isEmpty()) {
            preFlop();
        }
        if (gameState.getCommunityCards().size() == 3) {
            Flop();
        }
        if (gameState.getCommunityCards().size() == 4) {
            Turn();
        }
        if (gameState.getCommunityCards().size() == 5) {
            River();
        }

        return bet;
    }

    private void preFlop() {
        bet = gameState.getMinimumBet();

        randNumber = Math.random();
        List<Card> cards = gameState.requestActHoleCards(gameState);
        if (randNumber > 0.1) {
            bet = 10;
        } else {
            bet = 0;
        }

        if (isTwoBigCard(cards)) {
            bet += 20;
        }
        if (isPair(cards)) {
            bet += 30;
        }
        if (isPair(cards) && (cards.get(0).getRank().equals("A") || cards.get(0).getRank().equals("K"))) {
            bet = allIN();
        }
    }

    private void Flop() {
        if (Math.random() * 100 < 70) {
            bet = gameState.getCall();
        }else{bet=0;}

    }

    private void Turn() {
        if (Math.random() * 100 < 60) {
            bet = gameState.getCall();
        }else{bet=0;}
    }

    private void River() {
        if (Math.random() * 100 < 50) {
            bet = gameState.getCall();
        }else{bet=0;}
    }

    private boolean isPair(List<Card> cards) {
        return cards.get(0).getRank().equals(cards.get(1).getRank());
    }

    private boolean isEqualColor(List<Card> cards) {
        return cards.get(0).getSuit().equals(cards.get(1).getSuit());
    }

    private boolean isOneBigCard(List<Card> cards) {
        return bigRank.contains(cards.get(0).getRank()) || bigRank.contains(cards.get(1).getRank());
    }

    private boolean isTwoBigCard(List<Card> cards) {
        return bigRank.contains(cards.get(0).getRank()) && bigRank.contains(cards.get(1).getRank());
    }

    private int howManyBigCard(List<Card> cards) {
        int i = 0;
        if (bigRank.contains(cards.get(0).getRank())) {
            i++;
        }
        if (bigRank.contains(cards.get(1).getRank())) {
            i++;
        }
        return i;
    }

    private int allIN() {
        return gameState.getCurrentPlayer(gameState).getStack();
    }
}
