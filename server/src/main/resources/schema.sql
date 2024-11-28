-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS wsavolunteer;

-- Use the created database
USE wsavolunteer;

-- Drop the User table if it already exists to avoid conflicts during creation
DROP TABLE IF EXISTS point_allocations;
DROP TABLE IF EXISTS User;

-- Create the User table
CREATE TABLE IF NOT EXISTS User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    username VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

-- Drop tables based on the FK relation to avoid FK issue
DROP TABLE IF EXISTS volunteer_rewards;
DROP TABLE IF EXISTS event_reward;
DROP TABLE IF EXISTS volunteer_event;
DROP TABLE IF EXISTS reward;
DROP TABLE IF EXISTS volunteer_qualification;
DROP TABLE IF EXISTS qualification;
DROP TABLE IF EXISTS volunteer;
DROP TABLE IF EXISTS temporary_volunteers;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS organiser;
DROP TABLE IF EXISTS qualifications;
DROP TABLE IF EXISTS occupations;
DROP TABLE IF EXISTS accessibility_enhancement;
DROP TABLE IF EXISTS availability;
DROP TABLE IF EXISTS turnover;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS reward_point_requirements;

-- Create the Organiser table
CREATE TABLE organiser(
                          id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                          company_name VARCHAR(100) NOT NULL,
                          logo LONGBLOB,
                          post_code VARCHAR(8),
                          charity BOOLEAN,
                          address TEXT,
                          telephone VARCHAR(10),
                          email VARCHAR(100) NOT NULL,
                          website VARCHAR(100),
                          main_contact_name VARCHAR(100),
                          main_contact_position VARCHAR(50),
                          founding_date DATE,
                          number_of_members INT,
                          associated_clubs TEXT,
                          turnover VARCHAR(100),
                          membership_category VARCHAR(50),
                          preferred_language TEXT,
                          newsletter BOOLEAN,
                          activated BOOLEAN
) ENGINE=InnoDB;

-- Create the Event table
CREATE TABLE event(
                      id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(100) NOT NULL,
                      description TEXT,
                      dbs_required BOOLEAN,
                      address VARCHAR(255),
                      city VARCHAR(100),
                      postal_code VARCHAR(8),
                      landmark VARCHAR(255),
                      roles_needed TEXT,
                      rewards_offering TEXT,
                      image LONGBLOB,
                      date DATE,
                      start_time TIME,
                      end_time TIME,
                      accessibility_assistance_provided TEXT,
                      organiser_id INT,
                      approved BOOLEAN,
                      FOREIGN KEY(organiser_id) REFERENCES organiser(id)
) ENGINE=InnoDB;

-- Create the Volunteer table with the updated schema
CREATE TABLE volunteer(
                          id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        --   username VARCHAR(128) UNIQUE,
                        --   password VARCHAR(255),
                          email VARCHAR(100) UNIQUE NOT NULL,
                          first_name VARCHAR(50),
                          last_name VARCHAR(50),
                          gender VARCHAR(10),
                          dob DATE,
                          phone_number VARCHAR(10),
                          address VARCHAR(500),
                          postal_code VARCHAR(10),
                          occupation VARCHAR(200),
                          qualifications TEXT,
                          availability TEXT,
                          roles TEXT,
                          dbs VARCHAR(10),
                          accessibility_enhancement TEXT,
                          about TEXT,
                          rewards_earned INT DEFAULT 0,
                          emergency_contact_name VARCHAR(100),
                          emergency_phone_number VARCHAR(10),
                          emergency_relationship VARCHAR(50),
                        --   goals TEXT,
                        --   interests TEXT,
                        --   `references` TEXT,
                        --   agree_to_policies BOOLEAN,
                          member_status VARCHAR(50),
                          rating FLOAT,
                          membership_level VARCHAR(20),
                          event_attended INT,
                          image LONGBLOB
) ENGINE=InnoDB;


-- Create the Reward table
CREATE TABLE reward(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    image VARCHAR(255),
    description TEXT
)ENGINE=InnoDB;

-- Create the Volunteer Event Mapping table
CREATE TABLE volunteer_event(
    volunteer_id INT,
    event_id INT,
    approved BOOLEAN,
    PRIMARY KEY(volunteer_id, event_id),
    FOREIGN KEY(volunteer_id) REFERENCES volunteer(id),
    FOREIGN KEY(event_id) REFERENCES event(id)
)ENGINE=InnoDB;

-- Create the Event Rewards Mapping table
CREATE TABLE event_reward(
    reward_id INT,
    event_id INT,
    PRIMARY KEY(reward_id, event_id),
    FOREIGN KEY(reward_id) REFERENCES reward(id),
    FOREIGN KEY(event_id) REFERENCES event(id)
)ENGINE=InnoDB;

-- Create the Volunteer Rewards Mapping table
CREATE TABLE volunteer_rewards(
    reward_id INT,volunteer_id INT,
    PRIMARY KEY(reward_id, volunteer_id),
    FOREIGN KEY(reward_id) REFERENCES reward(id),
    FOREIGN KEY(volunteer_id) REFERENCES volunteer(id)
)ENGINE=InnoDB;

