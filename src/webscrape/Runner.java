package webscrape;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) throws IOException {

		// list of top 100 players by default ATP ranking
		// ArrayList<String> playerNames = new ArrayList<>();
		// list of top 100 players by UTR ratings
		// ArrayList<String> playerUTRs = new ArrayList<>();
		// list of players and their UTRs
		ArrayList<Player> atpPlayerList = new ArrayList<>();
		ArrayList<Player> wtaPlayerList = new ArrayList<>();
		ArrayList<String> wtaTemp = new ArrayList<>();

		// ATP
		 //atpCrawler atp = new atpCrawler();
		 //atpPlayerList = atp.getPlayersUTR(atp.getATPNames());
		 /*for (int i = 0; i < atpPlayerList.size(); i++) {
				// names with ? mark what to do
				System.out.println(atpPlayerList.get(i).getName() + "\t" + atpPlayerList.get(i).getUTR() + "\t"
						+ atpPlayerList.get(i).getId());
		 }
		// writeToFile(atpPlayerList);
		 */
		// WTA
		wtaCrawler wta = new wtaCrawler();
		wtaPlayerList = wta.getPlayersUTR(wta.getWTANames());
		// writeToFile(wtaPlayerList);

		// writeToFileNames(wtaTemp);
		/*
		 * for(int i = 0; i < wtaTemp.size(); i++) { //names with ? mark what to do
		 * System.out.println(wtaTemp.get(i));
		 */

	}
	// Crawler.MyGETRequest();
	// atpCrawler atp = new atpCrawler(atpURL);
	// atpPlayerList = atp.getPlayersUTR(atp.getATPNamesWithin());
	// for(Player p : atpPlayerList) {
	// System.out.println(p.getName() + p.getUTR());
	// }

	// Player.sortPlayersByUTR(atpPlayerList, 2);
	// writeToFile(atpPlayerList);
	// wtaCrawler wta = new wtaCrawler(wtaURL);

	// testing purposes below
	/*
	 * for(Player p : playerList) { System.out.println(p.getName() + p.getUTR()); }
	 */

	// System.out.println(Arrays.toString(playerList.toArray()));

	// writeToFile(playerList);

	// System.out.println(Crawler.getRequest(""));

	// writes player names and corresponding UTRs to text file
	public static void writeToFile(ArrayList<Player> playerList) {
		try {
			// File doc = new
			// File("C:\\Users\\andyz\\eclipse-workspace\\ATPWebScraper\\src\\PlayerList.txt");
			FileWriter myWriter = new FileWriter("C:\\Users\\andyz\\eclipse-workspace\\UTR Tool\\src\\PlayerList.txt");

			for (int i = 0; i < playerList.size(); i++) {
				// String toWrite = format()
				myWriter.write(playerList.get(i).getName() + "\t" + playerList.get(i).getUTR() + "\n");
			}
			myWriter.close();
			System.out.println("Players and UTRs have been written successfully.");
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void writeToFileNames(ArrayList<String> playerList) {
		try {
			// File doc = new
			// File("C:\\Users\\andyz\\eclipse-workspace\\ATPWebScraper\\src\\PlayerList.txt");
			FileWriter myWriter = new FileWriter("C:\\Users\\andyz\\eclipse-workspace\\UTR Tool\\src\\PlayerList.txt");

			for (int i = 0; i < playerList.size(); i++) {
				// String toWrite = format()
				myWriter.write(playerList.get(i) + "\n");
			}
			myWriter.close();
			System.out.println("Players' names have been written successfully");
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	//checks if they're any duplicate names in list
	public static boolean isDuplicate(ArrayList<String> players) {
	    for (int i = 0; i < players.size(); i++) {
	        for (int j = i + 1; j < players.size(); j++) {
	            if (players.get(i).trim().equalsIgnoreCase(players.get(j).trim())) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	
}
