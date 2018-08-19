package kg.coder.placeholderproject.ui.photos;

import java.util.ArrayList;

import kg.coder.placeholderproject.data.models.Photo;
import kg.coder.placeholderproject.ui.LifeCycle;

public interface PhotosListContract {

    interface View {
        void getPhotos(ArrayList<Photo> list);
        void showError(String message);
    }

    interface Presenter extends LifeCycle<View> {
        void getPhotos(int albumId);
    }
}