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
					match.setTeams(teamsList.get(i), teamsList.get(j));
					matches.add(match);
				}
			}
		}
		Collections.shuffle(matches);
		return matches;

	}
	
	
//	public ArrayList<Match> createSchedule(Tournament tournament, ArrayList<Match> matches)
//	{
//		ArrayList<Match> scheduledMatches = new ArrayList<Match>();
//		ArrayList<Venue> venueList = new ArrayList<Venue>(tournament.getTournamentVenues());
//		Date date = tournament.getTournamentBeginDate();
//		int matchIndex=0;
//		int venueIndex=0;
//		int totVenues = tournament.getTotalNumberOfVenues();
//		int numberOfRounds = tournament.getNumberOfRounds();
//		int totMatches = 	numberOfRounds*((tournament.getTotalNumberOfTeams()*(tournament.getTotalNumberOfTeams()-1))/2);
//		int[] trackMatches = new int[totMatches];
//		int indexOfTracking=0;
//
//
//		while(totMatches>0)
//		{
//			Match m=matches.get(matchIndex);
//			if(m.isPlayed()==false)
//			{
//				if(m.getTeam1().isNotPlaying() && m.getTeam2().isNotPlaying())
//				{
//					if(venueList.get(venueIndex%totVenues).isOccupied() == false && m.getVenue()==null)
//					{
//						matches.get(matchIndex).setVenue(venueList.get(venueIndex%totVenues));
//						venueList.get(venueIndex%totVenues).setOccupied(true);
//						matches.get(matchIndex).setPlayed(true);
//						matches.get(matchIndex).setMatchDate(date);
//						matches.get(matchIndex).getTeam1().setNotPlaying(false);
//						matches.get(matchIndex).getTeam2().setNotPlaying(false);
//						scheduledMatches.add(matches.get(matchIndex));
//						venueIndex = venueIndex + 1;
//						totMatches= totMatches - 1;
//						trackMatches[indexOfTracking] = matchIndex;
//						indexOfTracking = indexOfTracking + 1;
//
//						if((venueIndex%totVenues) == 0)
//						{
//						   date = dateIncrementer(date,1).getTime();
//
//						    for(Venue ven: venueList)
//						    {
//						    	ven.setOccupied(false);
//						    }
//						}
//					}
//				}
//			}
//			matchIndex = matchIndex+ 1;
//
//			if(matchIndex == matches.size()){
//				matchIndex = 0;
//				date = dateIncrementer(date,1).getTime();
//
//			    for(int trackedIndexes : trackMatches)
//			    {
//			    	matches.get(trackedIndexes).getTeam1().setNotPlaying(true);
//			    	matches.get(trackedIndexes).getTeam2().setNotPlaying(true);
//
//			    }
//			    trackMatches = new int[totMatches];
//				indexOfTracking=0;
//
//			}
//		}
//
//		return scheduledMatches;
//	}


	public boolean isPlayable(Match match, Set<String> teamsPlayingToday)
	{
        return !match.isPlayed() && !teamsPlayingToday.contains(match.getTeam1().getTeamName()) && !teamsPlayingToday.contains(match.getTeam2().getTeamName());
    }
	public ArrayList<Match> createSchedule(Tournament tournament, ArrayList<Match> matches, int numberOfSlotsPerGround) {
		ArrayList<Match> scheduledMatches = new ArrayList<>();
		ArrayList<Venue> venueList = new ArrayList<>(tournament.getTournamentVenues());
		Date date = tournament.getTournamentBeginDate();
		int matchIndex = 0;
		int totalMatches = tournament.getNumberOfRounds() * ((tournament.getTotalNumberOfTeams() * (tournament.getTotalNumberOfTeams() - 1)) / 2);
		Set<String> teamsPlayingToday = new HashSet<>();
		String[] slots = getSlots(numberOfSlotsPerGround, venueList);

		int slotIndex = 0;
		int prevScheduled = -1;
		while (totalMatches > 0) {
			Match m = matches.get(matchIndex);
			if (isPlayable(m, teamsPlayingToday)) {
						m.setVenue(tournament.getVenue(slots[slotIndex].split("#")[0]));
						m.setPlayed(true);
						m.setMatchDate(date);
						m.setSlotInfo(slots[slotIndex].split("#")[1]);
						scheduledMatches.add(m);
						teamsPlayingToday.add(m.getTeam1().getTeamName());
						teamsPlayingToday.add(m.getTeam2().getTeamName());
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
		return scheduledMatches;
	}

	// G1 G2

	private String[] getSlots(int numberOfSlotsPerGround, ArrayList<Venue> venueList) {
		String[] slots = new String[numberOfSlotsPerGround * venueList.size()];
		int count = 1;
		for (int i = 0; i < slots.length;) {
			for(int j = 0; j < venueList.size(); j++) {
				slots[i++] = venueList.get(j).getVenueName() + "#" + "slot" + count;
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
