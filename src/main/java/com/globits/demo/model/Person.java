package com.globits.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tbl_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("full_name")
    @Column(name = "full_name")
    private String fullName;
    @Column
    private String gender;
    @Column
    private Date dob;
    @Column
    private String address;

    @JsonProperty("phone_num")
    @Column(name = "phone_num")
    private String phoneNum;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User user;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setBirthdate(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {//user mới
        //Nếu như đã có user gán cho person này, và user hiện tại khác với user mới
        //thì xóa liên kết giữa user hiện tại (user cũ) với person này
        if (this.user != null && this.user != user) this.user.setPerson(null);

        //nếu như person này chưa có user (this.user == null)
        //hoặc user hiện tại trùng với user mới
        //gán user mới cho person này
        this.user = user;

        //nếu như user mới khác null
        //và person hiện tại của user mới khác với person này
        //thì gán person này cho user mới
        if (user != null && user.getPerson() != this) user.setPerson(this);

    }

    public String toString() { return "Person [id=" + id + ", fullName=" + fullName + ", gender=" + gender + ", " +
            "birthdate=" + dob + ", address=" + address + "]";}

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
