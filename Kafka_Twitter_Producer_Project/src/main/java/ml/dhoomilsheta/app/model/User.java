package ml.dhoomilsheta.app.model;

import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

public class User {
    private long id;
    private String name;

    @SerializedName("screen_name")
    private String screenName;
    private String location;

    @SerializedName("followers_count")
    private int followersCount;

    public User(long id, String name, String screenName, String location, int followersCount) {
        this.id = id;
        this.name = name;
        this.screenName = screenName;
        this.location = location;
        this.followersCount = followersCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    @Override
    public String toString() {

        String jsonString = new JSONObject()
                .put("user_id", id)
                .put("user_name", name)
                .put("user_screenName", screenName)
                .put("user_location", location)
                .put("user_followersCount", followersCount)
                .toString();
        //.put("lang", new JSONObject().put("key1", "value1"))

        return jsonString;
    }
}
