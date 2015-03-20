/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author poker10
 */
public class CheckConditions {

    private int bet;
    private GameState gameState;
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
        if (AceOrKingPair(cards)) {
            bet = allIn();
        }

        if (isSHITuation(cards)) {
            bet = 0;
        }

        if (bet == 0 && gameState.areWeBlind()) {
            bet = gameState.getCall();
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

    
 
    
    
    
    private boolean isSHITuation(List<Card> cards) {
        return Math.random() < 0.33 && howManyBigCard(cards) < 2 && !isPair(cards) && !isEqualSuit(cards);
    }

    private boolean AceOrKingPair(List<Card> cards) {
        return isPair(cards) && (cards.get(0).getRank().equals("A") || cards.get(0).getRank().equals("K"));
    }

    private boolean isPair(List<Card> cards) {
        return cards.get(0).isEqualRank(cards.get(1));
    }

    private boolean isEqualSuit(List<Card> cards) {        
        return cards.get(0).isEqualSuit(cards.get(1));
    }

    private boolean isOneBigCard(List<Card> cards) {
        return howManyBigCard(cards) == 1;
    }

    private boolean isTwoBigCard(List<Card> cards) {
        return howManyBigCard(cards) == 2;
    }

    private int howManyBigCard(List<Card> cards) {
        int i = 0;
        if (cards.get(0).isBigCard()) {
            i++;
        }
        if (cards.get(1).isBigCard()) {
            i++;
        }
        return i;
    }

    private int allIn() {
        return gameState.getCurrentPlayer().getStack();
    }

    
    
}
