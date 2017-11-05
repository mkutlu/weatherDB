package ScanAccu;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getDistrict {
	public static void districts(String regionCode, String countryCode,String cityCode) {
	Document doc;
	try {
		doc = Jsoup.connect("https://www.accuweather.com/en/browse-locations/"+regionCode+"/"+countryCode+"/"+cityCode).timeout(10000).validateTLSCertificates(false).get();
		Elements els = doc.getElementsByClass("drilldown cl");			
		for(Element elm : els) {
			String districtName = elm.toString().substring(elm.toString().indexOf("<em>")+4,elm.toString().indexOf("</em>"));
			String districtID = elm.toString().substring(elm.toString().indexOf("forecast/")+55,elm.toString().indexOf("https://www.accuweather.com/en/browse-locations/"+regionCode+"/"+countryCode)+58);
			
		
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}
