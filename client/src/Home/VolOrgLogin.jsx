import React from "react"; // Importing React
import { useEffect } from "react";
import NavBar from "./Components/Navigation"; // Importing Navigation Bar component
import LoginCard from "./Components/LoginCard"; // Importing the LoginCard component
import FooterSection from "./Components/Footer"; // Importing Footer Section component
import { useAuth0 } from "@auth0/auth0-react";

export default function VolOrgLoginSection() {
  
  useEffect(() => {
    const hideButton = document.getElementById('hideButtonLoginSignup');
    if (hideButton) {
      hideButton.style.display = 'none';
    }
  }, []);

  const { loginWithRedirect } = useAuth0();

  return (
    <div>
      {/* Navigation Bar */}
      <NavBar />

      {/* Login Section */}
      <section className="bg-white py-12 sm:py-16">
        {/* Container for the login section */}
        <div className="mx-auto max-w-screen-xl px-4 py-4 sm:px-6 sm:py-8 lg:px-8 lg:py-10">
          <div className="grid grid-cols-1 gap-8 lg:grid-cols-2 lg:gap-16">
            {/* Volunteer Login Section */}
            <LoginCard
              title="Volunteer Login"
              description="Volunteers can log in to manage their profiles, sign up for events, and track their participation."
              buttonText="Login"
              onClick={() =>
                loginWithRedirect({
                  authorizationParams: {
                    redirect_uri: "http://localhost:3000/volunteer",
                  },
                })
              }
            />

            {/* Organiser Login Section */}
            <LoginCard
              title="Organiser Login"
              description="Organisers can log in to create and manage events, coordinate with volunteers, and track event progress."
              buttonText="Login"
              onClick={() =>
                loginWithRedirect({
                  authorizationParams: {
                    redirect_uri: "http://localhost:3000/events-dashboard",
                    isOrganizer: true
                  },
                })
              }
            />
          </div>
        </div>
      </section>

      {/* Footer Section */}
      <FooterSection />
    </div>
  );
}
