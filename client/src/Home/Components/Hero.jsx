import React from "react"; // Importing React
import HeroImage from "./Hero-Image.jpg"; // Importing the Hero image

// Class name constants
const containerClass =
  "mx-auto max-w-screen-xl px-4 py-4 sm:px-6 sm:py-8 lg:px-8 lg:py-10";
const gridClass = "grid grid-cols-1 gap-8 lg:grid-cols-2 lg:gap-16";
const textSectionClass = "lg:py-12";
const titleClass =
  "text-4xl font-bold tracking-tight text-gray-900 sm:text-6xl";
const descriptionClass = "mt-6 text-lg leading-8 text-gray-600";
const buttonContainerClass = "mt-8 flex space-x-4";
const primaryButtonClass =
  "inline-block rounded-md bg-[#CC1B00] px-12 py-3 text-sm font-medium text-white shadow-sm hover:bg-opacity-90 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-[#CC1B00]";
const secondaryButtonClass =
  "inline-block rounded-md border border-black px-12 py-3 text-sm font-medium text-black shadow-sm hover:bg-gray-200 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black";
const imageContainerClass =
  "relative h-64 overflow-hidden rounded-lg sm:h-80 lg:h-full lg:order-last";
const imageClass = "absolute inset-0 h-full w-full object-cover";

export default function HeroSection() {
  return (
    <section className="overflow-hidden py-12 sm:py-16">
      {/* Container for the hero section */}
      <div className={containerClass}>
        <div className={gridClass}>
          {/* Text section */}
          <div className={textSectionClass}>
            <h1 className={titleClass}>
              Join the Movement: Volunteer for Sports Events in Wales
            </h1>
            <p className={descriptionClass}>
              Get involved in sports events across Wales. Whether you&apos;re a
              seasoned volunteer or looking to start, there are opportunities
              for everyone. Make a difference, gain experience, and be a part of
              the sports community.
            </p>

            {/* Buttons */}
            <div className={buttonContainerClass}>
              <a href="#" className={primaryButtonClass}>
                Register
              </a>
              <a href="#" className={secondaryButtonClass}>
                Learn More
              </a>
            </div>
          </div>

          {/* Image section */}
          <div className={imageContainerClass}>
            <img alt="Hero" src={HeroImage} className={imageClass} />
          </div>
        </div>
      </div>
    </section>
  );
}
