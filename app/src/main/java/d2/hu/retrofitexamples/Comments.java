package d2.hu.retrofitexamples;

import com.google.gson.annotations.SerializedName;

public class Comments {


    @SerializedName("postId")
    private int postID;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String text;

    public int getPostID() {
        return postID;
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
