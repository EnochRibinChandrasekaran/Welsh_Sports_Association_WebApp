-- AI Generated Mock Data
USE wsavolunteer;

INSERT INTO organiser (
    company_name, logo, post_code, charity, address, telephone, email, website,
    main_contact_name, main_contact_position, founding_date, number_of_members,
    associated_clubs, turnover, membership_category, preferred_language,
    newsletter, activated
) VALUES
      ('Eco Warriors', 'logo1.png', '12345', TRUE, '123 Green St, Eco City', '1234567890',
       'contact@ecowarriors.org', 'www.ecowarriors.org', 'Alice Johnson', 'CEO', '2000-05-15',
       1000, '50 clubs', 500000, 'Gold', 'Email, SMS', TRUE, TRUE),
      ('Helping Hands', 'logo2.png', '67890', FALSE, '456 Help St, Aid Town', '3216540987',
       'info@helpinghands.org', 'www.helpinghands.org', 'Bob Smith', 'Director', '2010-09-01',
       500, '20 clubs', 200000, 'Silver', 'Email', FALSE, TRUE),
      ('Green Earth', 'logo3.png', '54321', TRUE, '789 Earth Ln, Green Town', '9876543210',
       'greenearth@gmail.com', 'www.greenearth.org', 'Clara Evans', 'Founder', '2005-03-20',
       750, '35 clubs', 350000, 'Silver', 'Email, Phone', TRUE, FALSE),
      ('Volunteer United', 'logo4.png', '11223', TRUE, '456 Unity Rd, Volunteer City', '5557891234',
       'volunteerunited@yahoo.com', 'www.volunteerunited.org', 'David King', 'Coordinator', '2018-07-11',
       150, '5 clubs', 100000, 'Bronze', 'Email, SMS', FALSE, TRUE),
      ('Community Helpers', 'logo5.png', '13579', FALSE, '963 Community St, Helping Town', '2223334444',
       'communityhelpers@hotmail.com', 'www.communityhelpers.org', 'Eva Brown', 'Manager', '2012-12-25',
       300, '15 clubs', 150000, 'Gold', 'Email', TRUE, FALSE),
      ('Care Givers', 'logo6.png', '24680', TRUE, '147 Care Ln, Kind City', '3332221111',
       'caregivers@live.com', 'www.caregivers.org', 'Frank Lee', 'CEO', '2008-09-19',
       600, '25 clubs', 250000, 'Silver', 'Phone', FALSE, TRUE),
      ('Nature Lovers', 'logo7.png', '98765', FALSE, '258 Nature Ave, Eco Town', '7778889999',
       'naturelovers@gmail.com', 'www.naturelovers.org', 'Grace Kim', 'Director', '2003-02-02',
       400, '10 clubs', 100000, 'Gold', 'Email, Phone', TRUE, FALSE),
      ('Animal Rescuers', 'logo8.png', '87654', TRUE, '369 Animal St, Rescue Town', '9998887777',
       'animalrescuers@outlook.com', 'www.animalrescuers.org', 'Henry Green', 'Founder', '2011-05-18',
       200, '8 clubs', 80000, 'Bronze', 'Email, SMS', FALSE, TRUE),
      ('Food Distributors', 'logo9.png', '65432', FALSE, '147 Food Ln, Distribution City', '8887776666',
       'fooddistributors@yahoo.com', 'www.fooddistributors.org', 'Ivy White', 'Coordinator', '2006-11-30',
       500, '20 clubs', 200000, 'Silver', 'Email', TRUE, FALSE),
      ('Health Providers', 'logo10.png', '76543', TRUE, '321 Health Ave, Care Town', '6665554444',
       'healthproviders@gmail.com', 'www.healthproviders.org', 'Jack Black', 'CEO', '2009-04-25',
       700, '30 clubs', 300000, 'Gold', 'Phone', FALSE, TRUE),
      ('Child Protectors', 'logo11.png', '54321', TRUE, '258 Child St, Protection City', '1112223333',
       'childprotectors@hotmail.com', 'www.childprotectors.org', 'Kathy Red', 'Manager', '2001-06-15',
       350, '12 clubs', 120000, 'Silver', 'Email, Phone', TRUE, FALSE),
      ('Art Promoters', 'logo12.png', '12345', FALSE, '369 Art Ln, Creative Town', '4445556666',
       'artpromoters@live.com', 'www.artpromoters.org', 'Laura Blue', 'Founder', '2017-10-10',
       100, '3 clubs', 30000, 'Bronze', 'Email', FALSE, TRUE),
      ('Tech Innovators', 'logo13.png', '23456', TRUE, '147 Tech Ave, Innovation City', '5554443333',
       'techinnovators@outlook.com', 'www.techinnovators.org', 'Mike Yellow', 'Coordinator', '2013-01-01',
       450, '18 clubs', 180000, 'Gold', 'Email, SMS', TRUE, FALSE),
      ('Elderly Supporters', 'logo14.png', '34567', FALSE, '258 Elder St, Support Town', '2221110000',
       'elderlysupporters@gmail.com', 'www.elderlysupporters.org', 'Nancy Purple', 'Director', '2010-08-08',
       250, '10 clubs', 100000, 'Silver', 'Phone', FALSE, TRUE),
      ('Women Empowerment', 'logo15.png', '45678', TRUE, '963 Women Ave, Empowerment City', '7776665555',
       'womenempowerment@yahoo.com', 'www.womenempowerment.org', 'Oliver Brown', 'CEO', '2004-12-12',
       550, '22 clubs', 220000, 'Gold', 'Email, Phone', TRUE, FALSE),
      ('Education Support', 'logo16.png', '56789', FALSE, '741 Education Ln, Support Town', '8889990000',
       'educationsupport@live.com', 'www.educationsupport.org', 'Peter Grey', 'Manager', '2007-03-03',
       400, '15 clubs', 150000, 'Silver', 'Email', FALSE, TRUE),
      ('Disaster Relief', 'logo17.png', '67890', TRUE, '123 Disaster St, Relief City', '9998887777',
       'disasterrelief@outlook.com', 'www.disasterrelief.org', 'Quinn Green', 'Coordinator', '2015-05-05',
       300, '12 clubs', 120000, 'Bronze', 'Email, SMS', TRUE, FALSE),
      ('Youth Development', 'logo18.png', '78901', FALSE, '147 Youth Ave, Development City', '6665554444',
       'youthdevelopment@gmail.com', 'www.youthdevelopment.org', 'Rachel White', 'Director', '2002-09-09',
       500, '20 clubs', 200000, 'Gold', 'Phone', FALSE, TRUE),
      ('Environmental Action', 'logo19.png', '89012', TRUE, '258 Environment St, Action City', '4443332222',
       'environmentalaction@yahoo.com', 'www.environmentalaction.org', 'Sam Black', 'Founder', '2006-11-11',
       600, '24 clubs', 240000, 'Silver', 'Email, Phone', TRUE, FALSE),
      ('Social Justice', 'logo20.png', '90123', FALSE, '369 Social Ln, Justice Town', '3332221111',
       'socialjustice@live.com', 'www.socialjustice.org', 'Tom Blue', 'CEO', '2014-02-02',
       450, '18 clubs', 180000, 'Gold', 'Email', FALSE, TRUE);

