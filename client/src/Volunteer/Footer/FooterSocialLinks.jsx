import React from "react";

const FooterSocialLinks = ({ Icon, href }) => {
  return (
    <a
      href={href}
      rel="noopener noreferrer"
      target="_blank"
      className="hover:border-1  no-underline text-inherit transition ease-in-out delay-50 hover:-translate-y-1 hover:scale-110 duration-100 md:mr-2"
      data-testid="footer-social-link"
    >
      <Icon size={24} color="#cc1b00" />
    </a>
  );
};

export default FooterSocialLinks;
