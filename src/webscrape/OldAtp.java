package webscrape;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import com.sun.tools.javac.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class OldAtp extends UTR {

	private final String url;
	private Document doc;
	private Set<String> set;
	private Elements table;
	
	public OldAtp() throws IOException { //date in url???

		this.url = "https://www.atptour.com/en/rankings/singles/?rankDate=2021-6-28"
				+ "&countryCode=all&rankRange=0-100";
		this.doc = Jsoup.connect(url).get();
		this.table = doc.getElementById("rankingDetailAjaxContainer").getElementsByTag("tbody");
		//System.out.println(table.toString());
	}
	public OldAtp(int from, int to) throws IOException {
		this.url = "https://www.atptour.com/en/rankings/singles/?rankDate=2021-6-28"
				+ "&countryCode=all&rankRange=" + from + "-" + to;
		this.doc = Jsoup.connect(url).get();
		this.table = doc.getElementById("rankingDetailAjaxContainer").getElementsByTag("tbody");
	}

	public Set<String> getSet() {
		return this.set;
	}

	public Document getDoc() {
		return this.doc;
	}

	public Elements getTable() {
		return this.table;
	}

	public void getDate() {
		
	}
	
	// gets the names of players on the ATP website
	public ArrayList<String> getATPNames() {
		ArrayList<String> players = new ArrayList<String>();

		for (Element el : table) {
			Elements playerCell = el.getElementsByClass("player-cell");
			for (Element el1 : playerCell) {
				Elements playerWrapper = el1.getElementsByAttribute("href");
				for (Element el2 : playerWrapper) {
					players.add(el2.text());
				}
			}
		}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isEmpty()) {
				players.remove(i);
			}
		}
		return players;
	}

}