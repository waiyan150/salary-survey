CREATE TABLE salary_survey (
    id INT AUTO_INCREMENT PRIMARY KEY,
    timestamp TIMESTAMP DEFAULT NULL,
    employer VARCHAR(255) DEFAULT NULL,
    location VARCHAR(255) DEFAULT NULL,
    job_title VARCHAR(255) DEFAULT NULL,
    years_at_employer VARCHAR(255) DEFAULT NULL,
    years_of_experience VARCHAR(255) DEFAULT NULL,
    salary VARCHAR(255) DEFAULT NULL,
    signing_bonus VARCHAR(255) DEFAULT NULL,
    annual_bonus VARCHAR(255) DEFAULT NULL,
    annual_stock_value_bonus VARCHAR(255) DEFAULT NULL,
    gender VARCHAR(255) DEFAULT NULL,
    additional_comments VARCHAR(255) DEFAULT NULL
);

INSERT INTO salary_survey(timestamp, employer, location, job_title, years_at_employer, years_of_experience, salary, signing_bonus, annual_bonus, annual_stock_value_bonus, gender, additional_comments)
SELECT timestamp, employer, location, job_title, years_at_employer, years_of_experience, salary, signing_bonus, annual_bonus, annual_stock_value_bonus, gender, additional_comments
FROM CSVREAD('classpath:/salary_survey-3-fixed-pilot.csv', NULL, 'charset=UTF-8');