INSERT INTO event (
    title, description, dbs_required, address, city, postal_code, landmark, roles_needed, rewards_offering, image,
    date, start_time, end_time, accessibility_assistance_provided, organiser_id, approved
) VALUES
      ('Community Clean-Up', 'Join us for a community clean-up to keep our city beautiful.', TRUE, 'City Park Address', 'City Park City', '12345', 'Near the main fountain', 'Trash Pickers, Coordinators', 'Certificate, Lunch', 'cleanup.png', '2020-08-15', '09:00:00', '12:00:00', 'Wheelchair Access', 1, TRUE),
      ('Charity Run', 'Participate in a charity run to support local schools.', FALSE, 'Central Avenue Address', 'Central Avenue City', '23456', 'Start at Central Park', 'Runners, Water Distributors', 'Medal, T-shirt', 'charityrun.png', '2020-09-01', '08:00:00', '11:00:00', 'None', 2, TRUE),
      ('Tree Planting', 'Help us plant trees and make the city greener.', TRUE, 'Greenway Park Address', 'Greenway Park City', '34567', 'Next to the river', 'Planters, Waterers', 'Free T-shirt', 'treeplanting.png', '2020-09-10', '10:00:00', '13:00:00', 'Wheelchair Access', 3, FALSE),
      ('Food Drive', 'Collect and distribute food to those in need.', FALSE, 'Community Center Address', 'Community Center City', '45678', 'Opposite the library', 'Collectors, Distributors', 'Thank You Note', 'fooddrive.png', '2024-10-05', '11:00:00', '15:00:00', 'None', 4, TRUE),
      ('Beach Cleanup', 'Join us to clean up the beach and protect marine life.', TRUE, 'Sandy Beach Address', 'Sandy Beach City', '56789', 'Near the pier', 'Trash Pickers, Recyclers', 'Free Lunch', 'beachcleanup.png', '2024-08-20', '09:30:00', '13:30:00', 'Wheelchair Access', 5, TRUE),
      ('Blood Donation Camp', 'Donate blood and save lives.', FALSE, 'Health Center Address', 'Health Center City', '67890', 'Near the entrance', 'Donors, Assistants', 'Certificate', 'blooddonation.png', '2024-08-25', '09:00:00', '14:00:00', 'None', 6, TRUE),
      ('Animal Shelter Visit', 'Visit and help at the local animal shelter.', TRUE, 'Animal Shelter Address', 'Animal Shelter City', '78901', 'Next to the vet clinic', 'Caregivers, Cleaners', 'Thank You Card', 'animalshelter.png', '2024-09-15', '10:00:00', '14:00:00', 'Wheelchair Access', 7, FALSE),
      ('Elderly Home Visit', 'Spend time with the elderly and brighten their day.', TRUE, 'Elderly Home Address', 'Elderly Home City', '89012', 'Behind the hospital', 'Visitors, Entertainers', 'Gift Bag', 'elderlyhome.png', '2024-09-20', '10:30:00', '13:30:00', 'Wheelchair Access', 8, TRUE),
      ('Fundraising Gala', 'Attend a gala to raise funds for charity.', FALSE, 'Grand Hotel Address', 'Grand Hotel City', '90123', 'Ballroom', 'Hosts, Organizers', 'Dinner', 'gala.png', '2024-09-30', '18:00:00', '22:00:00', 'None', 9, FALSE),
      ('Art Exhibition', 'Support local artists by attending an art exhibition.', FALSE, 'Art Gallery Address', 'Art Gallery City', '10123', 'Main Hall', 'Guides, Assistants', 'Art Poster', 'artexhibition.png', '2024-10-10', '17:00:00', '20:00:00', 'None', 10, TRUE),
      ('Youth Workshop', 'Engage in workshops designed for youth empowerment.', TRUE, 'Youth Center Address', 'Youth Center City', '11123', 'Conference Room', 'Speakers, Coordinators', 'Certificate', 'youthworkshop.png', '2024-09-25', '10:00:00', '16:00:00', 'Wheelchair Access', 11, TRUE),
      ('Health Awareness Camp', 'Participate in a health awareness camp.', FALSE, 'Community Hall Address', 'Community Hall City', '12123', 'Main Stage', 'Speakers, Helpers', 'Health Kit', 'healthcamp.png', '2024-08-22', '09:00:00', '14:00:00', 'None', 12, FALSE),
      ('Book Fair', 'Join us at the annual book fair.', FALSE, 'Library Address', 'Library City', '13123', 'Main Atrium', 'Assistants, Cashiers', 'Bookmark', 'bookfair.png', '2024-09-18', '09:30:00', '17:00:00', 'None', 13, TRUE),
      ('Sports Day', 'Participate in a day full of sports activities.', FALSE, 'Sports Complex Address', 'Sports Complex City', '14123', 'Field A', 'Players, Coaches', 'Medal', 'sportsday.png', '2024-10-02', '08:00:00', '17:00:00', 'None', 14, TRUE),
      ('Music Festival', 'Attend a festival celebrating local musicians.', FALSE, 'Concert Grounds Address', 'Concert Grounds City', '15123', 'Main Stage', 'Volunteers, Ushers', 'T-Shirt', 'musicfestival.png', '2024-09-07', '16:00:00', '23:00:00', 'None', 15, FALSE),
      ('Science Fair', 'Explore the latest in science and technology.', FALSE, 'Exhibition Center Address', 'Exhibition Center City', '16123', 'Hall B', 'Exhibitors, Guides', 'Badge', 'sciencefair.png', '2024-09-12', '10:00:00', '18:00:00', 'None', 16, TRUE),
      ('Dance Marathon', 'Join us for a dance marathon to support charity.', FALSE, 'Dance Studio Address', 'Dance Studio City', '17123', 'Main Floor', 'Dancers, Instructors', 'Certificate', 'dancemarathon.png', '2024-09-28', '10:00:00', '22:00:00', 'None', 17, TRUE),
      ('Theater Performance', 'Attend a theater performance by local artists.', FALSE, 'Theater Address', 'Theater City', '18123', 'Main Stage', 'Ushers, Helpers', 'Program Booklet', 'theaterperformance.png', '2024-10-05', '19:00:00', '21:30:00', 'None', 18, TRUE),
      ('Career Fair', 'Network and learn at the annual career fair.', FALSE, 'Convention Center Address', 'Convention Center City', '19123', 'Hall C', 'Assistants, Guides', 'Notebook', 'careerfair.png', '2024-09-14', '09:00:00', '17:00:00', 'None', 19, FALSE),
      ('Cooking Competition', 'Show off your culinary skills at the competition.', FALSE, 'Community Kitchen Address', 'Community Kitchen City', '20123', 'Main Hall', 'Chefs, Judges', 'Apron', 'cookingcompetition.png', '2024-10-15', '11:00:00', '15:00:00', 'None', 20, TRUE);

