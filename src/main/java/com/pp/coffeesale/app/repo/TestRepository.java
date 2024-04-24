package com.pp.coffeesale.app.repo;

import com.pp.coffeesale.domain.Course.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
