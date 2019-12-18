package d2.hu.retrofitexamples;

import java.util.List;
import java.util.Map;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderAPI {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts")
    Call<List<Post>> getPostsBy(@Query("userId") int userid); //https://jsonplaceholder.typicode.com/posts?userId=4 ==> get data where userId field=4


    @GET("posts")
    Call<List<Post>> getPostByFilters(
            @Query("userId") int userid,
            @Query("_sort") String sort,
            @Query("_order") String order
    ); //https://jsonplaceholder.typicode.com/posts?userId=4&_sort=id&_order=desc


    @GET("posts")
    Call<List<Post>> getPostBy2ID(
            @Query("userId") Integer userid1,
            @Query("userId") Integer userid2,
            @Query("_sort") String sort,
            @Query("_order") String order
    ); //jsonPlaceHolderAPI.getPostBy2ID(1,4,null,null);

    @GET("posts")
    Call<List<Post>> getPostByIDs(
            @Query("userId") Integer[] userid,
            @Query("_sort") String sort,
            @Query("_order") String order
    ); //jsonPlaceHolderAPI.getPostByIDs(new Integer[]{2,3,6},null,null);


    @GET("posts")
    Call<List<Post>> getPostByParameters(@QueryMap Map<String,String> params);

    @GET("posts/{id}")
    Call<Post> getPostByID(@Path("id") int id); //https://jsonplaceholder.typicode.com/posts/4 ==> get data where id=4 (key)

    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postID);


    @GET
    Call<List<Comments>> getCommentsByURL(@Url String url);


    @GET("3/search/movie?api_key=d243e02fe7382c503c90edea8d0dbc21&language=en-US&query=GET&page=1&include_adult=false")
    Call<List<Results>> getResults();


    @GET("3/search/movie?api_key=d243e02fe7382c503c90edea8d0dbc21&language=en-US&query=GET&page=1&include_adult=false")
    Call<Movie> getMovies();



    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPostF2(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String body
    );


    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPostByMap(@FieldMap Map<String,String> fields);


    @PUT("posts/{id}")
    Call<Post> updatePostById(@Path("id") int id, @Body Post post);


    @PATCH("posts/{id}")
    Call<Post> patchPostById(@Path("id") int id, @Body Post post);
}