INSERT INTO volunteer (
    first_name, last_name, gender, dob, email, phone_number, address, postal_code, occupation, qualifications, availability,
    roles, dbs, accessibility_enhancement, about, rewards_earned, emergency_contact_name,
    emergency_phone_number, emergency_relationship, member_status, rating, event_attended
) VALUES
      ('Charlie', 'Brown', 'Male', '1990-01-01', 'charlie.brown@gmail.com', '1234567890', '123 Maple St, Springfield', '12345', 'Engineer', 'Certified First Aid', 'Weekends', 'Coordinator', TRUE, 'Wheelchair Access', 'Enthusiastic volunteer', 10, 'Linus van Pelt', '9876543210', 'Friend', 'Active', 4.5, 5),
      ('Dana', 'White', 'Female', '1985-05-15', 'dana.white@hotmail.com', '3216540987', '456 Elm St, Metropolis', '23456', 'Teacher', 'Teaching Degree', 'Evenings', 'Guide', FALSE, '', 'Passionate about teaching', 5, 'John White', '6543210987', 'Brother', 'Active', 4.7, 3),
      ('Eva', 'Green', 'Female', '1992-02-20', 'eva.green@yahoo.com', '5551234567', '789 Oak St, Gotham', '34567', 'Artist', 'Art Degree', 'Weekdays', 'Entertainer', TRUE, 'Sign Language', 'Creative and compassionate', 8, 'Liam Green', '3456789012', 'Father', 'Active', 4.8, 7),
      ('Frank', 'Blue', 'Male', '1988-03-30', 'frank.blue@outlook.com', '7894561230', '321 Pine St, Star City', '45678', 'Chef', 'Culinary Arts Diploma', 'Anytime', 'Chef', FALSE, '', 'Culinary enthusiast', 12, 'Nancy Blue', '2109876543', 'Mother', 'Inactive', 4.2, 9),
      ('Grace', 'Red', 'Female', '1995-04-25', 'grace.red@gmail.com', '9876543210', '654 Birch St, Central City', '56789', 'Nurse', 'Nursing License', 'Weekends', 'Caregiver', TRUE, 'Braille', 'Caring and dedicated', 15, 'Oscar Red', '6543210987', 'Husband', 'Active', 4.9, 10),
      ('Henry', 'Yellow', 'Male', '1983-06-10', 'henry.yellow@yahoo.com', '6549873210', '987 Cedar St, Coast City', '67890', 'Accountant', 'CPA', 'Weekdays', 'Organizer', FALSE, '', 'Meticulous and reliable', 7, 'Emma Yellow', '5432109876', 'Wife', 'Active', 4.4, 8),
      ('Ivy', 'Purple', 'Female', '1998-07-15', 'ivy.purple@hotmail.com', '3217896543', '258 Walnut St, Keystone City', '78901', 'Student', 'High School Diploma', 'Evenings', 'Assistant', TRUE, 'Wheelchair Access', 'Eager to learn and help', 3, 'Paul Purple', '8765432109', 'Brother', 'Active', 4.6, 4),
      ('Jack', 'Orange', 'Male', '1991-08-08', 'jack.orange@live.com', '2106549873', '321 Maple St, Smallville', '89012', 'Photographer', 'Photography Certificate', 'Weekends', 'Photographer', FALSE, '', 'Creative and innovative', 9, 'Olivia Orange', '9872106543', 'Sister', 'Inactive', 4.3, 6),
      ('Kathy', 'Brown', 'Female', '1987-09-09', 'kathy.brown@gmail.com', '7893216540', '456 Elm St, Smallville', '90123', 'Writer', 'Journalism Degree', 'Anytime', 'Writer', TRUE, 'Sign Language', 'Articulate and passionate', 11, 'Matthew Brown', '4321098765', 'Brother', 'Active', 4.8, 11),
      ('Leo', 'Pink', 'Male', '1984-10-20', 'leo.pink@hotmail.com', '9876543210', '789 Oak St, Star City', '10123', 'Lawyer', 'Law Degree', 'Weekdays', 'Advisor', FALSE, '', 'Analytical and persuasive', 6, 'Sophia Pink', '3456789012', 'Wife', 'Active', 4.7, 7),
      ('Mia', 'Gray', 'Female', '1996-11-25', 'mia.gray@outlook.com', '5551234567', '321 Pine St, Metropolis', '11123', 'Doctor', 'Medical License', 'Weekends', 'Doctor', TRUE, 'Braille', 'Compassionate and skilled', 13, 'Liam Gray', '2109876543', 'Father', 'Active', 4.9, 12),
      ('Noah', 'White', 'Male', '1989-12-30', 'noah.white@gmail.com', '3216540987', '654 Birch St, Gotham', '12123', 'Architect', 'Architecture Degree', 'Evenings', 'Designer', FALSE, '', 'Creative and detail-oriented', 8, 'Emma White', '6543210987', 'Mother', 'Inactive', 4.6, 5),
      ('Olivia', 'Green', 'Female', '1993-01-01', 'olivia.green@yahoo.com', '7894561230', '987 Cedar St, Central City', '13123', 'Psychologist', 'Psychology Degree', 'Anytime', 'Counselor', TRUE, 'Wheelchair Access', 'Empathetic and understanding', 14, 'Oscar Green', '9876543210', 'Husband', 'Active', 4.8, 13),
      ('Paul', 'Blue', 'Male', '1997-02-14', 'paul.blue@hotmail.com', '5559876543', '258 Walnut St, Coast City', '14123', 'Engineer', 'Engineering Degree', 'Weekdays', 'Technician', FALSE, '', 'Innovative and analytical', 5, 'Grace Blue', '5432109876', 'Sister', 'Active', 4.5, 9),
      ('Quinn', 'Yellow', 'Female', '1992-03-19', 'quinn.yellow@live.com', '3217896543', '123 Maple St, Keystone City', '15123', 'Scientist', 'Science Degree', 'Weekends', 'Researcher', TRUE, 'Sign Language', 'Curious and meticulous', 12, 'Frank Yellow', '8765432109', 'Brother', 'Inactive', 4.9, 10),
      ('Rachel', 'Purple', 'Female', '1986-04-22', 'rachel.purple@gmail.com', '2106549873', '456 Elm St, Smallville', '16123', 'Artist', 'Art Degree', 'Evenings', 'Artist', FALSE, '', 'Creative and expressive', 7, 'Henry Purple', '9872106543', 'Father', 'Active', 4.7, 8),
      ('Sam', 'Red', 'Male', '1990-05-25', 'sam.red@yahoo.com', '7893216540', '789 Oak St, Star City', '17123', 'Chef', 'Culinary Arts Diploma', 'Anytime', 'Chef', TRUE, 'Braille', 'Culinary artist', 9, 'Eva Red', '4321098765', 'Mother', 'Inactive', 4.3, 11),
      ('Tina', 'Orange', 'Female', '1985-06-30', 'tina.orange@hotmail.com', '9876543210', '321 Pine St, Metropolis', '18123', 'Teacher', 'Teaching Degree', 'Weekdays', 'Instructor', FALSE, '', 'Dedicated and patient', 11, 'Jack Orange', '3456789012', 'Husband', 'Active', 4.8, 6),
      ('Uma', 'Pink', 'Female', '1994-07-07', 'uma.pink@outlook.com', '5551234567', '654 Birch St, Gotham', '19123', 'Nurse', 'Nursing License', 'Weekends', 'Nurse', TRUE, 'Wheelchair Access', 'Caring and attentive', 15, 'Leo Pink', '2109876543', 'Wife', 'Active', 4.9, 14),
      ('Victor', 'Gray', 'Male', '1982-08-08', 'victor.gray@gmail.com', '3216540987', '987 Cedar St, Central City', '20123', 'Businessman', 'Business Degree', 'Evenings', 'Advisor', FALSE, '', 'Entrepreneurial and strategic', 10, 'Kathy Gray', '6543210987', 'Sister', 'Inactive', 4.6, 13);

