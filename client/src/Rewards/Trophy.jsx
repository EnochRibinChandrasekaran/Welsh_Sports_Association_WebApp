import React, { useState, useEffect } from "react";
import "./Trophy.css";
import { BASE_URL } from "../apiConfig";
import axios from "axios";

// Updated import statements for the new award icons
import outstandingOrganiserRecognition from "./trophy-images/1-outstanding-organiser-recognition.png";
import exceptionalPerformanceStar from "./trophy-images/2-exceptional-performance-star.png";
import commendableTeamContributor from "./trophy-images/3-commendable-team-contributor.png";
import leadershipExcellenceAward from "./trophy-images/4-leadership-excellence-award.png";
import innovationAward from "./trophy-images/5-innovation-award.png";
import communityImpactAward from "./trophy-images/6-community-impact-award.png";
import dedicationAward from "./trophy-images/7-dedication-award.png";
import mentorshipAward from "./trophy-images/8-mentorship-award.png";
import eventCoordinationAward from "./trophy-images/9-event-coordination-award.png";
import volunteerOfTheMonthAward from "./trophy-images/10-volunteer-of-the-month-award.png";
import longServiceAward from "./trophy-images/11-long-service-award.png";
import youthVolunteerAward from "./trophy-images/12-youth-volunteer-award.png";
import healthAndSafetyAward from "./trophy-images/13-health-and-safety-award.png";
import environmentalStewardshipAward from "./trophy-images/14-environmental-stewardship-award.png";
import teamLeadershipAward from "./trophy-images/15-team-leadership-award.png";
import customerServiceAward from "./trophy-images/16-customer-service-award.png";
import eventPromotionAward from "./trophy-images/17-event-promotion-award.png";
import problemSolverAward from "./trophy-images/18-problem-solver-award.png";
import inclusivityAward from "./trophy-images/19-inclusivity-award.png";
import volunteerCoordinatorAward from "./trophy-images/20-volunteer-coordinator-award.png";
import eventLogisticsAward from "./trophy-images/21-event-logistics-award.png";
import fundraisingAward from "./trophy-images/22-fundraising-award.png";
import specialRecognitionAward from "./trophy-images/23-special-recognition-award.png";
import VolunteerHeader from "../Volunteer/Header/VolunteerHeader";
import VolunteerFooter from "../Volunteer/Footer/VolunteerFooter";

// Existing imports for the top icons
// import logo1 from "./1-user-icon.png";
// import logo2 from "./2-bell-icon.png";
// import logo6 from "./6-home-icon.png";
// import logo7 from "./7-calender-icon.png";
// import logo8 from "./8-chat-icon.png";
// import logo9 from "./9-trophy-icon.png";
// import logo10 from "./10-wsa-icon.png";