-- Create the Qualifications table
CREATE TABLE qualifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type ENUM('Educational', 'Professional Certification', 'Skill', 'Other') NOT NULL
)ENGINE=InnoDB;

-- Create the Occupations table
CREATE TABLE occupations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100)
)ENGINE=InnoDB;

-- Create the Accessibility table
CREATE TABLE accessibility_enhancement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
)ENGINE=InnoDB;

-- Create the Availability table
CREATE TABLE availability (
    id INT AUTO_INCREMENT PRIMARY KEY,
    available_days VARCHAR(255) NOT NULL
)ENGINE=InnoDB;

-- Create the Turnover table
CREATE TABLE turnover (
    id INT AUTO_INCREMENT PRIMARY KEY,
    types VARCHAR(255) NOT NULL
)ENGINE=InnoDB;

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL
)ENGINE=InnoDB;

-- // Rewards System Tables // --
-- Stores all records of scores allocated to volunteers.
    -- Totals table below will use this to give a sum for each volunteer.
    -- Uses event id for now so that unique entries are guaranteed. Change to reward_id when rewards change based on the event. Currently all events yield flat value of 5 points.
CREATE TABLE IF NOT EXISTS point_allocations (
     volunteer_id INT,
     event_id INT,
     amount INT,
     PRIMARY KEY (volunteer_id, event_id), -- Assumes a single user cannot receive the same reward twice.
     FOREIGN KEY (volunteer_id) REFERENCES volunteer(id),
     FOREIGN KEY (event_id) REFERENCES event(id)
)ENGINE=InnoDB;

-- Stores the amount of points required to display rewards.
-- Entries manually added in data.sql & must match stored image names
CREATE TABLE IF NOT EXISTS reward_point_requirements (
     id INT PRIMARY KEY AUTO_INCREMENT,
     imageName VARCHAR(100),
     pointsRequired INT
)ENGINE=InnoDB;

-- Quick Register Volunteers
CREATE TABLE IF NOT EXISTS temporary_volunteers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(10),
    FOREIGN KEY (event_id) REFERENCES event(id)
)ENGINE=InnoDB;


-- Stored Procedure to fetch only Profile Picture from Organiser table
DROP PROCEDURE IF EXISTS GetOrganiserProfilePicture;
DELIMITER //

CREATE PROCEDURE GetOrganiserProfilePicture(IN  organiser_id INT)
BEGIN
    SELECT
         logo
    FROM
        organiser
    WHERE
        id = organiser_id;
END //

DELIMITER ;

-- Stored Procedure to fetch Organiser and Event Details from Event Table
DROP PROCEDURE IF EXISTS GetEventDetailsByEventID;
DELIMITER //

CREATE PROCEDURE GetEventDetailsByEventID(IN event_id INT)
BEGIN
    SELECT
        o.id AS organiserID,
        o.company_name AS organiser_companyName,
        e.*
    FROM
        event e
            INNER JOIN
        organiser o ON e.organiser_id = o.id
    WHERE
        e.id = event_id
        AND e.approved = TRUE;
END //



CREATE TABLE IF NOT EXISTS ContactInfo (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
subject VARCHAR(255),
message TEXT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);








-- Stored Procedure to fetch only the required details from Events table
DROP PROCEDURE IF EXISTS GetUpcomingEvents;
DELIMITER //

CREATE PROCEDURE GetUpcomingEvents()
BEGIN
    SELECT
        id,
        title,
        image,
        postal_code,
        city,
        date,
        DAYNAME(date) AS day_of_week
    FROM
        event
    WHERE
            date > CURDATE()
            AND approved = TRUE;
END //

DELIMITER ;



DROP PROCEDURE IF EXISTS GetApprovedEventsDates;
-- Stored Procedure to fetch only the title date of approved events
DELIMITER //
CREATE PROCEDURE GetApprovedEventsDates()
BEGIN
    SELECT
        id,
        title,
        date
    FROM
        event
    WHERE
            approved = TRUE;
END //

DELIMITER ;

-- Stored Procedure to fetch only the Past Events from Events table
DROP PROCEDURE IF EXISTS GetPastEvents;
DELIMITER //

CREATE PROCEDURE GetPastEvents()
BEGIN
    SELECT
        id,
        title,
        image,
        postal_code,
        city,
        date,
        DAYNAME(date) AS day_of_week
    FROM
        event
    WHERE
        date < CURDATE()
      AND approved = TRUE;
END //

DELIMITER ;

-- Stored Procedure to fetch only Profile Picture from Volunteer table
DROP PROCEDURE IF EXISTS GetVolunteerProfilePicture;
DELIMITER //

CREATE PROCEDURE GetVolunteerProfilePicture(IN  volunteer_id INT)
BEGIN
    SELECT
        image
    FROM
        volunteer
    WHERE
        id = volunteer_id;
END //

DELIMITER ;

DELIMITER ;

DROP PROCEDURE IF EXISTS QuickRegisterVolunteer;
DELIMITER //

