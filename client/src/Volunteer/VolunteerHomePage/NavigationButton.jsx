import React from "react";
import { MdNavigateNext, MdNavigateBefore } from "react-icons/md";

const NavigationButton = ({
  commonClassName,
  commonStyles,
  buttonColor,
  navigationClassName,
  handlePrev,
  handleNext,
}) => {
  return (
    <div className={commonClassName} style={commonStyles}>
      <MdNavigateBefore
        color={buttonColor}
        className={navigationClassName}
        onClick={() => handlePrev()}
      />
      <MdNavigateNext
        color={buttonColor}
        className={navigationClassName}
        onClick={() => handleNext()}
      />
    </div>
  );
};

export default NavigationButton;
