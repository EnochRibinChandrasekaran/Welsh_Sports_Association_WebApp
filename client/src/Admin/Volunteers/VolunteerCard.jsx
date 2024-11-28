import React from 'react';
import { Card, CardContent, Avatar, Typography, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';

export const VolunteerCard = ({ id, path, name, email, role, onPageChange, type }) => {
    const onEdit = (type) => {
        if (type === 'volunteer') {
            onPageChange('volunteerInfo', id);
        } else {
            onPageChange('organiserInfo', id);
        }
    };

    return (
        <Card sx={{ display: 'flex', alignItems: 'center', padding: 1 }}>
            <Avatar
                src={path}
                alt={name}
                sx={{ width: 56, height: 56, marginRight: 2 }}
            />
            <CardContent sx={{ flex: 1 }}>
                <Typography variant="h6" component="div">
                    {name}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    {email}
                </Typography>
            </CardContent>
            <Typography sx={{ marginRight: 2 }}>{role}</Typography>
            <IconButton onClick={() => onEdit(type)}>
                <EditIcon />
            </IconButton>
        </Card>
    );
};
