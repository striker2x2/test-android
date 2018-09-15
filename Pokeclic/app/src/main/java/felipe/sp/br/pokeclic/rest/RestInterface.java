package felipe.sp.br.pokeclic.rest;

import felipe.sp.br.pokeclic.model.Card;
import felipe.sp.br.pokeclic.model.CardDao;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Felipe on 14/09/2018.
 */

public interface RestInterface {

    @GET("cards?count=20")
    Call<CardDao> listarCards();

    @GET("cards/{id}")
    Call<CardDao> getCard(@Path("id") String id);

    //@GET("cards/{id}")
    //Call<Card> getCard(@Path("id") String id);
}
