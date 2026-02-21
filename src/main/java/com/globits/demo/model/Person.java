package com.globits.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;




import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tbl_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("full_name")
    @Column(name = "full_name")
    private String fullName;
    @JsonProperty("phone_num")
    @Column(name = "phone_num")
    private String phoneNum;
    @Column
    private String gender;
    @Column
    private Date dob;
    @Column
    private String address;
    @Column
    private String avatar;

    //user relation one to one
    //person owns the user
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User user;

    //many to one relation with company
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_code", referencedColumnName = "code", nullable = true)
    private Company company;

    //many to many relation with role
    @ManyToMany
    @JoinTable(
            name = "tbl_person_role",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "role")
    )
    private Set<Role> roles = new HashSet<>();

    //many to many relation with project
    @ManyToMany
    @JoinTable(
            name = "tbl_person_project",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
    )
    private Set<Project> projects = new HashSet<>();

    //
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Task> tasks;

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

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
    public void setDob(Date dob) {
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

    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    void setUserInternal(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {//user mới

        if (this.user == user) return; //không làm gì nếu user mới trùng với user hiện tại

        //Nếu như đã có user gán cho person này, và user hiện tại khác với user mới
        //thì xóa liên kết giữa user hiện tại (user cũ) với person này
        if (this.user != null) {
            User oldUser = this.user;
            this.user = null;
            oldUser.setPersonInternal(null);
        }

        //nếu như user mới khác null
        //và person hiện tại của user mới khác với person này
        //thì gán person này cho user mới
        if (user != null) {
            this.user = user;
            user.setPersonInternal(this);
        }

    }

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Project> getProjects() {
        return projects;
    }
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public String toString() { return "Person [id=" + id + ", fullName=" + fullName + ", gender=" + gender + ", " +
            "birthdate=" + dob + ", address=" + address + "]";}

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
