package com.globits.demo.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;
    @Column
    private String password;
    @Column(name="is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Person person;

    public Integer getId() {
        return id;
    }
    public void setId(Integer personId) {
        this.id = personId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean active) {
        isActive = active;
    }

    void setPersonInternal(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {//person mới
        // person mới trùng với person hiện tại => không làm gì
        if (this.person == person) return;

        //Nếu như đã có person gán cho user này, và person hiện tại khác với person mới
        //thì xóa liên kết giữa person hiện tại (person cũ) với user này
        if (this.person != null) {
            Person oldPerson = this.person;
            this.person = null;
            oldPerson.setUserInternal(null);
        }

        //nếu như person mới khác null
        //và user hiện tại của person mới khác với user này
        //thì gán user này cho person mới
        if (person != null) {
            this.person = person;
            person.setUserInternal(this);
        }

    }


}
