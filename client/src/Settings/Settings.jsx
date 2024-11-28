import React, { useState, useEffect } from "react";
import SettingsAccessibilityRoundedIcon from '@mui/icons-material/SettingsAccessibilityRounded';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import { RxReader } from "react-icons/rx";
import { FaRegKeyboard, FaRulerHorizontal } from "react-icons/fa";
import { FontGridContainer } from "./FontGridContainer";
import { Ruler } from "./Ruler";
import { HiOutlineSpeakerWave } from "react-icons/hi2";
import { MdMonochromePhotos } from "react-icons/md";
import { MdOutlineWaterDrop } from "react-icons/md";
import { MdWaterDrop } from "react-icons/md";

import {
    Drawer,
    Accordion,
    Typography,
    AccordionSummary,
    AccordionDetails,
    Divider,
    Box,
    Grid
} from "@mui/material";
import { HexColorPicker } from "react-colorful";
import HomePage from "../Home/HomePage";
import { ReadFocus } from "./ReadFocus";
import { TextReader } from "./TextReader";

export const Settings = () => {
    const [open, setOpen] = useState(false);
    const [backgroundColor, setBackgroundColor] = useState("white");
    const [fontColor, setFontColor] = useState("gray");
    const [isEnableReadFocus, setEnableReadFocus] = useState(false);
    const [isEnableRuler, setEnableRuler] = useState(false);
    const [isEnableSpeechText, setEnableTextSpeech] = useState(false);
    const [colorMode, setColorMode] = useState('');
    const [activeSetting, setActiveSetting] = useState('');

    useEffect(() => {
        const fonts = ['a', 'p', 'h1', 'h2', 'h3', 'h5', 'span', 'strong'];

        fonts.forEach(font => {
            const elements = document.getElementsByTagName(font);

            for (let i = 0; i < elements.length; i++) {
                if (elements[i]) {
                    elements[i].style.color = fontColor;
                }
            }
        });
    }, [fontColor, backgroundColor]);

    const toggleDrawer = (newOpen) => () => {
        setOpen(newOpen);
    };

    const toggleReadFocus = () => {
        setEnableReadFocus((prev) => !prev);
        setActiveSetting(('readFocus'))
    };

    const toggleRuler = () => {
        setEnableRuler((prev) => !prev);
        setActiveSetting(('ruler'))
    };

    const toggleSpeechText = () => {
        setEnableTextSpeech((prev) => !prev);
        setActiveSetting(('readText'));
    };

    const selectColorMode = (inputMode) => {
        const isExistingMode = inputMode === colorMode;

        setColorMode(!isExistingMode && inputMode || '');
    }

    const colorModeStyles = {
        'monochrome': 'grayscale(100%)',
        'lowSaturation': 'saturate(50%)',
        'highSaturation': 'saturate(200%)'
    }

    return (
        <div style={{ filter: colorModeStyles[colorMode] }}>
            <SettingsAccessibilityRoundedIcon
                sx={{ position: 'fixed', bottom: 50, right: 50 }}
                onClick={toggleDrawer(true)}
                anchor={'right'}
            />

            {(() => {
                switch (activeSetting) {
                    case 'readFocus':
                        return (
                            <ReadFocus isEnableReadFocus={isEnableReadFocus}>
                                <HomePage backgroundColor={backgroundColor} color={fontColor} />
                            </ReadFocus>
                        );
                    case 'ruler':
                        return (
                            <Ruler isEnableRuler={isEnableRuler}>
                                <HomePage backgroundColor={backgroundColor} color={fontColor} />
                            </Ruler>
                        );
                    case 'readText':
                        return (
                            <TextReader isEnableSpeechText={isEnableSpeechText}>
                                <HomePage backgroundColor={backgroundColor} color={fontColor} />
                            </TextReader>
                        );
                    default:
                        return (
                            // <Tooltip>
                                <HomePage backgroundColor={backgroundColor} color={fontColor} />
                            // </Tooltip>
                        );
                }
            })()}

            <Drawer open={open} onClose={toggleDrawer(false)} >
                <Accordion>
                    <AccordionSummary
                        expandIcon={<ArrowDropDownIcon />}
                        aria-controls="panel1-content"
                        id="panel1-header"
                    >
                        <Typography>Blindness</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                    </AccordionDetails>
                </Accordion>
                <Accordion>
                    <AccordionSummary
                        expandIcon={<ArrowDropDownIcon />}
                        aria-controls="panel2-content"
                        id="panel2-header"
                    >
                        <Typography>Motor Skills Disorders</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <Box
                            sx={{
                                width: '120px',
                                height: '100px',
                                border: '1px solid #e0e0e0',
                                borderRadius: '5px',
                                display: 'flex',
                                flexDirection: 'column',
                                alignItems: 'center',
                                justifyContent: 'center',
                                padding: '10px',
                                cursor: 'pointer',
                                '&:hover': {
                                    backgroundColor: '#e2e8f0',
                                },
                            }}
                        >
                            <FaRegKeyboard style={{ fontSize: '40px', color: '#007b9e' }} />
                            <Typography style={{ marginTop: '10px', color: '#007b9e', fontSize: '10px' }}>
                                Keyboard Navigation
                            </Typography>
                        </Box>
                    </AccordionDetails>
                </Accordion>
                <Accordion>
                    <AccordionSummary
                        expandIcon={<ArrowDropDownIcon />}
                        aria-controls="panel2-content"
                        id="panel2-header"
                    >
                        <Typography>Color Blindness</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <FontGridContainer setBackgroundColor={setBackgroundColor} setFontColor={setFontColor} />
                        <Divider sx={{ height: '10px' }} />
                        <div className="flex gap-4">
                            <div>
                                <Typography variant="h6">Background Color</Typography>
                                <HexColorPicker color={backgroundColor} onChange={setBackgroundColor} cursor={'pointer'} />
                            </div>
                            <div>
                                <Typography variant="h6">Font Color</Typography>
                                <HexColorPicker color={fontColor} onChange={setFontColor} />
                            </div>
                        </div>
                    </AccordionDetails>
                </Accordion>
                <Accordion>
                    <AccordionSummary
                        expandIcon={<ArrowDropDownIcon />}
                        aria-controls="panel2-content"
                        id="panel2-header"
                    >
                        <Typography>Visually Impaired</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <Grid container spacing={2}>
                            <Grid item xs={6}>
                                <Box
                                    sx={{
                                        width: '100%',
                                        height: '100px',
                                        border: '1px solid #e0e0e0',
                                        borderRadius: '5px',
                                        display: 'flex',
                                        flexDirection: 'column',
                                        alignItems: 'center',
                                        justifyContent: 'center',
                                        padding: '10px',
                                        cursor: 'pointer',
                                        '&:hover': {
                                            backgroundColor: '#e2e8f0',
                                        },
                                    }}
                                    onClick={() => selectColorMode('monochrome')}
                                >
                                    <MdMonochromePhotos style={{ fontSize: '40px', color: '#007b9e' }} />
                                    <Typography style={{ marginTop: '10px', color: '#007b9e', fontSize: '14px' }}>
                                        Monochrome
                                    </Typography>
                                </Box>
                            </Grid>

                            <Grid item xs={6}>
                                <Box
                                    sx={{
                                        width: '100%',
                                        height: '100px',
                                        border: '1px solid #e0e0e0',
                                        borderRadius: '5px',
                                        display: 'flex',
                                        flexDirection: 'column',
                                        alignItems: 'center',
                                        justifyContent: 'center',
                                        padding: '10px',
                                        cursor: 'pointer',
                                        '&:hover': {
                                            backgroundColor: '#e2e8f0',
                                        },
                                    }}
                                    onClick={() => selectColorMode('lowSaturation')}
                                >
                                    <MdOutlineWaterDrop style={{ fontSize: '40px', color: '#007b9e' }} />
                                    <Typography style={{ marginTop: '10px', color: '#007b9e', fontSize: '14px' }}>
                                        Low Saturation
                                    </Typography>
                                </Box>
                            </Grid>

                            <Grid item xs={6}>
                                <Box
                                    sx={{
                                        width: '100%',
                                        height: '100px',
                                        border: '1px solid #e0e0e0',
                                        borderRadius: '5px',
                                        display: 'flex',
                                        flexDirection: 'column',
                                        alignItems: 'center',
                                        justifyContent: 'center',
                                        padding: '10px',
                                        cursor: 'pointer',
                                        '&:hover': {
                                            backgroundColor: '#e2e8f0',
                                        },
                                    }}
                                    onClick={() => selectColorMode('highSaturation')}
                                >
                                    <MdWaterDrop style={{ fontSize: '40px', color: '#007b9e' }} />
                                    <Typography style={{ marginTop: '10px', color: '#007b9e', fontSize: '14px' }}>
                                        High Saturation
                                    </Typography>
                                </Box>
                            </Grid>
                        </Grid>
                    </AccordionDetails>
                </Accordion>
                <Accordion>
                    <AccordionSummary
                        expandIcon={<ArrowDropDownIcon />}
                        aria-controls="panel2-content"
                        id="panel2-header"
                    >
                        <Typography>Epilepsy</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                    </AccordionDetails>
                </Accordion>
                <Accordion>
                    <AccordionSummary
                        expandIcon={<ArrowDropDownIcon />}
                        aria-controls="panel2-content"
                        id="panel2-header"
                    >
                        <Typography>Learning Disabilities</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <Box
                            sx={{
                                width: '120px',
                                height: '100px',
                                border: '1px solid #e0e0e0',
                                borderRadius: '5px',
                                display: 'flex',
                                flexDirection: 'column',
                                alignItems: 'center',
                                justifyContent: 'center',
                                padding: '10px',
                                cursor: 'pointer',
                                '&:hover': {
                                    backgroundColor: '#e2e8f0',
                                },
                            }}
                            onClick={toggleReadFocus}
                        >
                            <RxReader style={{ fontSize: '40px', color: '#007b9e' }} />
                            <Typography style={{ marginTop: '10px', color: '#007b9e', fontSize: '14px' }}>
                                Read Focus
                            </Typography>
                        </Box>
                    </AccordionDetails>
                </Accordion>
                <Accordion>
                    <AccordionSummary
                        expandIcon={<ArrowDropDownIcon />}
                        aria-controls="panel2-content"
                        id="panel2-header"
                    >
                        <Typography>Elder</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <div className="flex gap-4">
                            <Box
                                sx={{
                                    width: '120px',
                                    height: '100px',
                                    border: '1px solid #e0e0e0',
                                    borderRadius: '5px',
                                    display: 'flex',
                                    flexDirection: 'column',
                                    alignItems: 'center',
                                    justifyContent: 'center',
                                    padding: '10px',
                                    cursor: 'pointer',
                                    '&:hover': {
                                        backgroundColor: '#e2e8f0',
                                    },
                                }}
                                onClick={toggleRuler}
                            >
                                <FaRulerHorizontal style={{ fontSize: '40px', color: '#007b9e' }} />
                                <Typography style={{ marginTop: '10px', color: '#007b9e', fontSize: '12px' }}>
                                    Ruler
                                </Typography>
                            </Box>
                            <Box
                                sx={{
                                    width: '120px',
                                    height: '100px',
                                    border: '1px solid #e0e0e0',
                                    borderRadius: '5px',
                                    display: 'flex',
                                    flexDirection: 'column',
                                    alignItems: 'center',
                                    justifyContent: 'center',
                                    padding: '10px',
                                    cursor: 'pointer',
                                    '&:hover': {
                                        backgroundColor: '#e2e8f0',
                                    },
                                }}
                                onClick={toggleSpeechText}
                            >
                                <HiOutlineSpeakerWave style={{ fontSize: '40px', color: '#007b9e' }} />
                                <Typography style={{ marginTop: '10px', color: '#007b9e', fontSize: '12px' }}>
                                    Read Text
                                </Typography>
                            </Box>
                        </div>
                    </AccordionDetails>
                </Accordion>
            </Drawer>
        </div>
    );
};