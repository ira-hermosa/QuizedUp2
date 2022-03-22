package com.qa.quizedup.testing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.quizedup.model.QuizMaking;
import com.qa.quizedup.repo.Repo;
import com.qa.quizedup.services.ServiceDB;


@SpringBootTest
public class ServiceTest {
	
	@MockBean
	private Repo repo;
	
	@Autowired
	private ServiceDB service;
	
	//Test objects
	QuizMaking question1 = new QuizMaking
	("History", "War","How many years did the 100 years war last? (a) 111 years (b)116 years (c) 200 years (d) 100 years",
	  "b",true);
	
	QuizMaking question2 = new QuizMaking
			("Language", "Germanic","Which of the following is a Germanic language? (a) Celtic  (b)Vietnamese (c) French (d) Malay",
			  "c",false);
	
	//Test objects with ID
	QuizMaking question1ID= new QuizMaking (1l,"Geography", "State","How many state are there in Australia? (a) 5 (b) 12 (c) 10 (d) 6",
			  "d",true);
	QuizMaking question2ID= new QuizMaking (2l,"Science", "Planet","What is the biggest planet in our solar system? (a) Jupiter (b) Saturn (c) Moon (d) Uranus",
			  "a",false);
	
	

}
