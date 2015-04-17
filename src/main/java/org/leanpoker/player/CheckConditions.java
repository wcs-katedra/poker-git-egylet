package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.ArrayList;
import java.util.List;
import org.leanpoker.player.checkCards.CheckResult;
import org.leanpoker.player.checkCards.HandRank;
import org.leanpoker.player.checkCards.HandChecker;
import org.leanpoker.player.strategies.*;

/**
 *
 * @author poker10
 */
public class CheckConditions {

    private int limit = 500;
    private int bet;
    private GameState gameState;
    private List<Card> allCards = new ArrayList<>();

    public CheckConditions(GameState gameState) {
        this.gameState = gameState;
    }

    public int check() {
        bet = 0;
        if (gameState.getCommunityCards().isEmpty()) {
            preFlop();
        } else {
            afterPreFlop();
        }

//        Strategy strategy = null;
//        switch (gameState.getCommunityCards().size()) {
//            case 0:
//                strategy = new PreFlopStrategy();
//                break;
//            case 3:
//                strategy = new FlopStrategy();
//                break;
//            case 4:
//                strategy = new TurnStrategy();
//                break;
//            case 5:
//                strategy = new RiverStrategy();
//                break;
//        }
//        if (strategy != null) {
//            bet = strategy.calculateBet(gameState);
//        }

        return bet;
    }

    private void preFlop() {
        HandChecker handChecker = new HandChecker();
        CheckResult checkResult = handChecker.getResult(gameState);
        HandRank hand = checkResult.getHand();
        String highRank1 = checkResult.getHighRank1();

        switch (hand) {

            case ONE_PAIR:
                if (Card.getBigRank().contains(highRank1)) {
                    bet = gameState.getCall();
                } else {
                    if (allIn() > limit) {
                        bet = gameState.getCall();
                    } else {
                        bet = 0;
                    }
                }
                break;
            case HIGH_CARD:
                if (Card.getBigRank().contains(highRank1)) {
                    if (gameState.getCall() < gameState.requestBigBlind() * 5) {
                        if (allIn() > limit) {
                            bet = gameState.getCall();
                        } else {
                            bet = 0;
                        }

                    } else {
                        bet = 0;
                    }

                } else {
                    bet = 0;
                }
                break;

        }
        if (bet == 0 && gameState.areWeBlind() && gameState.getCall() <= gameState.requestBigBlind()) {
            bet = gameState.getCall();
        }

    }

