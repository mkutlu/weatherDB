package org.dao;

import java.util.List;

import org.pojo.City;

public interface CityDAO {
	
	public void addCity(City City);
	public List<City> listCity(String projeNumarasi, String projeVersiyon);
	public void updateCityUretimIsmi(Integer CityId, String CityUretimIsmi);
	public void deleteCity(Integer CityId);
	public void updateCity(Integer CityId, Double bedel, Double katsayi, Double saat, Double toplam);
}
