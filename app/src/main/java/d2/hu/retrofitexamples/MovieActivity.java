package d2.hu.retrofitexamples;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieActivity extends AppCompatActivity {


    @BindView(R.id.title)
    TextView title_line;
    @BindView(R.id.recyclerView_results)
    RecyclerView recyclerView_resultlist;

    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

       // Call<List<Results>> call = jsonPlaceHolderAPI.getResults();

        Call<Movie> call = jsonPlaceHolderAPI.getMovies();

        /*call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                if (!response.isSuccessful()){
                    result_line.setText("Code: "+response.code());
                    return;
                }

                int counter=0;

                List<Results>  resultsList = response.body();
                for(Results results : resultsList){
                    System.out.println(" ---> result [] = "+results.getOriginalTitle());
                    counter++;

                }
                System.out.println(" COUNTER = "+counter);
                result_line.setText(counter);

            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                System.out.println(" error = "+t.getMessage());
                result_line.setText(t.getMessage());
            }
        });*/

        this.setupRecyclerView();

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                Movie movie = response.body();
                System.out.println(" movie total page = "+movie.getTotalpage());

                List<Results> resultsList = movie.getResultList();
                System.out.println(" result List size = "+resultsList.size());

                adapter.setMovieList(resultsList);

//                for (Results results : resultsList){
//
//                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                System.out.println("ERROR = "+t.getMessage());
            }
        });


    }

    public void setupRecyclerView() {
        Context context = getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        this.adapter = new MovieAdapter();
        this.recyclerView_resultlist.setLayoutManager(layoutManager);
        this.recyclerView_resultlist.setAdapter(this.adapter);
    }

}
