/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.leanpoker.player.strategies;

import com.wcs.poker.gamestate.GameState;
import org.leanpoker.player.checkCards.CheckResult;
import org.leanpoker.player.checkCards.HandRank;
import org.leanpoker.player.checkCards.HandChecker;

/**
 *
 * @author imate
 */
public abstract class Strategy {

    protected HandRank hand;
    protected String highRank1;
    protected String highRank2;
    protected int myCardsOfHand;
    protected GameState gameState;

    public int calculateBet(GameState gameState) {
        int bet = 0;
        HandChecker handChecker = new HandChecker();
        CheckResult result = handChecker.getResult(gameState);
        this.hand = result.getHand();
        this.highRank1 = result.getHighRank1();
        this.highRank2 = result.getHighRank2();
        this.myCardsOfHand = result.getMyCardsOfHand();

        switch (hand) {
            case ROYAL_FLUSH:
                bet = calcRoyalFlush();
                break;

            case STRAIGH_FLUSH:
                bet = calcStraightFlush();
                break;

            case POKER:
                bet = calcPoker();
                break;

            case FULL:
                bet = calcFull();
                break;

            case FLUSH:
                bet = callFlush();
                break;

            case STRAIGHT:
                bet = calcStraight();
                break;

            case DRILL:
                bet = calcDrill();
                break;

            case TWO_PAIR:
                bet = calcTwoPair();
                break;

            case ONE_PAIR:
                bet = calcOnePair();
                break;

            case HIGH_CARD:
                bet = calcHighCard();

        }

        return bet;
    }

    protected abstract int calcRoyalFlush();

    protected abstract int calcStraightFlush();

    protected abstract int calcPoker();

    protected abstract int calcFull();

    protected abstract int callFlush();

    protected abstract int calcStraight();

    protected abstract int calcDrill();

    protected abstract int calcTwoPair();

    protected abstract int calcOnePair();

    protected abstract int calcHighCard();
}
