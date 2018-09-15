package felipe.sp.br.pokeclic.rest.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import felipe.sp.br.pokeclic.rest.RestInterface;
import felipe.sp.br.pokeclic.rest.commons.AppUtils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Felipe on 14/09/2018.
 */

public class RetrofitConfig {

    private final Retrofit retrofit;

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder().baseUrl(AppUtils.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    public RetrofitConfig(OkHttpClient client) {

        this.retrofit = new Retrofit.Builder()
                .baseUrl(AppUtils.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public RestInterface getRestInterface(){
        return this.retrofit.create(RestInterface.class);
    }
}
