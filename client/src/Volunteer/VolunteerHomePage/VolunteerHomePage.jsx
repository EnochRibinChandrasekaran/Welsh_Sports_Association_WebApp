import React, { useState, useEffect } from "react";
import VolunteerHeader from "../Header/VolunteerHeader";
import VolunteerFooter from "../Footer/VolunteerFooter";
import { FaSearch, FaFilter } from "react-icons/fa";
import UpcomingEventsCardText from "./UpcomingEvents";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import VolunteerCardText from "./VolunteerCardText";
import { useTranslation } from "react-i18next";
import { useAuth0 } from "@auth0/auth0-react";
import axios from "axios";
// import { AUTH_URL } from "../../apiConfig";
import { useVolunteer } from "../../Context/Volunteer";
import { BASE_URL } from "../../apiConfig";

// Custom theme definition for Material UI Component
const theme = createTheme({
  typography: {
    allVariants: {
      fontFamily: `"Poppins", sans-serif`,
      textTransform: "none",
    },
  },
  breakpoints: {
    values: {
      xs: 0,
      sm: 640,
      md: 768,
      lg: 1024,
      tablet: 880,
    },
  },
});

const VolunteerHomePage = () => {
  const { user, isAuthenticated, getAccessTokenSilently } = useAuth0();
  // const [token, setToken] = useState('');
  const { t } = useTranslation();
  const [searchTerm, setSearchTerm] = useState("");
  const { volunteerID, updateVolunteerID } = useVolunteer();
  updateVolunteerID(2);

  // Handle search input change
  const handleInputChange = (event) => {
    setSearchTerm(event.target.value);
  };

  // Fetch token when authenticated
  useEffect(() => {
    const fetchToken = async () => {
      const domain = "dev-8c2xr3hvid8jvodp.uk.auth0.com";
      if (isAuthenticated) {
        try {
          const currentUserAccessToken = await getAccessTokenSilently({
            authorizationParams: {
              audience: `https://${domain}/api/v2/`,
              scope: "read:current_user",
            },
          });
  
          const userDetailsByIdUrl = `https://${domain}/api/v2/users/${user.sub}`;
          const metadataResponse = await fetch(userDetailsByIdUrl, {
            headers: {
              Authorization: `Bearer ${currentUserAccessToken}`,
            },
          });

          const {
            user_metadata,
            email
          } = await metadataResponse.json();

          const {
            first_name,
            last_name,
            phone_number
          } = user_metadata || {};

          const volunteer = {
            firstName: first_name,
            lastName: last_name,
            email: email,
            phoneNumber: phone_number,
          };

          const saveResponse = await axios.post(`${BASE_URL}/api/volunteer/saveVolunteer`, volunteer);
          updateVolunteerID(saveResponse.data);
        } catch (error) {
          console.error("Error fetching token:", error);
        }
      }
    };
  
    fetchToken();
  }, [user?.sub, getAccessTokenSilently]);

  // Search Submit Function
  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Search term submitted:", searchTerm);
  };

  console.log(volunteerID);
  return (
    true && (
      <ThemeProvider theme={theme}>
        <VolunteerHeader logged={true} volunteerID={1}/>
        <div className="poppins-regular min-h-screen w-full">
          <div className="flex items-center justify-center">
            <div className="w-full flex flex-col md:flex-row justify-between items-center md:pt-4 md:pb-4 md:ml-0 ml-3 pt-3 md:w-4/5 lg:w-3/5 md:space-y-0 space-y-2">
              <form onSubmit={handleSubmit} className="flex w-full mr-4">
                <input
                  type="text"
                  placeholder={t("eventSearch")}
                  value={searchTerm}
                  data-testid="search-button"
                  onChange={handleInputChange}
                  className="flex-grow px-4 py-2 text-sm bg-gray-100 text-[#000001] focus:text-[#000001] rounded-l-md focus:outline-none border-2 border-rose-700 hover:bg-white"
                />
                <button
                  type="submit"
                  className="bg-gray-200 hover:bg-white px-4 py-2 rounded-r-md text-sm tracking-wide transition-all border-2 border-rose-700"
                >
                  <FaSearch size={20} color="#cc1b00" />
                </button>
              </form>

              <button
                type="button"
                className="flex items-center justify-center bg-gray-200 hover:bg-white px-4 py-2 rounded-md text-sm tracking-wide transition-all border-2 border-rose-700 space-x-2 font-semibold md:w-auto w-1/2 "
              >
                <FaFilter size={18} color="#cc1b00" />
                {t("filter")}
              </button>
            </div>
          </div>

          <UpcomingEventsCardText />
          <VolunteerCardText />
        </div>

        <VolunteerFooter />
      </ThemeProvider>
    )
  );
};

export default VolunteerHomePage;
