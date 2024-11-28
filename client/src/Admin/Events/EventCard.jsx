import React, { useState } from 'react';
import { Card, CardContent, Box, Typography, IconButton, Menu, MenuItem } from '@mui/material';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { BASE_URL } from '../../apiConfig';
import axios from 'axios';


export const EventCard = ({ date, name, time, onPageChange, event }) => {
    const [anchorEl, setAnchorEl] = useState(null);

    const handleMenuOpen = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleMenuClose = async (menuOption) => {
        setAnchorEl(null);

        if (menuOption === 'Edit') {
            onPageChange('eventInfo', event);
        } else { 
            const eventData = {
                ...event,
                approved : true
            }

            console.log(eventData);

            await axios.put(`${BASE_URL}/api/events/${event.id}`, eventData).then(response => {
                console.log(response);
            }).catch(error => {
                console.log(error);
            });
        }
    }

    return (
        <Card sx={{ mb: 2 }}>
            <CardContent sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                    <Box sx={{
                        width: 50,
                        height: 50,
                        backgroundColor: '#CC0000',
                        color: 'white',
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'center',
                        alignItems: 'center',
                        mr: 2,
                    }}>
                        <Typography variant="body2" sx={{ fontWeight: 'bold' }}>
                            {date.split(' ')[0]}
                        </Typography>
                        <Typography variant="body2">{date.split(' ')[1]}</Typography>
                    </Box>
                    <Box>
                        <Typography variant="h6">{name}</Typography>
                        <Typography variant="body2">{time}</Typography>
                    </Box>
                </Box>
                <IconButton onClick={handleMenuOpen}>
                    <MoreVertIcon />
                </IconButton>
                <Menu
                    anchorEl={anchorEl}
                    open={Boolean(anchorEl)}
                    onClose={() => handleMenuClose('')}
                >
                    {!event.approved && <MenuItem onClick={() => handleMenuClose('Approve')}>Approve</MenuItem>}
                    <MenuItem onClick={() => handleMenuClose('Edit')}>Edit</MenuItem>
                </Menu>
            </CardContent>
        </Card>
    );
};
