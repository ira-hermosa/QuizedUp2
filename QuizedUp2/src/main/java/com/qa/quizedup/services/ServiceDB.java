package com.qa.quizedup.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import com.qa.quizedup.model.QuizMaking;
import com.qa.quizedup.repo.Repo;

@Service
public class ServiceDB {
	
	private Repo repo;
	
	public ServiceDB(Repo repo) {
		super();
		this.repo=repo;
	}
	
	//*****CRUD methods******
	public boolean createQuestion(QuizMaking question){
		repo.save(question);
		return true;
	}
	
	public List<QuizMaking> createMultipleQuestions(List<QuizMaking> testList){
		List<QuizMaking>questionBucket = new ArrayList<>();
		for (QuizMaking question: testList) {
			questionBucket.add(question);
		}
		return (List<QuizMaking>) repo.saveAll(questionBucket);
	}
	
	
	public QuizMaking getQuestionById(long id) {
		return repo.findById(id).get();
	}
	
	public List<QuizMaking> getQuestionsByCategory(String category){
		for(QuizMaking question: repo.findAll()) {
			if(question.getCategory() == category) {
			}
		}return repo.findByCategory(category);
	}
	
	public List<QuizMaking> getFinalExam(boolean finalExam){
		for(QuizMaking question: repo.findAll()) {
			if(question.isFinalExam() == finalExam) {
			}
		}return repo.findByFinalExam(finalExam);
	}
	
	public List<QuizMaking> getAllQuestions(){
		return repo.findAll();
	}
	
	public QuizMaking update (long id, QuizMaking question){
		
		 QuizMaking oldQuestion = getQuestionById(id);
		 
		 oldQuestion.setCategory(question.getCategory());
		 oldQuestion.setQuestion(question.getQuestion());
		 oldQuestion.setCorrectAnswer(question.getCorrectAnswer());
		 oldQuestion.setKeywords(question.getKeywords());
		 oldQuestion.setFinalExam(question.isFinalExam());
		 
		 return repo.save(oldQuestion);
	}
	
	public boolean deleteById(long id) {
		repo.deleteById(id);
		return true;
	}
	
	public boolean deleteAll() {
		repo.deleteAll();
		return true;
		
	}
	
	//***** STRETCH GOALS******
	
	//These methods need further debugging.Keeping them in as I will continue investigating after project submission
	
	//Shuffle all questions with final exam property = true
	//Return shuffled questions
	//Use get request in Postman to print lists of questions and shuffled questions to console
			public List<QuizMaking> shuffleFinalExam(){
				List<QuizMaking> listFinalExam = repo.findByFinalExam(true);
				System.out.println("Initial list : " + " " + listFinalExam);
				int i = 0; 
				while (i<listFinalExam.size()) {
					System.out.println("Shuffled list: ");
					Collections.shuffle(listFinalExam, new Random());
					i++;
				}
				return repo.saveAll(listFinalExam);
	
			
		}
			
	
	
	//For each question in the list: Display question to user, save their answer
	//If answer is correct, give 1 mark. Return total mark for all questions correctly attempted
	//Use get request in Postman to print questions to console
		public int takeTest(){

			int totalScore = 0;
			List<QuizMaking> testQuestions = repo.findAll();
			Scanner keyboard = new Scanner (System.in);
			System.out.println("Answer the following questions");
			for (int i=0; i<testQuestions.size(); i++) {
				System.out.println(testQuestions.get(i));
				String answer = keyboard.nextLine();
				if (Objects.equals(answer, testQuestions.get(i).getCorrectAnswer()));
				{totalScore++;
				}	
			}
			return repo.save(totalScore); 
			
		} 

	
}
