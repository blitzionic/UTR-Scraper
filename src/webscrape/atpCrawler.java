package webscrape;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import com.sun.tools.javac.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class atpCrawler extends UTR {

	private final String url;
	private Document doc;
	private Set<String> set;
	private Elements table;
	
	public atpCrawler() throws IOException {
		this.url = "https://live-tennis.eu/en/atp-live-ranking";
		this.doc = Jsoup.connect(url).get();
		this.table = doc.getElementById("t900").getElementsByTag("tbody");
		//System.out.println(table.toString());
	}
	public atpCrawler(int from, int to) throws IOException {
		this.url = "https://live-tennis.eu/en/atp-live-ranking";
		this.doc = Jsoup.connect(url).get();
		this.table = doc.getElementById("t900").getElementsByTag("tbody");
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
	
	// gets the names of players on the ATP website
	public ArrayList<String> getATPNames() {
		ArrayList<String> players = new ArrayList<>();
		for (Element el : table) {
			Elements playerClass = el.getElementsByClass("pn");
			for (Element el1 : playerClass) {
				players.add(unaccent(el1.text()));
			}
		}
		return players;
	}

}