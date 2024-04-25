package com.pp.coffeesale.domain.users;

import com.pp.coffeesale.domain.Organization;
import com.pp.coffeesale.domain.Request;
import com.pp.coffeesale.domain.сourse.Course;
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
@Table(name = "Administrator")
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "hr", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Course> courses = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UsersRole role;

    @OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "organization_name")
    private Organization organization;

}

