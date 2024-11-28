import React from "react"; // Importing React

// Class name constants
const containerClass =
  "mx-auto max-w-screen-xl px-4 py-4 sm:px-6 sm:py-8 lg:px-8 lg:py-10";
const gridClass = "grid grid-cols-1 gap-8 lg:grid-cols-2 lg:gap-16";
const textSectionClass = "lg:py-12 text-left";
const titleClass = "text-4xl font-bold text-gray-900 sm:text-4xl";
const descriptionClass = "mt-6 text-lg leading-8 text-gray-600";
const listClass = "mt-8 space-y-4";
const listItemClass = "flex items-start";
const iconClass = "mr-3 mt-1 text-emerald-600";
const buttonContainerClass =
  "mt-8 flex flex-col space-y-4 sm:space-y-0 sm:flex-row sm:space-x-4 justify-center lg:justify-start";
const primaryButtonClass =
  "inline-block rounded-md bg-[#CC1B00] px-12 py-3 text-sm font-medium text-white text-center shadow-sm hover:bg-opacity-90 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-[#CC1B00]";
const secondaryButtonClass =
  "inline-block rounded-md border border-black px-12 py-3 text-sm font-medium text-black text-center shadow-sm hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-black";

export default function LoginSection() {
  return (
    <section className="py-12 sm:py-16">
      {/* Container for the login section */}
      <div className={containerClass}>
        <div className={gridClass}>
          <div className={textSectionClass}>
            <h2 className={titleClass}>Get Involved Today!</h2>
            <p className={descriptionClass}>
              Whether you&apos;re looking to contribute or to get the best from the
              community, our platform offers opportunities for everyone. Join us
              to make a difference and experience the benefits of being part of
              a vibrant sports community.
            </p>
            <ul className={listClass}>
              <li className={listItemClass}>
                <span className={iconClass}>
                  <svg
                    className="h-5 w-5"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="M5 13l4 4L19 7"
                    />
                  </svg>
                </span>
                <span>
                  <strong className="block text-gray-900">
                    No cost to join
                  </strong>
                  Sign up for free and become a part of our community instantly.
                </span>
              </li>
              <li className={listItemClass}>
                <span className={iconClass}>
                  <svg
                    className="h-5 w-5"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="M5 13l4 4L19 7"
                    />
                  </svg>
                </span>
                <span>
                  <strong className="block text-gray-900">
                    Find the best opportunities
                  </strong>
                  Explore various roles and events to participate in, tailored
                  to your interests.
                </span>
              </li>
              <li className={listItemClass}>
                <span className={iconClass}>
                  <svg
                    className="h-5 w-5"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="M5 13l4 4L19 7"
                    />
                  </svg>
                </span>
                <span>
                  <strong className="block text-gray-900">
                    Make a difference
                  </strong>
                  Your contributions help shape the future of sports events in
                  Wales.
                </span>
              </li>
            </ul>

            <div className={buttonContainerClass}>
              <a href="#" className={primaryButtonClass}>
                Login
              </a>
              <a href="#" className={secondaryButtonClass}>
                Register
              </a>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
