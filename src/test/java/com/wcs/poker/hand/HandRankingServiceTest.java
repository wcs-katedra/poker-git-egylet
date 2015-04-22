package com.wcs.poker.hand;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wcs.poker.gamestate.Card;
import com.wcs.poker.gamestate.GameState;
import com.wcs.poker.gamestate.Player;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.hamcrest.core.Is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.leanpoker.player.checkCards.CheckResult;
import org.leanpoker.player.checkCards.HandChecker;
import org.leanpoker.player.checkCards.HandRank;

/**
 *
 * @author thomas
 */
public class HandRankingServiceTest {

    private HandRankingService handRankingService;
    private HandChecker handChecker;

    @Before
    public void setUp() {
        handRankingService = new HandRankingService();
        handChecker = new HandChecker();
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testNotLessThenFiveCardsAreAccepted() {
        handRankingService.evaulate(Collections.nCopies(4, (Card) null));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testNotMoreThenSevenCardsAreAccepted() {
        handRankingService.evaulate(Collections.nCopies(8, (Card) null));
    }

    @Test
    public void testEvaulateRoyalFlushHand() throws Exception {
        CheckResult result = handChecker.getResult(modifiedCards(loadCards("royalFlushHandTestDataSet.json")));

        assertThat(result.getHand(), Is.is(HandRank.ROYAL_FLUSH));
        assertThat(result.getCards(), Is.is((Collection) loadCards("royalFlushHand.json")));
    }

    @Test
    public void testEvaulateStraightFlush() throws Exception {
        CheckResult result = handChecker.getResult(modifiedCards(loadCards("straightFlushHandTestDataSet.json")));
    
        assertThat(result.getHand(), Is.is(HandRank.STRAIGH_FLUSH));
        assertThat(result.getCards(), Is.is((Collection) loadCards("straightFlush.json")));
    }

    @Test
    public void testEvaulateFourOfAKind() throws Exception {
        CheckResult result = handChecker.getResult(modifiedCards(loadCards("fourOfAKindTestDataSet.json")));

        assertThat(result.getHand(), Is.is(HandRank.POKER));
        assertThat(result.getCards(), Is.is((Collection) loadCards("fourOfAKindHand.json")));
    }

    @Test
    public void testEvaulateFullHouse() throws Exception {
        CheckResult result = handChecker.getResult(modifiedCards(loadCards("fullHouseTestDataSet.json")));

        assertThat(result.getHand(), Is.is(HandRank.FULL));
        assertThat(result.getCards(), Is.is((Collection) loadCards("fullHouseHand.json")));
    }

    @Test
    public void testEvaulateFlush() throws Exception {
        CheckResult result = handChecker.getResult(modifiedCards(loadCards("flushTestDataSet.json")));

        assertThat(result.getHand(), Is.is(HandRank.FLUSH));
        assertThat(result.getCards(), Is.is((Collection) loadCards("flushHand.json")));
    }

    @Test
    public void testEvaulateStraight() throws Exception {
        CheckResult result = handChecker.getResult(modifiedCards(loadCards("straightTestDataSet.json")));

        assertThat(result.getHand(), Is.is(HandRank.STRAIGHT));
        assertThat(result.getCards(), Is.is((Collection) loadCards("straightHand.json")));
    }

    @Test
    public void testEvaulateThreeOfAKind() throws Exception {
        CheckResult result = handChecker.getResult(modifiedCards(loadCards("threeOfAKindTestDataSet.json")));

        assertThat(result.getHand(), Is.is(HandRank.DRILL));
        assertThat(result.getCards(), Is.is((Collection) loadCards("threeOfAKindHand.json")));
    }

    @Test
    public void testEvaulateTwoPairs() throws Exception {
        CheckResult result = handChecker.getResult(modifiedCards(loadCards("twoPairsTestDataSet.json")));

        assertThat(result.getHand(), Is.is(HandRank.TWO_PAIR));
        assertThat(result.getCards(), Is.is((Collection) loadCards("twoPairsHand.json")));
    }

    @Test
    public void testEvaulatePair() throws Exception {
        CheckResult result = handChecker.getResult(modifiedCards(loadCards("pairTestDataSet.json")));

        assertThat(result.getHand(), Is.is(HandRank.ONE_PAIR));
        assertThat(result.getCards(), Is.is((Collection) loadCards("pairHand.json")));
    }

    @Test
    public void testEvaulateHighCard() throws Exception {
        CheckResult result = handChecker.getResult(modifiedCards(loadCards("highCardDataSet.json")));

        assertThat(result.getHand(), Is.is(HandRank.HIGH_CARD));
        assertThat(result.getCards(),Is.is((Collection) loadCards("highCardHand.json")));
    }

    private List<Card> loadCards(String name) throws IOException {
        InputStream resource = getClass().getResourceAsStream(name);
        String json = IOUtils.toString(resource);

        Type cardListType = new TypeToken<List<Card>>() {
        }.getType();
        return new Gson().fromJson(json, cardListType);
    }

    private List<Card> modifiedCards(List<Card> cards) {
        cards.get(0).setInMyHand(true);
        cards.get(1).setInMyHand(true);
        return cards;
    }
}
