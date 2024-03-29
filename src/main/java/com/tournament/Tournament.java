/**
 * COPYRIGHT (C) 2015 archit.imsec10@gmail.com. All Rights Reserved.
 * ******************************************************************
 * @author archit.kapoor
 *
 */

package com.tournament;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Tournament {

	private String tournamentName;
	private HashSet<Team> tournamentTeams;
	private HashSet<Ground> tournamentVenues;
	private ArrayList<Match> matches;
	private int totalNumberOfTeams;
	private int totalNumberOfVenues;
	private int numberOfRounds;
	private Date tournamentBeginDate;
	private Team winner;
	
	public Tournament(String name, int totNoTeams, int totNoVenues, Date date)
	{
		if(name!=null)
			this.tournamentName = name;
		
		if(totNoTeams>0)
		{
			this.totalNumberOfTeams = totNoTeams;
			this.tournamentTeams = new HashSet<Team>(this.totalNumberOfTeams);
		}
		else
		{
			this.totalNumberOfTeams = 0;
			this.tournamentTeams = new HashSet<Team>();
		}
		
		if(totNoVenues>0)
		{
			this.totalNumberOfVenues = totNoVenues;
			this.tournamentVenues = new HashSet<Ground>(this.totalNumberOfVenues);
		}
		else
		{
			this.totalNumberOfVenues = 0;
			this.tournamentVenues = new HashSet<Ground>();
		}
		
		this.matches = new ArrayList<Match>();
		this.numberOfRounds = 0;
		this.tournamentBeginDate = date;
		this.winner = null;
	}


	
	public Date getTournamentBeginDate() {
		return tournamentBeginDate;
	}

	public void setTournamentBeginDate(Date tournamentBeginDate) {
		this.tournamentBeginDate = tournamentBeginDate;
	}

	public int getTotalNumberOfTeams() {
		if(tournamentTeams!= null && this.totalNumberOfTeams != tournamentTeams.size())
			this.totalNumberOfTeams = tournamentTeams.size();
		
		return totalNumberOfTeams;
	}

	public void setTotalNumberOfTeams(int totalNumberOfTeams) {
		if(totalNumberOfTeams>=0)
		{
			this.totalNumberOfTeams = totalNumberOfTeams;
		}
	}

	public int getTotalNumberOfVenues() {
		if(tournamentVenues!=null && this.totalNumberOfVenues != tournamentVenues.size())
			this.totalNumberOfVenues = tournamentVenues.size();
		return this.totalNumberOfVenues;
	}

	public void setTotalNumberOfVenues(int totalNumberOfVenues) {
		if(totalNumberOfVenues>=0)
		{
			this.totalNumberOfVenues = totalNumberOfVenues;
		}
	}

	public String getTournamentName() {
		return tournamentName;
	}
	
	public void setTournamentName(String name) {
		if(name != null)
		{
			this.tournamentName = name;
		}
	}
	
	// Cannot keep the method getTournamentTeams() public because Team class is accessible only within the package, not outside the package.
	//	public HashSet<Team> getTournamentTeams() {
	HashSet<Team> getTournamentTeams() {
			return tournamentTeams;
	}

	private void setTournamentTeams(HashSet<Team> tournamentTeams)  // Hidden Method
	{
		this.tournamentTeams = tournamentTeams;
	}
	
	public void setTournamentTeams(ArrayList<String> tournamentTeamNames)  // Exposed Method
	{
		HashSet<Team> tournamentTeamSet = new HashSet<Team>();
		
		for(String teamName: tournamentTeamNames)
		{
			tournamentTeamSet.add(new Team(teamName));
		}
		this.setTournamentTeams(tournamentTeamSet);
	}
	

	// Cannot keep the method getTournamentVenues() public because Venue class is accessible only within the package, not outside the package.
	// public HashSet<Venue> getTournamentVenues() {
	HashSet<Ground> getTournamentVenues() {
		return tournamentVenues;
	}

	private void setTournamentVenues(HashSet<Ground> tournamentVenues)	// Hidden Method
	{
		this.tournamentVenues = tournamentVenues;
	}
	
	public void setTournamentVenues(ArrayList<String> tournamentVenueNames)	// Exposed Method
	{
		HashSet<Ground> tournamentVenueSet = new HashSet<Ground>();
		
		for(String venueName: tournamentVenueNames)
		{
			Ground venue = new Ground();
			venue.setGroundName(venueName);
			tournamentVenueSet.add(venue);
		}
		this.setTournamentVenues(tournamentVenueSet);
	}
	
	public int getNumberOfRounds() {
		return numberOfRounds;
	}

	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}

	private void addTeam(Team team)  // Hidden Method
	{
		if(this.tournamentTeams.size()==this.totalNumberOfTeams)
			this.totalNumberOfTeams = this.totalNumberOfTeams+1;
		
		this.tournamentTeams.add(team);
	}
	
	public void addTeam(String teamName)  // Exposed Method
	{
		this.addTeam(new Team(teamName));
	}
	
	private boolean removeTeam(Team team)  // Hidden Method
	{
		if(team!=null)
		{
			if(this.tournamentTeams.contains(team)==true)
			{
				this.tournamentTeams.remove(team);
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean removeTeam(String teamName)  // Exposed Method
	{
		return this.removeTeam(new Team(teamName));
	}
	
	
	private void addVenue(Ground venue)  // Hidden Method
	{
		if(this.tournamentVenues.size()==this.totalNumberOfVenues)
			this.totalNumberOfVenues = this.totalNumberOfVenues+1;
		
		this.tournamentVenues.add(venue);
	}

	public Ground getVenue(String venueName)  // Exposed Method
	{
		for(Ground venue: this.tournamentVenues)
		{
			if(venue.getGroundName().equals(venueName))
			{
				return venue;
			}
		}
		return null;
	}
	
	public void addVenue(String venueName)  // Exposed Method
	{
		Ground ground = new Ground();
		ground.setGroundName(venueName);
		this.addVenue(ground);
	}
	
	private boolean removeVenue(Ground venue)  // Hidden Method
	{
		if(venue!=null)
		{
			if(this.tournamentVenues.contains(venue)==true)
			{
				this.tournamentVenues.remove(venue);
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}


	
	public ArrayList<Match> getMatches(int numberOfSlotsPerGround)
	{
		RoundRobinScheduler rrs = new RoundRobinScheduler();
		this.matches = rrs.createMatches(this);
		this.matches = rrs.createSchedule(this, this.matches, numberOfSlotsPerGround);
		return matches;
	}
	
	private Team findWinner()
	{
		ArrayList<Team> tournamentTeamList = new ArrayList<Team>(this.tournamentTeams); 
		Collections.sort(tournamentTeamList, new Comparator<Team>(){	
			@Override
			public int compare(Team team1, Team team2) {
				return (team1.getPoints() - team2.getPoints());
			}
			
		});
		return tournamentTeamList.get(0);
	}
	
	public String getWinner()
	{
		winner = this.findWinner();
		
		return winner.getTeamName();
	}
	
}