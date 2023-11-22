package com.example.androidstudentregistrationapp;

public class Student
{
    private String name;
    private String id;
    private String gender;
    private String programme;
    private String cellNo;

    public Student(String name, String id, String gender, String programme, String cellNo) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.programme = programme;
        this.cellNo = cellNo;
    }

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }
}
