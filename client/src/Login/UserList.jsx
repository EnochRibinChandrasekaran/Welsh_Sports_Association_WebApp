import React, { useEffect, useState } from 'react';

export const UserList = () => {
    const [users, setUsers] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetch('http://localhost:8080/api/users')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => setUsers(data))
            .catch(error => setError('Failed to fetch users: ' + error.message));
    }, []);

    return (
        <div>
            <h2>All Users</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <ul>
                {users.length > 0 ? (
                    users.map(user => (
                        <li key={user.id}>
                            {user.username} - {user.password}
                        </li>
                    ))
                ) : (
                    <p>No users found</p>
                )}
            </ul>
        </div>
    );
};
