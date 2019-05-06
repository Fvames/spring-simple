package dev.frames.demo.dao;

public class User {

    private String userName;
    private Integer age;
    private String sex;

    public User(String userName, Integer age, String sex) {
        this.userName = userName;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
