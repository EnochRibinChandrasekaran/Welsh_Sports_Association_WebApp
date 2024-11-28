import React from "react";

const EventCard = ({ imgSrc, title, date, description }) => {
  return (
    <article className="overflow-hidden rounded-lg border border-gray-100 shadow-sm">
      {/* Event image */}
      <img alt="Event" src={imgSrc} className="h-56 w-full object-cover" />

      {/* Event details */}
      <div className="p-4 sm:p-6 text-left">
        {/* Event title */}
        <h3 className="text-lg font-medium text-gray-900 no-underline">
          {title}
        </h3>

        {/* Event date */}
        <p className="mt-1 text-sm text-gray-500">Date: {date}</p>

        {/* Event description */}
        <p className="mt-2 line-clamp-3 text-sm text-gray-500">{description}</p>
      </div>
    </article>
  );
};

export default EventCard;
