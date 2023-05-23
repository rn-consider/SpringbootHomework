package com.example.lowversion.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Entity(name = "t_authority")
public class Authority implements Serializable {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String authority ;
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getAuthority() {return authority;}
    public void setAuthority(String authority) {this.authority = authority;}
    @Override
    public String toString() {
        return "Authority{" + "id=" + id + ", authority='" + authority + '\'' + '}';
    }

}
