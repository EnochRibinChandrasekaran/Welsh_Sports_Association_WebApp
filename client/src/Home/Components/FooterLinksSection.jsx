import React from "react";

// FooterLinksSection component
const FooterLinksSection = ({ title, links }) => {
  return (
    <div className="text-left">
      {/* Section title */}
      <p className="font-medium text-gray-900">{title}</p>
      {/* List of links */}
      <ul className="mt-6 space-y-4 text-sm">
        {links.map((link, index) => (
          <li key={index}>
            <a
              href={link.href}
              className="text-gray-700 transition hover:text-gray-700"
            >
              {link.text}
            </a>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FooterLinksSection;
