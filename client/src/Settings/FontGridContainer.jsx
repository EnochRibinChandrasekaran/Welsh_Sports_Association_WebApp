import React from 'react';
import TextFieldsIcon from '@mui/icons-material/TextFields';
import { Grid, Box, Typography, Divider } from '@mui/material';

const darkColors = [
    { bg: 'black', fg: 'yellow' },
    { bg: 'black', fg: 'white' },
    { bg: 'green', fg: 'yellow' },
    { bg: 'green', fg: 'white' },
    { bg: 'blue', fg: 'yellow' },
    { bg: 'blue', fg: 'white' },
    { bg: 'red', fg: 'yellow' },
    { bg: 'red', fg: 'white' },
    { bg: 'blue', fg: 'gray' },
    { bg: 'gray', fg: 'yellow' },
    { bg: 'darkgray', fg: 'yellow' },
    { bg: 'purple', fg: 'yellow' },
    { bg: 'purple', fg: 'white' },
];

const lightColors = [
    { bg: 'beige', fg: 'red' },
    { bg: 'beige', fg: 'gray' },
    { bg: 'gray', fg: 'yellow' },
    { bg: 'gray', fg: 'black' },
    { bg: 'yellow', fg: 'red' },
    { bg: 'yellow', fg: 'black' },
    { bg: 'yellow', fg: 'blue' },
    { bg: 'yellow', fg: 'green' },
    { bg: 'white', fg: 'red' },
    { bg: 'white', fg: 'black' },
    { bg: 'white', fg: 'brown' },
    { bg: 'white', fg: 'blue' },
];

export const FontGridContainer = ({ setFontColor, setBackgroundColor }) => {

    const setColors = (bg, fg) => {
        setFontColor(fg);
        setBackgroundColor(bg);
    }

    return (
        <div>
            <Typography variant="h5" gutterBottom>
                Dark Background
            </Typography>
            <Grid container spacing={1}>
                {darkColors.map(({ bg, fg }, index) => (
                    <Grid item xs={3} key={index}>
                        <Box
                            display="flex"
                            justifyContent="center"
                            alignItems="center"
                            bgcolor={bg}
                            color={fg}
                            width={25}
                            height={25}
                            style={{ textAlign: 'center', margin: 'auto', padding: 10, border: '2px solid black', cursor: 'pointer' }}
                            onClick={() => setColors(bg, fg)}
                        >
                            <TextFieldsIcon style={{ fontSize: 20 }} />
                        </Box>
                    </Grid>
                ))}
            </Grid>
            <Divider />
            <Typography variant="h5" gutterBottom style={{ marginTop: 20 }}>
                Light Background
            </Typography>
            <Grid container spacing={2}>
                {lightColors.map(({ bg, fg }, index) => (
                    <Grid item xs={3} key={index}>
                        <Box
                            display="flex"
                            justifyContent="center"
                            alignItems="center"
                            bgcolor={bg}
                            color={fg}
                            width={25}
                            height={25}
                            style={{ textAlign: 'center', margin: 'auto', padding: 10, border: '2px solid black', cursor: 'pointer' }}
                            onClick={() => setColors(bg, fg)}
                        >
                            <TextFieldsIcon style={{ fontSize: 20 }} />
                        </Box>
                    </Grid>
                ))}
            </Grid>
        </div>
    );
};