package kg.coder.placeholderproject.ui.photos;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import kg.coder.placeholderproject.data.models.Photo;
import kg.coder.placeholderproject.data.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosListPresenter implements PhotosListContract.Presenter {
    private ApiService mService;
    private PhotosListContract.View mView;

    PhotosListPresenter(ApiService service) {
        this.mService = service;
    }

    @Override
    public void bind(PhotosListContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbind() {
        this.mView = null;
    }

    @Override
    public void getPhotos(int albumId) {
        mService.getPhotos(albumId).enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Photo>> call,
                                   @NonNull Response<ArrayList<Photo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mView.getPhotos(response.body());
                } else {
                    mView.showError(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Photo>> call, @NonNull Throwable t) {
                mView.showError(t.getMessage());
            }
        });
    }
}