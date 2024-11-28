import React, { useState } from "react"; // Importing useState hook for managing active view state
import SideMenu from "./Components/SideMenu"; // Importing SideMenu component
import DashboardEventsSection from "./Components/DashboardEventsSection"; // Importing DashboardEventsSection component
import AddEvent from "./AddEvent"; // Importing AddEvent component
import OrganiserInfoTemplate from "./OrganiserInfoTemplate";
import Chat from "../Chat/Chat";

const containerClass = "flex h-screen";

export default function EventsDashboard() {
  const [updated, setUpdated] = useState(false);
  const organiserID = 1;
  const handleUpdate = () => {
    setUpdated(true);
  };
  const [activeView, setActiveView] = useState("dashboard"); // State to track the active view
  const renderView = () => {
    switch (activeView) {
      case "dashboard":
        return <DashboardEventsSection />;
      case "addEvent":
        return <AddEvent setActiveView={setActiveView} />;
      case "organiserInfo":
        return (
          <OrganiserInfoTemplate editable={true} onUpdate={handleUpdate} organiserID={organiserID} />
        );
      case "chat":
        return <Chat id={organiserID} type="organiser"/>
      default:
        return <DashboardEventsSection />; // Fallback to dashboard view if activeView is unrecognized
    }
  };

  return (
    <div className={containerClass}>
      <SideMenu setActiveView={setActiveView} updated={updated} />
      <div className="flex-grow sm:ml-64">{renderView()}</div>
    </div>
  );
}
