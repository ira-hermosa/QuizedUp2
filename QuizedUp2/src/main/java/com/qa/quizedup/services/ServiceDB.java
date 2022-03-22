package com.qa.quizedup.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	
	public boolean createQuestion(QuizMaking question){
		repo.save(question);
		return true;
	}
	
	public ArrayList<QuizMaking> createMultipleQuestions(QuizMaking[] questions){
		ArrayList<QuizMaking>questionBucket = new ArrayList<>();
		for (QuizMaking question: questions) {
			questionBucket.add(question);
		}
		return (ArrayList<QuizMaking>) repo.saveAll(questionBucket);
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
	
	//Method to be tested via the console rather than Postman as we only use 1 table in this project
		public int takeTest(){

		
			int totalScore = 0;
			List<QuizMaking> testQuestions = repo.findAll();
			
			Scanner keyboard = new Scanner (System.in);

			for (int i=0; i<testQuestions.size(); i++) {
				System.out.println(testQuestions.get(i));
				String answer = keyboard.nextLine();
				if (answer == testQuestions.get(i).getCorrectAnswer()) {
				totalScore++;
			}	
		}
		System.out.println("You got: " + " " + totalScore + " " + "out of" + testQuestions.size());
		return repo.save(totalScore);

		}
		
		//Shuffle all final exam questions to minimise cheating :)
		public List<QuizMaking> shuffleFinalExam(){
		
			List<QuizMaking> listFinalExam = repo.findByFinalExam(true);
			Collections.shuffle(listFinalExam, new Random());
			return repo.saveAll(listFinalExam);
			
		
	}

}
