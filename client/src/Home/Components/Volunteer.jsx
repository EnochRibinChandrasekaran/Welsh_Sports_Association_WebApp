import React from "react"; // Importing React

export default function VolunteerSection() {
  return (
    <section className="py-12 sm:py-16">
      {/* Container for the volunteer section */}
      <div className="mx-auto max-w-screen-xl px-4 py-4 sm:px-6 sm:py-8 lg:px-8 lg:py-10">
        <div className="grid grid-cols-1 gap-8 lg:grid-cols-2 lg:gap-16">
          {/* Heading section */}
          <div className="lg:py-12 text-left">
            <h2 className="text-4xl font-bold text-gray-900 sm:text-4xl">
              Why Volunteer with Us?
            </h2>
          </div>

          {/* Content section */}
          <div className="lg:py-12 text-left">
            <p className="text-lg leading-8 text-gray-600">
              Volunteering with the Welsh Sports Association offers numerous
              benefits. You&apos;ll gain valuable experience, meet new people, and
              contribute to the success of sports events in your community. Join
              us and see the impact you can make.
            </p>
          </div>
        </div>
      </div>
    </section>
  );
}