    private void afterPreFlop() {
        HandChecker handChecker = new HandChecker();
        CheckResult checkResult = handChecker.getResult(gameState);
        HandRank hand = checkResult.getHand();
        String highRank1 = checkResult.getHighRank1();
        String highRank2 = checkResult.getHighRank2();

        switch (hand) {
            case ROYAL_FLUSH:
                if (isFlop()) {
                    raising(gameState.getSmallBlind() * 2);
                }
                if (isTurn()) {
                    raising(gameState.getSmallBlind() * 3);
                }
                if (isRiver()) {
                    raising(allIn());
                }

                break;

            case STRAIGH_FLUSH:
                if (isFlop()) {
                    raising(gameState.getSmallBlind() * 2);
                }
                if (isTurn()) {
                    raising(gameState.getSmallBlind() * 3);
                }
                if (isRiver()) {
                    raising(allIn());
                }
                break;

            case POKER:

                if (isFlop()) {
                    raising(gameState.getSmallBlind() * 2);
                }
                if (isTurn()) {
                    raising(gameState.getSmallBlind() * 3);
                }
                if (isRiver()) {
                    raising(allIn());
                }
                break;

            case FULL:
                if (isFlop()) {
                    raising(gameState.getSmallBlind() * 2);
                }
                if (isTurn()) {
                    raising(gameState.getSmallBlind() * 3);
                }
                if (isRiver()) {
                    raising(allIn());
                }
                break;

            case FLUSH:
                if (isFlop()) {
                    raising(gameState.getSmallBlind() * 2);
                }
                if (isTurn()) {
                    raising(gameState.getSmallBlind() * 4);
                }
                if (isRiver()) {

                    raising(gameState.getSmallBlind() * 5);
                }
                break;

            case STRAIGHT:

                if (Card.getBigRank().contains(highRank1)) {
                    if (isFlop()) {
                        raising(gameState.getSmallBlind());
                    }
                    if (isTurn()) {
                        raising(gameState.getSmallBlind() * 3);
                    }
                    if (isRiver()) {

                        raising(gameState.getSmallBlind() * 4);
                    }

                } else {
                    if (isFlop()) {
                        raising(gameState.getSmallBlind());
                    }
                    if (isTurn()) {
                        raising(gameState.getSmallBlind() * 2);
                    }
                    if (isRiver()) {

                        raising(gameState.getSmallBlind() * 3);
                    }
                }
                break;

            case DRILL:
                if (Card.getBigRank().contains(highRank1)) {
                    if (isFlop()) {
                        raising(gameState.getSmallBlind());
                    }
                    if (isTurn()) {
                        raising(gameState.getSmallBlind() * 2);
                    }
                    if (isRiver()) {

                        raising(gameState.getSmallBlind() * 3);
                    }

                } else {
                    if (isFlop()) {
                        raising(gameState.getSmallBlind());
                    }
                    if (isTurn()) {
                        raising(gameState.getSmallBlind());
                    }
                    if (isRiver()) {

                        raising(gameState.getSmallBlind() * 2);
                    }
                }

                break;

            case TWO_PAIR:
                if (Card.getBigRank().contains(highRank1)) {
                    if (isFlop()) {
                        raising(gameState.getSmallBlind());
                    }
                    if (isTurn()) {
                        raising(gameState.getSmallBlind() * 2);
                    }
                    if (isRiver()) {

                        raising(gameState.getSmallBlind() * 3);
                    }

                } else {
                    if (isFlop()) {
                        raising(gameState.getSmallBlind());
                    }
                    if (isTurn()) {
                        raising(gameState.getSmallBlind());
                    }
                    if (isRiver()) {

                        raising(gameState.getSmallBlind() * 2);
                    }
                }

                break;

            case ONE_PAIR:

                if (Card.getBigRank().contains(highRank1)) {
                    if (isFlop()) {

                        if (allIn() > limit && gameState.getCall() < gameState.requestBigBlind() * 7) {
                            bet = gameState.getCall();
                        } else {
                            bet = 0;
                        }
                    }
                    if (isTurn()) {

                        if (allIn() > limit && gameState.getCall() < gameState.requestBigBlind() * 5) {
                            bet = gameState.getCall();
                        } else {
                            bet = 0;
                        }
                    }
                    if (isRiver()) {

                        System.out.println("allin: " + allIn() + "call: " + gameState.getCall());
                        if (allIn() > limit && gameState.getCall() < gameState.requestBigBlind() * 5) {
                            bet = gameState.getCall();
                            System.out.println("true");
                        } else {
                            System.out.println("false");
                            bet = 0;
                        }
                    }

                } else {
                    if (isFlop()) {
                        if (allIn() > limit && gameState.getCall() < gameState.requestBigBlind()) {
                            bet = gameState.getCall();
                        } else {
                            bet = 0;
                        }
                    }
                    if (isTurn()) {
                        if (allIn() > limit && gameState.getCall() < gameState.requestBigBlind()) {
                            bet = gameState.getCall();
                        } else {
                            bet = 0;
                        }
                    }
                    if (isRiver()) {
                        bet = 0;
                    }
                }

                break;

            case HIGH_CARD:
                bet = 0;
        }

    }

    private boolean isFlop() {
        return gameState.getCommunityCards().size() == 3;
    }

    private boolean isTurn() {
        return gameState.getCommunityCards().size() == 4;
    }

    private boolean isRiver() {
        return gameState.getCommunityCards().size() == 5;
    }

    private void raising(int number) {
        bet = gameState.getMinimumBet() + number;
    }

    private boolean isSHITuation(List<Card> cards) {
        return Math.random() < 0.33 && howManyBigCard(cards) < 2 && !isPair(cards) && !isEqualSuit(cards);
    }

    private boolean isAceOrKingPair(List<Card> cards) {
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
