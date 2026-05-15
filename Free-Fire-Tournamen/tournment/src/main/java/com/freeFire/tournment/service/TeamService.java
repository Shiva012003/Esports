package com.freeFire.tournment.service;

import com.freeFire.tournment.model.Team;
import com.freeFire.tournment.repository.teamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private teamRepository teamRepository;

    @Autowired
    private EmailService emailService;

    public Team saveTeam(Team team) {

        Team savedTeam = teamRepository.save(team);

        emailService.sendRegistrationMail(
                team.getTeamName(),
                team.getTournamentType(),
                team.getLeaderName(),
                team.getWhatsapp()
        );

        return savedTeam;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public void sendContactEmail(String emailBody) {
        emailService.sendContactInquiry(emailBody); 
    }
}