export const Trophy = () => {
  const [setPoints] = useState(0); // const [points, setPoints] = useState(0); // Initialize points state
  const [userId] = useState(1); // const [userId, setUserId] = useState(1); // Temporary user ID, replace with actual user logic

  const awards = [
    {
      title: "Outstanding Organiser Recognition",
      points: 5,
      img: outstandingOrganiserRecognition,
      description:
        "Awarded for exceptional organizational skills and leadership.",
    },
    {
      title: "Exceptional Performance Star",
      points: 10,
      img: exceptionalPerformanceStar,
      description:
        "Given for outstanding performance in events and activities.",
    },
    {
      title: "Commendable Team Contributor",
      points: 15,
      img: commendableTeamContributor,
      description:
        "Recognized for excellent teamwork and collaborative spirit.",
    },
    {
      title: "Leadership Excellence Award",
      points: 20,
      img: leadershipExcellenceAward,
      description:
        "Awarded for demonstrating exceptional leadership qualities.",
    },
    {
      title: "Innovation Award",
      points: 25,
      img: innovationAward,
      description: "Given for introducing innovative ideas and solutions.",
    },
    {
      title: "Community Impact Award",
      points: 30,
      img: communityImpactAward,
      description:
        "Awarded for making a significant positive impact on the community.",
    },
    {
      title: "Dedication Award",
      points: 35,
      img: dedicationAward,
      description: "Recognized for unwavering dedication and commitment.",
    },
    {
      title: "Mentorship Award",
      points: 40,
      img: mentorshipAward,
      description: "Given for outstanding mentorship and guidance.",
    },
    {
      title: "Event Coordination Award",
      points: 45,
      img: eventCoordinationAward,
      description:
        "Awarded for exceptional event planning and coordination skills.",
    },
    {
      title: "Volunteer of the Month Award",
      points: 50,
      img: volunteerOfTheMonthAward,
      description:
        "Recognized as the volunteer of the month for exemplary service.",
    },
    {
      title: "Long Service Award",
      points: 55,
      img: longServiceAward,
      description:
        "Given for long-term service and dedication to the organization.",
    },
    {
      title: "Youth Volunteer Award",
      points: 60,
      img: youthVolunteerAward,
      description:
        "Awarded to an outstanding youth volunteer for their contributions.",
    },
    {
      title: "Health and Safety Award",
      points: 65,
      img: healthAndSafetyAward,
      description:
        "Recognized for promoting health and safety in the workplace.",
    },
    {
      title: "Environmental Stewardship Award",
      points: 70,
      img: environmentalStewardshipAward,
      description:
        "Given for significant contributions to environmental conservation.",
    },
    {
      title: "Team Leadership Award",
      points: 75,
      img: teamLeadershipAward,
      description: "Awarded for exceptional leadership within a team.",
    },
    {
      title: "Customer Service Award",
      points: 80,
      img: customerServiceAward,
      description: "Recognized for providing excellent customer service.",
    },
    {
      title: "Event Promotion Award",
      points: 85,
      img: eventPromotionAward,
      description:
        "Given for outstanding efforts in event promotion and marketing.",
    },
    {
      title: "Problem Solver Award",
      points: 90,
      img: problemSolverAward,
      description: "Awarded for exceptional problem-solving abilities.",
    },
    {
      title: "Inclusivity Award",
      points: 95,
      img: inclusivityAward,
      description: "Recognized for promoting inclusivity and diversity.",
    },
    {
      title: "Volunteer Coordinator Award",
      points: 100,
      img: volunteerCoordinatorAward,
      description: "Given for exceptional skills in volunteer coordination.",
    },
    {
      title: "Event Logistics Award",
      points: 105,
      img: eventLogisticsAward,
      description: "Awarded for outstanding logistics management in events.",
    },
    {
      title: "Fundraising Award",
      points: 110,
      img: fundraisingAward,
      description:
        "Recognized for outstanding contributions to fundraising efforts.",
    },
    {
      title: "Special Recognition Award",
      points: 115,
      img: specialRecognitionAward,
      description: "Given for special recognition of unique contributions.",
    },
  ];

  useEffect(() => {
    axios
      .get(`${BASE_URL}/rewards/getRewards`)
      .then((response) => {
        const userRewards = response.data.filter(
          (reward) => reward.userId === userId
        );
        const totalPoints = userRewards.reduce(
          (sum, reward) => sum + reward.points,
          0
        );
        setPoints(totalPoints);
        console.log(totalPoints);
      })
      .catch((error) => {
        console.error("There was an error fetching the rewards!", error);
      });
  }, [userId]);

  return (
    <>
      <VolunteerHeader logged={true} volunteerID={1} />
      <div className="trophy-container">
        {awards?.map((award, index) => (
          <div className="trophy" key={index}>
            <img src={award.img} alt={award.title} className="trophy-icon" />
            <div className="trophy-details">
              <div className="trophy-title">{award.title}</div>
              <div className="trophy-description">{award.description}</div>
              <div className="trophy-points right-aligned-text">
                You need {award.points} points to unlock this award.
              </div>
            </div>
          </div>
        ))}
      </div>
      <VolunteerFooter />
    </>
  );
};
