import React, { useState } from 'react';
import './SignUp.css';
import axios from 'axios';
import {useNavigate} from "react-router-dom";
import VolunteerHeader from "../Volunteer/Header/VolunteerHeader";
import VolunteerFooter from "../Volunteer/Footer/VolunteerFooter";

const BasicSignUp = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        username: '',
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: '',
    });

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData({
            ...formData,
            [name]: type === 'checkbox' ? checked : value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (formData.password !== formData.confirmPassword) {
            alert('Passwords do not match.');
            return;
        }

        // Construct the complete Volunteer object with optional fields set to null
        const volunteer = {
            username: formData.username,
            password: formData.password,
            firstName: formData.firstName,
            lastName: formData.lastName,
            email: formData.email,

            // Optional fields
            phoneNumber: null,
            dob: null,
            address: null,
            postalCode: null,
            occupation: null,
            qualifications: null,
            availability: null,
            roles: null,
            dbs: null,
            accessibilityEnhancement: null,
            about: null,
            rewardsEarned: null,
            emergencyContactName: null,
            emergencyPhoneNumber: null,
            emergencyRelationship: null,
            goals: null,
            interests: null,
            references: null,
            agreeToPolicies: null,
            memberStatus: null,
            rating: null,
            membershipLevel: null,
            eventAttended: null,
            gender: null,
            image: null,
        };

        try {
            const response = await axios.post('http://localhost:8080/api/signup', volunteer);
            console.log('Signup successful:', response.data);
            // Redirect user. Have this go to profile enhancement later
            navigate("/")
        } catch (error) {
            console.error('Error signing up:', error);
        }
    };

    return (
        <div className="form-container">
            <VolunteerHeader logged={false}  volunteerID={1}/>
            <h2>Create Your Account</h2>
            <form onSubmit={handleSubmit} className="signup-form">
                <label>
                    Display Name:
                    <input
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        required
                    />
                </label>
                <label>
                    First Name:
                    <input
                        type="text"
                        name="firstName"
                        value={formData.firstName}
                        onChange={handleChange}
                        required
                    />
                </label>
                <label>
                    Last Name:
                    <input
                        type="text"
                        name="lastName"
                        value={formData.lastName}
                        onChange={handleChange}
                        required
                    />
                </label>
                <label>
                    Email Address:
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                        pattern="[^@\s]+@[^@\s]+\.[^@\s]+"
                    />
                </label>
                <label>
                    Password:
                    <input
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </label>
                <label>
                    Confirm Password:
                    <input
                        type="password"
                        name="confirmPassword"
                        value={formData.confirmPassword}
                        onChange={handleChange}
                        required
                    />
                </label>
                <button type="submit" className="submit-button">Sign Up</button>
            </form>
            <VolunteerFooter />
        </div>
    );
};

export default BasicSignUp;
