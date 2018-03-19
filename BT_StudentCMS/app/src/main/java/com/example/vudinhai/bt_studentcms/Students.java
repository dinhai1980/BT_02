package com.example.vudinhai.bt_studentcms;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vudinhai on 3/14/18.
 */

public class Students implements Serializable {
    int id;
    String name;
    String address;
    int gender;

    public Students() {
    }

    public Students(int id, String name, String address, int gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public static ArrayList<Students> init(){
        ArrayList<Students> studentList = new ArrayList<>();

        studentList.add(new Students(1,"Nguyen Trinh", "http://hcmpreu.edu.vn", 1));
        studentList.add(new Students(2,"Doan Thi Khoa", "http://hcmpreu.edu.vn", 0));
        studentList.add(new Students(3,"Tran Thuy", "http://hiu.edu.vn", 0));
        studentList.add(new Students(4,"Nguyen Kim", "http://hcmup.edu.vn", 1));
        studentList.add(new Students(5,"Huu Nhan", "http://hcmussh.edu.vn", 1));
        studentList.add(new Students(6,"Yen Phuong", "http://hutech.edu.vn", 0));
        studentList.add(new Students(7,"Nguyen Oanh", "http://huflit.edu.vn", 0));
        studentList.add(new Students(8,"Huynh Nam", "http://uit.edu.vn", 1));
        studentList.add(new Students(9,"Hoang Kiem", "http://hcmpreu.edu.vn", 1));
        studentList.add(new Students(10,"Tran Trang", "http://hcmut.edu.vn", 0));
        studentList.add(new Students(11,"Ngoc Hong", "http://hcmut.edu.vn", 0));


        return studentList;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                '}';
    }
}
