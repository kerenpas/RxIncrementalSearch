package com.example.bmoriyama.rxincrementalsearch.dagger;

import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowAPI;
import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowService;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServiceModule {

    private String BASE_URL = "https://api.stackexchange.com/";

    @Provides
    StackOverflowService provideStackOverflowService(StackOverflowAPI stackOverflowAPI) {
        return new StackOverflowService(stackOverflowAPI);
    }

    @Provides
    StackOverflowAPI provideStackOverflowAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(StackOverflowAPI.class);
    }
}
