package org.leanpoker.player.checkCards;

import org.git_egylet.tools.Tools;

/**
 *
 * @author imate
 */
public class CheckHighCard extends Check {

    @Override
    protected void check() {
        handRank = HandRank.HIGH_CARD;
        makeOrderedCardList();
        highRank1 = orderedCardList.get(0).getRank();
        highRank2 = orderedCardList.get(1).getRank();
    }

    @Override
    protected void calcMyCardsOfHand() {
    }

    @Override
    protected void makeOrderedCardList() {
        cards = Tools.orderCards(cards);
        int i = 0;
        if (cards.size() == 2) {
            orderedCardList.addAll(cards);
        } else {
            while (orderedCardList.size() < 5) {
                orderedCardList.add(cards.get(i));
                i++;
            }
        }
    }

}