INSERT INTO reward (
    date, image, description
) VALUES
      ('2024-08-16', 'reward1.png', 'Certificate of Appreciation'),
      ('2024-08-18', 'reward2.png', 'Free Lunch Voucher'),
      ('2024-09-01', 'reward3.png', 'Medal of Participation'),
      ('2024-09-03', 'reward4.png', 'Event T-Shirt'),
      ('2024-09-05', 'reward5.png', 'Thank You Note'),
      ('2024-09-07', 'reward6.png', 'Gift Card'),
      ('2024-09-09', 'reward7.png', 'Volunteer Badge'),
      ('2024-09-11', 'reward8.png', 'Dinner Coupon'),
      ('2024-09-13', 'reward9.png', 'Exclusive Poster'),
      ('2024-09-15', 'reward10.png', 'Music Concert Ticket'),
      ('2024-09-17', 'reward11.png', 'Art Exhibition Pass'),
      ('2024-09-19', 'reward12.png', 'Health Kit'),
      ('2024-09-21', 'reward13.png', 'Sports Medal'),
      ('2024-09-23', 'reward14.png', 'Book Voucher'),
      ('2024-09-25', 'reward15.png', 'Cooking Class Discount'),
      ('2024-09-27', 'reward16.png', 'Spa Day Pass'),
      ('2024-09-29', 'reward17.png', 'Tech Gadget'),
      ('2024-10-01', 'reward18.png', 'Festival Entry'),
      ('2024-10-03', 'reward19.png', 'Educational Workshop Ticket'),
      ('2024-10-05', 'reward20.png', 'Career Coaching Session');
