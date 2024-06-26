package com.pp.coffeesale.app.repo;

import com.pp.coffeesale.domain.users.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
