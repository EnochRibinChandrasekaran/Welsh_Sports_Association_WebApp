import React, { useRef, useEffect, useState } from "react";
import axios from "axios";
import { BASE_URL } from "../../apiConfig";
import {
  Box,
  Button,
  CardContent,
  CardMedia,
  Grid,
  Typography,
  Modal,
  IconButton,
  Alert,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import { ThemeProvider, createTheme } from "@mui/material/styles";

const theme = createTheme({
  typography: {
    allVariants: {
      fontFamily: "Poppins, sans-serif",
      textTransform: "none",
    },
  },
});

const EventModel = ({ event, isVisible, toggleModal, volunteerID }) => {
  const modalRef = useRef(null);
  const [eventInfo, setEventInfo] = useState({});
  const [eventRegistered, setEventRegistered] = useState(false);
  const [successMessage, setSuccessMessage] = useState(false);
  const [errorMessage, setErrorMessage] = useState(false);

  // Fetch event details
  useEffect(() => {
    const fetchEventInfo = async () => {
      try {
        const [eventInfo, registered] = await Promise.all([
          axios.get(`${BASE_URL}/api/event/${event}`),
          axios.get(
            `${BASE_URL}/api/volunteer/${volunteerID}/event-accept/${event}`
          ),
        ]);
        setEventInfo(eventInfo.data);
        setEventRegistered(registered.data);
        setErrorMessage(false);
        setSuccessMessage(false);
      } catch (error) {
        console.error("Error fetching event information: ", error);
      }
    };
    fetchEventInfo();
  }, [event]);

  // Handle clicking outside the modal to close it
  const handleOutsideClick = (event) => {
    if (modalRef.current && !modalRef.current.contains(event.target)) {
      toggleModal();
    }
  };

  // Ensure outside click is detected
  useEffect(() => {
    document.addEventListener("mousedown", handleOutsideClick);
    return () => {
      document.removeEventListener("mousedown", handleOutsideClick);
    };
  }, []);

  const handleSubmit = async () => {
    try {
      setEventRegistered(true);
      const response = await axios.put(
        `${BASE_URL}/api/volunteer/${volunteerID}/event-accept/${eventInfo.id}`
      );

      if (response.status === 200) {
        setSuccessMessage(true);
        setErrorMessage(false);
      } else {
        setSuccessMessage(false);
        setErrorMessage(true);
      }
    } catch (error) {
      setSuccessMessage(false);
      setErrorMessage(true);
    }
  };

  const rolesNeeded = Array.isArray(eventInfo.rolesNeeded)
    ? eventInfo.rolesNeeded
    : typeof eventInfo.rolesNeeded === "string"
    ? eventInfo.rolesNeeded.split(",").map((role) => role.trim())
    : [];

  const accessibilityAssistance = Array.isArray(
    eventInfo.accessibilityAssistance
  )
    ? eventInfo.accessibilityAssistance
    : typeof eventInfo.accessibilityAssistance === "string"
    ? eventInfo.accessibilityAssistance.split(",").map((item) => item.trim())
    : [];

  return (
    <ThemeProvider theme={theme}>
      <Modal open={isVisible} onClose={toggleModal}>
        <Box
          ref={modalRef}
          sx={{
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: "80%",
            maxHeight: "90%",
            bgcolor: "background.paper",
            boxShadow: 24,
            p: 4,
            borderRadius: 2,
            overflowY: "auto",
          }}
        >
          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              mb: 2,
            }}
          >
            <Typography variant="h5" sx={{ fontWeight: "bold" }}>
              {eventInfo.name}
            </Typography>
            <IconButton
              edge="end"
              color="inherit"
              onClick={toggleModal}
              aria-label="close"
            >
              <CloseIcon />
            </IconButton>
          </Box>
          {successMessage && (
            <Alert sx={{ my: 1 }} severity="success">
              Profile Information has been updated
            </Alert>
          )}
          {errorMessage && (
            <Alert sx={{ my: 1 }} severity="error">
              Please try after later.
            </Alert>
          )}

          <CardContent>
            <Typography variant="body1" sx={{ mb: 2 }}>
              {eventInfo.description}
            </Typography>
            {eventInfo.eventImage && (
              <CardMedia
                component="img"
                height="200"
                image={`data:image/png;base64,${eventInfo.eventImage}`}
                alt={eventInfo.name}
                sx={{ mb: 2 }}
              />
            )}

            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <Typography variant="h6" gutterBottom>
                  Location
                </Typography>
                <Typography variant="body2">
                  {eventInfo.address}, {eventInfo.city}, {eventInfo.postalCode}
                </Typography>
                <Typography variant="body2">
                  Landmark: {eventInfo.landmark}
                </Typography>
              </Grid>
              <Grid item xs={12} sm={6}>
                <Typography variant="h6" gutterBottom>
                  Organiser
                </Typography>
                <Typography variant="body2">
                  {eventInfo.organiserName}
                </Typography>
              </Grid>
            </Grid>

            <Grid container spacing={2} sx={{ mt: 2 }}>
              <Grid item xs={12} sm={6}>
                <Typography variant="h6" gutterBottom>
                  Event Details
                </Typography>
                <Typography variant="body2">
                  Date: {new Date(eventInfo.date).toLocaleDateString()}
                </Typography>
                <Typography variant="body2">
                  Start Time:{" "}
                  {new Date(eventInfo.startTime).toLocaleTimeString()}
                </Typography>
                <Typography variant="body2">
                  End Time: {new Date(eventInfo.endTime).toLocaleTimeString()}
                </Typography>
              </Grid>
              <Grid item xs={12} sm={6}>
                <Typography variant="h6" gutterBottom>
                  Roles Needed
                </Typography>
                {rolesNeeded.length > 0 ? (
                  rolesNeeded.map((role, index) => (
                    <Typography key={index} variant="body2">
                      {role}
                    </Typography>
                  ))
                ) : (
                  <Typography variant="body2" color="textSecondary">
                    No specific roles needed.
                  </Typography>
                )}
              </Grid>
            </Grid>
            <Grid container spacing={2} sx={{ mt: 2 }}>
              <Grid item xs={12} sm={6}>
                <Typography variant="h6" gutterBottom>
                  Rewards
                </Typography>
                <Typography variant="body2">
                  {eventInfo.rewardsOffering}
                </Typography>
              </Grid>

              <Grid item xs={12} sm={6}>
                <Typography variant="h6" gutterBottom>
                  Accessibility Assistance
                </Typography>
                {accessibilityAssistance.length > 0 ? (
                  accessibilityAssistance.map((item, index) => (
                    <Typography key={index} variant="body2">
                      {item}
                    </Typography>
                  ))
                ) : (
                  <Typography variant="body2">
                    No specific accessibility assistance provided.
                  </Typography>
                )}
              </Grid>
            </Grid>
          </CardContent>

          <Box sx={{ display: "flex", justifyContent: "flex-end", p: 2 }}>
            <Button
              onClick={handleSubmit}
              variant="outlined"
              color="error"
              sx={{ fontWeight: "bold" }}
              disabled={eventRegistered}
            >
              {eventRegistered ? "Applied" : "Accept"}
            </Button>
          </Box>
        </Box>
      </Modal>
    </ThemeProvider>
  );
};

export default EventModel;
