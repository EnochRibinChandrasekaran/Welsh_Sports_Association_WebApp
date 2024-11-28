import React, { useState } from 'react';
import './Login.css';
import { useNavigate } from 'react-router-dom';

// Import the Header and Footer components
import VolunteerHeader from '../Volunteer/Header/VolunteerHeader';
import VolunteerFooter from '../Volunteer/Footer/VolunteerFooter';

export const LoginForm = () => {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password }),
            });

            if (response.ok) {
                const data = await response.json();
                const userId = data.userId;
                // Redirect to personalized rewards page using the user ID
                navigate(`/rewards/${userId}`);
            } else {
                const data = await response.json();
                setError(data.message || 'Invalid credentials');
            }
        } catch (error) {
            console.error('Error during login:', error);
            setError('An error occurred. Please try again.');
        }
    };


    return (
        <>
            <VolunteerHeader logged={false} />
            <div className='login-form-container'>
                <form className="login-form" onSubmit={handleSubmit}>
                    <h2>Login</h2>
                    {error && <p style={{ color: 'red' }}>{error}</p>}
                    <div className="form-group">
                        <label>
                            Username or Email:
                            <input
                                type="text"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required
                            />
                        </label>
                    </div>
                    <div className="form-group">
                        <label>
                            Password:
                            <input
                                type="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </label>
                    </div>
                    <div className="form-actions">
                        <button type="submit">Login</button>
                        <button type="button" onClick={() => navigate('/register')} className="register-link">
                            Register as WSA Volunteer?
                        </button>
                    </div>
                </form>
            </div>
            <VolunteerFooter />
        </>
    );
};
