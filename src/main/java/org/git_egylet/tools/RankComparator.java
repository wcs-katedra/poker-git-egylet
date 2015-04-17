package org.git_egylet.tools;

import com.wcs.poker.gamestate.Card;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author imate
 */
public class RankComparator implements Comparator<Card> {

    private static final List<String> ranks = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");

    @Override
    public int compare(Card c1, Card c2) {
        return ranks.indexOf(c2.getRank()) - ranks.indexOf(c1.getRank());
    }

}
