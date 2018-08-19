package kg.coder.placeholderproject.ui.albums;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import kg.coder.placeholderproject.data.models.Album;
import kg.coder.placeholderproject.data.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsListPresenter implements AlbumsListContract.Presenter {
    private AlbumsListContract.View mView;
    private ApiService mService;

    public AlbumsListPresenter(ApiService service) {
        this.mService = service;
    }

    @Override
    public void bind(AlbumsListContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbind() {
        this.mView = null;
    }

    @Override
    public void getAlbums() {
        mService.getAlbums().enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Album>> call,
                                   @NonNull Response<ArrayList<Album>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mView.getAlbums(response.body());
                } else {
                    mView.showError(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Album>> call, @NonNull Throwable t) {
                mView.showError(t.getMessage());
            }
        });
    }
}