package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Profile {
    private List<String> achievements;
    private int reputation;
    @SerializedName("about_raw")
    private String aboutRaw;
    private String locale;
    private String id;
    private String background;
    private String platform;
    @SerializedName("last_seen")
    private String lastSeen;
    private String status;
    private boolean banned;
    private String avatar;
    private String region;
    @SerializedName("ingame_name")
    private String inGameName;
    @SerializedName("own_profile")
    private boolean ownProfile;
    private String about;

    public List<String> getAchievements() {
        return achievements;
    }

    public int getReputation() {
        return reputation;
    }

    public String getAboutRaw() {
        return aboutRaw;
    }

    public String getLocale() {
        return locale;
    }

    public String getId() {
        return id;
    }

    public String getBackground() {
        return background;
    }

    public String getPlatform() {
        return platform;
    }

    public LocalDateTime getLastSeen() {
        return parseDateTime(lastSeen);
    }

    public String getStatus() {
        return status;
    }

    public boolean isBanned() {
        return banned;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getRegion() {
        return region;
    }

    public String getInGameName() {
        return inGameName;
    }

    public boolean isOwnProfile() {
        return ownProfile;
    }

    public String getAbout() {
        return about;
    }

    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        return LocalDateTime.parse(dateTime, formatter);
    }
}