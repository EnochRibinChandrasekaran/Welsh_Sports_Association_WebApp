import React, { useState, useEffect } from 'react';

export const ReadFocus = ({ children, isEnableReadFocus }) => {
    const [mouseY, setMouseY] = useState(0);
    const rectangleHeight = 150;

    const handleMouseMove = (e) => {
        const adjustedMouseY = e.clientY - 24;
        setMouseY(adjustedMouseY);
    };

    useEffect(() => {
        if (isEnableReadFocus) {
            window.addEventListener('mousemove', handleMouseMove);
        }

        return () => {
            if (isEnableReadFocus) {
                window.removeEventListener('mousemove', handleMouseMove);
            }
        };
    }, [isEnableReadFocus]);

    if (!isEnableReadFocus) {
        return <div>{children}</div>;
    }

    return (
        <div className="relative h-screen">
            <div
                className="bg-black opacity-100 w-full fixed top-0 z-[2147483647]"
                style={{ height: `${mouseY - rectangleHeight / 2}px` }}
            />

            <div
                className="bg-black opacity-100 w-full fixed z-[2147483647]"
                style={{
                    top: `${mouseY + rectangleHeight / 2}px`,
                    height: `calc(100vh - ${mouseY + rectangleHeight / 2}px)`,
                }}
            />

            <div className="relative z-10">
                {children}
            </div>
        </div>
    );
};
