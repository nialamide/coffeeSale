package com.pp.coffeesale.app.repo;

import com.pp.coffeesale.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
