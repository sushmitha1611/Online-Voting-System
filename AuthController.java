package com.votingsystem.controller;

import com.votingsystem.entity.Voter;
import com.votingsystem.repository.VoterRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final VoterRepository voterRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(VoterRepository voterRepository, BCryptPasswordEncoder passwordEncoder) {
        this.voterRepository = voterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Voter register(@RequestBody Voter voter) {
        voter.setPassword(passwordEncoder.encode(voter.getPassword()));
        return voterRepository.save(voter);
    }

    @PostMapping("/login")
    public String login(@RequestBody Voter voter) {
        Voter existingVoter = voterRepository.findByEmail(voter.getEmail());
        if (existingVoter != null && passwordEncoder.matches(voter.getPassword(), existingVoter.getPassword())) {
            // Generate and return JWT token
            return "JWT token";
        }
        return "Invalid credentials";
    }
}
