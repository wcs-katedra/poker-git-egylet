package org.leanpoker.player.checkCards;

import com.wcs.poker.gamestate.Card;
import java.util.ArrayList;
import java.util.List;
import org.git_egylet.tools.Tools;
import static org.leanpoker.player.checkCards.Check.*;

/**
 *
 * @author imate
 */
public class CheckTwoPair extends Check {

    @Override
    protected void check() {
        int numberOfPairs = 0;

        for (String rank : ranks) {
            if (countRank(rank) == 2) {
                numberOfPairs++;
                highRank2 = highRank1;
                highRank1 = rank;
            }
        }
        if (numberOfPairs >= 2) {
            handRank = HandRank.TWO_PAIR;
        }

        if (handRank != null) {
            calcMyCardsOfHand();
            makeOrderedCardList();
        }
    }

    @Override
    protected void calcMyCardsOfHand() {
        if (handRank != null) {
            for (Card card : cards) {
                if (card.isInMyHand() && (card.getRank().equals(highRank1) || card.getRank().equals(highRank2))) {
                    myCardsOfHand++;
                }
            }
        }
    }

    @Override
    protected void makeOrderedCardList() {
        List<Card> pair1Cards = new ArrayList<>();
        List<Card> pair2Cards = new ArrayList<>();
        Card lastCard = null;
        cards = Tools.orderCards(cards);
        for (Card card : cards) {
            if (card.isEqualRank(highRank1)) {
                pair1Cards.add(card);
            } else if (card.isEqualRank(highRank2)) {
                pair2Cards.add(card);
            } else if (lastCard == null) {
                lastCard = card;
            }
        }
        orderedCardList.addAll(pair1Cards);
        orderedCardList.addAll(pair2Cards);
        orderedCardList.add(lastCard);
    }

}
