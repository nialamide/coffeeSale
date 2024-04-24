package com.pp.coffeesale.domain;


import com.pp.coffeesale.domain.users.Administrator;
import com.pp.coffeesale.domain.users.RequestState;
import jakarta.persistence.*;
import lombok.Data;


/**
 * Запрос нужен, чтобы зарегистрировать нового пользователя системы
 */
@Entity
@Data
public class Request {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Administrator administrator;

    private String description;

    private RequestState state;
}
