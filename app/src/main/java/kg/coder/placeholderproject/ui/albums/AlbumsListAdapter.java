package kg.coder.placeholderproject.ui.albums;

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
import kg.coder.placeholderproject.data.models.Album;

public class AlbumsListAdapter extends ArrayAdapter<Album> {
    AlbumsListAdapter(@NonNull Context context, ArrayList<Album> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_albums, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Album albums = getItem(position);
        if (albums != null) {
            holder.mUserId.setText(String.valueOf(albums.getUserId()));
            holder.mId.setText(String.valueOf(albums.getId()));
            holder.mTitle.setText(albums.getTitle());
        }
        return convertView;
    }

    class ViewHolder {
        private TextView mUserId;
        private TextView mId;
        private TextView mTitle;

        ViewHolder(View view) {
            mUserId = view.findViewById(R.id.userId);
            mId = view.findViewById(R.id.id);
            mTitle = view.findViewById(R.id.title);
        }
    }
}