package felipe.sp.br.pokeclic.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import felipe.sp.br.pokeclic.R;
import felipe.sp.br.pokeclic.model.Card;
import felipe.sp.br.pokeclic.view.CardActivity;

/**
 * Created by Felipe on 14/09/2018.
 */

public class CardAdapter extends RecyclerView.Adapter  {

    private ArrayList<Card> cards;
    private Context context;
    private List<Card> listaCards;


    public CardAdapter( Context context, List<Card> listaCards) {

        this.listaCards = listaCards;
        this.context = context;
        this.cards = new ArrayList<>();
        this.cards.addAll(listaCards);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.detalhe_card, parent, false);

        PokemonViewHolder holder = new PokemonViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Card card = listaCards.get(position);

        ((PokemonViewHolder) holder).preencher(card);
    }

    @Override
    public int getItemCount() {
        return listaCards.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvPoke;
        private String cardId;

        public PokemonViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvPoke = itemView.findViewById(R.id.tv_poke);
        }

        private void preencher(Card card){
            cardId = card.getId();
            tvPoke.setText(card.getName().replace("-EX","").replace("ex",""));
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), CardActivity.class);
            intent.putExtra("CardId",cardId);
            v.getContext().startActivity(intent);
        }
    }
}
