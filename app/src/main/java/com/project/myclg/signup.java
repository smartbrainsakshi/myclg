package com.project.myclg;

/**
 * Created by sakshi pc on 07-08-2016.
 */
public class signup {
    String phone,email,name,password,branch,year,username,profile;

    public signup() {
    }

    public signup(String password, String email, String name, String phone,String branch,String year,String username,String profile) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.branch= branch;
        this.year = year;
        this.username=username;
        this.profile=profile;

    }

    public String getYear() {
        return year;
    }

    public String getBranch() {
        return branch;
    }

    public String getProfile() {
        return profile;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
