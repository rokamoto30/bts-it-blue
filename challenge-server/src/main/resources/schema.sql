DROP TABLE IF EXISTS speed;  
CREATE TABLE speed (  
id INT AUTO_INCREMENT  PRIMARY KEY,  
speed_type VARCHAR(5) NOT NULL,
speed_descpription VARCHAR(50) NOT NULL,
speed_value DECIMAL NOT NULL  
);

DROP TABLE IF EXISTS walked_steps;
CREATE TABLE walked_steps (
id INT AUTO_INCREMENT  PRIMARY KEY,
speed_direction VARCHAR(2),  
speed_type VARCHAR(5) NOT NULL,
duration_hours INT NOT NULL,
duration_minutes INT NOT NULL
);  
