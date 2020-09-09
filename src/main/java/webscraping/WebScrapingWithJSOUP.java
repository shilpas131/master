package webscraping;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScrapingWithJSOUP {

	static String documentURL = null;
	
	public WebScrapingWithJSOUP(String string) {
		documentURL = string;
	}

	public String getParsedDataFromDocument() {
		Document document = null;
		try {
			document = Jsoup.connect(documentURL).get();
			Element body = document.body();
		    Elements paragraphs = document.select("div.article").select("p");
			return paragraphs.html();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
}
