import React, { createContext, useContext, useState } from 'react';

const VolunteerContext = createContext();
export const VolunteerProvider = ({ children }) => {
  const [volunteerID, setVolunteerID] = useState(null);

  const updateVolunteerID = (newVolunteer) => {
    setVolunteerID(newVolunteer);
  };

  return (
    <VolunteerContext.Provider value={{ volunteerID, updateVolunteerID }}>
      {children}
    </VolunteerContext.Provider>
  );
};

// Custom hook to use the Volunteer context
export const useVolunteer = () => useContext(VolunteerContext);
