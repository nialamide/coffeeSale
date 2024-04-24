package com.pp.coffeesale.app.service;


import com.pp.coffeesale.app.repo.PersonalRepository;
import com.pp.coffeesale.domain.users.Personal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PersonalService {


    private final PersonalRepository personalRepository;

    @Autowired
    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public void createPersonal(Personal personal) {
        if (personal != null) {
            personalRepository.save(personal);
            log.info("Created personal");
        }
        log.info("Error creating personal");
    }

    public Optional<Personal> getPersonalById(Long id) {
        Optional<Personal> personal = personalRepository.findById(id);
        if (personal.isPresent()) {
            log.info("Found personal");
        }
        log.info("Error getting personal");
        return personal;
    }
}
