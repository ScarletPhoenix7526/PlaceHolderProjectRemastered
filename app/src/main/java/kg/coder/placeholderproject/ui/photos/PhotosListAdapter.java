package kg.coder.placeholderproject.ui.photos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kg.coder.placeholderproject.R;
import kg.coder.placeholderproject.data.models.Photo;

public class PhotosListAdapter extends ArrayAdapter<Photo> {
    PhotosListAdapter(@NonNull Context context, ArrayList<Photo> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_photo, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Photo photo = getItem(position);
        if (photo != null) {
            Glide.with(getContext())
                    .load(photo.getUrl())
                    .into(holder.mImage);
            holder.mTitle.setText(photo.getTitle());
        }

        return convertView;
    }

    class ViewHolder {
        private ImageView mImage;
        private TextView mTitle;

        ViewHolder(View view) {
            mImage = view.findViewById(R.id.imageView);
            mTitle = view.findViewById(R.id.textView);
        }
    }
}