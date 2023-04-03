package model;

import java.sql.Date;

public class Student {
    private Integer id;
    private String name ;
    private Integer phoneNumber;
    private String className;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Student() {
    }

    public Student(Integer id, String name,  Integer phoneNumber, String className) {
        this.id = id;
        this.name = name;

        this.phoneNumber = phoneNumber;
        this.className = className;
    }

}
