/**
 * COPYRIGHT (C) 2015 archit.imsec10@gmail.com. All Rights Reserved.
 * ******************************************************************
 * @author archit.kapoor
 *
 */


package com.app;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.tournament.*;


public class App {

	public static void main(String[] args) {

		String tournamentName = "ICC World Cup";
		int totalTeams = 10;
		int totVenues = 4;
		
		Date date=null;
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		
		/* Following is a deprecated method/way of initializing a Date object */
		// date = new Date("01/01/2016");
		
		
		// date = (Date) sdf.parse("01/01/2016");
		date = sdf.parse("01/01/2016");
		
		} catch (ParseException e) {
			System.out.println("Error in Parsing Date");
			//	e.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Program aborted !!");
			// return;
		}
		
		Tournament tournament = new Tournament (tournamentName,totalTeams, totVenues, date);

		tournament.addTeam("CSK");
		tournament.addTeam("MI"); //
		tournament.addTeam("SRH"); //
		tournament.addTeam("RCB"); //
		tournament.addTeam("RR"); //
		tournament.addTeam("KKR"); //
		tournament.addTeam("DC"); //
		tournament.addTeam("GT");	//
		tournament.addTeam("XX");	//
		tournament.addTeam("ZZ");	//

//		{ CSK - MI, CSK - SRH, CSK - RCB, CSK - RR, CSK - KKR, CSK - DC, CSK - GT, MI - SRH, MI - RCB, MI - RR,
//		MI - KKR, MI - DC, MI - GT, SRH - RCB, SRH - RR, SRH - KKR, SRH - DC, SRH - GT, RCB - RR, RCB - KKR,
//		RCB - DC, RCB - GT, RR - KKR, RR - DC, RR - GT, KKR - DC, KKR - GT, DC - GT }
		


		int numberOfSlotsPerGround = 2;

		tournament.addVenue("Chennai");
		tournament.addVenue("Mumbai");
		tournament.addVenue("Hyderabad");
		tournament.addVenue("Bangalore");


		
		int totRounds = 1;
		tournament.setNumberOfRounds(totRounds);

		
		ArrayList<Match> matches= tournament.getMatches(numberOfSlotsPerGround);
		
		System.out.println("\n****\t" + tournament.getTournamentName() + "\t****\n");
		System.out.print("Tournament beginning date: ");

		if(date!=null)
			System.out.println(MyDateParser.toString(date));
		System.out.println("Totoal matches: " + matches.size());
		System.out.println();
		
		for(Match m : matches){
			System.out.println(m.toString());
		}
		//System.out.println(tournament.getWinner());
	}


}

		// Functionality of => Maximum number of matches per day is yet to be implemented.
		// Functionality of => adding points per match yet to be implemented.
		// Functionality of => Semi final and Quarter final yet to be added in the schedule
		// Functionality of => Last played match and minimum 1/2 day gap between matches yet to be implemented.
		// JUnit test cases of every class of this application.
		// DateBase design and Database connection yet to be added to this application.


/**
 *  			3 CSk MI RCB GT
 *  		CSK v MI	CSK vRCB	MI v RCB
 *  		G1 - 2 G2 - 2	4
 *  		CSK v MI G1 S1 Sun
 *  		CSK v RCB G1 S2 Mon
 *
 *  		G1 S1 G2 S1 G1 s2 G2 S2
 *
 */
