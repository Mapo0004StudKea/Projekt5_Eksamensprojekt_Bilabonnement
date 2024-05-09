CREATE TABLE IF NOT EXISTS car (
                                   id	INT		AUTO_INCREMENT	NOT NULL 	PRIMARY KEY,
                                   car_serialnr	VARCHAR(50) NOT NULL,
    car_number	INT 	NOT NULL,
    car_model	VARCHAR(50),
    car_name	VARCHAR(50),
    car_year	INT,
    monthly_price	DOUBLE,
    is_leased	INT
    );

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







