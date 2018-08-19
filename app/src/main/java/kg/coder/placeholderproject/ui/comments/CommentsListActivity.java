package kg.coder.placeholderproject.ui.comments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import kg.coder.placeholderproject.PlaceHolderApp;
import kg.coder.placeholderproject.R;
import kg.coder.placeholderproject.data.models.Comment;
import kg.coder.placeholderproject.data.network.ApiService;
import kg.coder.placeholderproject.ui.BaseActivity;

public class CommentsListActivity extends BaseActivity implements CommentsListContract.View {
    private ListView mCommentsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        mCommentsList = findViewById(R.id.list_view);

        ApiService mService = PlaceHolderApp.get(this).getService();

        Intent intent = getIntent();
        int userId = intent.getIntExtra("id", 0);

        CommentsListPresenter mPresenter = new CommentsListPresenter(mService);
        mPresenter.bind(this);
        mPresenter.getComments(userId);
    }

    @Override
    public void getComments(ArrayList<Comment> list) {
        mCommentsList.setAdapter(new CommentsListAdapter(getApplicationContext(), list));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}