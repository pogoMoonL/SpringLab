package com.nickhuang.springLab.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nickhuang.springLab.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    Stream<Tutorial> findByPublished(boolean published);
    Stream<Tutorial> findByTitleContaining(String title);
}