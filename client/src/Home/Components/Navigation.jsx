import React, { useState } from "react"; // Importing React and useState
import Logo from "./wsa-red.png"; // Importing the logo
import { useAuth0 } from "@auth0/auth0-react";

export default function  NavBar() {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false); // State for mobile menu visibility

  const toggleMobileMenu = () => {
    setIsMobileMenuOpen(!isMobileMenuOpen);
  };

  const { loginWithRedirect } = useAuth0();

  return (
    <header>
      {/* Container for the navbar */}
      <div className="mx-auto max-w-screen-xl px-4 sm:px-6 lg:px-8">
        <div className="flex h-16 items-center justify-between">
          <div className="flex-1 md:flex md:items-center md:gap-12">
            <a className="block" href="#">
              <span className="sr-only">Home</span>
              {/* Logo image */}
              <img src={Logo} alt="Logo" className="h-8 w-auto" />
            </a>
          </div>

          <div className="md:flex md:items-center md:gap-12">
            {/* Navigation links for larger screens */}
            <nav aria-label="Global" className="hidden md:block">
              <ul className="flex items-center gap-6 text-sm">
                <li>
                  <a
                    className="text-gray-500 transition hover:text-gray-700"
                    href="#about"
                  >
                    About
                  </a>
                </li>
                <li>
                  <a
                    className="text-gray-500 transition hover:text-gray-700"
                    href="#services"
                  >
                    Services
                  </a>
                </li>
                <li>
                  <a
                    className="text-gray-500 transition hover:text-gray-700"
                    href="#events"
                  >
                    Events
                  </a>
                </li>
                <li>
                  <a
                    className="text-gray-500 transition hover:text-gray-700"
                    href="#volunteer"
                  >
                    Volunteer
                  </a>
                </li>
                <li>
                  <a
                    className="text-gray-500 transition hover:text-gray-700"
                    href="#contact"
                  >
                    Contact
                  </a>
                </li>
              </ul>
            </nav>

            <div className="flex items-center gap-4">
              <div className="sm:flex sm:gap-4">
                {/* Login button */}
                <a href="/vol-org-login" id="hideButtonLoginSignup"
                  className="rounded-md bg-[#CC1B00] px-5 py-2.5 text-sm font-medium text-white shadow hover:bg-opacity-90" 
                >
                  Login / Signup
                </a>

                <div className="hidden sm:flex">
                  {/* Register button */}
                  {/* <button
                    className="rounded-md bg-white border border-black px-5 py-2.5 text-sm font-medium text-black shadow hover:bg-gray-200"
                    onClick={() =>
                      loginWithRedirect({
                        authorizationParams: {
                          screen_hint: "signup",
                          redirect_uri: "http://localhost:3000/volunteer",
                        },
                      })
                    }
                  >
                    Register
                  </button> */}
                </div>
              </div>

              {/* Mobile menu button */}
              <div className="block md:hidden">
                <button
                  onClick={toggleMobileMenu}
                  className="rounded bg-gray-100 p-2 text-gray-600 transition hover:text-gray-700"
                >
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-5 w-5"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                    strokeWidth="2"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      d="M4 6h16M4 12h16M4 18h16"
                    />
                  </svg>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Mobile menu */}
      {isMobileMenuOpen && (
        <nav aria-label="Mobile" className="md:hidden">
          <ul className="flex flex-col items-center gap-6 text-sm p-4">
            <li>
              <a
                className="text-gray-500 transition hover:text-gray-700"
                href="#about"
              >
                About
              </a>
            </li>
            <li>
              <a
                className="text-gray-500 transition hover:text-gray-700"
                href="#services"
              >
                Services
              </a>
            </li>
            <li>
              <a
                className="text-gray-500 transition hover:text-gray-700"
                href="#events"
              >
                Events
              </a>
            </li>
            <li>
              <a
                className="text-gray-500 transition hover:text-gray-700"
                href="#volunteer"
              >
                Volunteer
              </a>
            </li>
            <li>
              <a
                className="text-gray-500 transition hover:text-gray-700"
                href="#contact"
              >
                Contact
              </a>
            </li>
            <li>
              <button
                className="rounded-md bg-[#CC1B00] px-5 py-2.5 text-sm font-medium text-white shadow hover:bg-opacity-90"
                onClick={() =>
                  loginWithRedirect({
                    authorizationParams: {
                      screen_hint: "signup",
                      redirect_uri: "http://localhost:3000/volunteer",
                    },
                  })
                }
              >
                Register
              </button>
            </li>
            <li>
              <button
                className="rounded-md bg-white border border-black px-5 py-2.5 text-sm font-medium text-black shadow hover:bg-gray-200"
                onClick={() =>
                  loginWithRedirect({
                    authorizationParams: {
                      redirect_uri: "http://localhost:3000/volunteer",
                    },
                  })
                }
              >
                Login
              </button>
            </li>
          </ul>
        </nav>
      )}
    </header>
  );
}
