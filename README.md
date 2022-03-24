# QuizedUp

QuizedUp is an application to create online quizzes. This app was developed as a final project for the DFE Software Development programme at QA.

At present, the app alows user to:
- add a quiz question
- add multiple questions
- read all questions
- read questions by their (1) IDs, (2) category, (3) final exam classification
- update questions
- delete question by id
- delete all questions

The app will evolve over time. Once fully developed, it will be donated to teachers in under-resourced countries so that they can deliver online exams.

# Future improvements - Short term
The work for the following features have commenced and is included in the code base. These features will be fully functional in the next sprint:
- feature to allow users to take test and get a score for their attempts
- feature to shuffle questions in the final exam pool

# Technologies
- The application was built using Java, SpringBoot, Maven, MySQL and H2 instance and runs locally on port 8081
- Service and Controller methods have been tested using JUnit5 and Mockito. Overall testing coverage is currently at 92.2%
- API endpoints can be tested with Postman

# Getting started
Java is needed in your system to run the application on your local system. Follow these instructions to download and test the application locally:
- Copy the github url: https://github.com/ira-hermosa/QuizedUp2
- Open a git bash terminal
- Git clone the project
- Cd into the project and do git pull
- Cd into the java file in the project
- Type:mvn clean package
- Cd target to list the files
- Copy the file the .jar-with-dependencies.jar line
- Type in: java-jar-jar-with-dependencies.jar
- Press enter and the application will run

# Screenshots
- Link to [Postman requests and outputs from the API] [https://github.com/ira-hermosa/QuizedUp2/tree/integrationtesting/QuizedUpScreenshots/Postman-requests-outputs]
- Link to [MySQL database] [https://github.com/ira-hermosa/QuizedUp2/tree/integrationtesting/QuizedUpScreenshots/MySQL-database]
- Link to [Test results, including coverage report] [https://github.com/ira-hermosa/QuizedUp2/tree/integrationtesting/QuizedUpScreenshots/Test-results-coverage]

# Project management
Jira has been used to manage this project. Link to Jira Board: https://ira-bootcamp-projects.atlassian.net/jira/software/projects/QUIZEDUP2/boards/3/roadmap

# Reflection
I expected the project to be quite challenging as I was still wrapping my head particularly around SpringBoot, JUnit and Mockito testing. However, it turned out to be a rewarding exercise. Not only did I learn the core technologies, I also learnt additional skills such as debugging and sharpened my logic when refactoring code.

Whilst I managed to push myself to include stretch goals, they need to be further refined. I think the Java code for the methods was correct, but there were still missing links to Spring resulting in errors.

