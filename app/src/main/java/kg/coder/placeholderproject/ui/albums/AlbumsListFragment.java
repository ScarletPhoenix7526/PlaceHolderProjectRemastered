package kg.coder.placeholderproject.ui.albums;

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
import kg.coder.placeholderproject.data.models.Album;
import kg.coder.placeholderproject.ui.photos.PhotosListActivity;

public class AlbumsListFragment extends Fragment
        implements AlbumsListContract.View, AdapterView.OnItemClickListener {
    private ListView mAlbums;
    private ArrayList<Album> mList = new ArrayList<>();
    private AlbumsListPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new AlbumsListPresenter(PlaceHolderApp.get(getContext()).getService());
        mPresenter.bind(this);
        mPresenter.getAlbums();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);
        mAlbums = view.findViewById(R.id.list_view);
        mAlbums.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void getAlbums(ArrayList<Album> list) {
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            mList.add(i, list.get(random.nextInt(list.size())));
        }
        if (getContext() != null) {
            AlbumsListAdapter adapter = new AlbumsListAdapter(getContext(), mList);
            mAlbums.setAdapter(adapter);
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
        Intent intent = new Intent(getActivity(), PhotosListActivity.class);
        intent.putExtra("albumId", ((Album) parent.getItemAtPosition(position)).getUserId());
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}