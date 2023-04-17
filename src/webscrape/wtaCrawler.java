package webscrape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class wtaCrawler extends UTR {

	private final String url;
	private Document doc;
	private Set<String> set;
	private Elements table;

	public wtaCrawler() throws IOException { // 0 to 100 default
		this.url = "https://live-tennis.eu/en/wta-live-ranking";
		this.doc = Jsoup.connect(url).get();
		// Jsoup.parse(new URL(url).openStream(), "utf-8", url);
		//
		this.table = doc.getElementById("u868").getElementsByTag("tbody");
	}

	public wtaCrawler(int from, int to) throws IOException {
		this.url = "https://live-tennis.eu/en/wta-live-ranking";
		this.doc = Jsoup.connect(url).get();
		this.table = doc.getElementById("u868").getElementsByTag("tbody");
	}

	// catch wtaPlayerList.get(i) error?

	public Set<String> getSet() {
		return this.set;
	}

	public Document getDoc() {
		return this.doc;
	}

	public Elements getTable() {
		return this.table;
	}

	public ArrayList<String> getWTANames() {
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
