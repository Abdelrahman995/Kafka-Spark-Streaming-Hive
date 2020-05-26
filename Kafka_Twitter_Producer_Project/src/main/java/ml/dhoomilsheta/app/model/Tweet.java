package ml.dhoomilsheta.app.model;

import com.google.gson.annotations.SerializedName;
import org.json.*;
import com.jayway.jsonpath.JsonPath;

public class Tweet {
    private long id;
    private String text;
    private String lang;
    private User user;

    @SerializedName("retweet_count")
    private int retweetCount;

    @SerializedName("favorite_count")
    private int favoriteCount;

    public Tweet(long id, String text, String lang, User user, int retweetCount, int favoriteCount) {
        this.id = id;
        this.text = text;
        this.lang = lang;
        this.user = user;
        this.retweetCount = retweetCount;
        this.favoriteCount = favoriteCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    @Override
    public String toString() {

        String jsonString = new JSONObject()
                .put("id", id)
                .put("text", text)
                .put("lang", lang)
                .put("user_id", user.getId())
                .put("user_name", user.getName())
                .put("user_screenName", user.getScreenName())
                .put("user_location", user.getLocation())
                .put("user_followersCount", user.getFollowersCount())
                .put("retweetCount", retweetCount)
                .put("favoriteCount", favoriteCount)
                .toString();
                //.put("lang", new JSONObject().put("key1", "value1"))

        return jsonString;

    }
}
