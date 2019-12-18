package d2.hu.retrofitexamples;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Results> resultsList = new ArrayList<>();

    public void setMovieList(List<Results> resultList){
        this.resultsList.clear();
        this.resultsList.addAll(resultList);
        this.notifyDataSetChanged();

        System.out.println(" LIST SIZE = "+this.resultsList.size());
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movie_row,parent,false);
        return new MovieAdapter.MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Results item = this.resultsList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        if (resultsList != null && resultsList.size() > 0) {
            return resultsList.size();
        } else {
            return 0;
        }
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {


//        @BindView(R.id.text_line)
//        TextView line;
        @BindView(R.id.title_line)
        TextView title_line;
        @BindView(R.id.description_line)
        TextView description_line;


        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void bind(Results results){
            //line.setText(results.getOriginalTitle());
            title_line.setText(results.getOriginalTitle());
            description_line.setText(results.getDetails());

        }

    }
}
