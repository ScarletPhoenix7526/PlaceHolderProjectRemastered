package kg.coder.placeholderproject.ui.albums;

import java.util.ArrayList;

import kg.coder.placeholderproject.data.models.Album;
import kg.coder.placeholderproject.ui.LifeCycle;

public interface AlbumsListContract {
    interface View {
        void getAlbums(ArrayList<Album> list);
        void showError(String message);
    }

    interface Presenter extends LifeCycle<View> {
        void getAlbums();
    }
}