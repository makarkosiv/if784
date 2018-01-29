package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "employees")
public class EmployeeDTO {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private double salary;

}
