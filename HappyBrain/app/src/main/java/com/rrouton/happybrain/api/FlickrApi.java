package com.rrouton.happybrain.api;

import android.util.Log;

import com.rrouton.happybrain.models.flickr.Photo;
import com.rrouton.happybrain.models.flickr.Photos;

import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class FlickrApi {

    private static final String BASE_URL = "https://api.flickr.com/";
    private static final String API_KEY = "771121adec7d520d28cf0340574e93f9";
    private static final String TAG = "HappyBrain/BaseApi";

    private FlickrService service;

    public interface Listener {
        void loadPhotos(List<Photo> photos);
    }

    public FlickrApi() {
        service = getService();
    }

    public void getPhotos(Listener listener) {
        service.getRecentPhotos(100, 1).enqueue(new Callback<FlickrGetRecentPhotosResponse>() {
            @Override
            public void onResponse(Call<FlickrGetRecentPhotosResponse> call, Response<FlickrGetRecentPhotosResponse> response) {
                Photos photos = response.body().getPhotos();
                listener.loadPhotos(photos.getPhoto());
            }

            @Override
            public void onFailure(Call<FlickrGetRecentPhotosResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private FlickrService getService() {
        return getRetrofit().create(FlickrService.class);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();
    }

    private Interceptor getInterceptor() {
        return chain -> {
            Request request = chain.request();
            HttpUrl url = request
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .addQueryParameter("nojsoncallback", "1")
                    .addQueryParameter("format", "json")
                    .build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        };
    }
}
