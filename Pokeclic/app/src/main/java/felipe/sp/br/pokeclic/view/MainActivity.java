package felipe.sp.br.pokeclic.view;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.security.PrivateKey;

import felipe.sp.br.pokeclic.Main;
import felipe.sp.br.pokeclic.R;

import felipe.sp.br.pokeclic.model.CardDao;
import felipe.sp.br.pokeclic.rest.config.RetrofitConfig;
import felipe.sp.br.pokeclic.view.adapter.CardAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ProgressBar pgrCards;
    private BarraProgresso barraProgresso = BarraProgresso.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_poke);
        pgrCards = findViewById(R.id.prg_list_card);

        acessaServidor();
    }

    private void acessaServidor(){
        Call<CardDao> call = new RetrofitConfig().getRestInterface().listarCards();
        call.enqueue(new Callback<CardDao>() {
            @Override
            public void onResponse(Call<CardDao> call, Response<CardDao> response) {
                barraProgresso.showProgress(true,pgrCards);
                if(response.isSuccessful()){

                    CardDao cards = response.body();

                    //usar getApplicationContext() faz com que o app aborte em versões mais antigas do Android
                    //adapter = new CardAdapter(getApplicationContext(),cards.getCards());
                    adapter = new CardAdapter(getBaseContext(),cards.getCards());

                    recyclerView.setAdapter(adapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                            LinearLayoutManager.VERTICAL,false);

                    recyclerView.setLayoutManager(layoutManager);
                    barraProgresso.showProgress(false,pgrCards);
                }
            }

            @Override
            public void onFailure(Call<CardDao> call, Throwable t) {
                Toast.makeText(getApplicationContext(),R.string.falha, Toast.LENGTH_SHORT).show();
                barraProgresso.showProgress(true,pgrCards);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idBtn = item.getItemId();
        if (idBtn == R.id.btn_exit){

            //Cria o Builder do dialogo de MSG
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));

            builder.setMessage("Deseja mesmo Sair")
                    .setTitle(getString(R.string.sair));

            // Cria botão sim
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    finish();

                }
            });
            //cria botão não
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // se cancelar volta pra tela de exclusão
                }
            });
            AlertDialog dialog = builder.create();

            // Mostra o Dialog
            dialog.show();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));

        }

        return true;
    }
}
