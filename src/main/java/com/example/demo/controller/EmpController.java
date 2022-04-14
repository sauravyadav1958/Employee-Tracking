package com.example.demo.controller;

import java.time.LocalDateTime;


import com.example.demo.response.responseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmpRepo;
import com.example.demo.service.EmpService;


@RestController
public class EmpController {
	

	@Autowired
	private EmpRepo empRepo;

	@Autowired
	private EmpService empservice;

	@Autowired
	private responseDetails responseDetailsObj;

	@GetMapping("/employees")
	public ResponseEntity<?> listUsers()
	{
		return empservice.getAllEmp();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmpById(@PathVariable int id)
	{
		return empservice.getEmpById(id);
	}
    	


	@PostMapping("/registerEmp")
	public ResponseEntity<?> empRegister(@RequestBody Employee employee) {
		// THis is commit comment
		if(employee.getName() == null) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Name can't be null");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getName().trim().length() == 0){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Name can't be empty");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(!employee.getName().matches("[a-zA-Z]+")) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Name must not contains numbers/special characters");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getAddress() == null){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Address can't be null");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getAddress().trim().length() == 0){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Address can't be empty");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getEmail() == null){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Email can't be null");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getEmail().trim().length() == 0){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Email can't be empty");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(!employee.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Email format is not correct or This Email already exist");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getPhno() == null){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("PhoneNumber can't be null");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getPhno().trim().length() == 0) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("PhoneNumber can't be empty");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(!employee.getPhno().matches("^(0|[1-9][0-9]*)$")) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("PhoneNumber should contains digits only/number shouldn't start from zero");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getPhno().length() != 10) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("PhoneNumber length should be 10 exactly");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(!(employee.getSalary() >= 0 &&  employee.getSalary() <= 10000000)) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("salary should be between 0 and 10000000");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}

		return empservice.addEmp(employee);

	}
    	

    @PutMapping("/edit/{id}")
	public ResponseEntity<?> edit(@PathVariable int id, @RequestBody Employee employee) {
		return empservice.edit(id, employee);
    }

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmp(@PathVariable int id) {
		return empservice.deleteEmp(id);
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity<?> deleteAll() {
		 return empservice.deleteAll();
	}


    	
    
}
