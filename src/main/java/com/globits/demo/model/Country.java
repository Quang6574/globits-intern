package com.globits.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

/*
* Đánh dấu model này là entity cho Spring Boot scan
* Và liên kết với bảng tbl_country trong mysql database
* */
@Entity
@Table(name = "tbl_country")
public class Country {
    @Id
    @Column(name = "id")//liên kết giá trị bên dưới với cột tương ứng trong tbl_country
    /*
    id country primary key, có thể tự đặt theo thứ tự tăng dần
    hoặc đặt thủ công
    */
    //@GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")//liên kết giá trị bên dưới với cột tương ứng trong tbl_country
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;

    //getter và setter
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
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //chuyển đổi toàn bộ thông tin thành String. Có thể dùng trong tương lai
    public String toString() {
        return "Country [id=" + id + ", name=" + name + ", code=" + code + ", description=" + description + "]";
    }

}
