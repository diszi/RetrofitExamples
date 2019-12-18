package d2.hu.retrofitexamples;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Path;

public class Post {

    @SerializedName("userId")
    private int userID;

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String text;


    public Post(int userID, String title, String text) {
        this.userID = userID;
        this.title = title;
        this.text = text;
    }

    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
