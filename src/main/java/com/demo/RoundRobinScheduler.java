package com.demo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Match {
    public String homeTeam;
    public String awayTeam;
    public String ground;
    public String timeSlot;

    public Match(String homeTeam, String awayTeam, String ground, String timeSlot) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.ground = ground;
        this.timeSlot = timeSlot;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getGround() {
        return ground;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return homeTeam + " vs " + awayTeam + " at " + ground + " - " + timeSlot;
    }
}

public class RoundRobinScheduler {
    public static List<Match> generateSchedule(List<String> teamsList, List<String> groundsList, List<String> timeSlots, int matchesPerDay) {
        List<Match> schedule = new ArrayList<>();
        int numTeams = teamsList.size();
        if (numTeams % 2 != 0) {
            // Add a bye team if the number of teams is odd
            teamsList.add("Bye");
            numTeams++;
        }

        int totalRounds = numTeams - 1;
        int matchesPerRound = numTeams / 2;

        // Generate the initial round-robin schedule
        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                String home = teamsList.get((round + match) % (numTeams - 1));
                String away = teamsList.get((numTeams - 1 - match + round) % (numTeams - 1));
                // Last team stays in the same place while the others rotate around it.
                if (match == 0) {
                    away = teamsList.get(numTeams - 1);
                }
                schedule.add(new Match(home, away, groundsList.get(round % groundsList.size()), timeSlots.get(round % timeSlots.size())));
            }
        }

        // Shuffle the schedule to randomize the matches within rounds
        Collections.shuffle(schedule);

        // Split the schedule into days with the specified number of matches per day
        List<List<Match>> dailySchedule = new ArrayList<>();
        for (int i = 0; i < schedule.size(); i += matchesPerDay) {
            List<Match> dayMatches = schedule.subList(i, Math.min(i + matchesPerDay, schedule.size()));
            dailySchedule.add(dayMatches);
        }

        // Ensure no team plays twice in the same day
        for (List<Match> dayMatches : dailySchedule) {
            List<String> teamsPlayed = new ArrayList<>();
            for (Match match : dayMatches) {
                if (teamsPlayed.contains(match.getHomeTeam()) || teamsPlayed.contains(match.getAwayTeam())) {
                    // Swap with another match to avoid repetition
                    for (Match otherMatch : dayMatches) {
                        if (!teamsPlayed.contains(otherMatch.getHomeTeam()) && !teamsPlayed.contains(otherMatch.getAwayTeam())) {
                            // Swap teams
                            String tempHomeTeam = match.getHomeTeam();
                            match.homeTeam = otherMatch.getHomeTeam();
                            otherMatch.homeTeam = tempHomeTeam;

                            String tempAwayTeam = match.getAwayTeam();
                            match.awayTeam = otherMatch.getAwayTeam();
                            otherMatch.awayTeam = tempAwayTeam;

                            break;
                        }
                    }
                }
                teamsPlayed.add(match.getHomeTeam());
                teamsPlayed.add(match.getAwayTeam());
            }
        }

        // Flatten the daily schedule into a single list
        List<Match> finalSchedule = new ArrayList<>();
        for (List<Match> dayMatches : dailySchedule) {
            finalSchedule.addAll(dayMatches);
        }

        return finalSchedule;
    }

    public static void main(String[] args) {
        // Sample usage
        List<String> teamsList = new ArrayList<>();
        teamsList.add("Team A");
        teamsList.add("Team B");
        teamsList.add("Team C");
        teamsList.add("Team D");

        List<String> groundsList = new ArrayList<>();
        groundsList.add("Ground 1");
        groundsList.add("Ground 2");
        groundsList.add("Ground 3");

        List<String> timeSlots = new ArrayList<>();
        timeSlots.add("Morning");
        timeSlots.add("Afternoon");
        timeSlots.add("Evening");

        int matchesPerDay = 3;

        List<Match> schedule = generateSchedule(teamsList, groundsList, timeSlots, matchesPerDay);

        // Print the schedule
        int day = 1;
        for (Match match : schedule) {
            System.out.println("Day " + day + ": " + match);
            if (day % (teamsList.size() - 1) == 0)
                day++;
        }
    }
}
