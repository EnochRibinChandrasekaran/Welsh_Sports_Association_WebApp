import React, { useState, useEffect } from "react";
import axios from "axios";
import EventsDashboardCard from "./EventsDashboardCard";

export default function DashboardEventsSection() {
  const [upcomingEvents, setUpcomingEvents] = useState([]);
  const [pastEvents, setPastEvents] = useState([]);

  useEffect(() => {
    const organiserId = 2; // Hardcoded organiserId for now

    // Fetch upcoming events for the specific organiser
    axios.get("http://localhost:8080/organiser-dashboard/events/upcoming", {
      params: { organiserId }
    })
      .then((response) => {
        setUpcomingEvents(response.data);
      })
      .catch((error) => {
        console.error("Error fetching upcoming events:", error);
      });

    // Fetch past events for the specific organiser
    axios.get("http://localhost:8080/organiser-dashboard/events/past", {
      params: { organiserId }
    })
      .then((response) => {
        setPastEvents(response.data);
      })
      .catch((error) => {
        console.error("Error fetching past events:", error);
      });
  }, []);

  const defaultImage = "https://via.placeholder.com/400x300?text=Event+Image";

  return (
    <section className="py-12 bg-white">
      <div className="mx-auto max-w-screen-xl px-4 sm:px-6 lg:px-8">
        <div className="text-left">
          <h2 className="text-4xl font-bold text-gray-900 sm:text-4xl">
            Upcoming Events
          </h2>
          <p className="mt-6 text-lg leading-8 text-gray-600">
            Get involved in our exciting upcoming sports events across Wales.
            Whether you&apos;re a participant or a spectator, there&apos;s something for
            everyone. Join us and be a part of the vibrant sports community.
          </p>
        </div>

        <div className="mt-12 grid gap-8 lg:grid-cols-3">
          {upcomingEvents.map((event, index) => (
            <EventsDashboardCard
              key={index}
              imgSrc={event.eventImage || defaultImage} // Use Base64 string for image source
              title={event.name}
              description={event.description}
              status={event.approved ? "approved" : "pending"}
              eventId={event.id} // Pass the event ID to the card component
            />
          ))}
        </div>
      </div>

      <div className="mx-auto max-w-screen-xl px-4 sm:px-6 lg:px-8 mt-20">
        <div className="text-left">
          <h2 className="text-4xl font-bold text-gray-900 sm:text-4xl">
            Past Events
          </h2>
          <p className="mt-6 text-lg leading-8 text-gray-600">
            Reflect on our past events and the memorable moments we&apos;ve shared.
            Thank you to everyone who participated and made these events
            special.
          </p>
        </div>

        <div className="mt-12 grid gap-8 lg:grid-cols-3">
          {pastEvents.map((event, index) => (
            <EventsDashboardCard
              key={index}
              imgSrc={event.eventImage || defaultImage} // Use Base64 string for image source
              title={event.name}
              description={event.description}
              status={event.approved ? "approved" : "pending"}
              eventId={event.id} // Pass the event ID to the card component
            />
          ))}
        </div>
      </div>
    </section>
  );
}
