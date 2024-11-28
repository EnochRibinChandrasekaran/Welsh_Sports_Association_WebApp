import React from "react";
import CardTextCustomCard from "./CardTextCustomCard";
import volunteeringImage from "./../../assets/volunteering.jpg";
import { useTranslation } from "react-i18next";
const VolunteerCardText = () => {
  //Extracting translation function
  const { t } = useTranslation();
  return (
    <>
      <div className="w-full h-[100%] items-center justify-center flex flex-col ">
        <CardTextCustomCard
          cardVersion="1"
          mediaClassname="w-full md:w-1/2 bg-gray-50"
          mediaSX={{
            width: { xs: "100%", md: "50%", lg: "30%" },
            height: { xs: "auto", tablet: "40%", lg: "30%" },
            objectFit: { xs: "cover", tablet: "contain" },
          }}
          mediaType="img"
          mediaSrc={volunteeringImage}
          mediaAltText="Volunteering Image"
          contentClassName=" flex flex-grow flex-col justify-center bg-gray-50 h-auto overflow:auto"
          title={t("supportVolunteer")}
          description={t("supportDescription")}
        />
        <CardTextCustomCard
          cardVersion="2"
          mediaClassname="block min-h-full"
          mediaSX={{
            width: { xs: "100%" },
            height: { xs: "40vh" },
          }}
          mediaType="iframe"
          mediaSrc="https://www.youtube.com/embed/OeDycAFm0Xo"
          mediaAltText="Volunteering Video"
          contentClassName=" flex flex-grow flex-col justify-center bg-gray-50 w-[100%] overflow-auto"
          title={t("opportunityTitle")}
          description={t("opportunityDescription")}
        />
      </div>
    </>
  );
};

export default VolunteerCardText;
