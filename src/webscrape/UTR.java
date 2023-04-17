package webscrape;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

//extracting UTRs
public class UTR {
	// no constructor

	public static String getJsonRequest(String url) throws IOException {
		URL urlGetRequest = new URL(url);
		String readLine = null; // current reading line
		HttpURLConnection connection = (HttpURLConnection) urlGetRequest.openConnection();
		connection.setRequestMethod("GET");
		int responseCode = connection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = in.readLine()) != null) {
				response.append(readLine);
			}
			in.close();

			return response.toString();
		} else {
			System.out.println("Reading failed");

		}
		return "Reading failed 2";

	}

	// two parameters: first and last. use if statements to check only first or only
	// CHANGE TO only one string parameter?
	public static String getPlayerAPI(String firstName, String lastName) { // aka json CHECK or overloaded method
		try {
			String json;
			if (lastName == null) {
				json = (getJsonRequest(
						"https://app.universaltennis.com/api/v2/search/players?query=" + firstName + "%20&top=1"));
				return json;
			}
			if (firstName == null) {
				json = (getJsonRequest(
						"https://app.universaltennis.com/api/v2/search/players?query=%20" + lastName + "&top=1"));
				return json;
			}
			json = (getJsonRequest("https://app.universaltennis.com/api/v2/search/players?query=" + firstName + "%20"
					+ lastName + "&top=1"));
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			// when connection has failed
			return "-3";
		}
	}

	// takes an ArrayList and looks up UTR for each element, then returns ArrayList
	// of players and their UTRs
	public ArrayList<Player> getPlayersUTR(ArrayList<String> playerNames) throws IOException {
		ArrayList<Player> playersInfo = new ArrayList<>();

		for (String name : playerNames) {
			try {
				Player pl = new Player(name, searchUTR(name));
				playersInfo.add(pl);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("getplayersutr error");
			}
		}

		return playersInfo;
	}

	public static String decimalFormat(String num) {
		DecimalFormat df = new DecimalFormat("#.00");
		String s = df.format(Double.valueOf(num));
		return s;
	}
	// returns first name with a space after it

	public static String searchUTR(String playerName) throws IOException { // need exceptions for weird names that utr
																			// doesn't match with
		// real name
		int id = Player.findPlayerID(playerName);
		String json = getJsonRequest("https://app.universaltennis.com/api/v1/player/" + id);
		int loc = json.indexOf("singlesUtr\":");
		int loc1 = json.indexOf(",", loc);
		String utr = decimalFormat(json.substring(loc + 12, loc1)); // formats double in string to nearest tenths
		if (Double.valueOf(utr) == 0.0) {
			return "UR/EXPIRED";
		}
		return utr;
	}

	public static String[] splitName(String playerName) {
		String[] newName = new String[2];
		newName[0] = playerName.substring(0, playerName.indexOf(" "));
		if (countSpaces(playerName) > 1) {
			int spaceLoc1 = playerName.indexOf(" ");
			int spaceLoc2 = playerName.indexOf(" ", spaceLoc1 + 1);
			newName[1] = playerName.substring(spaceLoc1 + 1, spaceLoc2);
			return newName;
		}
		newName[1] = playerName.substring(playerName.indexOf(" ") + 1);
		return newName;
	}

	public static int getID(String playerName) {
		//ID can only appear with a vlid player's json
		String[] firstLast = Player.splitName(playerName);
		String json = getPlayerAPI(playerName.substring(0,1), playerName.substring(2));
		int loc = json.indexOf("\"id\":");
		return Integer.parseInt(json.substring(loc + 5, json.indexOf(",", loc)));
		
		
	}
	
	public static int countSpaces(String s) {
		char space = ' ';
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == space) {
				count++;
			}
		}
		return count;
	}

	public static String unaccent(String s) {
		return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

}
