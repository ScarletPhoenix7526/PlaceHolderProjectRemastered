package kg.coder.placeholderproject.ui.comments;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import kg.coder.placeholderproject.data.models.Comment;
import kg.coder.placeholderproject.data.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsListPresenter implements CommentsListContract.Presenter {
    private CommentsListContract.View mView;
    private ApiService mService;

    CommentsListPresenter(ApiService service) {
        this.mService = service;
    }

    @Override
    public void bind(CommentsListContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbind() {
        this.mView = null;
    }

    @Override
    public void getComments(int postId) {
        mService.getComments(postId).enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Comment>> call,
                                   @NonNull Response<ArrayList<Comment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mView.getComments(response.body());
                } else {
                    mView.showError(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Comment>> call,
                                  @NonNull Throwable t) {
                mView.showError(t.getMessage());
            }
        });
    }
}