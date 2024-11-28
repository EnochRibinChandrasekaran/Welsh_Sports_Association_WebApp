import React from "react";
import { LoginForm } from "../Login/Login";
import { RegisterForm } from "../Login/Register";
import { UserList } from "../Login/UserList";
import { AdminDashboard } from "../Admin/AdminDashboard";
import { Rewards } from "../Rewards/Rewards";
import { Trophy } from "../Rewards/Trophy";

import EventCalendar from "../Volunteer/EventCalendar/EventCalendar";
import VolunteerHomePage from "../Volunteer/VolunteerHomePage/VolunteerHomePage";
import SignUp from "../Volunteer/SignUp";
import ProfileEnhancement from "../Volunteer/ProfileEnhancement";
import HomePage from "../Home/HomePage";
import AddEvent from "../Organiser/AddEvent";
import EventsDashboard from "../Organiser/EventsDashboard";
import VolOrgLoginSection from "../Home/VolOrgLogin";
import VolunteerInfo from "../Volunteer/VolunteerInfo/VolunteerInfo";
import VolunteerApprovalsPage from "../Organiser/VolunteerApprovalsPage";
import { Settings } from "../Settings/Settings";
import UpdateEvent from "../Organiser/UpdateEvent";
import Contact from "../Contact/NewContactUs";
import MessagingPage from "../Chat/MessagingPage";

const paths = [
  "/admin",
  "/volunteer",
  "/volunteer/eventcalendar",
  "/",
  "/add-event",
  "/login",
  "/register",
  "/signup",
  "/userlist",
  "/vol-org-login",
  "/volunteer/signup",
  "/events-dashboard",
  "/rewards/:userId",
  "/volunteer/profile-enhancement",
  "/volunteer/info",
  "/volunteer/trophy",
  "/volunteer-approvals",
  "/update-event/:id",
  "/contact",
  'setings',
  "/chat"
];

const elements = [
  <AdminDashboard />,
  <VolunteerHomePage />,
  <EventCalendar />,
  <HomePage />,
  <AddEvent />,
  <LoginForm />,
  <RegisterForm />,
  <SignUp />,
  <UserList />,
  <VolOrgLoginSection />,
  <SignUp />,
  <EventsDashboard />,
  <Rewards />,
  <ProfileEnhancement />,
  <VolunteerInfo />,
  <Trophy />,
  <VolunteerApprovalsPage />,
  <UpdateEvent />,
  <Contact />,
  <Settings />,
  <MessagingPage />
];

const routes = paths.map((path, index) => ({
  path,
  element: elements[index],
}));

export { routes };