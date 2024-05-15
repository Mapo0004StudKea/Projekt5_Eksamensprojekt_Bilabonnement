CREATE TABLE `car` (
                       `id` int NOT NULL AUTO_INCREMENT,
                       `car_picture` varchar(255) DEFAULT NULL,
                       `car_full_name` varchar(255) DEFAULT NULL,
                       `car_name` varchar(50) DEFAULT NULL,
                       `car_model` varchar(50) DEFAULT NULL,
                       `car_serialnr` varchar(50) NOT NULL,
                       `car_number` int NOT NULL,
                       `car_year` int DEFAULT NULL,
                       `monthly_price` double DEFAULT NULL,
                       `is_leased` int DEFAULT NULL,
                       `car_description` varchar(255) DEFAULT NULL,
                       PRIMARY KEY (`id`)
)

CREATE TABLE IF NOT EXISTS leasingcontract (
                                               id	INT 	AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
                                               employee_name VARCHAR(100)	NOT NULL,
    monthly_price	DOUBLE,
    customer_name 	VARCHAR(100)	NOT NULL,
    start_leasing	DATE	NOT NULL,
    end_leasing		DATE	NOT NULL,
    is_unlimited	INT,
    is_limited		INT,
    car_id	INT,
    FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE
    );


CREATE TABLE IF NOT EXISTS damages (
                                       id	INT 	AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
                                       damage_name	VARCHAR(100) NOT NULL,
    damage_price	DOUBLE	NOT NULL,
    damage_description		VARCHAR(200),
    damagereport_id	INT,
    FOREIGN KEY (damagereport_id) REFERENCES damagereport(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS damagereport (
                                            id	INT 	AUTO_INCREMENT	NOT NULL	PRIMARY KEY,
                                            report_name	VARCHAR(50) NOT NULL,
    car_id	INT,
    FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE
    );







