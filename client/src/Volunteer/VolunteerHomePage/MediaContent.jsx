import React from "react";
import { CardMedia } from "@mui/material";

const CardTextMediaContent = ({
  mediaClassName,
  sx,
  type,
  src,
  altText,
  style,
}) => {
  return (
    <CardMedia
      className={mediaClassName}
      sx={sx}
      component={type === "img" ? "img" : "iframe"}
      style={style}
      image={type === "img" ? src : undefined}
      src={type === "iframe" ? src : undefined}
      alt={altText}
      title={type === "iframe" ? altText : undefined}
    />
  );
};

export default CardTextMediaContent;
