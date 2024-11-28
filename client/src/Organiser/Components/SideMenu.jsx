import axios from "axios";
import React, { useEffect, useState } from "react"; // Importing React
import { BASE_URL } from "../../apiConfig";
// Class name constants
const classes = {
  containerClass:
    "flex h-screen flex-col justify-between border-e bg-gray-100 fixed left-0 top-0 bottom-0",
  topSectionClass: "px-4 py-6",
  logoClass: "grid h-10 w-32 place-content-center",
  sectionTitleClass: "px-4 py-2 text-xs font-semibold text-gray-500",
  listClass: "mt-6 space-y-1",
  listItemClass:
    "flex items-center rounded-lg px-4 py-2 text-sm font-medium text-gray-500 hover:bg-gray-200 hover:text-gray-700 no-underline",
  activeListItemClass:
    "flex items-center rounded-lg bg-red-100 px-4 py-2 text-sm font-medium text-red-500 no-underline",
  bottomSectionClass: "sticky inset-x-0 bottom-0 border-t border-gray-100",
  userClass:
    "flex items-center gap-2 bg-white p-4 hover:bg-gray-50 no-underline",
  userImageClass: "size-10 rounded-full object-cover",
  userInfoClass: "text-xs",
  userNameClass: "block font-medium text-black no-underline",
  userEmailClass: "text-gray-500 no-underline",
  iconWrapperClass:
    "mr-2 h-6 w-6 flex items-center justify-center rounded-full bg-red-500",
};

