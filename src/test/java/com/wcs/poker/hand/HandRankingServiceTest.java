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
    private GameState gameState;
    private Player player;
    private HandChecker handChecker;

    @Before
    public void setUp() {
        handRankingService = new HandRankingService();
        gameState = new GameState(10, 320, 400, 241, 0, 7, 0);
        player = new Player(1, "Albert", "active", "Default random player", 1590, 80);
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
        setCards(loadCards("royalFlushHandTestDataSet.json"));
        CheckResult result = handChecker.getResult(gameState);

        assertThat(result.getHand(), Is.is(HandRank.ROYAL_FLUSH));
        assertThat(result.getCards(), Is.is((Collection) loadCards("royalFlushHand.json")));
    }

    @Test
    public void testEvaulateStraightFlush() throws Exception {
        setCards(loadCards("straightFlushHandTestDataSet.json"));
        CheckResult result = handChecker.getResult(gameState);

        assertThat(result.getHand(), Is.is(HandRank.STRAIGH_FLUSH));
        assertThat(result.getCards(), Is.is((Collection) loadCards("straightFlush.json")));
    }

    @Test
    public void testEvaulateFourOfAKind() throws Exception {
        setCards(loadCards("fourOfAKindTestDataSet.json"));
        CheckResult result = handChecker.getResult(gameState);

        assertThat(result.getHand(), Is.is(HandRank.POKER));
        assertThat(result.getCards(), Is.is((Collection) loadCards("fourOfAKindHand.json")));
    }

    @Test
    public void testEvaulateFullHouse() throws Exception {
        setCards(loadCards("fullHouseTestDataSet.json"));
        CheckResult result = handChecker.getResult(gameState);

        assertThat(result.getHand(), Is.is(HandRank.FULL));
        assertThat(result.getCards(), Is.is((Collection) loadCards("fullHouseHand.json")));
    }

    @Test
    public void testEvaulateFlush() throws Exception {
        setCards(loadCards("flushTestDataSet.json"));
        CheckResult result = handChecker.getResult(gameState);

        assertThat(result.getHand(), Is.is(HandRank.FLUSH));
        assertThat(result.getCards(), Is.is((Collection) loadCards("flushHand.json")));
    }

    @Test
    public void testEvaulateStraight() throws Exception {
        setCards(loadCards("straightTestDataSet.json"));
        CheckResult result = handChecker.getResult(gameState);

        assertThat(result.getHand(), Is.is(HandRank.STRAIGHT));
        assertThat(result.getCards(), Is.is((Collection) loadCards("straightHand.json")));
    }

    @Test
    public void testEvaulateThreeOfAKind() throws Exception {
        setCards(loadCards("threeOfAKindTestDataSet.json"));
        CheckResult result = handChecker.getResult(gameState);

        assertThat(result.getHand(), Is.is(HandRank.DRILL));
        assertThat(result.getCards(), Is.is((Collection) loadCards("threeOfAKindHand.json")));
    }

    @Test
    public void testEvaulateTwoPairs() throws Exception {
        setCards(loadCards("twoPairsTestDataSet.json"));
        CheckResult result = handChecker.getResult(gameState);

        assertThat(result.getHand(), Is.is(HandRank.TWO_PAIR));
        assertThat(result.getCards(), Is.is((Collection) loadCards("twoPairsHand.json")));
    }

    @Test
    public void testEvaulatePair() throws Exception {
        setCards(loadCards("pairTestDataSet.json"));
        CheckResult result = handChecker.getResult(gameState);

        assertThat(result.getHand(), Is.is(HandRank.ONE_PAIR));
        assertThat(result.getCards(), Is.is((Collection) loadCards("pairHand.json")));
    }

    @Test
    public void testEvaulateHighCard() throws Exception {
        setCards(loadCards("highCardDataSet.json"));
        CheckResult result = handChecker.getResult(gameState);

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

    private void setCards(List<Card> cards) {
        player.setHoleCards(cards.subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(cards.subList(2, cards.size()));
    }
}
