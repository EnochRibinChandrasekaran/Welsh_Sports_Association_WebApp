import React, { useEffect, useState } from "react";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import {
  Alert,
  Box,
  Button,
  Checkbox,
  Container,
  FormControl,
  FormControlLabel,
  FormGroup,
  InputLabel,
  Link,
  MenuItem,
  OutlinedInput,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import EditAvatar from "../Volunteer/VolunteerInfo/EditAvatar";
import ChangePasswordDialog from "../Volunteer/VolunteerInfo/ChangePasswordDialog";
import axios from "axios";
import { BASE_URL } from "../apiConfig";

// Custom MUI theme
const theme = createTheme({
  typography: {
    allVariants: {
      fontFamily: "Poppins, sans-serif",
      textTransform: "none",
    },
  },
  components: {
    MuiTypography: {
      styleOverrides: {
        root: {
          fontFamily: "Poppins, sans-serif",
          textTransform: "none",
        },
      },
    },
    MuiCssBaseline: {
      styleOverrides: `
        html, body {
          overflow-x:hidden;
          position: relative;
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

const OrganiserInfoTemplate = ({ editable, onUpdate, organiserID }) => {
  const [formChanged, setFormChanged] = useState(false);
  const [errors, setErrors] = useState({});
  const [changePasswordOpen, setChangePasswordOpen] = useState(false);
  const [passwords, setPasswords] = useState({
    oldPassword: "",
    newPassword: "",
    confirmNewPassword: "",
  });
  const [editAvatarSuccess, setEditAvatarSuccess] = useState(false);
  const [editAvatarError, setEditAvatarError] = useState(false);
  const [turnover, setTurnOver] = useState([]);
  const [organiserInfo, setOrganiserInfo] = useState({
    companyName: "",
    logo: "",
    postCode: "",
    address: "",
    telephone: "",
    charity: "",
    email: "",
    website: "",
    mainContactName: "",
    mainContactPosition: "",
    mainContactMobileNumber: "",
    foundingDate: "",
    numberOfMembers: "",
    associatedClubs: "",
    turnover: "",
    membershipCategory: "",
    preferredLanguage: "",
    newsletter: "",
  });
  const [successMessage, setSuccessMessage] = useState(false);
  const [errorMessage, setErrorMessage] = useState(false);
  const [warningMessage, setWarningMessage] = useState(false);

  useEffect(() => {
    const fetchOptions = async () => {
      try {
        const [turnOverOptions, organiserInfo] = await Promise.all([
          axios.get(`${BASE_URL}/api/turnover`),
          axios.get(`${BASE_URL}/api/organiser/${organiserID}`),
        ]);
        console.log(organiserInfo.data);
        setTurnOver(turnOverOptions.data);
        setOrganiserInfo(organiserInfo.data);
      } catch (error) {
        console.error("Error fetching options: ", error);
      }
    };
    fetchOptions();
  }, []);

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
      return; // if passwords don't match
    }

    console.log("Updating password:", passwords.newPassword);
    // an API call
    closeChangePasswordDialog();
  };

  // Form validation patterns and error messages
  const validateField = (name, value) => {
    let errMsg = "";
    switch (name) {
      case "mainContactName":
      case "address":
        if (!value.trim()) errMsg = "This field is required";
        break;
      case "email":
        if (!/^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(value))
          errMsg = "Invalid email format";
        break;
      case "telephone":
      case "mainContactMobileNumber":
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

  // Handle form changes
  const handleChange = (event) => {
    const { name, value } = event.target;
    setOrganiserInfo((prev) => ({
      ...prev,
      [name]: Array.isArray(value) ? value : value,
    }));
    setFormChanged(true);
    validateField(name, value);
  };

  // Check if form is valid
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
      await axios.put(
        `${BASE_URL}/api/organiser/${organiserID}`,
        organiserInfo
      );
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
              Organisation Info
            </Typography>
            <EditAvatar
              edit={editable}
              submitURL={`${BASE_URL}/api/organiser/profile-picture/${organiserID}`}
              setEditAvatarSuccess={setEditAvatarSuccess}
              setEditAvatarError={setEditAvatarError}
              onUpdate={onUpdate}
            />
            <Box display="flex" justifyContent="center" alignItems="center">
              <Typography variant="h6" p={1}>
                {organiserInfo.companyName}
              </Typography>
            </Box>
            <FormGroup>
              {editAvatarSuccess && (
                <Alert sx={{ my: 1 }} severity="success">
                  Organisation Picture Updated.
                </Alert>
              )}
              {editAvatarError && (
                <Alert sx={{ my: 1 }} severity="error">
                  Facing Problem while updating Organisation Picture. Please try
                  again later.
                </Alert>
              )}
              {successMessage && (
                <Alert sx={{ my: 1 }} severity="success">
                  Organisation Information has been updated
                </Alert>
              )}
              {errorMessage && (
                <Alert sx={{ my: 1 }} severity="error">
                  Organisation Infomation has not been updated. Please try after
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
                  label: "Postal Code",
                  name: "postCode",
                  value: organiserInfo.postCode,
                },
                {
                  label: "Address",
                  name: "address",
                  value: organiserInfo.address,
                },
                {
                  label: "Telephone",
                  name: "telephone",
                  value: organiserInfo.telephone,
                },
                { label: "Email", name: "email", value: organiserInfo.email },
                {
                  label: "Website Link",
                  name: "website",
                  value: organiserInfo.website,
                },
                {
                  label: "Founding Date",
                  name: "foundingDate",
                  type: "date",
                  value: organiserInfo.foundingDate,
                },
                {
                  label: "Associated Clubs",
                  name: "associatedClubs",
                  value: organiserInfo.associatedClubs,
                  multiline: true,
                },
                {
                  label: "Number of Members in Organisation",
                  name: "numberOfMembers",
                  value: organiserInfo.numberOfMembers,
                  type: "number",
                },
              ].map((field, index) => (
                <TextField
                  key={index}
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
                  disabled={!editable}
                  inputProps={field.type === "number" ? { min: 2 } : {}}
                />
              ))}
              <FormControl fullWidth>
                <InputLabel>Annual Turnover</InputLabel>
                <Select
                  disabled={!editable}
                  name="turnover"
                  value={organiserInfo.turnover}
                  onChange={handleChange}
                  input={<OutlinedInput label="Annual Turnover" />}
                >
                  {turnover.map((option) => (
                    <MenuItem key={option.id} value={option.types}>
                      {option.types}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
              <FormControl fullWidth>
                <InputLabel>Membership Category</InputLabel>
                <Select
                  disabled={!editable}
                  name="membershipCategory"
                  value={organiserInfo.membershipCategory}
                  onChange={handleChange}
                  input={<OutlinedInput label="Membership Category" />}
                >
                  <MenuItem value="standard">Standard</MenuItem>
                  <MenuItem value="premium">Premium</MenuItem>
                  <MenuItem value="ultra premium">Ultra Premium</MenuItem>
                </Select>
              </FormControl>
              <FormControl fullWidth>
                <InputLabel>Preferred Language</InputLabel>
                <Select
                  disabled={!editable}
                  name="preferredLanguage"
                  value={organiserInfo.preferredLanguage}
                  onChange={handleChange}
                  input={<OutlinedInput label="Preferred Language" />}
                >
                  <MenuItem value="English">English</MenuItem>
                  <MenuItem value="Welsh">Welsh</MenuItem>
                  <MenuItem value="English & Welsh">English & Welsh</MenuItem>
                </Select>
              </FormControl>
              <FormControlLabel
                label="Is your organisation registered as a charity ?"
                control={
                  <Checkbox
                    onChange={handleChange}
                    name="charity"
                    checked={organiserInfo.charity}
                    value={organiserInfo.charity}
                  />
                }
              />
              <FormControlLabel
                label="Sign up for Newsletter."
                control={
                  <Checkbox
                    onChange={handleChange}
                    name="newsletter"
                    checked={organiserInfo.newsletter}
                    value={organiserInfo.newsletter}
                  />
                }
              />

              <Typography variant="h6" sx={{ mt: 3 }}>
                Main Person of Contact
              </Typography>
              {[
                {
                  label: "Full Name",
                  name: "mainContactName",
                  value: organiserInfo.mainContactName,
                },
                {
                  label: "Position",
                  name: "mainContactPosition",
                  value: organiserInfo.mainContactPosition,
                },
                {
                  label: "Mobile Number",
                  name: "mainContactMobileNumber",
                  value: organiserInfo.mainContactMobileNumber,
                },
              ].map((field, index) => (
                <TextField
                  key={index}
                  name={field.name}
                  label={field.label}
                  onChange={handleChange}
                  value={field.value}
                  type={field.type || "text"}
                  error={!!errors[field.name]}
                  helperText={errors[field.name]}
                  disabled={!editable}
                />
              ))}
              <Typography hidden={!editable}>
                <Link component="button" onClick={openChangePasswordDialog}>
                  Change Password
                </Link>
              </Typography>
              <Button
                variant="contained"
                color="error"
                sx={{ mt: 2 }}
                disabled={!formChanged}
                onClick={handleSubmit}
                hidden={!editable}
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
  );
};

export default OrganiserInfoTemplate;
