package webscrape;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Player extends UTR {

	private String name;
	private String UTR;
	private int id;

	// name is created when players are scanned
	public Player(String name, String UTR) throws IOException {
		this.name = searchNameInFile(name);
		System.out.println(this.name);
		this.UTR = UTR;
		this.id = findPlayerID(name);
	}

	public String getName() {
		return this.name;
	}

	public String getUTR() {
		return this.UTR;
	}

	public long getId() {
		return this.id;
	}

	// initialize id
	// should findplayersID also update names to correct ones from text file?

	public static int findPlayerID(String playerName) throws FileNotFoundException { // intended to work with any name
		// //change to non static
		// need exceptions for weird names that utr doesn't match with real name
		// //order: if name not in text file, lookup api for id
		// should also update name to real name in file
		int id = scanForID(playerName);
		if (id == -1) {
			return getId(playerName);
		}
		return id;
	}

	public static int getId(String playerName) {// returns player ID
		String[] firstLast = splitName(playerName);
		String json = getPlayerAPI(firstLast[0], firstLast[1]);
		int loc = json.indexOf("\"id\":");
		return Integer.parseInt(json.substring(loc + 5, json.indexOf(",", loc)));
	}

	public static ArrayList<Player> sortPlayersByUTR(ArrayList<Player> players, int choice) throws IOException {

		Collections.sort(players, new Comparator<Player>() {
			@Override
			public int compare(Player player1, Player player2) {
				return Double.compare(Double.valueOf(player1.getUTR()), Double.valueOf(player2.getUTR()));
			}
		});
		if (choice == 1) {
			return players;
		} else if (choice == 2) {
			Collections.reverse(players);
		}
		players.set(0, new Player("Sort choice incorrect", "UR"));
		return players;
	}

	public static int scanForID(String playerName) throws FileNotFoundException { // searches and returns id of play if
																					// he is
																					// in text file, else if not found
																					// return -1
		Scanner sc = new Scanner(
				new File("C:\\Users\\andyz\\eclipse-workspace\\UTR Tool\\src\\Unreachable Players.txt"));
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.contains(playerName)) {
				return Integer.parseInt(line.substring(line.indexOf("ID:") + 3));
			}
		}
		return -1; // if name is not found in file
	}

	public static String searchNameInFile(String playerName) throws FileNotFoundException { // searches and returns id of
																						// play if
		// he is
		// in text file, else if not found
		// return -1
		Scanner sc = new Scanner(
				new File("C:\\Users\\andyz\\eclipse-workspace\\UTR Tool\\src\\Unreachable Players.txt"));
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.contains(playerName)) {
				return line.substring(line.indexOf("Actual:") + 8, line.indexOf("ID:") - 1);
			}
		}
		return playerName; // if name is not found in file
	}

// some ideas: last 12 months
	public static double highestUTRBeat() {
		double beat = 0.0;

		return beat;
	}
// method for min utr()
}
