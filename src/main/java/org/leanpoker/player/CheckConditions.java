package org.leanpoker.player;

import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import java.util.ArrayList;
import java.util.List;
import org.leanpoker.player.checkCards.CheckResult;
import org.leanpoker.player.checkCards.Hand;
import org.leanpoker.player.checkCards.HandChecker;

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
        if (isAceOrKingPair(cards)) {
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
        HandChecker handChecker = new HandChecker();
        CheckResult checkResult = handChecker.getResult(gameState);
        Hand hand = checkResult.getHand();
        String highRank1 = checkResult.getHighRank1();
        String highRank2 = checkResult.getHighRank2();

        switch (hand) {
            case ROYAL_FLUSH:
                if (isFlop()) {
                    raising(gameState.getSmallBlind()*2);
                }
                if (isTurn()) {
                    raising(gameState.getSmallBlind()*3);
                }
                if (isRiver()) {
                    raising(allIn());
                }

                break;

            case STRAIGH_FLUSH:
                if (isFlop()) {
                    raising(gameState.getSmallBlind()*2);
                }
                if (isTurn()) {
                    raising(gameState.getSmallBlind()*3);
                }
                if (isRiver()) {
                    raising(allIn());
                }
                break;

            case POKER:

                if (isFlop()) {
                    raising(gameState.getSmallBlind()*2);
                }
                if (isTurn()) {
                    raising(gameState.getSmallBlind()*3);
                }
                if (isRiver()) {
                    raising(allIn());
                }
                break;

            case FULL:
                if (isFlop()) {
                    raising(gameState.getSmallBlind()*2);
                }
                if (isTurn()) {
                    raising(gameState.getSmallBlind()*3);
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
                        if (allIn() > 500 && gameState.getMinimumBet() < 200) {
                            gameState.getCall();
                        } else {
                            bet = 0;
                        }
                    }
                    if (isTurn()) {
                        if (allIn() > 500 && gameState.getMinimumBet() < 150) {
                            gameState.getCall();
                        } else {
                            bet = 0;
                        }
                    }
                    if (isRiver()) {
                        if (allIn() > 500 && gameState.getMinimumBet() < 150) {
                            gameState.getCall();
                        } else {
                            bet = 0;
                        }
                    }

                } else {
                    if (isFlop()) {
                        if (allIn() > 500 && gameState.getMinimumBet() < 100) {
                            gameState.getCall();
                        } else {
                            bet = 0;
                        }
                    }
                    if (isTurn()) {
                        if (allIn() > 500 && gameState.getMinimumBet() < 100) {
                            gameState.getCall();
                        } else {
                            bet = 0;
                        }
                    }
                    if (isRiver()) {
                        bet=0;
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
