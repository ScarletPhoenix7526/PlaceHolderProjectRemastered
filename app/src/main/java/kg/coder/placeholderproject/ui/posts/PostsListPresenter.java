package kg.coder.placeholderproject.ui.posts;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import kg.coder.placeholderproject.data.models.Post;
import kg.coder.placeholderproject.data.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsListPresenter implements PostsListContract.Presenter {
    private PostsListContract.View mView;
    private ApiService mService;

    PostsListPresenter(ApiService service) {
        this.mService = service;
    }

    @Override
    public void bind(PostsListContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbind() {
        this.mView = null;
    }

    @Override
    public void getPosts() {
        mService.getPosts().enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Post>> call,
                                   @NonNull Response<ArrayList<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mView.getPosts(response.body());
                } else {
                    mView.showError(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Post>> call, @NonNull Throwable t) {
                mView.showError(t.getMessage());

            }
        });
    }
}