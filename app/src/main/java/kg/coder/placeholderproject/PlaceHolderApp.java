package kg.coder.placeholderproject;

import android.app.Application;
import android.content.Context;

import kg.coder.placeholderproject.data.network.ApiService;
import kg.coder.placeholderproject.data.network.NetworkBuilder;

public class PlaceHolderApp extends Application {
    private ApiService mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = NetworkBuilder.initService();
    }

    public static PlaceHolderApp get (Context context) {
        return (PlaceHolderApp) context.getApplicationContext();
    }

    public ApiService getService() {
        return mService;
    }
}