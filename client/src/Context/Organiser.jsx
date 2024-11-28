import React, { createContext, useContext, useState } from 'react';

const OrganiserContext = createContext();
export const OrganiserProvider = ({ children }) => {
  const [OrganiserID, setOrganiserID] = useState(null);

  const updateOrganiserID = (newOrganiser) => {
    setOrganiserID(newOrganiser);
  };

  return (
    <OrganiserContext.Provider value={{ OrganiserID, updateOrganiserID }}>
      {children}
    </OrganiserContext.Provider>
  );
};

// Custom hook to use the Organiser context
export const useOrganiser = () => useContext(OrganiserContext);