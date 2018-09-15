package felipe.sp.br.pokeclic.view;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import felipe.sp.br.pokeclic.R;
import felipe.sp.br.pokeclic.model.Card;
import felipe.sp.br.pokeclic.model.CardDao;
import felipe.sp.br.pokeclic.rest.config.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardActivity extends AppCompatActivity {

    private Button btnInfo;
    private ImageView imgCard;
    private String id;
    private TextView tvNomeCard;
    private ProgressBar progressBar;
    private BarraProgresso barraProgresso = BarraProgresso.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        btnInfo = findViewById(R.id.btn_moreInfo);
        imgCard = findViewById(R.id.img_card);
        tvNomeCard = findViewById(R.id.toolbar_card_name);
        progressBar = findViewById(R.id.pgr_card);

        mostraViews(false);

        //usa o ID no Bundle para atribuir valor aos elementos da tela

        final Bundle bundle = getIntent().getExtras();
        final String cardId = (bundle != null) ? bundle.getString("CardId") : null;
        
        if (!cardId.isEmpty()) {
            id = cardId;
            acessaServidor();
            //chamada do botão More INFO

        }


    }

    private void acessaServidor() {
        Call<CardDao> call = new RetrofitConfig().getRestInterface().getCard(id);
        call.enqueue(new Callback<CardDao>() {
            @Override
            public void onResponse(Call<CardDao> call, Response<CardDao> response) {
                barraProgresso.showProgress(true,progressBar);

                if(response.isSuccessful()) {
                    CardDao cards = response.body() ;
                    try {
                        inicializa(cards.getCard());
                        mostraViews(true);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), getString(R.string.falha)
                                , Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<CardDao> call, Throwable t) {
                Toast.makeText(getBaseContext(),getString(R.string.falha), Toast.LENGTH_SHORT).show();
                mostraViews(false);
                barraProgresso.showProgress(false,progressBar);
            }
        });

    }

    private void inicializa(Card card) throws Exception{

        try {
            Picasso.with(getApplicationContext()).load(card.getImageUrlHiRes()).into(imgCard);
        }catch (Exception e){
            try{
                Picasso.with(getApplicationContext()).load(card.getImageUrl()).into(imgCard);
            }catch (Exception e2){
                Toast.makeText(getApplicationContext(), getString(R.string.falhaImagem), Toast.LENGTH_SHORT).show();
            }
        }

        tvNomeCard.setText(card.getName().replace("-EX","").replace("ex",""));
        moreInfo(card);

    }

    public void mostraViews(boolean mostra){

        if(mostra){
            btnInfo.setVisibility(View.VISIBLE);
            imgCard.setVisibility(View.VISIBLE);
            tvNomeCard.setVisibility(View.VISIBLE);
            barraProgresso.showProgress(false,progressBar);
        }else {

            btnInfo.setVisibility(View.INVISIBLE);
            imgCard.setVisibility(View.INVISIBLE);
            tvNomeCard.setVisibility(View.INVISIBLE);
        }
    }

    public void moreInfo(final Card card){


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg;
                if(card.getSupertype().equals("Pokémon")){
                    msg = "Esse Pokémon possui "+ card.getHp() + " de HP e Seu Numero da pokedex é "
                            + card.getNationalPokedexNumber() + ".";
                }else if(card.getSupertype().equals("Trainer")){
                    msg = card.getText().get(0);
                }else{
                    msg = card.getName();
                }

                //Cria o Builder do dialogo de MSG
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(CardActivity.this, R.style.myDialog));

                builder.setMessage(msg)
                        .setTitle(card.getName().replace("-EX","").replace("ex",""));

                // Cria botão ok
                builder.setPositiveButton("ok", null);
                AlertDialog dialog = builder.create();

                // Mostra o Dialog
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
            }
        });
    }
}
