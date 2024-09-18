package com.zbyszkobud.repository;

import com.zbyszkobud.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
