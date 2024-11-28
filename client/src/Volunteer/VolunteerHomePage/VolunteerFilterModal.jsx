import * as React from 'react';
import { Popover, Button, FormGroup, FormControlLabel, Checkbox, TextField, MenuItem } from '@mui/material';
import { useState } from 'react';


export default function VolunteerFilterPopup() {
    const [anchorEl, setAnchorEl] = useState(null);
    const [filters, setFilters] = useState({
        eventType: [],
        location: '',
        dateRange: '',
        category: [],
        ageGroup: [],
        skillLevel: '',
        price: '',
        accessibility: [],
        organizer: '',
        duration: '',
        availability: '',
    });

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleFilterChange = (filterName) => (event) => {
        setFilters({
            ...filters,
            [filterName]: event.target.value || event.target.checked,
        });
    };

    const handleCheckboxChange = (filterName) => (event) => {
        const currentValue = filters[filterName];
        if (event.target.checked) {
            setFilters({
                ...filters,
                [filterName]: [...currentValue, event.target.value],
            });
        } else {
            setFilters({
                ...filters,
                [filterName]: currentValue.filter((value) => value !== event.target.value),
            });
        }
    };

    const open = Boolean(anchorEl);
    const id = open ? 'simple-popover' : undefined;

    return (
        <div>
            <Button aria-describedby={id} variant="contained" onClick={handleClick}>
                Open Popover
            </Button>
            <Popover
                id={id}
                open={open}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'left',
                }}
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'center',
                }}
            >
                <div style={{ padding: '16px', maxWidth: '300px' }}>
                    <FormGroup>
                        <TextField
                            select
                            label="Event Type"
                            value={filters.eventType}
                            onChange={handleFilterChange('eventType')}
                            SelectProps={{
                                multiple: true,
                            }}
                            variant="outlined"
                            margin="normal"
                        >
                            <MenuItem value="Sports">Sports</MenuItem>
                            <MenuItem value="Conferences">Conferences</MenuItem>
                            <MenuItem value="Workshops">Workshops</MenuItem>
                            <MenuItem value="Competitions">Competitions</MenuItem>
                            <MenuItem value="Training Sessions">Training Sessions</MenuItem>
                        </TextField>

                        <TextField
                            label="Location"
                            value={filters.location}
                            onChange={handleFilterChange('location')}
                            variant="outlined"
                            margin="normal"
                        />

                        <TextField
                            label="Date Range"
                            type="date"
                            value={filters.dateRange}
                            onChange={handleFilterChange('dateRange')}
                            variant="outlined"
                            margin="normal"
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />

                        <TextField
                            select
                            label="Category"
                            value={filters.category}
                            onChange={handleFilterChange('category')}
                            SelectProps={{
                                multiple: true,
                            }}
                            variant="outlined"
                            margin="normal"
                        >
                            <MenuItem value="Running">Running</MenuItem>
                            <MenuItem value="Swimming">Swimming</MenuItem>
                            <MenuItem value="Cycling">Cycling</MenuItem>
                            <MenuItem value="Multi-sport">Multi-sport</MenuItem>
                            <MenuItem value="Outdoor Activities">Outdoor Activities</MenuItem>
                            <MenuItem value="Indoor Activities">Indoor Activities</MenuItem>
                        </TextField>

                        <FormControlLabel
                            control={
                                <Checkbox
                                    checked={filters.ageGroup.includes('Children')}
                                    onChange={handleCheckboxChange('ageGroup')}
                                    value="Children"
                                />
                            }
                            label="Children"
                        />
                        <FormControlLabel
                            control={
                                <Checkbox
                                    checked={filters.ageGroup.includes('Teens')}
                                    onChange={handleCheckboxChange('ageGroup')}
                                    value="Teens"
                                />
                            }
                            label="Teens"
                        />
                        <FormControlLabel
                            control={
                                <Checkbox
                                    checked={filters.ageGroup.includes('Adults')}
                                    onChange={handleCheckboxChange('ageGroup')}
                                    value="Adults"
                                />
                            }
                            label="Adults"
                        />
                        <FormControlLabel
                            control={
                                <Checkbox
                                    checked={filters.ageGroup.includes('Seniors')}
                                    onChange={handleCheckboxChange('ageGroup')}
                                    value="Seniors"
                                />
                            }
                            label="Seniors"
                        />

                        <TextField
                            select
                            label="Skill Level"
                            value={filters.skillLevel}
                            onChange={handleFilterChange('skillLevel')}
                            variant="outlined"
                            margin="normal"
                        >
                            <MenuItem value="Beginner">Beginner</MenuItem>
                            <MenuItem value="Intermediate">Intermediate</MenuItem>
                            <MenuItem value="Advanced">Advanced</MenuItem>
                        </TextField>

                        <TextField
                            label="Price"
                            value={filters.price}
                            onChange={handleFilterChange('price')}
                            variant="outlined"
                            margin="normal"
                        />

                        <FormControlLabel
                            control={
                                <Checkbox
                                    checked={filters.accessibility.includes('Wheelchair Accessible')}
                                    onChange={handleCheckboxChange('accessibility')}
                                    value="Wheelchair Accessible"
                                />
                            }
                            label="Wheelchair Accessible"
                        />
                        <FormControlLabel
                            control={
                                <Checkbox
                                    checked={filters.accessibility.includes('Family-Friendly')}
                                    onChange={handleCheckboxChange('accessibility')}
                                    value="Family-Friendly"
                                />
                            }
                            label="Family-Friendly"
                        />
                        <FormControlLabel
                            control={
                                <Checkbox
                                    checked={filters.accessibility.includes('Pet-Friendly')}
                                    onChange={handleCheckboxChange('accessibility')}
                                    value="Pet-Friendly"
                                />
                            }
                            label="Pet-Friendly"
                        />

                        <TextField
                            label="Organizer"
                            value={filters.organizer}
                            onChange={handleFilterChange('organizer')}
                            variant="outlined"
                            margin="normal"
                        />

                        <TextField
                            select
                            label="Duration"
                            value={filters.duration}
                            onChange={handleFilterChange('duration')}
                            variant="outlined"
                            margin="normal"
                        >
                            <MenuItem value="Half-Day">Half-Day</MenuItem>
                            <MenuItem value="Full-Day">Full-Day</MenuItem>
                            <MenuItem value="Multi-Day">Multi-Day</MenuItem>
                        </TextField>

                        <TextField
                            select
                            label="Availability"
                            value={filters.availability}
                            onChange={handleFilterChange('availability')}
                            variant="outlined"
                            margin="normal"
                        >
                            <MenuItem value="Tickets Available">Tickets Available</MenuItem>
                            <MenuItem value="Registration Required">Registration Required</MenuItem>
                            <MenuItem value="Open for Walk-ins">Open for Walk-ins</MenuItem>
                        </TextField>
                    </FormGroup>
                    <Button variant="contained" color="primary" onClick={handleClose} fullWidth>
                        Apply Filters
                    </Button>
                </div>
            </Popover>
        </div>
    );
}