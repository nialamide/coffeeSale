package com.pp.coffeesale.domain;


import com.pp.coffeesale.domain.users.Personal;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "documents_id")
    private Personal personal;

    private String title;
    private String url;
}