CREATE PROCEDURE QuickRegisterVolunteer(
    IN eventID INT,
    IN firstName VARCHAR(50),
    IN lastName VARCHAR(50),
    IN email VARCHAR(100),
    IN phoneNumber VARCHAR(10)
)
BEGIN
    -- Example of logging the registration to another table
    INSERT INTO temporary_volunteers (
        event_id,
        first_name,
        last_name,
        email,
        phone_number
    )
    VALUES (
        eventID,
        firstName,
        lastName,
        email,
        phoneNumber
    );
END //

DELIMITER ;

DROP PROCEDURE IF EXISTS SaveVolunteerAuth;
DELIMITER //

CREATE PROCEDURE SaveVolunteerAuth(
    IN firstName VARCHAR(50),
    IN lastName VARCHAR(50),
    IN emailID VARCHAR(100),
    IN phoneNumber VARCHAR(15),
    OUT volunteerId INT
)
BEGIN
    DECLARE existingVolunteerId INT;

    -- Attempt to find an existing volunteer with the provided emailID
    SELECT id INTO existingVolunteerId
    FROM organiser
    WHERE email = emailID
    LIMIT 1;

    -- If no existing volunteer was found, insert a new volunteer
    IF existingVolunteerId IS NULL THEN
        INSERT INTO volunteer (
            first_name,
            last_name,
            email,
            phone_number
        ) VALUES (
                     firstName,
                     lastName,
                     emailID,
                     phoneNumber
                 );

        -- Set the OUT parameter to the newly inserted volunteer's ID
        SET volunteerId = LAST_INSERT_ID();
    ELSE
        -- If a volunteer was found, return the existing ID
        SET volunteerId = existingVolunteerId;
    END IF;
END //

DELIMITER ;

DELIMITER //

DROP PROCEDURE IF EXISTS ApproveVolunteerEvent;
CREATE PROCEDURE ApproveVolunteerEvent(
    IN p_volunteer_id INT,
    IN p_event_id INT
)
BEGIN
    -- Update the approved status to TRUE
    UPDATE volunteer_event
    SET approved = TRUE
    WHERE volunteer_id = p_volunteer_id AND event_id = p_event_id;

    -- Return 1 to indicate success
    SELECT 1 AS result;
END //

DELIMITER ;

#
# DROP PROCEDURE IF EXISTS SaveOrganiserAuth;
# DELIMITER //
#
# CREATE PROCEDURE SaveOrganiserAuth(
#     IN email_id VARCHAR(100),
#     IN telephone_no VARCHAR(15),
#     IN postCode VARCHAR(20),
#     IN address_link VARCHAR(255),
#     IN website_link VARCHAR(255),
#     IN foundingDate DATE,
#     IN associatedClubs TEXT,
#     IN numberOfMembers INT,
#     OUT organiserId INT
# )
# BEGIN
#     DECLARE existingOrganiserId INT;
#
#     SELECT id INTO existingOrganiserId
#     FROM organiser
#     WHERE email = email_id
#     LIMIT 1;
#
#     IF existingOrganiserId IS NULL THEN
#         INSERT INTO organiser (
#             email,
#             telephone,
#             post_code,
#             address,
#             website,
#             founding_date,
#             associated_clubs,
#             number_of_members,
#             company_name
#         )
#         VALUES (
#             email_id,
#             telephone_no ,
#             postCode ,
#             address_link ,
#             website_link ,
#             foundingDate ,
#             associatedClubs ,
#             numberOfMembers,
#             'wsa'
#         );
#
#         -- Get the last inserted ID
#         SET organiserId = LAST_INSERT_ID();
#     ELSE
#         -- If email exists, return the existing ID
#         SET organiserId = existingOrganiserId;
#     END IF;
# END //
#
# DELIMITER ;
#
# DROP PROCEDURE IF EXISTS GetVolunteerOrganiser;
# DELIMITER //
# CREATE PROCEDURE GetVolunteerOrganiser(
#     IN p_id INT,
#     IN p_tableName VARCHAR(50),
#     OUT p_resultId INT
# )
# BEGIN
#     -- Declare a variable to hold the SQL query
#     DECLARE v_sql TEXT;
#
#     -- Initialize the result
#     SET p_resultId = NULL;
#
#     -- Construct the dynamic SQL query
#     SET v_sql = CONCAT('SELECT id INTO @resultId FROM ', p_tableName, ' WHERE id = ? LIMIT 1');
#
#     -- Prepare and execute the dynamic SQL statement
#     PREPARE stmt FROM v_sql;
#     EXECUTE stmt USING p_id;
#
#     -- Get the result
#     SET p_resultId = @resultId;
#
#     -- Clean up
#     DEALLOCATE PREPARE stmt;
#     INSERT INTO volunteer (
#         first_name,
#         last_name,
#         email,
#         phone_number
#     )
#     VALUES (
#         firstName,
#         lastName,
#         email,
#         phoneNumber
#     );
#
#     SET volunteerId = LAST_INSERT_ID();
# END //
#
# DELIMITER ;

