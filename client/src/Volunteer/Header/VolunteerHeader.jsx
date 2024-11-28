import React, { useState, useRef, useEffect } from "react";
import WSALogo from "../../assets/wsa-logo.png";
import WalesFlag from "../../assets/cymraeg.svg";
import UKFlag from "../../assets/unitedkingdom.svg";
import { IoIosMenu } from "react-icons/io";
import { IoIosClose } from "react-icons/io";
import HeaderNavigationLinks from "./HeaderNavigationLinks";
import { useTranslation } from "react-i18next";
import AvatarImage from "../../assets/avatar.png";
import LogoutButton from "../../Login/Logout";
import axios from "axios";
import { BASE_URL } from "../../apiConfig";

const VolunteerHeader = ({ logged = false, updated, volunteerID }) => {
  const [headerInfo, setHeaderInfo] = useState({});
  //Using translation function and creating i18n instance
  const { i18n } = useTranslation();
  //State to show and hide menu icon
  const [showMenuIcon, setShowMenuIcon] = useState(true);
  //State to change top position of the navigation bar in mobile screen
  const [topValue, setTopValue] = useState("-100%");
  //State to change the flag and language
  const [flag, setFlag] = useState(WalesFlag);
  // const volunteerID = 2;
  // Function to toggle flag
  const toggleFlag = () => {
    setFlag((prevState) => (prevState === WalesFlag ? UKFlag : WalesFlag));
    //handle language change logic
    const currentLang = i18n.language === "en" ? "cy" : "en";
    //changing language in the i18n instance
    i18n.changeLanguage(currentLang);
  };

  //DOM elements reference to measure the top
  const navDivRef = useRef(null);
  const navFlagRef = useRef(null);
  //Effect to handle the layout changes on resize
  useEffect(() => {
    const fetchVolunteerInfo = async () => {
      try {
        const response = await axios.get(
          `${BASE_URL}/api/volunteer/volunteer-header/${volunteerID}`
        );
        setHeaderInfo(response.data);
      } catch (error) {
        console.error("Failed to fetch volunteer info:", error);
      }
    };

    fetchVolunteerInfo();
    if (navDivRef.current || navFlagRef.current) {
      const updateTopValue = () => {
        const totalHeight =
          navDivRef.current.clientHeight + navFlagRef.current.clientHeight;

        setTopValue(!showMenuIcon ? `${totalHeight}px` : "-100%");
      };
      window.addEventListener("resize", updateTopValue);
      updateTopValue();
      return () => window.removeEventListener("resize", updateTopValue);
    }
  }, [showMenuIcon, updated]);

  const toggleMenuIcon = () => {
    setShowMenuIcon(!showMenuIcon);
  };
  //Navigation Links
  const navLinks = [
    { href: "/volunteer", label: "home" },
    { href: "/volunteer/eventcalendar", label: "events" },
    { href: "/volunteer/trophy", label: "trophy" },
    { href: "/contact", label: "contactus" },
    { href: "/chat", label: "chat" },
  ];
  return (
    <>
      <header className="bg-white poppins-semibold md:sticky md:top-0 z-2">
        <div className="py-1  flex flex-row-reverse w-[96%] " ref={navFlagRef}>
          <a
            className="no-underline text-inherit flex  hover:text-red-700 ml-2"
            href="#"
            onClick={toggleFlag}
          >
            <span
              className="poppins-regular-italic underline decoration-red-700 "
              lang={flag === WalesFlag ? "cy" : "en"}
            >
              {flag === WalesFlag ? "Cymraeg" : "English"}
            </span>
            <img src={flag} className="h-6 w-9 mx-1" />
          </a>
        </div>
        <nav
          className="flex justify-between items-center w-[92%] mx-auto"
          ref={navDivRef}
        >
          <div>
            <a className="bg-contain" href="/volunteer">
              <img
                src={WSALogo}
                alt="Wales Sports Association Logo"
                className="w-40 my-1"
              />
            </a>
          </div>
          <div
            className="md:static absolute bg-white md:min-h-fit min-h-[20vh] left-0 md:w-auto w-full flex md:flex-col justify-start items-center lg:px-5 md:px-1"
            style={{ top: topValue }}
          >
            <ul className="flex flex-col md:flex-row md:items-center lg:gap-[4vw] gap-[8vx] md:my-auto my-4 text-lg md:w-auto w-[100%]">
              <HeaderNavigationLinks links={navLinks} />
            </ul>
          </div>
          <div className="flex items-center my-auto gap-2">
            {logged === true && (
              <a
                className="cursor-pointer no-underline text-inherit flex items-center my-auto hover:text-red-700"
                href="/volunteer/info"
              >
                <img
                  src={
                    headerInfo && headerInfo.image
                      ? `data:image/png;base64,${headerInfo.image}`
                      : AvatarImage
                  }
                  alt="Profile Picture"
                  className="mx-0.5"
                  style={{ width: 50, height: 50, borderRadius: "50%" }}
                />

                {headerInfo.firstName}
              </a>
            )}
            {logged === true && <LogoutButton />}
            {showMenuIcon ? (
              <IoIosMenu
                onClick={toggleMenuIcon}
                className="md:hidden text-3xl cursor-pointer"
                data-testid="menu-icon"
              />
            ) : (
              <IoIosClose
                onClick={toggleMenuIcon}
                className="md:hidden text-3xl cursor-pointer"
                data-testid="close-icon"
              />
            )}
            l̥
          </div>
        </nav>
      </header>
    </>
  );
};

export default VolunteerHeader;
