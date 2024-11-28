import React from "react";
import NavBar from "./Components/Navigation";
import HeroSection from "./Components/Hero";
import LoginSection from "./Components/Login";
import EventsSection from "./Components/Events";
import VolunteerSection from "./Components/Volunteer";
import StartSection from "./Components/Start";
import FooterSection from "./Components/Footer";

export default function HomePage({ backgroundColor = 'white', color = 'gray' }) {

  return (
    <div style={{ backgroundColor: backgroundColor, color: color }} >
      <NavBar />
      <HeroSection />
      <LoginSection />
      <EventsSection />
      <VolunteerSection />
      <StartSection />
      <FooterSection />
    </div>
  );
}
