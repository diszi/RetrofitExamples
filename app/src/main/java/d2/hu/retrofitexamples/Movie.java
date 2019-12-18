package d2.hu.retrofitexamples;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    @SerializedName("results")
    private List<Results> resultList;

    @SerializedName("total_pages")
    private int totalpage;

    public List<Results> getResultList() {
        return resultList;
    }

    public int getTotalpage() {
        return totalpage;
    }
}
