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

public class getCountries {
	public static void countries(String regionCode) {
		try {
			Document doc = Jsoup.connect("https://www.accuweather.com/en/browse-locations/"+regionCode).timeout(10000).validateTLSCertificates(false).get();
			Elements els = doc.getElementsByClass("drilldown cl");			
			for(Element elm : els) {
				String countryCode = elm.toString().substring(elm.toString().indexOf("https://www.accuweather.com/en/browse-locations/"+regionCode)+52,elm.toString().indexOf("https://www.accuweather.com/en/browse-locations/"+regionCode)+54);
				String countryName = elm.toString().substring(elm.toString().indexOf("<em>")+4,elm.toString().indexOf("</em>"));
				System.out.print("-"+countryCode+"\t");
				System.out.println(countryName);
				getCities cities = new getCities();
				cities.cities(regionCode,countryCode);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}
}
