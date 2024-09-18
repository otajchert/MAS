package com.zbyszkobud.repository;

import com.zbyszkobud.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
