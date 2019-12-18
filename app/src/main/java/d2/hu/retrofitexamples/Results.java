package d2.hu.retrofitexamples;

import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("overview")
    private String details;

//    @SerializedName("popularity")
//    private long popularity;

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getDetails() {
        return details;
    }

    //    public long getPopularity() {
//        return popularity;
//    }
}
