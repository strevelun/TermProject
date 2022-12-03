package com.example.termproject;

public class User implements Comparable<User> {
    String Badge = "";

    String Name = "";
    String Univ = "";

    public User() {}
    public User(String badge, String name, String univ) {
        this.Badge = badge;
        this.Name = name;
        this.Univ = univ;
    }

    public String getBadge() {
        return Badge;
    }

    public void setBadge(String badge) {
        this.Badge = badge;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getUniv() {
        return Univ;
    }

    public void setUniv(String univ) {
        this.Univ = univ;
    }

    @Override
    public int compareTo(User user) {
        if(Integer.parseInt(user.Badge) < Integer.parseInt(Badge))
            return 1;
        else if(Integer.parseInt(user.Badge) > Integer.parseInt(Badge))
            return -1;
        return 0;
    }
}
