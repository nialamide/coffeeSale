package com.pp.coffeesale;


import com.pp.coffeesale.app.service.AdministratorService;
import com.pp.coffeesale.domain.users.Administrator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class AdminServiceTest {


    private final AdministratorService administratorService;

    @Autowired
    public AdminServiceTest(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }


    @Test
    void createAdministrator() {
        Administrator administrator = new Administrator();
        administratorService.createAdministrator(administrator);
        Assertions.assertTrue(administratorService.findAdministratorById(administrator.getId()).isPresent());
    }

}