INSERT INTO volunteer_event (volunteer_id, event_id, approved) VALUES
                                                         (1, 1, false), (2, 1,false), (3, 2,false), (4, 2,false),
                                                         (5, 3, false), (6, 3, false), (7, 4,false),
                                                         (8, 4, false), (9, 5, false), (10, 5,false),
                                                         (11, 6,false), (12, 6, false), (13, 7, false),
                                                         (14, 7,false), (15, 8,false), (16, 8,false),
                                                         (17, 9, false), (18, 9, false), (19, 10, false), (20, 10, false);
INSERT INTO event_reward (reward_id, event_id) VALUES
                                                   (1, 1), (2, 1), (3, 2), (4, 2), (5, 3),
                                                   (6, 3), (7, 4), (8, 4), (9, 5), (10, 5),
                                                   (11, 6), (12, 6), (13, 7), (14, 7), (15, 8),
                                                   (16, 8), (17, 9), (18, 9), (19, 10), (20, 10);
INSERT INTO volunteer_rewards (reward_id, volunteer_id) VALUES
                                                            (1, 1), (2, 2), (3, 3), (4, 4), (5, 5),
                                                            (6, 6), (7, 7), (8, 8), (9, 9), (10, 10),
                                                            (11, 11), (12, 12), (13, 13), (14, 14), (15, 15),
                                                            (16, 16), (17, 17), (18, 18), (19, 19), (20, 20);
INSERT INTO qualifications (name, type) VALUES
-- Educational Qualifications
('High School Diploma', 'Educational'),
('GED (General Education Development)', 'Educational'),
('Associate of Arts (A.A.)', 'Educational'),
('Associate of Science (A.S.)', 'Educational'),
('Associate of Applied Science (A.A.S.)', 'Educational'),
('Bachelor of Arts (B.A.)', 'Educational'),
('Bachelor of Science (B.S.)', 'Educational'),
('Bachelor of Fine Arts (B.F.A.)', 'Educational'),
('Bachelor of Engineering (B.Eng.)', 'Educational'),
('Bachelor of Business Administration (B.B.A.)', 'Educational'),
('Bachelor of Education (B.Ed.)', 'Educational'),
('Bachelor of Technology (B.Tech.)', 'Educational'),
('Master of Arts (M.A.)', 'Educational'),
('Master of Science (M.S.)', 'Educational'),
('Master of Business Administration (M.B.A.)', 'Educational'),
('Master of Fine Arts (M.F.A.)', 'Educational'),
('Master of Education (M.Ed.)', 'Educational'),
('Master of Public Health (M.P.H.)', 'Educational'),
('Master of Social Work (M.S.W.)', 'Educational'),
('Master of Engineering (M.Eng.)', 'Educational'),
('Master of Laws (LL.M.)', 'Educational'),
('Master of Architecture (M.Arch.)', 'Educational'),
('Doctor of Philosophy (Ph.D.)', 'Educational'),
('Doctor of Education (Ed.D.)', 'Educational'),
('Doctor of Business Administration (D.B.A.)', 'Educational'),
('Doctor of Medicine (M.D.)', 'Educational'),
('Juris Doctor (J.D.)', 'Educational'),
('Doctor of Nursing Practice (D.N.P.)', 'Educational'),
('Doctor of Psychology (Psy.D.)', 'Educational'),
('Doctor of Dental Surgery (D.D.S.)', 'Educational'),

-- Professional Certifications
('Certified Ethical Hacker (CEH)', 'Professional Certification'),
('CompTIA A+', 'Professional Certification'),
('CompTIA Network+', 'Professional Certification'),
('CompTIA Security+', 'Professional Certification'),
('Microsoft Certified: Azure Solutions Architect Expert', 'Professional Certification'),
('Google Certified Professional Cloud Architect', 'Professional Certification'),
('Certified Kubernetes Administrator (CKA)', 'Professional Certification'),
('Red Hat Certified Engineer (RHCE)', 'Professional Certification'),
('Certified ScrumMaster (CSM)', 'Professional Certification'),
('Certified Public Accountant (CPA)', 'Professional Certification'),
('Chartered Financial Analyst (CFA)', 'Professional Certification'),
('Certified Financial Planner (CFP)', 'Professional Certification'),
('Chartered Global Management Accountant (CGMA)', 'Professional Certification'),
('Financial Risk Manager (FRM)', 'Professional Certification'),
('Enrolled Agent (EA)', 'Professional Certification'),
('Certified Medical Assistant (CMA)', 'Professional Certification'),
('Licensed Practical Nurse (LPN)', 'Professional Certification'),
('Registered Nurse (RN)', 'Professional Certification'),
('Certified Professional Coder (CPC)', 'Professional Certification'),
('Board of Pharmacy Specialties (BPS) Certification', 'Professional Certification'),
('Project Management Professional (PMP)', 'Professional Certification'),
('Six Sigma Green Belt', 'Professional Certification'),
('Six Sigma Black Belt', 'Professional Certification'),
('Certified Business Analysis Professional (CBAP)', 'Professional Certification'),
('Professional Scrum Master (PSM)', 'Professional Certification'),
('Lean Six Sigma Certification', 'Professional Certification'),
('National Board Certification for Teachers', 'Professional Certification'),
('Montessori Certification', 'Professional Certification'),
('TESOL Certification', 'Professional Certification'),
('International Baccalaureate Certification', 'Professional Certification'),
('Certified Welding Inspector (CWI)', 'Professional Certification'),
('LEED Accredited Professional', 'Professional Certification'),
('Certified Construction Manager (CCM)', 'Professional Certification'),
('Global Professional in Human Resources (GPHR)', 'Professional Certification'),
('SHRM Senior Certified Professional (SHRM-SCP)', 'Professional Certification'),
('Google Analytics Certification', 'Professional Certification'),
('Certified Digital Marketing Professional (CDMP)', 'Professional Certification'),
('Public Relations Society of America Accreditation (APR)', 'Professional Certification'),
('Certified Paralegal (CP)', 'Professional Certification'),
('Certified Compliance & Ethics Professional (CCEP)', 'Professional Certification'),
('Certified Safety Professional (CSP)', 'Professional Certification'),
('Certified Environmental Professional (CEP)', 'Professional Certification'),
('Real Estate Broker License', 'Professional Certification'),
('Accredited Buyer\'s Representative (ABR)', 'Professional Certification'),
('Certified Emergency Manager (CEM)', 'Professional Certification'),
('Professional Certified Coach (PCC)', 'Professional Certification'),
('Certification in Applied Positive Psychology (CAPP)', 'Professional Certification'),

