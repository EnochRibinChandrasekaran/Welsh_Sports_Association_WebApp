import React, { useState, useEffect } from "react";
import Rating from "@mui/material/Rating";
import {
  Button,
  Box,
  Typography,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  OutlinedInput,
  FormGroup,
  Link,
  Container,
  ListSubheader,
  Alert,
} from "@mui/material";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import EditAvatar from "./EditAvatar";
import ChangePasswordDialog from "./ChangePasswordDialog";
import axios from "axios";
import { BASE_URL } from "../../apiConfig";

//Custom theme changes
const theme = createTheme({
  typography: {
    allVariants: {
      fontFamily: "Poppins, sans-serif",
      textTransform: "none",
    },
  },
  components: {
    MuiCssBaseline: {
      styleOverrides: `
        html, body {
          overflow-x:hidden;
          posiition: relative;
        }
      `,
    },
    MuiOutlinedInput: {
      styleOverrides: {
        root: {
          "&.Mui-focused .MuiOutlinedInput-notchedOutline": {
            borderColor: "#212121",
          },
        },
      },
    },
    MuiInputLabel: {
      styleOverrides: {
        root: {
          "&.Mui-focused": {
            color: "#212121",
          },
        },
      },
    },
    MuiFormControl: {
      styleOverrides: {
        root: {
          margin: "8px 0",
        },
      },
    },
    MuiSlider: {
      styleOverrides: {
        root: {
          color: "rgba(0, 0, 0, 0.87)",
        },
      },
    },
    MuiLink: {
      styleOverrides: {
        root: {
          color: "rgba(0, 0, 0, 0.87)",
          textDecorationColor: "rgba(0, 0, 0, 0.87)",
          "&:hover": {
            color: "#cc1b00",
          },
        },
      },
    },
    MuiSelect: {
      styleOverrides: {
        select: {
          "&.Mui-disabled": {
            color: "black", // Ensures text color is black for disabled state
            WebkitTextFillColor: "black", // Ensures text color for webkit browsers
          },
        },
        icon: {
          "&.Mui-disabled": {
            display: "none", // Hides the dropdown arrow when disabled
          },
        },
      },
    },
    MuiInputBase: {
      styleOverrides: {
        input: {
          "&.Mui-disabled": {
            color: "black", // Changes the text color
            WebkitTextFillColor: "black", // Ensures text color for webkit browsers
            opacity: 1, // Ensures the text is fully opaque
          },
        },
      },
    },
  },
});

const scrollToTop = () => {
  setTimeout(() => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  }, 0);
};

const dbsOptions = [
  { id: 1, title: "Yes" },
  { id: 2, title: "No" },
  { id: 3, title: "In Progress" },
];

