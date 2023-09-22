package com.jspiders.cardekho_case_study_springmvc.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspiders.cardekho_case_study_springmvc.pojo.CarPOJO;

@Repository
public class CarRepository {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Query query;
	
	private static void openConnection() {
		factory = Persistence.createEntityManagerFactory("car_mvc");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	}
	
	private static void closeConnection() {
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction != null) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	public CarPOJO addCar(String name, String model, String brand, String fuel_type, double price) {
		openConnection();
		transaction.begin();
		
		CarPOJO pojo = new CarPOJO();
		pojo.setName(name);
		pojo.setModel(model);
		pojo.setBrand(brand);
		pojo.setFuel_type(fuel_type);
		pojo.setPrice(price);
		
		manager.persist(pojo);
		transaction.commit();
		closeConnection();
		return pojo;
	}

	public List<CarPOJO> findAllCar() {
		openConnection();
		transaction.begin();
		String jpql="from CarPOJO";
		query = manager.createQuery(jpql);
		List<CarPOJO> cars=query.getResultList();
		transaction.commit();
		closeConnection();
		return cars;
	}

	public CarPOJO searchAdd(int car_id) {
		openConnection();
		transaction.begin();
		CarPOJO pojo = manager.find(CarPOJO.class, car_id);
		transaction.commit();
		closeConnection();
		return pojo;
	}

	public CarPOJO removeCar(int car_id) {
		openConnection();
		transaction.begin();
		CarPOJO pojo = manager.find(CarPOJO.class, car_id);
		if (pojo != null) {
			manager.remove(pojo);
		}
		transaction.commit();
		closeConnection();
		return pojo;
	}

	public CarPOJO updateCar(int car_id,String name, String model, String brand, String fuel_Type, double price) {
		openConnection();
		transaction.begin();
		CarPOJO pojo = manager.find(CarPOJO.class, car_id);
		pojo.setName(name);
		pojo.setModel(model);
		pojo.setBrand(brand);
		pojo.setFuel_type(fuel_Type);
		pojo.setPrice(price);
		
		manager.persist(pojo);
		
		transaction.commit();
		closeConnection();
		return pojo;
	}



}

	

