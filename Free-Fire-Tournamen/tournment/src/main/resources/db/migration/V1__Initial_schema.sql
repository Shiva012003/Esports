-- Initial Database Schema for Free Fire Tournament
-- Created by Flyway Migration V1

CREATE TABLE IF NOT EXISTS team (
    id BIGINT NOT NULL AUTO_INCREMENT,
    tournament_type VARCHAR(255),
    team_name VARCHAR(255),
    leader_name VARCHAR(255),
    whatsapp VARCHAR(255),
    email VARCHAR(255),
    player2 VARCHAR(255),
    player3 VARCHAR(255),
    player4 VARCHAR(255),
    payment_screenshot VARCHAR(255),
    PRIMARY KEY (id)
);

-- Create index for faster queries
CREATE INDEX idx_tournament_type ON team(tournament_type);
CREATE INDEX idx_team_name ON team(team_name);
