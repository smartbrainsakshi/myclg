package com.project.myclg;

public class FeedItem {
    String id;
    String  status,name, image, profilePic, url,timestamp;
    long time;
    String email;

    public FeedItem() {
    }

    public FeedItem(  String a,String f,String b,String c, String d,
                      String e, String g,long h,String email) {
        super();

      id=a;
        name=f;
        timestamp=b;
        image = g;
        status = d;
        profilePic = e;
time=h;
        url = c;
        this.email=email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }


    public String getProfilePic() {
        return profilePic;
    }

    public String getUrl() {
        return url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}