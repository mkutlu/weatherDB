package org.dao;

import java.util.ArrayList;
import java.util.List;

//import org.dao.comparator.CityIdComparator;
import org.hibernate.Session;
import org.hibernate.entity.HibernateUtil;
import org.pojo.City;

public class CityDAOImpl implements CityDAO{

	@Override
	public void addCity(City City) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.save(City);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<City> listCity(String projeNumarasi, String projeVersiyon) {
		// TODO Auto-generated method stub
		List<City> list = new ArrayList<>();
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		list = session.createQuery("from City where id = '"+projeNumarasi +"' and versiyon = '"+projeVersiyon+"'").list();
		session.getTransaction().commit();
		session.close();		
		
		CityIdComparator comparator;
		comparator = new CityIdComparator();
		list.sort(comparator);
		
		return list;
	}
	
	@Override
	public void updateCityUretimIsmi(Integer CityId, String CityUretimIsmi){
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		City City = (City) session.get(City.class, CityId);
		City.setGelistirmeAdim(CityUretimIsmi);

		session.update(City);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public void deleteCity(Integer CityId){
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		City City = (City) session.get(City.class, CityId);
		
		session.delete(City);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public void updateCity(Integer CityId, Double bedel, Double katsayi, Double saat, Double toplam){
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		City City = (City) session.get(City.class, CityId);
		City.setBedel(bedel);
		City.setKatSayi(katsayi);
		City.setSaat(saat);
		City.setToplam(toplam);
		
		session.update(City);
		session.getTransaction().commit();
		session.close();
	}
}
