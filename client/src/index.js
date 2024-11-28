import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./Router/config";
import "bootstrap/dist/css/bootstrap.css";
import { Auth0Provider } from '@auth0/auth0-react';
import "./i18n.js";
import 'bootstrap/dist/css/bootstrap.min.css';

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <Auth0Provider
      domain="dev-8c2xr3hvid8jvodp.uk.auth0.com"
      clientId="P5Ds94EsfApXzmkI67Ly2skrH6czh1bj"
      authorizationParams={{
        redirect_uri: window.location.origin,
        audience: "https://dev-8c2xr3hvid8jvodp.uk.auth0.com/api/v2/",
        scope: "read:current_user update:current_user_metadata update:users"
      }}
    >
      <App />
    </Auth0Provider>
  </React.StrictMode>
);