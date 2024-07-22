package com.votingsystem.repository;

import com.votingsystem.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    Voter findByEmail(String email);
}
