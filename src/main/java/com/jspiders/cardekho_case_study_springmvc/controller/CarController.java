package com.jspiders.cardekho_case_study_springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.jspiders.cardekho_case_study_springmvc.pojo.AdminPOJO;
import com.jspiders.cardekho_case_study_springmvc.pojo.CarPOJO;
import com.jspiders.cardekho_case_study_springmvc.service.CarService;

@Controller
public class CarController {
	@Autowired
	   private CarService service;
	   
	   //Home page Controller
	   @GetMapping("/home")
	   public String home(@SessionAttribute(name = "login", required = false)AdminPOJO admin,
			               ModelMap map) {
		   if (admin != null) {
				return "Home";
			}
			map.addAttribute("msg", "Session inactive. Login to proceed..!");
			return "Login";
	   }
	   
	  //Add page Controller
	 	@GetMapping("/add")
	 	public String addPage(@SessionAttribute(name = "login", required = false)AdminPOJO admin,
	 			               ModelMap map) {
	 		if (admin != null) {
	 		List<CarPOJO> cars = service.findAllCars();
	 		if (!cars.isEmpty()) {
	 			map.addAttribute("cars",cars);
	 			return "AddCar";
			}
	 		
	 		return "AddCar";
	 		}
			map.addAttribute("msg", "Session inactive. Login to proceed..!");
			return "Login";
	 	}
	 	
	 	@PostMapping("/add")    
	 	public String addCar(@SessionAttribute(name = "login", required = false)AdminPOJO admin,
	 			             @RequestParam String name,
	 			             @RequestParam String model,
	 			             @RequestParam String brand,
	 			             @RequestParam String fuel_type,
	 			             @RequestParam double price,
	 			             ModelMap map)
	 	{
	 		if (admin != null) 
	 		{
	 		CarPOJO pojo = service.addCar(name,model,brand,fuel_type,price);
	 		//success
	 		if(pojo != null) {
	 			map.addAttribute("msg","Data Inserted Successfully..!");
	 			List<CarPOJO> cars = service.findAllCars();
	 			map.addAttribute("cars",cars);
	 		  return "AddCar";
	 		} 
	 		//failure
	 		map.addAttribute("msg","Data not inserted..!");
	 		List<CarPOJO> cars = service.findAllCars();
	 		if (!cars.isEmpty()) {
	 			map.addAttribute("cars",cars);
			}
	 		return "AddCar";
	 		}
			map.addAttribute("msg", "Session inactive. Login to proceed..!");
			return "Login";
	 	}
	 	
	 	//Search page Controller
		@GetMapping("/search")
		public String searchPage(@SessionAttribute(name = "login", required = false)AdminPOJO admin,ModelMap map) {
			if (admin != null) {
			return "SearchCar";
			}
			map.addAttribute("msg", "Session inactive. Login to proceed..!");
			return "Login";
		}
		
		@PostMapping("/search")
		public String searchCar(@SessionAttribute(name = "login", required = false)AdminPOJO admin,
				                @RequestParam int car_id,ModelMap map) {
			if (admin != null) {
			CarPOJO pojo = service.searchCar(car_id);
			//Success
			if (pojo != null) {
				map.addAttribute("car",pojo);
				map.addAttribute("msg","Car data found..!");
				return "SearchCar";
			}
			//Failure
			map.addAttribute("msg", "Car data not found..!");
			return "SearchCar";
			}
			map.addAttribute("msg", "Session inactive. Login to proceed..!");
			return "Login";
		}
		
		//Remove page Controller
		@GetMapping("/remove")
		public String removePage(@SessionAttribute(name = "login", required = false)AdminPOJO admin,
				                                ModelMap map) {
			if (admin != null) {
			List<CarPOJO> cars = service.findAllCars();
			if (!cars.isEmpty()) {
				map.addAttribute("cars",cars);
			   return "RemoveCar";
			}
			map.addAttribute("msg", "No data present..!");
			return "RemoveCar";
		}
		map.addAttribute("msg", "Session inactive. Login to proceed..!");
		return "Login";
		}
		
		//Remove Car Controller
		@PostMapping("/remove")
		public String removeCar(@SessionAttribute(name = "login", required = false)AdminPOJO admin,
				                @RequestParam int car_id,ModelMap map) {
			if (admin != null) {
			CarPOJO pojo = service.removeCar(car_id);
			List<CarPOJO> cars = service.findAllCars();
			//Success
			if (pojo != null) {
				map.addAttribute("msg", "Data removed successfully..!");
				map.addAttribute("cars", cars);
				return "RemoveCar";
			}
			//Failure
			map.addAttribute("msg", "Data does not exist..!");
			map.addAttribute("cars", cars);
			return "RemoveCar";
			}
			map.addAttribute("msg", "Session inactive. Login to proceed..!");
			return "Login";
		}
		
		@GetMapping("/update")
		public String updatePage(@SessionAttribute(name = "login", required = false)AdminPOJO admin,
				                 ModelMap map) {
			if (admin != null) {
			List<CarPOJO> cars = service.findAllCars();
			map.addAttribute("cars", cars);
			return "UpdateCar";
			}
			map.addAttribute("msg", "Session inactive. Login to proceed..!");
			return "Login";
		}
		
		@PostMapping("/update")
		public String updateFrom(@SessionAttribute(name = "login", required = false)AdminPOJO admin,
				                 @RequestParam int car_id,ModelMap map) {
			 if(admin != null) {
			CarPOJO pojo = service.searchCar(car_id);
			//Success
			if (pojo != null) {
				map.addAttribute("car",pojo);
				return "UpdateCar";
			}
			//Failure
			List<CarPOJO> cars = service.findAllCars();
			map.addAttribute("cars", cars);
			map.addAttribute("msg", "Cars data not found..!");
			return "UpdateCar";
			 }
			  map.addAttribute("msg", "Session inactive. Login to proceed..!");
				return "Login";
		}
		
		//Update Car Controller
		@PostMapping("/updateCar")
		public String updateCar(@SessionAttribute(name = "login", required = false)AdminPOJO admin,
				                @RequestParam int car_id,
				                @RequestParam String name,
	                            @RequestParam String model,
	                            @RequestParam String brand,
	                            @RequestParam String fuel_type,
	                            @RequestParam double price,
	                            ModelMap map) {
			if (admin != null) {
			CarPOJO pojo = service.updateCar(car_id,name,model,brand,fuel_type,price);
			//Success
			if (pojo != null) {
				List<CarPOJO> cars = service.findAllCars();
				map.addAttribute("msg", "Data updated successfully..!");
				map.addAttribute("cars", cars);
				return "UpdateCar";
			}
			List<CarPOJO> cars = service.findAllCars();
			map.addAttribute("msg", "Data not updated..!");
			map.addAttribute("cars", cars);
			return "UpdateCar";
		}
		map.addAttribute("msg", "Session inactive. Login to proceed..!");
		return "Login";
}

}