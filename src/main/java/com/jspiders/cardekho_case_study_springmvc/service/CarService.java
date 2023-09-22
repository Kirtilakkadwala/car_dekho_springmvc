package com.jspiders.cardekho_case_study_springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.cardekho_case_study_springmvc.pojo.CarPOJO;
import com.jspiders.cardekho_case_study_springmvc.repository.CarRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository repository;

	public CarPOJO addCar(String name, String model, String brand, String fuel_type, double price) {
		CarPOJO pojo = repository.addCar(name,model,brand,fuel_type,price);
		return pojo;
	}

	public List<CarPOJO> findAllCars() {
		List<CarPOJO> cars = repository.findAllCar();
		return cars;
	}

	public CarPOJO searchCar(int car_id) {
		CarPOJO pojo = repository.searchAdd(car_id);
		return pojo;
	}

	public CarPOJO removeCar(int car_id) {
		CarPOJO pojo = repository.removeCar(car_id);
		return pojo;
	}

	public CarPOJO updateCar(int car_id,String name, String model, String brand, String fuel_type, double price) {
		CarPOJO pojo = repository.updateCar(car_id,name,model,brand,fuel_type,price);
		return pojo;
	}

	
   
	

}
