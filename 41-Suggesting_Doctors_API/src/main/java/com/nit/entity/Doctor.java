package com.nit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.print.Doc;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;

    @NotEmpty
    @Size(max = 20)
    private String city;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Min(1000000000)
    private Long phoneNumber;

    @NotEmpty
    private String speciality;

}
