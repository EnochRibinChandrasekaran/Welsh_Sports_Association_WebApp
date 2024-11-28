import React from "react";
import { Card, CardContent } from "@mui/material";
import CardTextCustomTypography from "./CustomTypography";
import CardTextMediaContent from "./MediaContent";
const CardTextCustomCard = ({
  mediaClassName,
  mediaSX,
  mediaType,
  mediaSrc,
  contentClassName,
  title,
  description,
  cardVersion,
  mediaAltText,
}) => {
  return (
    <Card className="flex flex-col md:flex-row w-[92%] my-4 bg-gray-50">
      {cardVersion === "1" ? (
        <>
          <CardTextMediaContent
            mediaClassName={mediaClassName}
            sx={mediaSX}
            type={mediaType}
            src={mediaSrc}
            altText={mediaAltText}
          />
          <CardContent className={contentClassName}>
            <CardTextCustomTypography
              className="text-center"
              variant="h6"
              gutterBottom
            >
              {title}
            </CardTextCustomTypography>
            <CardTextCustomTypography variant="body2">
              {description}
            </CardTextCustomTypography>
          </CardContent>
        </>
      ) : cardVersion === "2" ? (
        <>
          <CardContent className={contentClassName}>
            <CardTextCustomTypography
              className="text-center"
              variant="h6"
              gutterBottom
            >
              {title}
            </CardTextCustomTypography>
            <CardTextCustomTypography variant="body2">
              {description}
            </CardTextCustomTypography>
          </CardContent>
          <CardTextMediaContent
            mediaClassName={mediaClassName}
            sx={mediaSX}
            type={mediaType}
            src={mediaSrc}
            altText={mediaAltText}
            title={mediaAltText}
          />
        </>
      ) : null}
    </Card>
  );
};

export default CardTextCustomCard;
