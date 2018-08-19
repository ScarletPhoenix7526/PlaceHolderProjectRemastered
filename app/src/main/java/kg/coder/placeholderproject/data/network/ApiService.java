package kg.coder.placeholderproject.data.network;

import java.util.ArrayList;

import kg.coder.placeholderproject.BuildConfig;
import kg.coder.placeholderproject.data.models.Album;
import kg.coder.placeholderproject.data.models.Comment;
import kg.coder.placeholderproject.data.models.Photo;
import kg.coder.placeholderproject.data.models.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(BuildConfig.ALBUMS_ENDPOINT)
    Call<ArrayList<Album>> getAlbums();

    @GET(BuildConfig.PHOTOS_ENDPOINT)
    Call<ArrayList<Photo>> getPhotos(@Query("albumId") int albumId);

    @GET(BuildConfig.POSTS_ENDPOINT)
    Call<ArrayList<Post>> getPosts();

    @GET(BuildConfig.COMMENTS_ENDPOINT)
    Call<ArrayList<Comment>> getComments(@Query("postId") int postId);
}