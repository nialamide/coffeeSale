package com.pp.coffeesale.extern.controller.hr;


import com.pp.coffeesale.app.service.AdministratorService;
import com.pp.coffeesale.app.service.PersonalService;
import com.pp.coffeesale.domain.users.Administrator;
import com.pp.coffeesale.domain.users.Personal;
import com.pp.coffeesale.domain.users.UsersRole;
import com.pp.coffeesale.extern.record.NewUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
public class HrController {

    private static final Logger log = LoggerFactory.getLogger(HrController.class);
    private final AdministratorService administratorService;
    private final PersonalService personalService;

    public HrController(AdministratorService administratorService, PersonalService personalService) {
        this.administratorService = administratorService;
        this.personalService = personalService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", this.administratorService.getAllAdministratorForHr());
        model.addAttribute("personals", this.administratorService.getAllPersonalForHr());
        return "hr/users";
    }

    @GetMapping("/create")
    public String getNewUsers() {
        return "hr/newUser";
    }

    @PostMapping("/create")
    public String saveNewUser(NewUserDTO newUserDTO) {
        if (newUserDTO.getRole().equals(UsersRole.HR) || newUserDTO.getRole().equals(UsersRole.ADMINISTRATOR)) {
            Administrator administrator = new Administrator();
            administrator.setRole(newUserDTO.getRole());
            administrator.setName(newUserDTO.getName());
            administrator.setEmail(newUserDTO.getEmail());
            this.administratorService.createAdministrator(administrator);
        } else {
            Personal p = new Personal();
            p.setRole(newUserDTO.getRole());
            p.setName(newUserDTO.getName());
            p.setEmail(newUserDTO.getEmail());
            personalService.createPersonal(p);
        }
        return "redirect:/hr/users";
    }

    @GetMapping("users/{userId:\\d+}")
    public String getUserById(@PathVariable("userId") Long userId, Model model) {
        log.info("Работает");
        model.addAttribute("user", this.administratorService.findAdministratorById(userId).orElseThrow());
        return "hr/user";
    }
}

