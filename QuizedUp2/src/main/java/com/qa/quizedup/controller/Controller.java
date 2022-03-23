package com.qa.quizedup.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.quizedup.model.QuizMaking;
import com.qa.quizedup.services.ServiceDB;

@RestController
public class Controller {
	
	private ServiceDB service;

	//Constructor
	
	public Controller(ServiceDB service) {
		super();
		this.service = service;
	}
	
	
	//Controller methods
	@PostMapping("/createQuestion")
	public ResponseEntity<String> createQuestion(@RequestBody QuizMaking question){
		service.createQuestion(question);
		String response = "Question has been created";
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/createMultipleQuestions")
	public ResponseEntity<String> createMultipleQuestions(@RequestBody List<QuizMaking> testList){
		service.createMultipleQuestions(testList);
		String response = "Questions have been created";
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
//	@PostMapping("/createMultipleQuestions")
//	public ResponseEntity<String> createMultipleQuestions(@RequestBody QuizMaking[] questions){
//		service.createMultipleQuestions(questions);
//		String response = "Questions have been created";
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<QuizMaking>getQuestionById(@PathVariable("id") long id){
		QuizMaking response = service.getQuestionById(id);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getByCategory/{category}")
	public ResponseEntity<List<QuizMaking>>getQuestionsByCategory(@PathVariable("category") String category){
		List<QuizMaking> response = service.getQuestionsByCategory(category);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getFinalExam/{finalExam}")
	public ResponseEntity<List<QuizMaking>>getFinalExam(@PathVariable("finalExam") boolean finalExam){
		List<QuizMaking> response = service.getFinalExam(finalExam);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getQuestions")
	public ResponseEntity<List<QuizMaking>>getAllQuestions(){
		List<QuizMaking> response = service.getAllQuestions();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String>update(@PathVariable("id") long id, @RequestBody QuizMaking question){
		service.update(id, question);
		String response = "Question of id: " + " " + id + " " + "has been updated";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteById(@PathVariable("id") long id){
		service.deleteById(id);
		String response = "Question of id: " + " " + id + " " + "has been deleted";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String>deleteAll(){
		service.deleteAll();
		String response = "All questions have been deleted";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/takeTest")
	public ResponseEntity<String>takeTest(){
		service.takeTest();
		String response = "You have completed the test";
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/shuffleFinalExam")
	public ResponseEntity<String>shuffleFinalExam(){
		service.shuffleFinalExam();
		String response = "Exam questions have been shuffled";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}

}
