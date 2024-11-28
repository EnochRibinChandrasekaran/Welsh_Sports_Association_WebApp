import React from "react";

import { useTranslation } from "react-i18next";
const HeaderNavigationLinks = ({ links }) => {
  //Extracting translation function
  const { t } = useTranslation();
  return (
    <>
      {links.map((link, index) => (
        <li key={index}>
          <a
            className="whitespace-nowrap text-inherit no-underline hover:text-red-700 md:border-2 md:border-white md:hover:border-rose-700 rounded md:p-3"
            href={link.href}
          >
            {t(link.label)}
          </a>
          <hr className="md:hidden mr-9 my-1"></hr>
        </li>
      ))}
    </>
  );
};

export default HeaderNavigationLinks;
