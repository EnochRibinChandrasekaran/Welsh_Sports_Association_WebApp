import React, { useState } from 'react';
import TopBarMenu from './TopBarMenu';
import { AdminEvents } from './Events/EventsPage';
import { AdminVolunteers } from './Volunteers/VolunteersPage';
import { EventInformation } from '../Common/EventInformation';
import VolunteerInfo from '../Volunteer/VolunteerInfo/VolunteerProfileInfoTemplate';
import OrganiserInfoTemplate from '../Organiser/OrganiserInfoTemplate';

export const AdminDashboard = () => {
  const styles = {
    container: 'flex flex-col bg-white min-h-screen',
  };

  const [page, setPage] = useState('Events');
  const [selectedEvent, setSelectedEvent] = useState(null);
  const [selectedVolunteerId, setSelectedVolunteerId] = useState(null);

  const handlePageChange = (newPage, id = null) => {
    setPage(newPage);
    if (newPage === 'volunteerInfo' || newPage === 'organiserInfo') {
      setSelectedVolunteerId(id);
    }
  };

  const pages = {
    'Events': <AdminEvents onPageChange={handlePageChange} onEventSelect={setSelectedEvent} />,
    'eventInfo': <EventInformation event={selectedEvent} />,
    'Volunteers': <AdminVolunteers type="volunteer" onPageChange={handlePageChange} />,
    'volunteerInfo': <VolunteerInfo editable={true} propID={selectedVolunteerId} />,
    'Organizers': <AdminVolunteers type="organizer" onPageChange={handlePageChange} />,
    'organiserInfo': <OrganiserInfoTemplate editable={true} propID={selectedVolunteerId} />,
  };

  return (
    <div className={styles.container}>
      <TopBarMenu onPageChange={handlePageChange} />
      {pages[page]}
    </div>
  );
};

export default AdminDashboard;
