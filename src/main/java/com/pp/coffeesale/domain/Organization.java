package com.pp.coffeesale.domain;

import com.pp.coffeesale.domain.users.Administrator;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String address;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Administrator> administrators;

}
