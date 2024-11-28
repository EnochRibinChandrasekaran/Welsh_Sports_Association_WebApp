import React, { useEffect, useState } from 'react';
import {
    Container,
    Box,
    Typography,
    Grid,
    Paper,
    IconButton,
    InputBase,
    Select,
    MenuItem,
    Divider,
    Pagination
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import axios from 'axios';
import { BASE_URL } from '../../apiConfig';
import { VolunteerCard } from './VolunteerCard';

export const AdminVolunteers = ({ onPageChange, type = 'volunteer' }) => {
    const [volunteers, setVolunteers] = useState([]);
    const [pageNum, setPageNum] = useState(0);

    useEffect(() => {
        const endpoint =
            type === 'volunteer'
                ? `${BASE_URL}/api/volunteer/getPageVolunteer?page=${pageNum}&size=10`
                : `${BASE_URL}/api/organiser/getPageOrganiser?page=${pageNum}&size=10`;

        axios.get(endpoint)
            .then(response => {
                setVolunteers(response.data.content);
            })
            .catch(error => {
                console.error('There was an error fetching the data!', error);
            });

    }, [pageNum, type]);

    const handleSearchSubmit = (event) => {
        event.preventDefault();
        // Handle search logic here
    };

    return (
        <Container sx={{ mt: 4, padding: 2 }}>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
                <Typography variant="h5" sx={{ mb: 2 }}>
                    {type === 'volunteer' ? 'Volunteers' : 'Organizers'}
                </Typography>
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
                    <Paper
                        component="form"
                        sx={{
                            display: 'flex',
                            alignItems: 'center',
                            width: 400,
                            p: '2px 4px'
                        }}
                        onSubmit={handleSearchSubmit}
                    >
                        <InputBase sx={{ ml: 1, flex: 1 }} placeholder="Search" inputProps={{ 'aria-label': 'search' }} />
                        <IconButton type="submit" sx={{ p: '10px' }} aria-label="search">
                            <SearchIcon />
                        </IconButton>
                    </Paper>
                    <Select defaultValue="Date" sx={{ minWidth: 120 }}>
                        <MenuItem value="Date">Sort by Date</MenuItem>
                        <MenuItem value="Name">Sort by Name</MenuItem>
                    </Select>
                </Box>
            </Box>

            <Divider />
            <Grid container spacing={2} sx={{ mt: 2 }}>
                {volunteers?.map((volunteer) => (
                    <Grid item xs={12} key={volunteer.id}>
                        <VolunteerCard
                            id={volunteer.id}
                            name={volunteer.name}
                            role={volunteer.role}
                            email={volunteer.email}
                            onPageChange={onPageChange}
                            type={type}
                        />
                    </Grid>
                ))}
            </Grid>

            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 2 }}>
                <Pagination count={4} onChange={(e, page) => setPageNum(page - 1)} />
            </Box>
        </Container>
    );
};