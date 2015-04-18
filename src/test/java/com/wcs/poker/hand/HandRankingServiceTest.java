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
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.leanpoker.player.checkCards.CheckResult;
import org.leanpoker.player.checkCards.HandChecker;
import org.leanpoker.player.checkCards.HandRank;
import static org.junit.Assert.assertEquals;


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
        gameState=new GameState(10, 320, 400, 241, 0, 7, 0);
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
        player.setHoleCards(loadCards("royalFlushHandTestDataSet.json").subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(loadCards("royalFlushHandTestDataSet.json").subList(2, 7));
        CheckResult result = handChecker.getResult(gameState);
        
        assertThat(result.getHand(), Is.is(HandRank.ROYAL_FLUSH));
        //assertThat(result.getCards(), Is.is((Collection) loadCards("royalFlushHand.json")));
    }
    
    @Test
    public void testEvaulateStraightFlush() throws Exception {
        player.setHoleCards(loadCards("straightFlushHandTestDataSet.json").subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(loadCards("straightFlushHandTestDataSet.json").subList(2, 7));
        CheckResult result = handChecker.getResult(gameState);
        
        assertThat(result.getHand(), Is.is(HandRank.STRAIGH_FLUSH));
        //assertThat(result.getCards(), Is.is((Collection) loadCards("straightFlush.json")));
    }
    
    @Test
    public void testEvaulateFourOfAKind() throws Exception {
        player.setHoleCards(loadCards("fourOfAKindTestDataSet.json").subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(loadCards("fourOfAKindTestDataSet.json").subList(2, 6));
        CheckResult result = handChecker.getResult(gameState);
        
        assertThat(result.getHand(), Is.is(HandRank.POKER));
        //assertThat(result.getCards(), Is.is((Collection) loadCards("fourOfAKindHand.json")));
    }
    
    @Test
    public void testEvaulateFullHouse() throws Exception {
        player.setHoleCards(loadCards("fullHouseTestDataSet.json").subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(loadCards("fullHouseTestDataSet.json").subList(2, 6));
        CheckResult result = handChecker.getResult(gameState);
        
        assertThat(result.getHand(), Is.is(HandRank.FULL));
        //assertThat(result.getCards(), Is.is((Collection) loadCards("fullHouseHand.json")));
    }
    
    @Test
    public void testEvaulateFlush() throws Exception {
        player.setHoleCards(loadCards("flushTestDataSet.json").subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(loadCards("flushTestDataSet.json").subList(2, 6));
        CheckResult result = handChecker.getResult(gameState);
        
        assertThat(result.getHand(), Is.is(HandRank.FLUSH));
        //assertThat(result.getCards(), Is.is((Collection) loadCards("flushHand.json")));
    }
    
    @Test
    public void testEvaulateStraight() throws Exception {
        player.setHoleCards(loadCards("straightTestDataSet.json").subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(loadCards("straightTestDataSet.json").subList(2, 7));
        CheckResult result = handChecker.getResult(gameState);
        
        assertThat(result.getHand(), Is.is(HandRank.STRAIGHT));
        //assertThat(result.getCards(), Is.is((Collection) loadCards("straightHand.json")));
    }
    
    @Test
    public void testEvaulateThreeOfAKind() throws Exception {
        player.setHoleCards(loadCards("threeOfAKindTestDataSet.json").subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(loadCards("threeOfAKindTestDataSet.json").subList(2, 7));
        CheckResult result = handChecker.getResult(gameState);
        
        assertThat(result.getHand(), Is.is(HandRank.DRILL));
        //assertThat(result.getCards(), Is.is((Collection) loadCards("threeOfAKindHand.json")));
    }
    
    @Test
    public void testEvaulateTwoPairs() throws Exception {
        player.setHoleCards(loadCards("twoPairsTestDataSet.json").subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(loadCards("twoPairsTestDataSet.json").subList(2, 5));
        CheckResult result = handChecker.getResult(gameState);
        
        assertThat(result.getHand(), Is.is(HandRank.TWO_PAIR));
        //assertThat(result.getCards(), Is.is((Collection) loadCards("twoPairsHand.json")));
    }
    
    @Test
    public void testEvaulatePair() throws Exception {
        player.setHoleCards(loadCards("pairTestDataSet.json").subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(loadCards("pairTestDataSet.json").subList(2, 5));
        CheckResult result = handChecker.getResult(gameState);
        
        assertThat(result.getHand(), Is.is(HandRank.ONE_PAIR));
        //assertThat(result.getCards(), Is.is((Collection) loadCards("pairHand.json")));
    }
    
    @Test
    public void testEvaulateHighCard() throws Exception {
        player.setHoleCards(loadCards("highCardDataSet.json").subList(0, 2));
        gameState.setPlayers(Arrays.asList(player));
        gameState.setCommunityCards(loadCards("highCardDataSet.json").subList(2, 6));
        CheckResult result = handChecker.getResult(gameState);
             
        assertThat(result.getHand(), Is.is(HandRank.HIGH_CARD));
        //assertThat(result.getCards(),Is.is((Collection) loadCards("highCardHand.json")));
    }
    
    

    private List<Card> loadCards(String name) throws IOException {
        InputStream resource = getClass().getResourceAsStream(name);
        String json = IOUtils.toString(resource);
        
        Type cardListType = new TypeToken<List<Card>>(){}.getType();
        return new Gson().fromJson(json, cardListType);
    }
}
