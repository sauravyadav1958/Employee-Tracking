package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.demo.response.responseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmpRepo;

@Service
public class EmpService {
	
	@Autowired
	private EmpRepo repo;

	@Autowired
	private responseDetails responseDetailsObj;


	public ResponseEntity<?> addEmp(Employee employee) {


		repo.save(employee);

		responseDetailsObj.setDateAndTime(LocalDateTime.now());
		responseDetailsObj.setResponseCode("200");
		responseDetailsObj.setResponseMessage("Employee is successfully registered");
		responseDetailsObj.setResponseData(employee);


		return new ResponseEntity<>(responseDetailsObj, HttpStatus.CREATED);
	}



	public ResponseEntity<?> getAllEmp(){


			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("200");
			responseDetailsObj.setResponseMessage("List of all the Employees");
			responseDetailsObj.setResponseData(repo.findAll());

			return new ResponseEntity<>(responseDetailsObj, HttpStatus.OK);

		}



	public ResponseEntity<?> getEmpById(int id) {

		Optional<Employee> employee = repo.findById(id);
		if(employee.isPresent()) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("200");
			responseDetailsObj.setResponseMessage("Details of employee as per ID");
			responseDetailsObj.setResponseData(employee.get());

			return new ResponseEntity<>(responseDetailsObj, HttpStatus.OK);
		}else{

			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("404");
			responseDetailsObj.setResponseMessage("Employee dosen't exist");
			responseDetailsObj.setResponseData(null);

			return new ResponseEntity<>(responseDetailsObj,HttpStatus.NOT_FOUND);
		}

	}
	
	public ResponseEntity<?> deleteEmp(int id) {

		Optional<Employee> employee = repo.findById(id);
		if(employee.isPresent()) {
			repo.deleteById(id);
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("200");
			responseDetailsObj.setResponseMessage("Employee details with " +id +" is successfully deleted");
			responseDetailsObj.setResponseData(null);

			return new ResponseEntity<>(responseDetailsObj, HttpStatus.OK);
		}else{

			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("404");
			responseDetailsObj.setResponseMessage("Employee details with " +id +" doesn't exist");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<?> deleteAll() {

		 repo.deleteAll();

		responseDetailsObj.setDateAndTime(LocalDateTime.now());
		responseDetailsObj.setResponseCode("200");
		responseDetailsObj.setResponseMessage("successfully deleted");
		responseDetailsObj.setResponseData(null);

		return new ResponseEntity<>(responseDetailsObj, HttpStatus.OK);
	}

	public ResponseEntity<?> edit(int id, Employee employee) {
		Optional<Employee> e = repo.findById(id);

		if(e.isPresent()) {
			employee.setId(id);// for setting the same above id automatically as this can't be edited by user
			addEmp(employee);
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("200");
			responseDetailsObj.setResponseMessage("successfully Edited");
			responseDetailsObj.setResponseData(employee);
			return new ResponseEntity<>(responseDetailsObj,HttpStatus.OK);
		}else{
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("404");
			responseDetailsObj.setResponseMessage("Id not found");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj,HttpStatus.NOT_FOUND);
		}


	}
}
