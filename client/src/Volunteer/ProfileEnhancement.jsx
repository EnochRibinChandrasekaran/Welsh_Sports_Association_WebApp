import React, { useState } from 'react';
import './ProfileEnhancement.css';
import axios from 'axios';

const ProfileEnhancement = () => {
    const [formData, setFormData] = useState({
        phoneNumber: '',
        dob: '',
        address: '',
        postalCode: '',
        occupation: '',
        qualifications: '',
        availability: '',
        roles: '',
        dbs: '',
        accessibilityEnhancement: '',
        about: '',
        rewardsEarned: '',
        emergencyContactName: '',
        emergencyPhoneNumber: '',
        emergencyRelationship: '',
        goals: '',
        interests: '',
        references: '',
        agreeToPolicies: '',
        memberStatus: '',
        rating: '',
        membershipLevel: '',
        eventAttended: '',
        gender: '',
        image: ''
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

        // Construct the complete Volunteer object
        const volunteer = {
            phoneNumber: formData.phoneNumber,
            dob: formData.dob,
            address: formData.address,
            postalCode: formData.postalCode,
            occupation: formData.occupation,
            qualifications: formData.qualifications,
            availability: formData.availability,
            roles: formData.roles,
            dbs: formData.dbs,
            accessibilityEnhancement: formData.accessibilityEnhancement,
            about: formData.about,
            rewardsEarned: formData.rewardsEarned,
            emergencyContactName: formData.emergencyContactName,
            emergencyPhoneNumber: formData.emergencyPhoneNumber,
            emergencyRelationship: formData.emergencyRelationship,
            goals: formData.goals,
            interests: formData.interests,
            references: formData.references,
            agreeToPolicies: formData.agreeToPolicies,
            memberStatus: formData.memberStatus,
            rating: formData.rating,
            membershipLevel: formData.membershipLevel,
            eventAttended: formData.eventAttended,
            gender: formData.gender,
            image: formData.image,
        };

        try {
            const response = await axios.post('http://localhost:8080/api/profile-enhancement', volunteer);
            console.log('Profile enhancement successful:', response.data);
            // Redirect or show a success message
        } catch (error) {
            console.error('Error enhancing profile:', error);
        }
    };

    return (
        <div className="form-container">
            <h2>Enhance Your Profile</h2>
            <form onSubmit={handleSubmit} className="enhancement-form">
                <label>
                    Phone Number:
                    <input
                        type="text"
                        name="phoneNumber"
                        value={formData.phoneNumber}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Date of Birth:
                    <input
                        type="date"
                        name="dob"
                        value={formData.dob}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Address:
                    <input
                        type="text"
                        name="address"
                        value={formData.address}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Postal Code:
                    <input
                        type="text"
                        name="postalCode"
                        value={formData.postalCode}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Occupation:
                    <input
                        type="text"
                        name="occupation"
                        value={formData.occupation}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Qualifications:
                    <input
                        type="text"
                        name="qualifications"
                        value={formData.qualifications}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Availability:
                    <input
                        type="text"
                        name="availability"
                        value={formData.availability}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Roles:
                    <input
                        type="text"
                        name="roles"
                        value={formData.roles}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    DBS:
                    <input
                        type="text"
                        name="dbs"
                        value={formData.dbs}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Accessibility Enhancement:
                    <input
                        type="text"
                        name="accessibilityEnhancement"
                        value={formData.accessibilityEnhancement}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    About:
                    <textarea
                        name="about"
                        value={formData.about}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Rewards Earned:
                    <input
                        type="text"
                        name="rewardsEarned"
                        value={formData.rewardsEarned}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Emergency Contact Name:
                    <input
                        type="text"
                        name="emergencyContactName"
                        value={formData.emergencyContactName}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Emergency Phone Number:
                    <input
                        type="text"
                        name="emergencyPhoneNumber"
                        value={formData.emergencyPhoneNumber}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Emergency Relationship:
                    <input
                        type="text"
                        name="emergencyRelationship"
                        value={formData.emergencyRelationship}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Goals:
                    <input
                        type="text"
                        name="goals"
                        value={formData.goals}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Interests:
                    <input
                        type="text"
                        name="interests"
                        value={formData.interests}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    References:
                    <input
                        type="text"
                        name="references"
                        value={formData.references}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Agree to Policies:
                    <input
                        type="checkbox"
                        name="agreeToPolicies"
                        checked={formData.agreeToPolicies}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Member Status:
                    <input
                        type="text"
                        name="memberStatus"
                        value={formData.memberStatus}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Rating:
                    <input
                        type="number"
                        name="rating"
                        value={formData.rating}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Membership Level:
                    <input
                        type="text"
                        name="membershipLevel"
                        value={formData.membershipLevel}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Events Attended:
                    <input
                        type="text"
                        name="eventAttended"
                        value={formData.eventAttended}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Gender:
                    <input
                        type="text"
                        name="gender"
                        value={formData.gender}
                        onChange={handleChange}
                    />
                </label>
                <label>
                    Profile Image URL:
                    <input
                        type="text"
                        name="image"
                        value={formData.image}
                        onChange={handleChange}
                    />
                </label>
                <button type="submit" className="submit-button">Enhance Profile</button>
            </form>
        </div>
    );
};

export default ProfileEnhancement;
