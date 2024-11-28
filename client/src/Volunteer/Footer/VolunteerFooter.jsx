import React, { useState } from "react";
import {
  FaXTwitter,
  FaInstagram,
  FaLinkedin,
  FaLocationDot,
  FaPhone,
  FaYoutube,
  FaSquareFacebook,
} from "react-icons/fa6";
import { MdEmail } from "react-icons/md";
import FooterSocialLinks from "./FooterSocialLinks";
import EmailSubscriptionForm from "./EmailSubscriptionForm";
import { useTranslation } from "react-i18next";

const ContactUs = ({ href, Icon, iconClassName, children }) => (
  <a
    href={href}
    rel="noopener noreferrer"
    target="_blank"
    className="flex justify-start no-underline text-inherit hover:text-red-700"
  >
    <Icon size={14} className={iconClassName} />
    <span
      className="text-sm"
      dangerouslySetInnerHTML={{ __html: children }}
    ></span>
  </a>
);

const Legal = ({ href, children }) => (
  <a
    className="text-sm no-underline text-inherit hover:text-red-700"
    href={href}
  >
    {children}
  </a>
);

//Footer for Voluntter Websites
const VolunteerFooter = () => {
  //Extracting translation function
  const { t } = useTranslation();
  //States to handle email submit, error and success messages
  const [email, setEmail] = useState("");
  const [emailError, setEmailError] = useState("");
  const [emailSuccess, setEmailSuccess] = useState("");

  //Email Pattern Validation
  const validateEmail = (email) => {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(String(email).toLowerCase());
  };

  //Email Submit
  const handleSubmit = async (event) => {
    event.preventDefault();
    setEmailError("");
    setEmailSuccess("");
    if (!validateEmail(email)) {
      setEmailError("Please enter a valid email address.");
      return;
    }
    try {
      // const response = await fetch("/",{
      //     method: 'POST',
      //     headers: {
      //         'Contect-Type' : 'application/json',
      //     },
      //     body: JSON.stringify({email})
      // });
      // const result = await response.json();

      const result = { success: true };

      if (result.success) {
        setEmailSuccess(t("thankYouSigningUp"));
        setEmailError("");
        setEmail("");
      } else {
        setEmailError(t("somethingWentWrong"));
        setEmailSuccess("");
      }
    } catch (error) {
      setEmailError(t("somethingWentWrong"));
      setEmailSuccess("");
    }
  };

  //Social Media List
  const socialMedia = [
    { icon: FaXTwitter, href: "https://twitter.com/welshsportassoc" },
    {
      icon: FaLinkedin,
      href: "https://www.linkedin.com/company/welshsportsassociation",
    },
    { icon: FaInstagram, href: "#" },
    { icon: FaSquareFacebook, href: "#" },
    { icon: FaYoutube, href: "#" },
  ];
  //Contact Us List
  const contactUsLinks = [
    {
      icon: FaLocationDot,
      href: "https://maps.app.goo.gl/r1UpjtHrwY3KQiJj7",
      iconClassName: "mr-1 mt-1",
      description: "locationDescription",
    },
    {
      icon: MdEmail,
      href: "mailto: admin@wsa.wales",
      iconClassName: "mr-1",
      description: "admin@wsa.wales",
    },
    {
      icon: FaPhone,
      href: "tel:02920334972",
      iconClassName: "mr-1",
      description: "029 2033 4972",
    },
  ];
  //Legal List
  const legalList = [
    { href: "", label: "privacyPolicy" },
    { href: "", label: "termsAndCondition" },
    { href: "", label: "accessibility" },
  ];
  return (
    <>
      <footer className="text-black poppins-regular tracking-wide bg-gray-200">
        <hr className="w-[95%]  mx-auto border-2"></hr>
        <div className="mx-auto md:px-10 py-1 px-4 grid sm:grid-cols-2 md:gap-2 gap-1 w-[92%]">
          <div>
            <h4 className="text-lg">{t("dontmissout")}</h4>
            <p className="text-base  mb-1">{t("stayupdated")}</p>
            <EmailSubscriptionForm
              email={email}
              setEmail={setEmail}
              handleSubmit={handleSubmit}
              emailError={emailError}
              emailSuccess={emailSuccess}
              textBoxClassName="px-4 py-2 text-sm bg-gray-50 text-[#000001] focus:text-red-700 hover:text-red-700  rounded-l-md w-[40%] focus:outline-none border-2 border-rose-700 mr-1 hover:bg-white"
              buttonClassName="bg-[#cc1b00] text-white hover:bg-[#dc1b00]  px-4 py-2 rounded-r-md text-sm tracking-wide transition-all"
            />
          </div>
          <div className="md:px-[20%] md:mx-[10%] md:py-0 py-2">
            <h4 className="text-black text-lg">{t("followus")}</h4>
            <div className="flex">
              {socialMedia.map((media, index) => (
                <FooterSocialLinks
                  key={index}
                  Icon={media.icon}
                  href={media.href}
                />
              ))}
            </div>
          </div>
          <hr className="w-[95%] mx-auto mb-0 md:hidden"></hr>
          <div className="md:mt-2">
            <h4 className="text-black text-lg ">{t("legal")}</h4>
            <ul className="list-none space-y-1 pl-0">
              {legalList.map((legal, index) => (
                <li key={index}>
                  <Legal href={legal.href}>{t(legal.label)}</Legal>
                </li>
              ))}
            </ul>
          </div>
          <div className="md:px-[20%] md:mx-[10%]">
            <h4 className="text-black text-lg">{t("contact")}</h4>

            <ul className="list-none space-y-1 pl-0">
              {contactUsLinks.map((contact, index) => (
                <li key={index}>
                  <ContactUs
                    Icon={contact.icon}
                    iconClassName={contact.iconClassName}
                    href={contact.href}
                    >
                    {t(contact.description)}
                    </ContactUs>
                </li>
              ))}
            </ul>
          </div>
        </div>

        <hr className="w-[95%] mx-auto mb-0 "></hr>
        <div className="flex flex-col items-center text-base my-0.5 justify-center ">
          <span>{t("allrightsreserved")}</span>
        </div>
      </footer>
    </>
  );
};

export default VolunteerFooter;
