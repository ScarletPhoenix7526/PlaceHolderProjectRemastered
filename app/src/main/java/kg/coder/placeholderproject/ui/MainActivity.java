package kg.coder.placeholderproject.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import kg.coder.placeholderproject.R;
import kg.coder.placeholderproject.data.network.NetworkStatus;
import kg.coder.placeholderproject.ui.albums.AlbumsListFragment;
import kg.coder.placeholderproject.ui.posts.PostsListFragment;

public class MainActivity extends AppCompatActivity {
    private TextView mInternetStatus;
    private ImageView mPlaceHolderImage;
    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_albums:
                        switchFragment(new AlbumsListFragment());
                        if (!NetworkStatus.isNetworkAvailable(getApplicationContext())) {
                            mInternetStatus.setVisibility(View.VISIBLE);
                            mInternetStatus.setText(R.string.text_internet_ok);
                            mPlaceHolderImage.setVisibility(View.VISIBLE);
                        } else {
                            mInternetStatus.setVisibility(View.INVISIBLE);
                            mInternetStatus.setText(R.string.text_internet_not);
                            mPlaceHolderImage.setVisibility(View.INVISIBLE);
                        }
                        return true;
                    case R.id.navigation_posts:
                        switchFragment(new PostsListFragment());
                        if (!NetworkStatus.isNetworkAvailable(getApplicationContext())) {
                            mInternetStatus.setVisibility(View.VISIBLE);
                            mInternetStatus.setText(R.string.text_internet_ok);
                            mPlaceHolderImage.setVisibility(View.VISIBLE);
                        } else {
                            mInternetStatus.setVisibility(View.INVISIBLE);
                            mInternetStatus.setText(R.string.text_internet_not);
                            mPlaceHolderImage.setVisibility(View.INVISIBLE);
                        }
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mPlaceHolderImage = findViewById(R.id.placeholder);
        mInternetStatus = findViewById(R.id.internet);
        if (!NetworkStatus.isNetworkAvailable(getApplicationContext())) {
            mInternetStatus.setText(R.string.text_internet_ok);
            mInternetStatus.setVisibility(View.VISIBLE);
            mPlaceHolderImage.setVisibility(View.VISIBLE);
        } else {
            mInternetStatus.setText(R.string.text_internet_not);
            mInternetStatus.setVisibility(View.VISIBLE);
            mPlaceHolderImage.setVisibility(View.VISIBLE);
        }
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .commit();
    }
}