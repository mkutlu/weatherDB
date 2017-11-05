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

public class getCities {
	public static void cities(String regionCode, String countryCode) {
		try {
			Document doc = Jsoup.connect("https://www.accuweather.com/en/browse-locations/"+regionCode+"/"+countryCode).timeout(10000).validateTLSCertificates(false).get();
			Elements els = doc.getElementsByClass("drilldown cl");			
			for(Element elm : els) {
				String cityCode = elm.toString().substring(elm.toString().indexOf("https://www.accuweather.com/en/browse-locations/"+regionCode+"/"+countryCode)+55,elm.toString().indexOf("https://www.accuweather.com/en/browse-locations/"+regionCode+"/"+countryCode)+58);
				String cityName = elm.toString().substring(elm.toString().indexOf("<em>")+4,elm.toString().indexOf("</em>"));
				System.out.print("\t"+cityCode+"\t");
				System.out.println(cityName);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}
}
