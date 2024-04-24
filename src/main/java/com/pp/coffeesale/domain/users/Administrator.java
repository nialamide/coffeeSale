package com.pp.coffeesale.domain.users;

import com.pp.coffeesale.domain.Course.Course;
import com.pp.coffeesale.domain.Organization;
import com.pp.coffeesale.domain.Request;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

