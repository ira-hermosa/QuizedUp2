DROP TABLE IF EXISTS `quiz_making`;

CREATE TABLE quiz_making( 
id long AUTO_INCREMENT, 
category VARCHAR(30) NOT NULL,
keywords VARCHAR(30) NOT NULL,
question VARCHAR(500) NOT NULL,
correct_answer VARCHAR(20) NOT NULL,
final_exam Boolean NOT NULL,
PRIMARY KEY (id)
);


INSERT INTO `quiz_making` (`category`, `keywords`, `question`, `correct_answer`, `final_exam`) 
VALUES 
('Science', 'Gravity', 'The concept of gravity was discovered by which famous physicist? (a) Graham Bell (b)Marie Curie (c) Isaac Newton (d) none is correct',
'b', false);

INSERT INTO `quiz_making` (`category`, `keywords`, `question`, `correct_answer`, `final_exam`) 
VALUES 
('History', 'War', 'How many years did the 100 years war last? (a) 111 years (b)116 years (c) 200 years (d) 100 years','b', true);

INSERT INTO `quiz_making` (`category`, `keywords`, `question`, `correct_answer`, `final_exam`) 
VALUES 
('Language', 'Germanic', 'Which of the following is a Germanic language? (a) Celtic  (b)Vietnamese (c) French (d) Malay','c', false);

INSERT INTO `quiz_making` (`category`, `keywords`, `question`, `correct_answer`, `final_exam`) 
VALUES 
('Geography', 'State', 'How many state are there in Australia? (a) 5 (b) 12 (c) 10 (d) 6','d', true);

