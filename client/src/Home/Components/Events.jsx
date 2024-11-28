import React, { useEffect, useState } from "react";
import axios from "axios";
import EventCard from "./EventsCard"; // Import your EventCard component

export default function EventsSection() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    // Fetch the latest 6 events from the backend
    axios.get("http://localhost:8080/api/events", {
      params: {
        page: 0,
        size: 6,
        sort: "date,asc"
      }
    })
    .then(response => {
      setEvents(response.data.content); // Assuming your backend response has a 'content' field for paginated data
    })
    .catch(error => {
      console.error("Error fetching events:", error);
    });
  }, []);

  return (
    <section className="py-12 sm:py-16">
      {/* Container for the events section */}
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
          {events.map((event, index) => (
            <EventCard
              key={index}
              imgSrc={event.eventImageUrl} // Update according to your data structure
              title={event.name}
              date={new Date(event.date).toLocaleDateString()}
              description={event.description}
            />
          ))}
        </div>
      </div>
    </section>
  );
}
