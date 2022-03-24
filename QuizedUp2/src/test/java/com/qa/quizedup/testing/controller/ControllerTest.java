package com.qa.quizedup.testing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.quizedup.model.QuizMaking;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"classpath:quiz-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")

public class ControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	//Test Objects
	QuizMaking question1 = new QuizMaking
	("History", "War","How many years did the 100 years war last? (a) 111 years (b)116 years (c) 200 years (d) 100 years",
	"b",true);
			
	QuizMaking question2 = new QuizMaking
	("Language", "Germanic","Which of the following is a Germanic language? (a) Celtic  (b)Vietnamese (c) French (d) Malay",
	"c",false);
	
	//Test Objects with ID
	QuizMaking question1ID= new QuizMaking (1l,"Geography", "State","How many state are there in Australia? (a) 5 (b) 12 (c) 10 (d) 6",
	"d",true);
	QuizMaking question2ID= new QuizMaking (2l,"Science", "Planet","What is the biggest planet in our solar system? (a) Jupiter (b) Saturn (c) Moon (d) Uranus",
	"a",false);
	QuizMaking question3ID= new QuizMaking (3l,"Science", "Planet","What is the smallest planet in our solar system? (a) Jupiter (b) Saturn (c) Moon (d) Uranus",
	"b",true);
	QuizMaking question4ID= new QuizMaking (4l,"Geography", "Continent","Which continent is Estonia located in? (a) Australia (b) Asia (c) Europe (d) Africa",
	"c",true);
	
	
	@Test
	public void testCreateQuestion() throws Exception{
		
		String questionJson = mapper.writeValueAsString(question1);
		RequestBuilder req = post("/createQuestion").contentType(MediaType.APPLICATION_JSON).content(questionJson);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().string("Question has been created");
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	public void testCreateMultipleQuestions() throws Exception{
		List<QuizMaking>testList = List.of(question1, question2);
		String questionsJson = mapper.writeValueAsString(testList);
		RequestBuilder req = post("/createMultipleQuestions").contentType(MediaType.APPLICATION_JSON).content(questionsJson);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().string("Questions have been created");
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
		
	}
	//Failure -- Looks like it reads data from quiz-data.sql
	@Test
	public void testGetById() throws Exception{
		String QuestionIDJson = mapper.writeValueAsString(question1ID);
		RequestBuilder req = get("/getById/1");
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(QuestionIDJson);
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	//Failure - Expected 2, but only got 1. Not sure why. The List has got 2 questions
	@Test
	public void testGetByCategory() throws Exception{
		List<QuizMaking>testList = List.of(question1ID, question2ID);
		String testQuestionIDJson = mapper.writeValueAsString(testList);
		RequestBuilder req = get("/getByCategory/Science");
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testQuestionIDJson);
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	//Failure - Expected 3, but only got 2. Not sure why, the list has got 3 questions
	@Test
	public void testGetFinalExam() throws Exception{
		List<QuizMaking>testList = List.of(question1ID, question3ID, question4ID);
		String testQuestionIDJson = mapper.writeValueAsString(testList);
		RequestBuilder req = get("/getFinalExam/true");
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testQuestionIDJson);
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	//Failure - Couldn't find match for 
	@Test
	public void testGetQuestions() throws Exception{
		List<QuizMaking>testList = List.of(question1ID, question2ID, question3ID, question4ID);
		String testQuestionIDJson = mapper.writeValueAsString(testList);
		RequestBuilder req = get("/getQuestions");
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testQuestionIDJson);
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	
	@Test
	public void testUpdate() throws Exception{
		QuizMaking updatedQuestion = new QuizMaking ("new category", "keywords","New Questions? (a) 5 (b) 12 (c) 10 (d) 6",
		"d",true);
		
		String updatedQuestionJson = mapper.writeValueAsString(updatedQuestion);
		RequestBuilder req = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(updatedQuestionJson);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().string("Question of id:  1 has been updated");
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	
	}
	
	@Test
	public void testDeleteId() throws Exception{
		RequestBuilder req = delete("/delete/1");
		ResultMatcher checkStatus = status(). isAccepted();
		ResultMatcher checkBody = content().string("Question of id:  1 has been deleted"); 
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testDeleteAll() throws Exception{
		RequestBuilder req = delete("/deleteAll");
		ResultMatcher checkStatus = status(). isAccepted(); //Is the status code of our request created(201)
		ResultMatcher checkBody = content().string("All questions have been deleted"); 
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
//******Stretch goals*********//
	
	@Test
	public void testShuffleFinalExam() throws Exception{
		List<QuizMaking>testList = List.of(question1ID, question3ID, question4ID);
		String testQuestionIDJson = mapper.writeValueAsString(testList);
		RequestBuilder req = get("/shuffleFinalExam");
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().string("Exam questions have been shuffled");
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
//	@Test
//	public void testTakeTest() throws Exception{
//		List<QuizMaking>testList = List.of(question1ID, question2ID, question3ID, question4ID);
//		String testQuestionIDJson = mapper.writeValueAsString(testList);
//		RequestBuilder req = get("/takeTest");
//		ResultMatcher checkStatus = status().isOk();
//		ResultMatcher checkBody = content().json(testQuestionIDJson);
//		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
//	}

}
