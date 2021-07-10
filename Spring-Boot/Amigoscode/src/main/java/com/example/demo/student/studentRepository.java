package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface studentRepository extends JpaRepository<student, Long> {

    @Query("SELECT s From student s WHERE s.email = ?1")
    Optional<student> findstudentByEmail(String email);
}
