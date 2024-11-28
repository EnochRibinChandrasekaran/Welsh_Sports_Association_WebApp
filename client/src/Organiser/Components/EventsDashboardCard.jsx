import React from "react";
import { Link } from "react-router-dom"; // Import Link from react-router-dom

// EventCard component to display individual event details
const EventsDashboardCard = ({
  imgSrc,
  title,
  description,
  status,
  eventId,
}) => {
  const getStatusLabel = (status) => {
    let statusClass = "";
    let statusText = "";

    switch (status) {
      case "approved":
        statusClass = "bg-green-300 text-green-700";
        statusText = "Approved";
        break;
      case "pending":
        statusClass = "bg-yellow-300 text-yellow-700";
        statusText = "Pending Approval";
        break;
      default:
        statusClass = "bg-gray-300 text-gray-700";
        statusText = "Unknown";
    }

    return (
      <span
        className={`px-2 py-1 text-xs font-semibold ${statusClass} rounded-md`}
      >
        {statusText}
      </span>
    );
  };

  return (
    <Link
      to={`/update-event/${eventId}`}
      className="block rounded-lg p-4 shadow-sm shadow-indigo-100"
      style={{ textDecoration: "none" }}
    >
      {/* Event image */}
      <img
        alt="Event"
        // src={`data:image/jpeg;base64,${imgSrc}`} // Use Base64 string directly as image source
        src={`data:image/jpeg;base64,${imgSrc}`} // Use Base64 string directly as image source
        className="h-56 w-full rounded-md object-cover"
      />

      {/* Event details */}
      <div className="mt-2">
        <h3 className="text-lg font-medium text-black">{title}</h3>
        <p className="mt-2 text-sm text-black">{description}</p>

        {/* Event status label */}
        <div className="mt-2">{getStatusLabel(status)}</div>
      </div>
    </Link>
  );
};

export default EventsDashboardCard;
