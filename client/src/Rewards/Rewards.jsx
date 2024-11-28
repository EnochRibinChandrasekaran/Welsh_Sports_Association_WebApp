import React, { useState, useEffect } from 'react';
import './Rewards.css';
import { useParams } from 'react-router-dom'; // Import to access route parameters
import { BASE_URL } from "../apiConfig";
import axios from "axios";

// Import additional logos
import logo1 from './1-user-icon.png';
import logo2 from './2-bell-icon.png';
import logo6 from './6-home-icon.png';
import logo7 from './7-calender-icon.png';
import logo8 from './8-chat-icon.png';
import logo9 from './9-trophy-icon.png';
import logo10 from './10-wsa-icon.png';

import logo11 from './trophy-images/1-outstanding-organiser-recognition.png';
import logo12 from './trophy-images/2-exceptional-performance-star.png';
import logo13 from './trophy-images/3-commendable-team-contributor.png';
import logo14 from './trophy-images/4-leadership-excellence-award.png'
import logo15 from './trophy-images/5-innovation-award.png';
import logo16 from './trophy-images/6-community-impact-award.png';
import logo17 from './trophy-images/7-dedication-award.png';
import logo18 from './trophy-images/8-mentorship-award.png';
import logo19 from './trophy-images/9-event-coordination-award.png';
import logo20 from './trophy-images/10-volunteer-of-the-month-award.png';
import logo21 from './trophy-images/11-long-service-award.png';
import logo22 from './trophy-images/12-youth-volunteer-award.png';
import logo23 from './trophy-images/13-health-and-safety-award.png';
import logo24 from './trophy-images/14-environmental-stewardship-award.png';
import logo25 from './trophy-images/15-team-leadership-award.png';
import logo26 from './trophy-images/16-customer-service-award.png';
import logo27 from './trophy-images/17-event-promotion-award.png';
import logo28 from './trophy-images/18-problem-solver-award.png';
import logo29 from './trophy-images/19-inclusivity-award.png';
import logo30 from './trophy-images/20-volunteer-coordinator-award.png';
import logo31 from './trophy-images/21-event-logistics-award.png';
import logo32 from './trophy-images/22-fundraising-award.png';
import logo33 from './trophy-images/23-special-recognition-award.png';





// Ensure that the import names match the strings returned by the API
// this means make sure image names added to reward_point_requirements table in DB is included here (".png" included).
// in data.sql.
const imageMapping = {
    '1 - Outstanding Organiser Recognition': logo11,
    '2 - Exceptional Performance Star': logo12,
    '3 - Commendable Team Contributor': logo13,
    '4 - Leadership Excellence Award': logo14,
    '5 - Innovation Award': logo15,
    '6 -Community Impact Award': logo16,
    '7 - Dedication Award': logo17,
    '8 - Mentorship Award': logo18,
    '9 - Event Coordination Award': logo19,
    '10 - Volunteer of The Month Award': logo20,
    '11 - Long Service Award': logo21,
    '12 - Youth Volunteer Award': logo22,
    '13 - Health and Safety Award': logo23,
    '14 - Environmental Stewardship Award': logo24,
    '15 - Team Leadership Award': logo25,
    '16 - Customer Service Award': logo26,
    '17 - Event Promotion Award': logo27,
    '18 - Problem Solver Award': logo28,
    '19 - Inclusivity Award': logo29,
    '20 - Volunteer Coordinator Award': logo30,
    '21 - Event Logistics Award': logo31,
    '22 - Fundraising Award': logo32,
    '23 - Special Recognition Award': logo33,






};

export const Rewards = () => {
    const { userId } = useParams(); // Access the userId from the route
    const [points, setPoints] = useState(0);
    const [rewardsList, setRewardsList] = useState([]); // State to store the list of rewards

    // First useEffect: Fetch points based on userId
    useEffect(() => {
        axios.get(`${BASE_URL}/rewards/getPoints`, { params: { userId } })
            .then(response => {
                console.log(response.data);
                setPoints(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the points!', error);
            });
    }, [userId]);

    // Second useEffect: Fetch rewards based on points
    useEffect(() => {
        if (points > 0) { // Only fetch rewards if points have been set
            axios.get(`${BASE_URL}/rewards/getRewards`, { params: { total_points: points } })
                // Controller mapped to /getRewards expects "total_points" as a parameter.
                .then(response => {
                    console.log(response.data);
                    setRewardsList(response.data); // Set the rewards list with the data from the response
                })
                .catch(error => {
                    console.error('There was an error fetching the rewards list!', error);
                });
        }
    }, [points]);

    return (
        <div className="app">
            <a href="/" className="home-link">
                <img src={logo10} alt="WSA Logo" className="top-left-icon larger-icon" />
            </a>
            <div className="header">
                <img src={logo1} alt="User Icon" className="header-icon" />
                <h1>Rewards</h1>
                <img src={logo2} alt="Bell Icon" className="header-icon" />
            </div>

            {/* Dynamically generate reward items based on list retrieved from getRewards */}
            <div className="rewards-container">
                {rewardsList.map((reward, index) => (
                    <div className="reward" key={index}>
                        <img src={imageMapping[reward]} alt={reward} className="reward-icon" />
                        <div className="reward-text">{reward.replace(/-/g, ' ')}</div>
                    </div>
                ))}
            </div>

            <div className="points">
                <p>Congratulations! You have earned {points} points for your contributions!</p>
                <a href="/trophy" className="view-rewards-btn">View all possible Rewards!</a>
            </div>
            <nav className="bottom-nav">
                <a href="/"><img src={logo6} alt="Home" /></a>
                <a href="/volunteer/eventcalendar"><img src={logo7} alt="Calendar" /></a>
                <a href="/"><img src={logo8} alt="Chat" /></a>
                <a href="/trophy"><img src={logo9} alt="Trophy" /></a>
            </nav>
        </div>
    );
};

// export default Rewards;
