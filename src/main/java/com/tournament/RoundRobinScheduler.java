/**
 * COPYRIGHT (C) 2015 archit.imsec10@gmail.com. All Rights Reserved.
 * ******************************************************************
 * @author archit.kapoor
 *
 */

package com.tournament;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.*;

class RoundRobinScheduler {

	
	public RoundRobinScheduler(){
	}
	
	public ArrayList<Match> createMatches(Tournament tournament)
	{
		ArrayList<Match> matches = new ArrayList<Match>();
		
		int numberOfRounds = tournament.getNumberOfRounds();
		ArrayList<Team> teamsList = new ArrayList<Team>(tournament.getTournamentTeams());
		
		int totTeams = tournament.getTotalNumberOfTeams();
		
		for(int k = 1; k<=numberOfRounds; k++)
		{
			
			for(int i = 0; i < totTeams-1; i++)
			{
				
				for(int j = i+1; j < totTeams; j++)
				{
					Match match = new Match();
					match.setTeamOneSnapshot(teamsList.get(i));
					match.setTeamTwoSnapshot(teamsList.get(j));
					matches.add(match);
				}
			}
		}
		Collections.shuffle(matches);
		return matches;

	}


	public boolean isPlayable(Match match, Set<Match> isMatchPlayed, Set<String> teamsPlayingToday)
	{
        return !isMatchPlayed.contains(match) && !teamsPlayingToday.contains(match.getTeamOneSnapshot().getTeamName()) && !teamsPlayingToday.contains(match.getTeamTwoSnapshot().getTeamName());
    }
	public ArrayList<Match> createSchedule(Tournament tournament, ArrayList<Match> matches, int numberOfSlotsPerGround) {
		ArrayList<Match> scheduledMatches = new ArrayList<>();
		ArrayList<Ground> venueList = new ArrayList<>(tournament.getTournamentVenues());
		Date date = tournament.getTournamentBeginDate();
		int matchIndex = 0;
		int totalMatches = tournament.getNumberOfRounds() * ((tournament.getTotalNumberOfTeams() * (tournament.getTotalNumberOfTeams() - 1)) / 2);
		Set<String> teamsPlayingToday = new HashSet<>();
		String[] slots = getSlots(numberOfSlotsPerGround, venueList);
		Set<Match> isMatchPlayed = new HashSet<>();

		int slotIndex = 0;
		int prevScheduled = -1;
		while (totalMatches > 0) {
			Match m = matches.get(matchIndex);
			if (isPlayable(m, isMatchPlayed, teamsPlayingToday)) {
						m.setGround(tournament.getVenue(slots[slotIndex].split("#")[0]));
						isMatchPlayed.add(m);
						m.setStartDate(date);
						m.setSlotInfo(slots[slotIndex].split("#")[1]);
						scheduledMatches.add(m);
						teamsPlayingToday.add(m.getTeamOneSnapshot().getTeamName());
						teamsPlayingToday.add(m.getTeamTwoSnapshot().getTeamName());
						totalMatches--;
						slotIndex++;
						prevScheduled = matchIndex;
			}
			matchIndex = (matchIndex + 1) % matches.size();

			if(prevScheduled == matchIndex) {
				date = dateIncrementer(date, 1).getTime();
				teamsPlayingToday.clear();
				if(slotIndex > slots.length / 2){
					slotIndex = 0;
				}
			} else if(slotIndex == slots.length) {
				slotIndex = 0;
				date = dateIncrementer(date, 1).getTime();
				teamsPlayingToday.clear();
			}
		}

		System.out.println("Total Matches played "+ isMatchPlayed.size());
		return scheduledMatches;
	}

	// G1 G2

	private String[] getSlots(int numberOfSlotsPerGround, ArrayList<Ground> venueList) {
		String[] slots = new String[numberOfSlotsPerGround * venueList.size()];
		int count = 1;
		for (int i = 0; i < slots.length;) {
			for(int j = 0; j < venueList.size(); j++) {
				slots[i++] = venueList.get(j).getGroundName() + "#" + "slot" + count;
			}
			count++;
		}
		return slots;
	}

	private Calendar dateIncrementer(Date currentDate, int numberOfDaysToIncrement)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.DATE, numberOfDaysToIncrement);  // number of days to add
		return cal;
	}
}
