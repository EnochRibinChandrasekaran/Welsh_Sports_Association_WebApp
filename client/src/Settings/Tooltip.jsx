import React, { useState, useEffect } from "react";

export const Tooltip = ({ children }) => {
  const [tooltip, setTooltip] = useState({ text: "", visible: false, x: 0, y: 0 });

  const handleMouseMove = (e) => {
    const target = e.target;
    let textToDisplay = "";

    if (target.tagName === "IMG" && target.alt) {
      textToDisplay = target.alt;
    }
    else if (target.textContent) {
      textToDisplay = target.textContent.trim();
    }

    if (textToDisplay) {
      setTooltip({
        text: textToDisplay,
        visible: true,
        x: e.pageX - 10,
        y: e.pageY - 10,
      });
    } else {
      setTooltip({ ...tooltip, visible: false });
    }
  };

  useEffect(() => {
    document.addEventListener("mousemove", handleMouseMove);

    return () => {
      document.removeEventListener("mousemove", handleMouseMove);
    };
  }, []);

  return (
    <div>
      {children}
      {tooltip.visible && (
        <div
          className="fixed bg-gray-700 text-white text-xs px-2 py-1 rounded shadow-lg z-50"
          style={{ top: tooltip.y, left: tooltip.x }}
        >
          {tooltip.text}
        </div>
      )}
    </div>
  );
};