package com.example.demo.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Select;

@Data
public class User {
    private int id;
    private String user;
    private String pass;

}

