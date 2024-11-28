import React from "react";
import { Typography } from "@mui/material";
const CardTextCustomTypography = ({
  children,
  className,
  fontName = "poppins-regular",
  variant,
  rootComponent,
  gutterBottom,
}) => {
  const fullClassName = `${className} ${fontName}`.trim();

  const typographyProps = {
    className: fullClassName,
    variant: variant,
    gutterBottom: gutterBottom,
  };

  if (rootComponent) {
    typographyProps.component = rootComponent;
  }

  return <Typography {...typographyProps}>{children}</Typography>;
};

export default CardTextCustomTypography;
