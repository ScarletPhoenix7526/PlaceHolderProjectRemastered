package kg.coder.placeholderproject.ui.posts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import kg.coder.placeholderproject.PlaceHolderApp;
import kg.coder.placeholderproject.R;
import kg.coder.placeholderproject.data.models.Post;
import kg.coder.placeholderproject.data.network.ApiService;
import kg.coder.placeholderproject.ui.comments.CommentsListActivity;

public class PostsListFragment extends Fragment
        implements PostsListContract.View, AdapterView.OnItemClickListener {
    private PostsListPresenter mPresenter;
    private ListView mPostsList;
    private ArrayList<Post> mList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiService mService = PlaceHolderApp.get(getContext()).getService();
        mPresenter = new PostsListPresenter(mService);
        mPresenter.bind(this);
        mPresenter.getPosts();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);
        mPostsList = view.findViewById(R.id.list_view);
        mPostsList.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void getPosts(ArrayList<Post> arrayList) {
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            mList.add(i, arrayList.get(random.nextInt(arrayList.size())));
        }

        setAdapter();
    }

    private void setAdapter() {
        if (getContext() != null) {
            PostsListAdapter adapter = new PostsListAdapter(getContext(), mList);
            mPostsList.setAdapter(adapter);
        }
    }

    @Override
    public void showError(String message) {
        if (getActivity() != null)
            Toast.makeText(getActivity(),
                    "Please, connect the Internet! \n" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), CommentsListActivity.class);
        intent.putExtra("id", ((Post) parent.getItemAtPosition(position)).getUserId());
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}