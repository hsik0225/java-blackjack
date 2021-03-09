package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CompeteResultTest {
    
    
    @Test
    @DisplayName("딜러와 모든 플레이어간의 승패 계산 테스트")
    void compete() {
        
        // given
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        Player jason = Player.from("jason");
        jason.receive(new Card(Suit.CLOVER, Rank.TEN));
        jason.receive(new Card(Suit.CLOVER, Rank.NINE));
        
        Player cu = Player.from("cu");
        cu.receive(new Card(Suit.SPADE, Rank.TEN));
        cu.receive(new Card(Suit.SPADE, Rank.JACK));
        
        List<Player> players = Arrays.asList(jason, cu);
        
        // when
        final String competeResult = CompeteResult.getCompeteResultAgainstPlayers(dealer, players);
        
        // then
        assertThat(competeResult).isEqualTo("1승 1무");
    }
    
    
    
    @Test
    @DisplayName("플레이어의 카드 합이 딜러의 합보다 클 경우 승리")
    void compete_playerSumGreaterThanDealerSum_Win() {
        
        // given
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        
        Player pobi = Player.from("pobi");
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        pobi.receive(new Card(Suit.DIAMOND, Rank.ACE));
        
        // when
        String result = CompeteResult.getCompeteResultAgainstDealer(dealer, pobi);
        
        // then
        assertThat(result).isEqualTo("승");
    }
    
    @Test
    @DisplayName("플레이어의 카드 합이 딜러의 합보다 작을 경우 패배")
    void compete_playerSumLessThanDealerSum_Defeat() {
        
        // given
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        Player pobi = Player.from("pobi");
        pobi.receive(new Card(Suit.CLOVER, Rank.TEN));
        pobi.receive(new Card(Suit.CLOVER, Rank.FIVE));
        
        // when
        String result = CompeteResult.getCompeteResultAgainstDealer(dealer, pobi);
        
        // then
        assertThat(result).isEqualTo("패");
    }
    
    @Test
    @DisplayName("플레이어의 카드 합이 딜러의 합과 같을 경우 무승부")
    void compete_playerSumEqualToDealerSum_Draw() {
        
        // given
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        
        Player pobi = Player.from("pobi");
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        
        // when
        String result = CompeteResult.getCompeteResultAgainstDealer(dealer, pobi);
        
        // then
        assertThat(result).isEqualTo("무");
    }
    
    @Test
    @DisplayName("플레이어가 버스트일 경우 무조건 플레이어 패배")
    void compete_isBust_Defeat() {
        
        // given
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        
        Player pobi = Player.from("pobi");
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        
        // when
        String result = CompeteResult.getCompeteResultAgainstDealer(dealer, pobi);
        
        // then
        assertThat(result).isEqualTo("패");
    }
}