package com.zbyszkobud.repository;

import com.zbyszkobud.model.ProjectEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectEquipmentRepository extends JpaRepository<ProjectEquipment, Long> {
}
