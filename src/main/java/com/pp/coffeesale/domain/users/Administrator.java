package com.pp.coffeesale.domain.users;

import com.pp.coffeesale.domain.Course.Course;
import com.pp.coffeesale.domain.Organization;
import com.pp.coffeesale.domain.Request;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "hrId", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Course> courses = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UsersRole role;

    @OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "organization_name")
    private Organization organization;

}

