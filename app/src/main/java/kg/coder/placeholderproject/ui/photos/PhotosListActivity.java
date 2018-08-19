package kg.coder.placeholderproject.ui.photos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import kg.coder.placeholderproject.PlaceHolderApp;
import kg.coder.placeholderproject.R;
import kg.coder.placeholderproject.data.models.Photo;
import kg.coder.placeholderproject.data.network.ApiService;
import kg.coder.placeholderproject.ui.BaseActivity;

public class PhotosListActivity extends BaseActivity
        implements PhotosListContract.View, AdapterView.OnItemClickListener {
    private GridView mAlbums;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        mAlbums = findViewById(R.id.gridView);
        mAlbums.setOnItemClickListener(this);

        ApiService service = PlaceHolderApp.get(this).getService();
        PhotosListPresenter mPresenter = new PhotosListPresenter(service);
        mPresenter.bind(this);
        mPresenter.getPhotos(getIntent().getIntExtra("albumId", 0));
    }

    @Override
    public void getPhotos(ArrayList<Photo> list) {
        mAlbums.setAdapter(new PhotosListAdapter(this, list));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), ShowPhotoActivity.class);
        intent.putExtra("id", ((Photo)parent.getItemAtPosition(position)).getUrl());
        startActivity(intent);
    }
}