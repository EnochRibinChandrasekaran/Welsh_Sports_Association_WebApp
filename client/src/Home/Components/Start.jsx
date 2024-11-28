import React from "react"; // Importing React
import HeroImage from "./GetStarted-Image.jpg"; // Importing the Get Started image

export default function StartSection() {
  return (
    <section className="overflow-hidden py-12 sm:py-16">
      {/* Container for the get started section */}
      <div className="mx-auto max-w-screen-xl px-4 py-4 sm:px-6 sm:py-8 lg:px-8 lg:py-10">
        <div className="grid grid-cols-1 gap-8 lg:grid-cols-2 lg:gap-16">
          {/* Text section */}
          <div className="lg:py-12">
            <h2 className="text-4xl font-bold text-gray-900 sm:text-4xl">
              How to Get Started
            </h2>
            <p className="mt-6 text-lg leading-8 text-gray-600">
              Signing up is easy. Simply create an account, browse through
              upcoming events, and sign up for roles that match your interests
              and expertise. We provide all the information you need to get
              started and support you every step of the way.
            </p>

            {/* Buttons */}
            <div className="mt-8 flex space-x-4">
              <a
                href="#"
                className="inline-block rounded-md bg-[#CC1B00] px-12 py-3 text-sm font-medium text-white shadow-sm hover:bg-opacity-90 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-[#CC1B00]"
              >
                Get Started
              </a>
            </div>
          </div>

          {/* Image section */}
          <div className="relative h-64 overflow-hidden rounded-lg sm:h-80 lg:h-full lg:order-last">
            <img
              alt="Hero"
              src={HeroImage}
              className="absolute inset-0 h-full w-full object-cover"
            />
          </div>
        </div>
      </div>
    </section>
  );
}
