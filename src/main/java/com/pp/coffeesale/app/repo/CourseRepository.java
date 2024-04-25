package com.pp.coffeesale.app.repo;

import com.pp.coffeesale.domain.сourse.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
