package kg.coder.placeholderproject.ui.comments;

import java.util.ArrayList;

import kg.coder.placeholderproject.data.models.Comment;
import kg.coder.placeholderproject.ui.LifeCycle;

public interface CommentsListContract {

    interface View {
        void getComments(ArrayList<Comment> list);
        void showError(String message);
    }

    interface Presenter extends LifeCycle<View> {
        void getComments(int id);
    }
}