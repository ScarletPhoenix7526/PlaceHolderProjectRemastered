package kg.coder.placeholderproject.ui.posts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kg.coder.placeholderproject.R;
import kg.coder.placeholderproject.data.models.Post;

public class PostsListAdapter extends ArrayAdapter<Post> {
    PostsListAdapter(@NonNull Context context, ArrayList<Post> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_posts, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Post post = getItem(position);
        if (post != null) {
            holder.mUserId.setText(String.valueOf(post.getUserId()));
            holder.mId.setText(String.valueOf(post.getId()));
            holder.mTitle.setText(post.getTitle());
            holder.mBody.setText(post.getBody());
        }

        return convertView;
    }

    class ViewHolder {
        private TextView mUserId;
        private TextView mId;
        private TextView mTitle;
        private TextView mBody;

        ViewHolder(View view) {
            mUserId = view.findViewById(R.id.userId);
            mId = view.findViewById(R.id.id);
            mTitle = view.findViewById(R.id.title);
            mBody = view.findViewById(R.id.body);
        }
    }
}