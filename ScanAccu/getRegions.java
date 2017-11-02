package ScanAccu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getRegions {
	public static void main(String[] args) {	
		try {
			Document doc = Jsoup.connect("https://www.accuweather.com/en/browse-locations/").timeout(10000).validateTLSCertificates(false).get();
			Elements els = doc.getElementsByClass("link-region");
			for(Element elm : els) {
				Element regionLine = elm.parent();
				String regionCode = regionLine.toString().substring(regionLine.toString().indexOf("#region")+8,regionLine.toString().indexOf("#region")+11);
				String regionName = regionLine.toString().substring(regionLine.toString().indexOf("#region")+13,regionLine.toString().indexOf("</a>"));
				getCountries country = new getCountries();
				getCountries.countries(regionCode);
				System.out.print(regionCode+"\t");
				System.out.println(regionName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