export default function SideMenu({ setActiveView, updated }) {
  const [sidebarInfo, setSidebarInfo] = useState({});
  const organiserID = 1;
  useEffect(() => {
    const fetchVolunteerInfo = async () => {
      try {
        const response = await axios.get(
          `${BASE_URL}/api/organiser/organiser-header/${organiserID}`
        );
        setSidebarInfo(response.data);
      } catch (error) {
        console.error("Failed to fetch volunteer info:", error);
      }
    };

    fetchVolunteerInfo();
  }, [updated]);
  return (
    <div className={classes.containerClass}>
      <div className={classes.topSectionClass}>
        <div className={classes.logoClass}>
          <img
            src="https://morgansconsult.com/wp-content/uploads/2020/11/wsa-logo.png"
            alt="WSA Logo"
            className="h-10 w-auto"
          />
        </div>

        <div className="mt-6">
          <div className={classes.sectionTitleClass}>MENU</div>
          <ul className={classes.listClass}>
            <li>
              <a
                href="#"
                onClick={() => setActiveView("dashboard")}
                className={classes.listItemClass}
              >
                <span className={classes.iconWrapperClass}>
                  <svg
                    className="h-3 w-3 text-white"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                  >
                    <path d="M3 12l7-9v18L3 12zm11-9v18l7-9-7-9z" />
                  </svg>
                </span>
                View Events
              </a>
            </li>
            <li>
              <a
                href="#"
                onClick={() => setActiveView("addEvent")}
                className={classes.listItemClass}
              >
                <span className={classes.iconWrapperClass}>
                  <svg
                    className="h-3 w-3 text-white"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                  >
                    <path d="M24 10h-10v-10h-4v10h-10v4h10v10h4v-10h10z" />
                  </svg>
                </span>
                Add Event
              </a>
            </li>
            <li>
              <a href="#"
                onClick={() => setActiveView("chat")}
                className={classes.listItemClass}>
                <span className={classes.iconWrapperClass}>
                  <svg
                    className="h-3 w-3 text-white"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 121.58 122.88"
                    fill="currentColor"
                  >
                    <path d="M25.8,111.27,44.08,94.69a3.46,3.46,0,0,1,2.41-1h66.18a2,2,0,0,0,2-1.95V8.9a2,2,0,0,0-2-1.95H8.9A1.95,1.95,0,0,0,7,8.9V91.76a1.95,1.95,0,0,0,2,1.95H22.33a3.48,3.48,0,0,1,3.47,3.48v14.08Zm1.17-45a3.48,3.48,0,0,0,0,7H68a3.48,3.48,0,0,0,0-7Zm0-39.86a3.48,3.48,0,0,0,0,7H94.69a3.48,3.48,0,1,0,0-6.95Zm0,19.93a3.48,3.48,0,0,0,0,6.95H87.66a3.48,3.48,0,0,0,0-6.95Zm20.9,54.32-23,21.07a3.48,3.48,0,0,1-6.06-2.32V100.66H8.9A8.91,8.91,0,0,1,0,91.76V8.9A8.91,8.91,0,0,1,8.9,0H112.67a8.93,8.93,0,0,1,8.91,8.9V91.76a8.93,8.93,0,0,1-8.91,8.9Z" />
                  </svg>
                </span>
                Messages
              </a>
            </li>
            <li>
              <a href="#" className={classes.listItemClass}>
                <span className={classes.iconWrapperClass}>
                  <svg
                    className="h-3 w-3 text-white"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 122.877 101.052"
                    fill="currentColor"
                  >
                    <path d="M4.43,63.63c-2.869-2.755-4.352-6.42-4.427-10.11c-0.074-3.689,1.261-7.412,4.015-10.281 c2.752-2.867,6.417-4.351,10.106-4.425c3.691-0.076,7.412,1.255,10.283,4.012l24.787,23.851L98.543,3.989l1.768,1.349l-1.77-1.355 c0.141-0.183,0.301-0.339,0.479-0.466c2.936-2.543,6.621-3.691,10.223-3.495V0.018l0.176,0.016c3.623,0.24,7.162,1.85,9.775,4.766 c2.658,2.965,3.863,6.731,3.662,10.412h0.004l-0.016,0.176c-0.236,3.558-1.791,7.035-4.609,9.632l-59.224,72.09l0.004,0.004 c-0.111,0.141-0.236,0.262-0.372,0.368c-2.773,2.435-6.275,3.629-9.757,3.569c-3.511-0.061-7.015-1.396-9.741-4.016L4.43,63.63 L4.43,63.63z" />
                  </svg>
                </span>
                Approvals
              </a>
            </li>
          </ul>

          <div className={classes.sectionTitleClass}>OTHERS</div>
          <ul className={classes.listClass}>
            <li>
              <a href="#" className={classes.listItemClass}>
                <span className={classes.iconWrapperClass}>
                  <svg
                    className="h-3 w-3 text-white"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 122.88 122.878"
                    fill="currentColor"
                  >
                    <path d="M101.589,14.7l8.818,8.819c2.321,2.321,2.321,6.118,0,8.439l-7.101,7.101 c1.959,3.658,3.454,7.601,4.405,11.752h9.199c3.283,0,5.969,2.686,5.969,5.968V69.25c0,3.283-2.686,5.969-5.969,5.969h-10.039 c-1.231,4.063-2.992,7.896-5.204,11.418l6.512,6.51c2.321,2.323,2.321,6.12,0,8.44l-8.818,8.819c-2.321,2.32-6.119,2.32-8.439,0 l-7.102-7.102c-3.657,1.96-7.601,3.456-11.753,4.406v9.199c0,3.282-2.685,5.968-5.968,5.968H53.629 c-3.283,0-5.969-2.686-5.969-5.968v-10.039c-4.063-1.232-7.896-2.993-11.417-5.205l-6.511,6.512c-2.323,2.321-6.12,2.321-8.441,0 l-8.818-8.818c-2.321-2.321-2.321-6.118,0-8.439l7.102-7.102c-1.96-3.657-3.456-7.6-4.405-11.751H5.968 C2.686,72.067,0,69.382,0,66.099V53.628c0-3.283,2.686-5.968,5.968-5.968h10.039c1.232-4.063,2.993-7.896,5.204-11.418l-6.511-6.51 c-2.321-2.322-2.321-6.12,0-8.44l8.819-8.819c2.321-2.321,6.118-2.321,8.439,0l7.101,7.101c3.658-1.96,7.601-3.456,11.753-4.406 V5.969C50.812,2.686,53.498,0,56.78,0h12.471c3.282,0,5.968,2.686,5.968,5.969v10.036c4.064,1.231,7.898,2.992,11.422,5.204 l6.507-6.509C95.471,12.379,99.268,12.379,101.589,14.7L101.589,14.7z M61.44,36.92c13.54,0,24.519,10.98,24.519,24.519 c0,13.538-10.979,24.519-24.519,24.519c-13.539,0-24.519-10.98-24.519-24.519C36.921,47.9,47.901,36.92,61.44,36.92L61.44,36.92z" />
                  </svg>
                </span>
                Settings
              </a>
            </li>
            <li>
              <a href="#" className={classes.listItemClass}>
                <span className={classes.iconWrapperClass}>
                  <svg
                    className="h-3 w-3 text-white"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 91.06 122.88"
                    fill="currentColor"
                  >
                    <path d="M58.68,84.96H27.37v-3.12c0-5.32,0.59-9.65,1.8-12.97c1.21-3.35,3.01-6.36,5.4-9.11c2.39-2.76,7.76-7.6,16.12-14.52 c4.45-3.63,6.67-6.95,6.67-9.96c0-3.04-0.9-5.37-2.67-7.06c-1.8-1.66-4.5-2.5-8.13-2.5c-3.91,0-7.12,1.29-9.68,3.88 c-2.56,2.56-4.19,7.09-4.9,13.5L0,39.13c1.1-11.76,5.37-21.21,12.8-28.39C20.25,3.57,31.68,0,47.06,0c11.98,0,21.63,2.5,29,7.48 c9.99,6.78,15,15.78,15,27.04c0,4.67-1.29,9.2-3.88,13.53c-2.56,4.33-7.85,9.65-15.81,15.89c-5.54,4.42-9.06,7.93-10.52,10.61 C59.42,77.19,58.68,80.68,58.68,84.96L58.68,84.96z M26.28,93.29h33.56v29.6H26.28V93.29L26.28,93.29z" />
                  </svg>
                </span>
                Help
              </a>
            </li>
          </ul>
        </div>
      </div>

      <div className={classes.bottomSectionClass}>
        <a
          onClick={() => setActiveView("organiserInfo")}
          className={`${classes.userClass} no-underline cursor-pointer`}
        >
          <img
            alt="User"
            src={`data:image/png;base64,${sidebarInfo.logo}`}
            className={classes.userImageClass}
          />
          <div className={classes.userInfoClass}>
            <strong className={classes.userNameClass}>
              {sidebarInfo.companyName}
            </strong>
            <span className={classes.userEmailClass}>
              {sidebarInfo.website}
            </span>
          </div>
        </a>
      </div>
    </div>
  );
}
