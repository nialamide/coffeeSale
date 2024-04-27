package com.pp.coffeesale.extern.controller.barista;

import com.pp.coffeesale.app.service.PersonalService;
import com.pp.coffeesale.domain.users.Personal;
import com.pp.coffeesale.extern.record.NewUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/barista")
@Slf4j
@RequiredArgsConstructor
public class BaristasController {

    private final PersonalService personalService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("personals", this.personalService.getAll());
        return "barista/users";
    }

    @GetMapping("/create")
    public String getNewUsers() {
        return "barista/newUser";
    }

    @PostMapping("/create")
    public String saveNewUser(NewUserDTO newUserDTO) {
        Personal p = new Personal();
        p.setRole(newUserDTO.getRole());
        p.setName(newUserDTO.getName());
        p.setEmail(newUserDTO.getEmail());
        personalService.createPersonal(p);
        return "redirect:/barista/users";
    }

    @GetMapping("/users/{userId:\\d+}")
    public String getUserById(@PathVariable("userId") Long userId, Model model) {
        log.info("Пользователь по id");
        model.addAttribute("user", this.personalService.getPersonalById(userId).orElseThrow());
        return "barista/user";
    }
}

