import React, { useState, useEffect } from "react";
import ruler from './assets/ruler-measurements.png';


export const Ruler = ({ children, isEnableRuler }) => {
    const [mousePosition, setMousePosition] = useState({ x: 0, y: 0 });

    useEffect(() => {
        const handleMouseMove = (event) => {
            setMousePosition({ x: event.clientX, y: event.clientY });
        };

        window.addEventListener("mousemove", handleMouseMove);

        return () => {
            window.removeEventListener("mousemove", handleMouseMove);
        };
    }, [isEnableRuler]);

    if (!isEnableRuler) {
        return <div>{children}</div>;
    }

    return (
        <div>
            <div
                className="fixed w-full bg-black bg-repeat-x z-50 pointer-events-none"
                style={{
                    top: `${mousePosition.y}px`,
                    backgroundImage: `url(${ruler})`,
                    height: '100px'
                }}
            />
            {children}
        </div>
    );
};