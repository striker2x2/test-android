package felipe.sp.br.pokeclic.model;

import java.util.List;

/**
 * Created by Felipe on 14/09/2018.
 */

public class CardDao {

    private List<Card> cards;

    private Card card;

    public Card getCard() {
        return card;
    }

    public List<Card> getCards() {
        return cards;
    }

/*    public List<Card> removerNaoPoke(List<Card> cards){
        int i =0;
        for (Card card: cards) {
            i++;
            if(!card.getSupertype().equals("Pokémon")){
                cards.remove(i);
            }

        }
        return cards;
    }*/




}
