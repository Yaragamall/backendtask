package com.librarymanagementsystem.model;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;


@Getter
@Setter
public class User {

    private Integer id;


    private String name;


    private String email;


    private Role role;


}

