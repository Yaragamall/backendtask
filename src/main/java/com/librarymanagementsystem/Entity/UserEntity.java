package com.librarymanagementsystem.Entity;

import com.librarymanagementsystem.model.Role;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
//import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "users")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;


    @Email
    @Column(unique = true)
    private String email;


    @Enumerated(EnumType.STRING)
    private Role role;


}
