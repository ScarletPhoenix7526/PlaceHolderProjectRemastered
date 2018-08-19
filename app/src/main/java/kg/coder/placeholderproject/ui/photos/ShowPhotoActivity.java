package kg.coder.placeholderproject.ui.photos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kg.coder.placeholderproject.R;
import kg.coder.placeholderproject.ui.BaseActivity;

public class ShowPhotoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showphoto);

        ImageView mImage = findViewById(R.id.image);

        Glide.with(this)
                .load(getIntent().getStringExtra("id"))
                .into(mImage);
    }
}