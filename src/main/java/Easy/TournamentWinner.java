package Easy;

import java.util.*;

/**
 * There's an algorithms tournament taking place in which teams of programmers
 * compete against each other to solve algorithmic problems as fast as possible.
 * Teams compete in a round-robin, where each team faces off against all other
 * teams. Only two teams compete against each other at a time, and for each
 * competition, one team is designated the home team, while the other team is the
 * away team. In each competition there's always one winner and one loser; there
 * are no ties. A team receives 3 points if it wins and 0 points if it loses. The
 * winner of the tournament is the team that receives the most amount of points.
 *
 * Given an array of pairs representing the teams that have competed against each
 * other and an array containing the results of each competition, write a
 * function that returns the winner of the tournament. The input arrays are named
 * `competitions` and `results`, respectively. The `competitions` array has elements
 * in the form of `[homeTeam, awayTeam]`, where each team is a string of at most 30
 * characters representing the name of the team. The `results` array contains
 * information about the winner of each corresponding competition in the
 * `competitions` array. Specifically, `results[i]` denotes the winner of
 * `competitions[i]`, where a `1` in the `results` array means that the home team
 * in the corresponding competition won and a `0` means that the away team won.
 *
 * It's guaranteed that exactly one team will win the tournament and that each
 * team will compete against all other teams exactly once. It's also guaranteed
 * that the tournament will always have at least two teams.
 *
 * SAMPLE INPUT:
 * competitions = [
 *  ["HTML", "C#"],
 *  ["C#", "Python"],
 *  ["Python", "HTML"],
 * ]
 * results = [0, 0, 1]
 *
 * SAMPLE OUTPUT:
 * "Python"
 * // C# beats HTML, Python Beats C#, and Python Beats HTML.
 * // HTML - 0 points
 * // C# -  3 points
 * // Python -  6 points
 */

public class TournamentWinner {
    public String tournamentWinner(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
        // DESC: Initialize necessary variables
        int competitionArraySize = competitions.size();
        Set<String> competingTeams = new HashSet<String>();
        HashMap<String, Integer> teamScorecard = new HashMap<String, Integer>();
        int highestScore = 0;
        String winningTeam = "";


        // DESC: Get all unique team names
        for (int incrementer = 0; incrementer < competitionArraySize; incrementer++) {
            // NOTE: To work with nested ArrayLists, use `.get()` multiple times
            competingTeams.add(competitions.get(incrementer).get(0));
            competingTeams.add(competitions.get(incrementer).get(1));
        }

        // DESC: Create scorecard (i.e., team name and a zero score)
        for (String teamName : competingTeams) {
            teamScorecard.put(teamName, 0);
        }

        // DESC: Loop through 'competitions' and 'results'
        for (int incrementer = 0; incrementer < competitionArraySize; incrementer++) {

            int homeOrAwayTeam = results.get(incrementer);
            // DESC: Give points to Home-Team (1) or Away-Team (0)
            if (homeOrAwayTeam == 1) {
                int currentScore = teamScorecard.get(competitions.get(incrementer).get(0));
                teamScorecard.put(competitions.get(incrementer).get(0), currentScore + 3);
            } else {
                int currentScore = teamScorecard.get(competitions.get(incrementer).get(1));
                teamScorecard.put(competitions.get(incrementer).get(1), currentScore + 3);
            }
        }

        // DESC: Determine highest score and return
        for (String teamName : teamScorecard.keySet()) {
            if (teamScorecard.get(teamName) > highestScore) {
                winningTeam = teamName;
                highestScore = teamScorecard.get(teamName);
            }
        }

        return winningTeam;
    }
}