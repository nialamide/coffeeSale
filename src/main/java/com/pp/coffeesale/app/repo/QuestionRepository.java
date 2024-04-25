package com.pp.coffeesale.app.repo;

import com.pp.coffeesale.domain.сourse.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
