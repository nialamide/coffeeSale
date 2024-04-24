package com.pp.coffeesale.app.repo;

import com.pp.coffeesale.domain.Course.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
