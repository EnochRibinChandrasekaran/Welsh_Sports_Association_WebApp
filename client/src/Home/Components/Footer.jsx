import React from "react"; // Importing React
import Logo from "./wsa-red.png"; // Importing the logo
import FooterLinksSection from "./FooterLinksSection"; // Importing the FooterLinksSection component

export default function FooterSection() {
  // Links for each section
  const servicesLinks = [
    { href: "#about", text: "About" },
    { href: "#services", text: "Services" },
    { href: "#events", text: "Events" },
    { href: "#volunteer", text: "Volunteer" },
    { href: "#contact", text: "Contact" },
  ];

  const companyLinks = [{ href: "#blog", text: "Blog" }];

  const helpfulLinks = [{ href: "#faqs", text: "FAQs" }];

  const legalLinks = [
    { href: "#accessibility", text: "Accessibility" },
    { href: "#privacy", text: "Privacy Policy" },
    { href: "#terms", text: "Terms & Conditions" },
  ];

  return (
    <footer>
      {/* Container for the footer section */}
      <div className="mx-auto max-w-screen-xl space-y-8 px-4 py-16 sm:px-6 lg:space-y-16 lg:px-8">
        <div className="grid grid-cols-1 gap-8 lg:grid-cols-3">
          {/* Logo and description */}
          <div>
            <img src={Logo} alt="WSA Logo" className="h-8" />
          </div>

          {/* Links sections */}
          <div className="grid grid-cols-1 gap-8 sm:grid-cols-2 lg:col-span-2 lg:grid-cols-4">
            <FooterLinksSection title="Services" links={servicesLinks} />
            <FooterLinksSection title="Company" links={companyLinks} />
            <FooterLinksSection title="Helpful Links" links={helpfulLinks} />
            <FooterLinksSection title="Legal" links={legalLinks} />
          </div>
        </div>
        <p className="text-xs text-gray-500">
          &copy; 2024. Welsh Sports Association. All rights reserved.
        </p>
      </div>
    </footer>
  );
}
