import React, { useEffect, useState } from 'react';
import {
    Container, Box, Typography, Grid, Paper, IconButton, InputBase, Select, MenuItem, Divider, Pagination
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import { EventCard } from './EventCard';
import axios from 'axios';
import { BASE_URL } from '../../apiConfig';

export const AdminEvents = ({ onPageChange, onEventSelect }) => {
    const [events, setEvents] = useState([]);
    const [approvedEvents, setApprovedEvents] = useState([]);
    const [pageNum, setPageNum] = useState(0);
    const [searchQuery, setSearchQuery] = useState('');
    const [sortOption, setSortOption] = useState('Date');

    useEffect(() => {
        axios.get(`${BASE_URL}/api/events?page=${pageNum}&size=10`)
            .then(response => {
                const allEvents = response.data.content;
                setEvents(allEvents.filter(event => !event.approved));
                setApprovedEvents(allEvents.filter(event => event.approved));
            })
            .catch(error => {
                console.error('There was an error fetching the events!', error);
            });
    }, [pageNum]);

    const handleSearchChange = (e) => {
        setSearchQuery(e.target.value);
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        // Apply the search logic here
    };

    const handleSortChange = (e) => {
        setSortOption(e.target.value);
        // Apply sorting logic here based on the selected sort option
    };

    return (
        <Container sx={{ mt: 4, padding: 2 }}>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
                <Typography variant="h5" sx={{ mb: 2 }}>Events Management</Typography>
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
                    <Paper
                        component="form"
                        sx={{ display: 'flex', alignItems: 'center', width: 400, p: '2px 4px' }}
                        onSubmit={handleSearchSubmit}
                    >
                        <InputBase
                            sx={{ ml: 1, flex: 1 }}
                            placeholder="Search"
                            inputProps={{ 'aria-label': 'search events' }}
                            value={searchQuery}
                            onChange={handleSearchChange}
                        />
                        <IconButton type="submit" sx={{ p: '10px' }} aria-label="search">
                            <SearchIcon />
                        </IconButton>
                    </Paper>
                    <Select value={sortOption} sx={{ minWidth: 120 }} onChange={handleSortChange}>
                        <MenuItem value="Date">Sort by Date</MenuItem>
                        <MenuItem value="Name">Sort by Name</MenuItem>
                    </Select>
                </Box>
            </Box>

            <Divider />

            <Typography variant="h6" sx={{ mt: 2 }}>Upcoming Events</Typography>
            <Grid container spacing={2} sx={{ mt: 2 }}>
                {events.map((event) => (
                    <Grid item xs={12} key={event.id}>
                        <EventCard
                            date={new Date(event.date).toLocaleDateString('en-US', { month: 'short', day: 'numeric' })?.toUpperCase()}
                            name={event.name}
                            time={`${new Date(event.startTime).toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' })} - ${new Date(event.endTime).toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' })}`}
                            onPageChange={(page) => {
                                onEventSelect(event);
                                onPageChange(page);
                            }}
                            event={event}
                        />
                    </Grid>
                ))}
            </Grid>

            <Typography variant="h6" sx={{ mt: 4 }}>Approved Events</Typography>
            <Grid container spacing={2} sx={{ mt: 2 }}>
                {approvedEvents.map((event) => (
                    <Grid item xs={12} key={event.id}>
                        <EventCard
                            date={new Date(event.date).toLocaleDateString('en-US', { month: 'short', day: 'numeric' })?.toUpperCase()}
                            name={event.name}
                            time={`${new Date(event.startTime).toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' })} - ${new Date(event.endTime).toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' })}`}
                            onPageChange={(page) => {
                                onEventSelect(event);
                                onPageChange(page);
                            }}
                            event={event}
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
