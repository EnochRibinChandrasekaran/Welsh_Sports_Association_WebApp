// Note this is just a test Register page

import React, { useState } from 'react';
import './Register.css';

export const RegisterForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log({ username, password });
    };

    return (
        <div className='register-form-container'>
        <form className="register-form" onSubmit={handleSubmit}>
            <h2>Register as WSA Volunteer</h2>
            <label>
                Username or Email:
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} required />
            </label>
            <label>
                Password:
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
            </label>
            <label>
                Confirm Password:
                <input type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} required />
            </label>
            <button type="submit">Register</button>
        </form>
        </div>
    );
};
