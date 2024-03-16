//package com.demo;
//
//import com.criccenter.criccenter.db.table.Ground;
//import com.criccenter.criccenter.db.table.Match;
//import com.criccenter.criccenter.db.table.TeamSnapshot;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class Scheduler {
//    private List<TeamSnapshot> teams;
//    private List<Ground> grounds;
//    private int matchesPerDay;
//    private List<Slot> schedule;
//
//    public Scheduler(List<TeamSnapshot> teams, List<Ground> grounds, int matchesPerDay) {
//        this.teams = teams;
//        this.grounds = grounds;
//        this.matchesPerDay = matchesPerDay;
//        this.schedule = new ArrayList<>();
//    }
//
//    public void createSchedule(LocalDateTime startTime) {
//        // Assuming matches are played every day at the same time for simplicity
//
//        LocalDateTime currentTime = startTime;
//        for (int i = 0; i < teams.size(); i++) {
//            for (int j = i + 1; j < teams.size(); j++) {
////                Match match = new Match(teams.get(i), teams.get(j));
//                Match match = new Match();
//                match.setTeamOneSnapshot(teams.get(i));
//                match.setTeamTwoSnapshot(teams.get(j));
//                Slot slot = findNextAvailableSlot(currentTime);
//                match.setStartDate(Date.from(slot.getTime().toInstant());
//                if (slot != null) {
//                    slot.setMatch(match);
//                    currentTime = currentTime.plusDays(1);
//                } else {
//                    System.out.println("Not enough slots available for the schedule.");
//                    return;
//                }
//            }
//        }
//    }
//
//    private Slot findNextAvailableSlot(LocalDateTime time) {
//        // Check for an available ground and time slot
//        for (Ground ground : grounds) {
//            Slot slot = new Slot(ground, time); // Assuming constructor takes a ground and a time
//            if (!schedule.contains(slot)) { // Implement necessary logic in Slot.equals() for comparison
//                schedule.add(slot);
//                return slot;
//            }
//        }
//        return null; // No slot available
//    }
//
//    public List<Slot> getSchedule() {
//        return schedule;
//    }
//}
//
