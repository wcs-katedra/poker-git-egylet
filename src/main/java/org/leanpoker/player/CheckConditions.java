/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.ArrayList;
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
    private List<Card> allCards = new ArrayList<>();

    public CheckConditions(GameState gameState) {
        this.gameState = gameState;
    }

    public int check() {
        if (gameState.getCommunityCards().isEmpty()) {
            preFlop();
        } else {
            afterPreFlop();
        }
        info();
        return bet;
    }

    private void preFlop() {
        bet = gameState.getCall();
        List<Card> cards = gameState.requestActHoleCards();

        if (isTwoBigCard(cards)) {
            if (gameState.requestBigBlind() <= 50) {
                bet += gameState.requestBigBlind() * 2;
            } else {
                bet += 100;
            }
        }
        if (isPair(cards)) {
            if (gameState.requestBigBlind() <= 50) {
                bet += gameState.requestBigBlind() * 2;
            } else {
                bet += 100;
            }
        }
        if (isPair(cards) && (cards.get(0).getRank().equals("A") || cards.get(0).getRank().equals("K"))) {
            bet = allIn();
        }

        if (Math.random() < 0.33 && howManyBigCard(cards) < 2 && !isPair(cards) && !isEqualColor(cards)) {
            bet = 0;
        }
        if (bet==0 && weAreBlind()) {
            bet=gameState.getCall();
        }
    }

    private void flop() {
        if (Math.random() * 100 < 70) {
            bet = gameState.getCall();
        } else {
            bet = 0;
        }

    }

    private void turn() {
        if (Math.random() * 100 < 60) {
            bet = gameState.getCall();
        } else {
            bet = 0;
        }
    }

    private void river() {
        if (Math.random() * 100 < 50) {
            bet = gameState.getCall();
        } else {
            bet = 0;
        }
    }

    private void afterPreFlop() {
        allCards.clear();
        allCards.addAll(gameState.requestActHoleCards());
        allCards.addAll(gameState.getCommunityCards());
        HandChecker handChecker = new HandChecker(allCards);
        int hand = handChecker.checkCards();

        if (hand > 4) {
            bet = allIn() - (int) (Math.random() * allIn() / 10);
        }

        if (hand >= 3 && hand <= 4) {
            bet = (int) (allIn() / 2) - (int) (Math.random() * allIn() / 10);
        }

        if (hand == 2) {
            bet = gameState.getCall() + 20;
        }
        if (hand == 1) {
            bet = gameState.getCall() + 10;
        }

        if (hand == 0) {
            if (Math.random() < 0.20) {
                bet = gameState.getCall();
            } else {
                bet = 0;
            }
        }

        if (gameState.getCall() > gameState.getCurrentPlayer().getStack() * 0.75 && hand < 5) {
            bet = 0;
        }
    }

    private boolean isPair(List<Card> cards) {
        return cards.get(0).getRank().equals(cards.get(1).getRank());
    }

    private boolean isEqualColor(List<Card> cards) {
        return cards.get(0).getSuit().equals(cards.get(1).getSuit());
    }

    private boolean isOneBigCard(List<Card> cards) {
        return howManyBigCard(cards) == 1;
    }

    private boolean isTwoBigCard(List<Card> cards) {
        return howManyBigCard(cards) == 2;
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

    private int allIn() {
        return gameState.getCurrentPlayer().getStack();
    }

    private void info() {
        System.out.println(gameState.requestActHoleCards() + "," + gameState.getCommunityCards());
        System.out.println(bet + "." + gameState.getMinimumBet() + "." + gameState.getMinimumRaise() + "." + gameState.getCall());
        System.out.println("__________...róka fogta csuka...__________");
    }
    private boolean weAreBlind(){
    return ((gameState.getDealer()+1)%gameState.getPlayers().size()==gameState.getInAction()
          ||(gameState.getDealer()+2)%gameState.getPlayers().size()==gameState.getInAction());
    }
}
