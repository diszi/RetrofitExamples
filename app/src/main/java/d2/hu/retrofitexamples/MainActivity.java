package d2.hu.retrofitexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.text_view_result)
    TextView text_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts();

        Call<List<Comments>> callComments  = jsonPlaceHolderAPI.getComments(4);

        Call<List<Comments>> callComments_URL = jsonPlaceHolderAPI.getCommentsByURL("posts/3/comments");

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

    @OnClick(R.id.button_next)
    public void onClickButtonMovie() {
        Intent intent = new Intent(this, MovieActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_post_userID)
    public void onClickPosts(){
        Intent intent = new Intent(this,PostsActivity.class);
        startActivity(intent);
    }


}
