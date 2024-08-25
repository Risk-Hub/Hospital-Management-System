package com.hsbc.model;

public class User extends HospitalStaff {
    private int userId;
    private String userName;

    public User() {
    }

    public User(String loginId, String password, String personType, int userId, String userName) {
        super(loginId, password, personType);
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
