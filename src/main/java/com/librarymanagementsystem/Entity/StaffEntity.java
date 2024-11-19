package com.librarymanagementsystem.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "staff")
@Getter
@Setter
public class StaffEntity extends UserEntity {
    private String position;
    @Column(columnDefinition = "NUMERIC")
    private float salary;
}
