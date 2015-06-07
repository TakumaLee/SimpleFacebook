package com.github.takumalee.simplefacebook.response;

/**
 * Created by Nijugon on 2015/6/6.
 */
public class MeResponse {

    /**
     * updated_time : 2014-10-27T04:33:34+0000
     * gender : male
     * timezone : 8
     * link : https://www.facebook.com/app_scoped_user_id/651797408244163/
     * name : 嚴雨峰
     * verified : true
     * bio : 3.我的YOUTUBE頻道  http://www.youtube.com/user/adsl99801?feature=watch
     * last_name : 嚴
     * id : 651797408244163
     * locale : zh_TW
     * first_name : 雨峰
     */
    private String updated_time;
    private String gender;
    private int timezone;
    private String link;
    private String name;
    private boolean verified;
    private String bio;
    private String last_name;
    private String id;
    private String locale;
    private String first_name;

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
