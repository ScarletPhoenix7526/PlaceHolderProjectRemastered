package kg.coder.placeholderproject.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable {
    @SerializedName("albumId")
    @Expose
    private Integer albumId;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;

    public final static Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public Photo(Parcel in) {
        albumId = in.readByte() == 0x00 ? null : in.readInt();
        id = in.readByte() == 0x00 ? null : in.readInt();
        title = in.readString();
        url = in.readString();
        thumbnailUrl = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (albumId == null) {
            dest.writeByte((byte) 0x00);
        } else {
            dest.writeByte((byte) 0x01);
            dest.writeInt(albumId);
        }

        if (id == null) {
            dest.writeByte((byte) 0x00);
        } else {
            dest.writeByte((byte) 0x01);
            dest.writeInt(id);
        }

        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(thumbnailUrl);
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}