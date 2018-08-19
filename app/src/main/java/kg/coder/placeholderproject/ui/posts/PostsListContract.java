package kg.coder.placeholderproject.ui.posts;

import java.util.ArrayList;

import kg.coder.placeholderproject.data.models.Post;
import kg.coder.placeholderproject.ui.LifeCycle;

public interface PostsListContract {

    interface View {
        void getPosts(ArrayList<Post> arrayList);
        void showError(String message);
    }

    interface Presenter extends LifeCycle<View> {
        void getPosts();
    }
}