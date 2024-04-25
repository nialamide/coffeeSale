package com.pp.coffeesale.extern.controller.record;

import com.pp.coffeesale.domain.users.UsersRole;
import lombok.Data;


@Data
public class NewUserDTO {
    String name;
    String email;
    String password;
    UsersRole role;
}