const VolunteeerInfo = ({ editable, onUpdate, volunteerID }) => {
  const [formChanged, setFormChanged] = useState(false);
  const [errors, setErrors] = useState({});
  const [changePasswordOpen, setChangePasswordOpen] = useState(false);
  const [successMessage, setSuccessMessage] = useState(false);
  const [errorMessage, setErrorMessage] = useState(false);
  const [warningMessage, setWarningMessage] = useState(false);
  const [passwords, setPasswords] = useState({
    oldPassword: "",
    newPassword: "",
    confirmNewPassword: "",
  });

  const [userInfo, setUserInfo] = useState({
    firstName: "",
    lastName: "",
    dob: "",
    gender: "",
    email: "",
    about: "",
    phoneNumber: "",
    address: "",
    postalCode: "",
    occupation: "",
    qualifications: [],
    roles: [],
    availability: [],
    dbs: "",
    accessibilityEnhancement: [],
    rating: "",
    membership: "",
    emergencyContactName: "",
    emergencyPhoneNumber: "",
    emergencyRelationship: "",
  });

  const [occupationOptions, setOccupationOptions] = useState([]);
  const [qualificationOptions, setQualificationOptions] = useState([]);
  const [desiredPositionOptions, setDesiredPositionOptions] = useState([]);
  const [availabilityOptions, setAvailabilityOptions] = useState([]);
  const [accessibilityOptions, setAccessibilityOptions] = useState([]);
  const [qualificationTypes, setQualificationTypes] = useState([]);
  const [occupationTypes, setOccupationTypes] = useState([]);
  const [editAvatarSuccess, setEditAvatarSuccess] = useState(false);
  const [editAvatarError, setEditAvatarError] = useState(false);

  useEffect(() => {
    const fetchOptions = async () => {
      console.log(volunteerID);
      try {
        const [
          occupations,
          qualifications,
          positions,
          availability,
          accessibility,
          userInformation,
        ] = await Promise.all([
          axios.get(`${BASE_URL}/api/occupations`),
          axios.get(`${BASE_URL}/api/qualifications`),
          axios.get(`${BASE_URL}/api/roles`),
          axios.get(`${BASE_URL}/api/availability`),
          axios.get(`${BASE_URL}/api/accessibility`),
          axios.get(`${BASE_URL}/api/volunteer/${volunteerID}`),
        ]);
        setOccupationOptions(occupations.data);
        setQualificationOptions(qualifications.data);
        setQualificationTypes(
          Array.from(
            new Set(
              qualifications.data.map((qualification) => qualification.type)
            )
          )
        );
        setDesiredPositionOptions(positions.data);
        setAvailabilityOptions(availability.data);
        setAccessibilityOptions(accessibility.data);
        setOccupationTypes(
          Array.from(
            new Set(occupations.data.map((occupations) => occupations.category))
          )
        );
        console.log(userInformation.data);
        setUserInfo(userInformation.data);
      } catch (error) {
        console.error("Error fetching options: ", error);
      }
    };

    fetchOptions();
  }, [volunteerID]);

  //Handle Password Change and Validation
  const handlePasswordChange = (event) => {
    const { name, value } = event.target;
    setPasswords((prev) => ({ ...prev, [name]: value }));
    if (name === "newPassword" || name === "confirmNewPassword") {
      if (
        value !==
        passwords[name === "newPassword" ? "confirmNewPassword" : "newPassword"]
      ) {
        setErrors((prev) => ({ ...prev, [name]: "Passwords must match." }));
      } else {
        setErrors((prev) => ({
          ...prev,
          newPassword: "",
          confirmNewPassword: "",
        }));
      }
    }
  };
  //Open and close change password model
  const openChangePasswordDialog = () => setChangePasswordOpen(true);
  const closeChangePasswordDialog = () => setChangePasswordOpen(false);
  // Handle password submission and validation
  const handleSubmitChangePassword = () => {
    if (passwords.newPassword !== passwords.confirmNewPassword) {
      setErrors((prev) => ({
        ...prev,
        confirmNewPassword: "Passwords must match.",
      }));
      return;
    }
    // an API call
    closeChangePasswordDialog();
  };

  //Handle Form changes
  const handleChange = (event) => {
    const { name, value } = event.target;
    setUserInfo((prev) => ({
      ...prev,
      [name]: Array.isArray(value) ? value : value,
    }));
    setFormChanged(true);
    validateField(name, value);
  };

  //Form validation patterns and error messages
  const validateField = (name, value) => {
    let errMsg = "";
    switch (name) {
      case "firstName":
      case "lastName":
        if (!value.trim()) errMsg = "This field is required";
        break;
      case "email":
        if (!/^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(value))
          errMsg = "Invalid email format";
        break;
      case "phoneNumber":
      case "emergencyPhoneNumber":
        if (!/^\d{10}$/.test(value)) errMsg = "Must be 10 digits";
        break;
      case "postalCode":
        if (!/^[A-Z]{1,2}\d[A-Z\d]? \d[A-Z]{2}$/.test(value.toUpperCase()))
          errMsg = "Invalid UK postal code";
        break;
      default:
        break;
    }
    setErrors((prev) => ({ ...prev, [name]: errMsg }));
  };

  //Form validation check
  const isFormValid = () => {
    return Object.values(errors).every((x) => x === "");
  };

  //Handle Form Submission
  const handleSubmit = async () => {
    scrollToTop();
    if (!isFormValid()) {
      setEditAvatarError(false);
      setEditAvatarSuccess(false);
      setWarningMessage(true);
      setSuccessMessage(false);
      setErrorMessage(false);
      return;
    }
    try {
      await axios.put(`${BASE_URL}/api/volunteer/${volunteerID}`, userInfo);
      setEditAvatarError(false);
      setEditAvatarSuccess(false);
      setWarningMessage(false);
      setSuccessMessage(true);
      setErrorMessage(false);
      setFormChanged(false);
      scrollToTop();
    } catch (error) {
      setEditAvatarError(false);
      setEditAvatarSuccess(false);
      setWarningMessage(false);
      setSuccessMessage(false);
      setErrorMessage(true);
      scrollToTop();
    }
    onUpdate();
  };

  return (
    <>
      <ThemeProvider theme={theme}>
        <Container sx={{ width: "100vw", overflowX: "hidden" }}>
          <Box
            display="flex"
            justifyContent="center"
            alignItems="center"
            minHeight="100vh"
            p={3}
          >
            <Box
              width="100%"
              maxWidth={600}
              bgcolor="background.paper"
              p={4}
              borderRadius={2}
              boxShadow={2}
            >
              <Typography variant="h4" gutterBottom>
                Personal Info
              </Typography>
              <EditAvatar
                edit={editable}
                submitURL={`${BASE_URL}/api/volunteer/profile-picture/${volunteerID}`}
                setEditAvatarSuccess={setEditAvatarSuccess}
                setEditAvatarError={setEditAvatarError}
                onUpdate={onUpdate}
              />

              <Box display="flex" justifyContent="center" alignItems="center">
                <Typography variant="h6" p={1}>
                  {userInfo.membership}
                </Typography>
                <Rating
                  name="read-only"
                  value={parseInt(userInfo.rating)}
                  readOnly
                />
              </Box>
              {/* Form fields */}
              <FormGroup fullWidth>
                {editAvatarSuccess && (
                  <Alert sx={{ my: 1 }} severity="success">
                    Profile Picture Updated.
                  </Alert>
                )}
                {editAvatarError && (
                  <Alert sx={{ my: 1 }} severity="error">
                    Facing Problem while updating Profile Picture. Please try
                    again later.
                  </Alert>
                )}
                {successMessage && (
                  <Alert sx={{ my: 1 }} severity="success">
                    Profile Information has been updated
                  </Alert>
                )}
                {errorMessage && (
                  <Alert sx={{ my: 1 }} severity="error">
                    Profile Infomation has not been updated. Please try after
                    later.
                  </Alert>
                )}
                {warningMessage && (
                  <Alert sx={{ my: 1 }} severity="warning">
                    Please provide valid information.
                  </Alert>
                )}
                {[
                  {
                    label: "About",
                    name: "about",
                    multiline: true,
                    value: userInfo.about,
                  },
                  {
                    label: "First Name",
                    name: "firstName",
                    value: userInfo.firstName,
                  },
                  {
                    label: "Last Name",
                    name: "lastName",
                    value: userInfo.lastName,
                  },
                  {
                    label: "Date of Birth",
                    name: "dob",
                    type: "date",
                    value: userInfo.dob,
                  },
                ].map((field) => (
                  <TextField
                    disabled={!editable}
                    name={field.name}
                    label={field.label}
                    onChange={handleChange}
                    value={field.value}
                    type={field.type || "text"}
                    error={!!errors[field.name]}
                    helperText={errors[field.name]}
                    InputLabelProps={
                      field.type === "date" ? { shrink: true } : undefined
                    }
                    {...(field.multiline ? { multiline: true, rows: 3 } : {})}
                    InputProps={{
                      sx: {
                        "&.Mui-disabled MuiInputBase-input": {
                          opacity: 1,
                          color: "black",
                          WebkitTextFillColor: "black",
                        },
                      },
                    }}
                  />
                ))}

                <FormControl fullWidth>
                  <InputLabel>Gender</InputLabel>
                  <Select
                    disabled={!editable}
                    name="gender"
                    value={userInfo.gender}
                    onChange={handleChange}
                    input={<OutlinedInput label="Gender" />}
                  >
                    <MenuItem value="male">Male</MenuItem>
                    <MenuItem value="female">Female</MenuItem>
                    <MenuItem value="other">Others</MenuItem>
                  </Select>
                </FormControl>
                {[
                  { label: "Email", name: "email", value: userInfo.email },
                  {
                    label: "Mobile Number",
                    name: "phoneNumber",
                    value: userInfo.phoneNumber,
                  },
                  {
                    label: "Address",
                    name: "address",
                    value: userInfo.address,
                  },
                  {
                    label: "Postal Code",
                    name: "postalCode",
                    value: userInfo.postalCode,
                  },
                ].map((field) => (
                  <TextField
                    disabled={!editable}
                    name={field.name}
                    label={field.label}
                    onChange={handleChange}
                    value={field.value}
                    type={field.type || "text"}
                    error={!!errors[field.name]}
                    helperText={errors[field.name]}
                    InputLabelProps={
                      field.type === "date" ? { shrink: true } : undefined
                    }
                  />
                ))}
                <Typography hidden={!editable}>
                  <Link component="button" onClick={openChangePasswordDialog}>
                    Change Password
                  </Link>
                </Typography>
                <Typography variant="h6" sx={{ mt: 3 }}>
                  Emergency Contact Details
                </Typography>
                {[
                  {
                    label: "Contact Name",
                    name: "emergencyContactName",
                    value: userInfo.emergencyContactName,
                  },
                  {
                    label: "Relationship",
                    name: "emergencyRelationship",
                    value: userInfo.emergencyRelationship,
                  },
                  {
                    label: "Phone Number",
                    name: "emergencyPhoneNumber",
                    value: userInfo.emergencyPhoneNumber,
                  },
                ].map((field) => (
                  <TextField
                    disabled={!editable}
                    name={field.name}
                    label={field.label}
                    onChange={handleChange}
                    value={field.value}
                    type={field.type || "text"}
                    error={!!errors[field.name]}
                    helperText={errors[field.name]}
                  />
                ))}
                {/* Additional details */}
                <Typography variant="h6" sx={{ mt: 2 }}>
                  Additional Details
                </Typography>
                <FormControl fullWidth>
                  <InputLabel>Occupation</InputLabel>
                  <Select
                    disabled={!editable}
                    name="occupation"
                    value={userInfo.occupation}
                    onChange={handleChange}
                    input={<OutlinedInput label="Occupation" />}
                  >
                    {occupationTypes.reduce((acc, category) => {
                      acc.push(
                        <ListSubheader>{category}</ListSubheader>,
                        ...occupationOptions
                          .filter((option) => option.category === category)
                          .map((option) => (
                            <MenuItem key={option.id} value={option.name}>
                              {option.name}
                            </MenuItem>
                          ))
                      );
                      return acc;
                    }, [])}
                  </Select>
                </FormControl>
                <FormControl fullWidth>
                  <InputLabel>Qualification</InputLabel>
                  <Select
                    disabled={!editable}
                    name="qualifications"
                    multiple
                    value={userInfo.qualifications}
                    onChange={handleChange}
                    input={<OutlinedInput label="Qualification" />}
                    renderValue={(selected) => selected.join(", ")}
                  >
                    {qualificationTypes.reduce((acc, type) => {
                      acc.push(
                        <ListSubheader>{type}</ListSubheader>,
                        ...qualificationOptions
                          .filter((option) => option.type === type)
                          .map((option) => (
                            <MenuItem key={option.id} value={option.name}>
                              {option.name}
                            </MenuItem>
                          ))
                      );
                      return acc;
                    }, [])}
                  </Select>
                </FormControl>
                <FormControl fullWidth>
                  <InputLabel>Desired Position</InputLabel>
                  <Select
                    disabled={!editable}
                    name="roles"
                    multiple
                    value={userInfo.roles}
                    onChange={handleChange}
                    input={<OutlinedInput label="Desired Position" />}
                  >
                    {desiredPositionOptions.map((option) => (
                      <MenuItem key={option.id} value={option.title}>
                        {option.title}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <FormControl fullWidth>
                  <InputLabel>Availability</InputLabel>
                  <Select
                    disabled={!editable}
                    name="availability"
                    multiple
                    value={userInfo.availability}
                    onChange={handleChange}
                    input={<OutlinedInput label="Availability" />}
                  >
                    {availabilityOptions.map((option) => (
                      <MenuItem key={option.id} value={option.availableDays}>
                        {option.availableDays}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <FormControl fullWidth>
                  <InputLabel>Accessibility Enhancements</InputLabel>
                  <Select
                    disabled={!editable}
                    name="accessibilityEnhancement"
                    multiple
                    value={userInfo.accessibilityEnhancement}
                    onChange={handleChange}
                    input={<OutlinedInput label="Accessibility Enhancements" />}
                  >
                    {accessibilityOptions.map((option) => (
                      <MenuItem key={option.id} value={option.name}>
                        {option.name}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <FormControl>
                  <InputLabel>DBS Check</InputLabel>
                  <Select
                    disabled={!editable}
                    name="dbs"
                    value={userInfo.dbs}
                    onChange={handleChange}
                    input={<OutlinedInput label="DBS Check" />}
                  >
                    {dbsOptions.map((option) => (
                      <MenuItem key={option.id} value={option.title}>
                        {option.title}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <Button
                  hidden={!editable}
                  variant="contained"
                  color="error"
                  sx={{ mt: 2 }}
                  disabled={!formChanged}
                  onClick={handleSubmit}
                >
                  Save Changes
                </Button>
              </FormGroup>
            </Box>
          </Box>
        </Container>
        <ChangePasswordDialog
          open={changePasswordOpen}
          onClose={closeChangePasswordDialog}
          passwords={passwords}
          onChange={handlePasswordChange}
          onSubmit={handleSubmitChangePassword}
          errors={errors}
        />
      </ThemeProvider>
    </>
  );
};

export default VolunteeerInfo;
