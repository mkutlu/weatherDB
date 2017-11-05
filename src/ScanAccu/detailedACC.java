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

public class detailedACC {
	protected String local,region,country,day,weather;
	
	 public static void getWeatherDetails(String url) {
		System.out.println(getCurrentTime());
		try {
			Document doc = Jsoup.connect(url).timeout(10000).validateTLSCertificates(false).get();
			
			//Document doc = Jsoup.connect("https://www.accuweather.com/tr/tr/cankaya/320647/hourly-weather-forecast/320647/").timeout(10000).validateTLSCertificates(false).get();
			Elements els = doc.getElementsByClass("hourly-table overview-hourly");
			//System.out.println(doc.toString());
			System.out.println("Local:"+getLocality(doc));
			System.out.println("Region:"+ getRegion(doc));
			System.out.println("Country:"+getCountry(doc));
			//System.out.println(els.toString());
			
			System.out.println(els.toString().substring(els.toString().indexOf("<span>")+6,els.toString().indexOf("</span>")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(getTodaysName()+"\t");
		
	 }
	 private static String getCurrentTime(){
		
		 String date = new java.util.Date().toString();
		 return date;
	 }
	 private static String getUrlSource(String url) throws IOException {		    
         URL yahoo = new URL(url);
         URLConnection yc = yahoo.openConnection();
         BufferedReader in = new BufferedReader(new InputStreamReader(
                 yc.getInputStream(), "UTF-8"));
         String inputLine;
         StringBuilder a = new StringBuilder();
         while ((inputLine = in.readLine()) != null)
             a.append(inputLine);
         in.close();
         return a.toString();
     }
	 private static String getTodaysName() {
		 Calendar calendar = Calendar.getInstance();
		 Date date = calendar.getTime();
		 return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
	 }
	 private static String getLocality(Document doc) {
		 String local="";
		 Elements elm =  doc.getElementsByClass("vcard");
		 Elements elem2 = elm.select("span.locality");
		 Elements elem = elem2.select("span.value-title");
		 	
		 for (Element span:elem){
			  Element priceDiv = span.parent();
			  local = priceDiv.toString().substring(priceDiv.toString().indexOf("title=")+7,priceDiv.toString().indexOf("\"></span>"));
		}
		 return local;
	 }
	 private static String getRegion(Document doc) {
		 String region="";
		 Elements elm =  doc.getElementsByClass("vcard");
		 Elements elem = elm.select("abbr.region");
		 //Country:<span class="adr"> <span class="locality"> <span class="value-title" title="Çankaya"></span> </span> <abbr class="region" title="Ankara"> <span class="value-title" title="06"></span> </abbr> <abbr class="country-name" title="Türkiye"> <span class="value-title" title="TR"></span> </abbr> </span>

		 for (Element span:elem){
			  Element priceDiv = span.parent();
			  String yedek = priceDiv.toString().substring(priceDiv.toString().indexOf("region")+15);
			  region = yedek.substring(0,yedek.indexOf("<span class")-3);
		}
		 return region;
	 }
	 private static String getCountry(Document doc) {
		 String country="";
		 Elements elm =  doc.getElementsByClass("vcard");
		 Elements elem = elm.select("abbr.region");
		 //Country:<span class="adr"> <span class="locality"> <span class="value-title" title="Çankaya"></span> </span> <abbr class="region" title="Ankara"> <span class="value-title" title="06"></span> </abbr> <abbr class="country-name" title="Türkiye"> <span class="value-title" title="TR"></span> </abbr> </span>

		 for (Element span:elem){
			  Element priceDiv = span.parent();
			  String yedek = priceDiv.toString().substring(priceDiv.toString().indexOf("country")+21);
			  country = yedek.substring(0,yedek.indexOf("<span class")-3);
		}
		 return country;
	 }
}
