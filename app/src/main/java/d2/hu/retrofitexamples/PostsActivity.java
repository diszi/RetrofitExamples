package d2.hu.retrofitexamples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsActivity extends AppCompatActivity {

    @BindView(R.id.text)
    TextView text_result;


    JsonPlaceHolderAPI jsonPlaceHolderAPI= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        getPosts();



    }

    private void getPosts(){



        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");

        Call<List<Post>> call = jsonPlaceHolderAPI.getPostsBy(4); // == jsonPlaceHolderAPI.getPostByFilters(4,null,null)
        Call<Post> callPost = jsonPlaceHolderAPI.getPostByID(4);
        Call<List<Post>> callgetByFilter = jsonPlaceHolderAPI.getPostByFilters(4,"id","desc");
        Call<List<Post>> callMAP = jsonPlaceHolderAPI.getPostByParameters(parameters);



        callPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) { }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {


                if (!response.isSuccessful()) {
                    text_result.setText("Code: " + response.code());
                    return;
                }

                List<Post> postList = response.body();
                for (Post post : postList) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "Users ID: " + post.getUserID() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n";

                    text_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                text_result.setText(t.getMessage());
            }
        });
    }

    private void createPost(){
        Post post = new Post(23,"NEW Title","NEW TEXT");

        Call<Post> callPOST = jsonPlaceHolderAPI.createPost(post);
        Call<Post> callPost2 = jsonPlaceHolderAPI.createPostF2(24,"New Title 2"," New text 2");



        Map<String,String> map = new HashMap<>();
        map.put("userId","25");
        map.put("title","New Title 3");

        Call<Post> callPostByMAP = jsonPlaceHolderAPI.createPostByMap(map);



        callPOST.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                System.out.println(" SUCCESS");
                Post postResponse = response.body();

                String content="";
                content+= "Response Code: "+response.code()+"\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "Users ID: " + postResponse.getUserID() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n";

                text_result.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                System.out.println("FAILED");
            }
        });

    }


    @OnClick(R.id.button_post_method)
    public void onClickPOSTmethod(){
        createPost();
    }

    @OnClick(R.id.button_update)
    public void onClickUpdate(){
        updatePost();
    }

    private void updatePost(){

        Post post = new Post(12,null,"New text");

        Call<Post> updatePost = jsonPlaceHolderAPI.updatePostById(5,post);
        Call<Post> patchPost = jsonPlaceHolderAPI.patchPostById(5,post);
    }
}
