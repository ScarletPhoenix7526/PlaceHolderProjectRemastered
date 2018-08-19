package kg.coder.placeholderproject.ui.comments;

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
import kg.coder.placeholderproject.data.models.Comment;

public class CommentsListAdapter extends ArrayAdapter<Comment> {
    CommentsListAdapter(@NonNull Context context, ArrayList<Comment> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_comment, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Comment comment = getItem(position);
        if (comment != null) {
            holder.mBody.setText(comment.getBody());
            holder.mEmail.setText(comment.getEmail());
            holder.mName.setText(comment.getName());
            holder.mPostId.setText(String.valueOf(comment.getPostId()));
            holder.mId.setText(String.valueOf(comment.getId()));
        }

        return convertView;
    }

    class ViewHolder {
        private TextView mBody;
        private TextView mEmail;
        private TextView mName;
        private TextView mPostId;
        private TextView mId;

        ViewHolder(View view) {
            mBody = view.findViewById(R.id.body);
            mEmail = view.findViewById(R.id.email);
            mName = view.findViewById(R.id.name);
            mPostId = view.findViewById(R.id.postId);
            mId = view.findViewById(R.id.id);
        }
    }
}