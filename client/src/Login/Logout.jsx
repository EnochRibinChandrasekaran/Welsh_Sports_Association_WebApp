import React from "react";
import { useAuth0 } from "@auth0/auth0-react";

const LogoutButton = () => {
  const { logout } = useAuth0();

  return (
    <button
      className="whitespace-nowrap bg-gray-50 text-[#000001] hover:text-red-700 px-2 py-1 rounded border-2 border-rose-700 mx-1
               hover:bg-white"
      onClick={() =>
        logout({ logoutParams: { returnTo: window.location.origin } })
      }
      data-testid="logout-button"
    >
      Log Out
    </button>
  );
};

export default LogoutButton;