-- Skills and Other Qualifications
('Fluency in specific languages', 'Skill'),
('First Aid and CPR Certification', 'Skill'),
('Diversity and Inclusion Training', 'Skill'),
('Volunteer Experience', 'Skill'),
('Leadership Training', 'Skill'),
('Skills in graphic design', 'Skill'),
('Skills in photography', 'Skill'),
('Skills in multimedia', 'Skill'),
('Proficiency in Adobe Creative Suite', 'Skill'),
('Proficiency in AutoCAD', 'Skill'),
('Experience with social media platforms', 'Skill'),
('Experience with analytics tools', 'Skill'),
('Training in sales strategies', 'Skill');
-- Insert Technology and IT Occupations
INSERT INTO occupations (name, category) VALUES ('Software Engineer', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('Web Developer', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('Data Scientist', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('System Administrator', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('Network Engineer', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('Cybersecurity Analyst', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('IT Support Specialist', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('Database Administrator', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('DevOps Engineer', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('Cloud Solutions Architect', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('Machine Learning Engineer', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('Game Developer', 'Technology and IT');
INSERT INTO occupations (name, category) VALUES ('UX/UI Designer', 'Technology and IT');

-- Insert Healthcare Occupations
INSERT INTO occupations (name, category) VALUES ('Doctor', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Nurse', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Pharmacist', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Dentist', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Physical Therapist', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Occupational Therapist', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Medical Laboratory Technician', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Radiologic Technologist', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Paramedic', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Physician Assistant', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Psychologist', 'Healthcare');
INSERT INTO occupations (name, category) VALUES ('Dietitian/Nutritionist', 'Healthcare');

-- Insert Education Occupations
INSERT INTO occupations (name, category) VALUES ('Teacher (Elementary, Middle, High School)', 'Education');
INSERT INTO occupations (name, category) VALUES ('Professor', 'Education');
INSERT INTO occupations (name, category) VALUES ('Teaching Assistant', 'Education');
INSERT INTO occupations (name, category) VALUES ('School Counselor', 'Education');
INSERT INTO occupations (name, category) VALUES ('Educational Administrator', 'Education');
INSERT INTO occupations (name, category) VALUES ('Special Education Teacher', 'Education');
INSERT INTO occupations (name, category) VALUES ('ESL Teacher', 'Education');
INSERT INTO occupations (name, category) VALUES ('Librarian', 'Education');
INSERT INTO occupations (name, category) VALUES ('Instructional Coordinator', 'Education');

-- Insert Finance and Accounting Occupations
INSERT INTO occupations (name, category) VALUES ('Accountant', 'Finance and Accounting');
INSERT INTO occupations (name, category) VALUES ('Financial Analyst', 'Finance and Accounting');
INSERT INTO occupations (name, category) VALUES ('Auditor', 'Finance and Accounting');
INSERT INTO occupations (name, category) VALUES ('Investment Banker', 'Finance and Accounting');
INSERT INTO occupations (name, category) VALUES ('Financial Advisor', 'Finance and Accounting');
INSERT INTO occupations (name, category) VALUES ('Tax Specialist', 'Finance and Accounting');
INSERT INTO occupations (name, category) VALUES ('Credit Analyst', 'Finance and Accounting');
INSERT INTO occupations (name, category) VALUES ('Actuary', 'Finance and Accounting');
INSERT INTO occupations (name, category) VALUES ('Loan Officer', 'Finance and Accounting');
INSERT INTO occupations (name, category) VALUES ('Payroll Specialist', 'Finance and Accounting');

-- Insert Engineering and Manufacturing Occupations
INSERT INTO occupations (name, category) VALUES ('Civil Engineer', 'Engineering and Manufacturing');
INSERT INTO occupations (name, category) VALUES ('Mechanical Engineer', 'Engineering and Manufacturing');
INSERT INTO occupations (name, category) VALUES ('Electrical Engineer', 'Engineering and Manufacturing');
INSERT INTO occupations (name, category) VALUES ('Chemical Engineer', 'Engineering and Manufacturing');
INSERT INTO occupations (name, category) VALUES ('Aerospace Engineer', 'Engineering and Manufacturing');
INSERT INTO occupations (name, category) VALUES ('Industrial Engineer', 'Engineering and Manufacturing');
INSERT INTO occupations (name, category) VALUES ('Quality Control Inspector', 'Engineering and Manufacturing');
INSERT INTO occupations (name, category) VALUES ('Manufacturing Technician', 'Engineering and Manufacturing');
INSERT INTO occupations (name, category) VALUES ('Production Manager', 'Engineering and Manufacturing');

-- Insert Arts, Design, and Media Occupations
INSERT INTO occupations (name, category) VALUES ('Graphic Designer', 'Arts, Design, and Media');
INSERT INTO occupations (name, category) VALUES ('Photographer', 'Arts, Design, and Media');
INSERT INTO occupations (name, category) VALUES ('Videographer', 'Arts, Design, and Media');
INSERT INTO occupations (name, category) VALUES ('Illustrator', 'Arts, Design, and Media');
INSERT INTO occupations (name, category) VALUES ('Animator', 'Arts, Design, and Media');
INSERT INTO occupations (name, category) VALUES ('Art Director', 'Arts, Design, and Media');
INSERT INTO occupations (name, category) VALUES ('Musician', 'Arts, Design, and Media');
INSERT INTO occupations (name, category) VALUES ('Actor', 'Arts, Design, and Media');
INSERT INTO occupations (name, category) VALUES ('Writer/Author', 'Arts, Design, and Media');
INSERT INTO occupations (name, category) VALUES ('Journalist', 'Arts, Design, and Media');

-- Insert Sales and Marketing Occupations
INSERT INTO occupations (name, category) VALUES ('Sales Representative', 'Sales and Marketing');
INSERT INTO occupations (name, category) VALUES ('Marketing Manager', 'Sales and Marketing');
INSERT INTO occupations (name, category) VALUES ('Brand Manager', 'Sales and Marketing');
INSERT INTO occupations (name, category) VALUES ('Social Media Specialist', 'Sales and Marketing');
INSERT INTO occupations (name, category) VALUES ('Content Creator', 'Sales and Marketing');
INSERT INTO occupations (name, category) VALUES ('Advertising Executive', 'Sales and Marketing');
INSERT INTO occupations (name, category) VALUES ('Market Research Analyst', 'Sales and Marketing');
INSERT INTO occupations (name, category) VALUES ('Public Relations Specialist', 'Sales and Marketing');

-- Insert Legal and Compliance Occupations
INSERT INTO occupations (name, category) VALUES ('Lawyer', 'Legal and Compliance');
INSERT INTO occupations (name, category) VALUES ('Paralegal', 'Legal and Compliance');
INSERT INTO occupations (name, category) VALUES ('Legal Secretary', 'Legal and Compliance');
INSERT INTO occupations (name, category) VALUES ('Judge', 'Legal and Compliance');
INSERT INTO occupations (name, category) VALUES ('Compliance Officer', 'Legal and Compliance');
INSERT INTO occupations (name, category) VALUES ('Contract Administrator', 'Legal and Compliance');
INSERT INTO occupations (name, category) VALUES ('Legal Analyst', 'Legal and Compliance');

-- Insert Human Resources Occupations
INSERT INTO occupations (name, category) VALUES ('HR Manager', 'Human Resources');
INSERT INTO occupations (name, category) VALUES ('Recruiter', 'Human Resources');
INSERT INTO occupations (name, category) VALUES ('Training and Development Specialist', 'Human Resources');
INSERT INTO occupations (name, category) VALUES ('Compensation and Benefits Manager', 'Human Resources');
INSERT INTO occupations (name, category) VALUES ('HR Analyst', 'Human Resources');

-- Insert Hospitality and Tourism Occupations
INSERT INTO occupations (name, category) VALUES ('Hotel Manager', 'Hospitality and Tourism');
INSERT INTO occupations (name, category) VALUES ('Travel Agent', 'Hospitality and Tourism');
INSERT INTO occupations (name, category) VALUES ('Chef', 'Hospitality and Tourism');
INSERT INTO occupations (name, category) VALUES ('Event Planner', 'Hospitality and Tourism');
INSERT INTO occupations (name, category) VALUES ('Tour Guide', 'Hospitality and Tourism');
INSERT INTO occupations (name, category) VALUES ('Restaurant Manager', 'Hospitality and Tourism');
INSERT INTO occupations (name, category) VALUES ('Front Desk Receptionist', 'Hospitality and Tourism');

-- Insert Construction and Trades Occupations
INSERT INTO occupations (name, category) VALUES ('Carpenter', 'Construction and Trades');
INSERT INTO occupations (name, category) VALUES ('Electrician', 'Construction and Trades');
INSERT INTO occupations (name, category) VALUES ('Plumber', 'Construction and Trades');
INSERT INTO occupations (name, category) VALUES ('Construction Manager', 'Construction and Trades');
INSERT INTO occupations (name, category) VALUES ('Heavy Equipment Operator', 'Construction and Trades');
INSERT INTO occupations (name, category) VALUES ('Welder', 'Construction and Trades');
INSERT INTO occupations (name, category) VALUES ('Bricklayer', 'Construction and Trades');

-- Insert Transportation and Logistics Occupations
INSERT INTO occupations (name, category) VALUES ('Truck Driver', 'Transportation and Logistics');
INSERT INTO occupations (name, category) VALUES ('Pilot', 'Transportation and Logistics');
INSERT INTO occupations (name, category) VALUES ('Flight Attendant', 'Transportation and Logistics');
INSERT INTO occupations (name, category) VALUES ('Logistician', 'Transportation and Logistics');
INSERT INTO occupations (name, category) VALUES ('Supply Chain Manager', 'Transportation and Logistics');
INSERT INTO occupations (name, category) VALUES ('Warehouse Manager', 'Transportation and Logistics');
INSERT INTO occupations (name, category) VALUES ('Delivery Driver', 'Transportation and Logistics');

-- Insert Science and Research Occupations
INSERT INTO occupations (name, category) VALUES ('Research Scientist', 'Science and Research');
INSERT INTO occupations (name, category) VALUES ('Lab Technician', 'Science and Research');
INSERT INTO occupations (name, category) VALUES ('Environmental Scientist', 'Science and Research');
INSERT INTO occupations (name, category) VALUES ('Chemist', 'Science and Research');
INSERT INTO occupations (name, category) VALUES ('Biologist', 'Science and Research');
INSERT INTO occupations (name, category) VALUES ('Physicist', 'Science and Research');
INSERT INTO occupations (name, category) VALUES ('Geologist', 'Science and Research');

-- Insert Public Service and Administration Occupations
INSERT INTO occupations (name, category) VALUES ('Police Officer', 'Public Service and Administration');
INSERT INTO occupations (name, category) VALUES ('Firefighter', 'Public Service and Administration');
INSERT INTO occupations (name, category) VALUES ('Public Administrator', 'Public Service and Administration');
INSERT INTO occupations (name, category) VALUES ('Social Worker', 'Public Service and Administration');
INSERT INTO occupations (name, category) VALUES ('Policy Analyst', 'Public Service and Administration');
INSERT INTO occupations (name, category) VALUES ('Urban Planner', 'Public Service and Administration');
INSERT INTO occupations (name, category) VALUES ('Government Official', 'Public Service and Administration');

-- Insert Retail Occupations
INSERT INTO occupations (name, category) VALUES ('Retail Manager', 'Retail');
INSERT INTO occupations (name, category) VALUES ('Cashier', 'Retail');
INSERT INTO occupations (name, category) VALUES ('Sales Associate', 'Retail');
INSERT INTO occupations (name, category) VALUES ('Inventory Specialist', 'Retail');
INSERT INTO occupations (name, category) VALUES ('Merchandiser', 'Retail');
INSERT INTO occupations (name, category) VALUES ('Customer Service Representative', 'Retail');

-- Insert accessibility needed into the accessibility_enhancement table
INSERT INTO accessibility_enhancement (name) VALUES
                                                 ('Wheelchair Accessibility'),
                                                 ('Barrier-Free Facilities'),
                                                 ('Reserved Parking'),
                                                 ('Accessible Viewing Areas'),
                                                 ('Sensory-Friendly Spaces'),
                                                 ('Visual Aids'),
                                                 ('Audio Aids'),
                                                 ('Sign Language Interpretation'),
                                                 ('Communication Boards and Apps'),
                                                 ('Accessible Event Information'),
                                                 ('Trained Staff and Volunteers'),
                                                 ('First Aid and Medical Support'),
                                                 ('Service Animal Accommodations'),
                                                 ('Transport Services'),
                                                 ('Inclusive Programming'),
                                                 ('Accessible Ticketing Options');

-- Insert availability options into the availability table
INSERT INTO availability (available_days) VALUES
                                              ('Full Time'),
                                              ('Part Time'),
                                              ('Weekdays'),
                                              ('Weekends'),
                                              ('Mornings'),
                                              ('Afternoons'),
                                              ('Evenings'),
                                              ('Nights'),
                                              ('Flexible Schedule'),
                                              ('One-Time Events'),
                                              ('Short-Term Projects'),
                                              ('Long-Term Commitment'),
                                              ('Seasonal Availability'),
                                              ('Remote Opportunities'),
                                              ('Emergency Availability');
-- Insert yearly turnover into the turnover table
INSERT INTO turnover (types) VALUES
                                 ('Under £50k'),
                                 ('£50k - £250k'),
                                 ('£250k - £500k'),
                                 ('£500k - £1m'),
                                 ('£1m - £2m'),
                                 ('Above £2m');
-- Insert roles required for Volunteering to the roles table
INSERT INTO roles (title) VALUES
                              ('Event Coordinator'),
                              ('Volunteer Coordinator'),
                              ('Logistics Manager'),
                              ('Registration Staff'),
                              ('Security Personnel'),
                              ('Medical Staff'),
                              ('Technical Support'),
                              ('Marketing and Communications Team'),
                              ('Referees or Judges'),
                              ('Hospitality Team'),
                              ('Grounds Crew'),
                              ('Sponsorship Manager'),
                              ('Finance Manager'),
                              ('Awards and Ceremonies Coordinator'),
                              ('Catering Manager'),
                              ('Transport Manager'),
                              ('VIP Liaison'),
                              ('Social Media Coordinator'),
                              ('Environment and Sustainability Officer'),
                              ('Emergency Response Team');


-- Add some initial data
INSERT INTO User (username, password) VALUES
('user1', 'password1'),
('user2', 'password2'),
('user3', 'password3'),
('user4', 'password4'),
('user5', 'password5'),
('user6', 'password6'),
('user7', 'password7'),
('user8', 'password8'),
('user9', 'password9'),
('user10', 'password10');

INSERT INTO reward_point_requirements (imageName, pointsRequired)
VALUES
    ('1 - Outstanding Organiser Recognition', 5),
    ('2 - Exceptional Performance Star', 10),
    ('3 - Commendable Team Contributor', 15),
    ('4 - Leadership Excellence Award', 20),
    ('5 - Innovation Award', 25),
    ('6 -Community Impact Award', 30),
    ('7 - Dedication Award', 35),
    ('8 - Mentorship Award', 40),
    ('9 - Event Coordination Award', 45),
    ('10 - Volunteer of The Month Award', 50),
    ('11 - Long Service Award', 55),
    ('12 - Youth Volunteer Award', 60),
    ('13 - Health and Safety Award', 65),
    ('14 - Environmental Stewardship Award', 70),
    ('15 - Team Leadership Award', 75),
    ('16 - Customer Service Award', 80),
    ('17 - Event Promotion Award', 85),
    ('18 - Problem Solver Award', 90),
    ('19 - Inclusivity Award', 95),
    ('20 - Volunteer Coordinator Award', 100),
    ('21 - Event Logistics Award', 105),
    ('22 - Fundraising Award', 110),
    ('23 - Special Recognition Award', 115);

INSERT INTO ContactInfo (name, email, subject, message) VALUES
('John Doe', 'john.doe@example.com', 'Inquiry', 'I would like to know more about your services.'),
('Jane Smith', 'jane.smith@example.com', 'Support', 'I need help with my account.'),
('Sam Wilson', 'sam.wilson@example.com', 'Feedback', 'Great service! Keep up the good work.');
