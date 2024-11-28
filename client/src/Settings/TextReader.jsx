import React, { useEffect } from 'react';

export const TextReader = ({ children, isEnableSpeechText }) => {

    useEffect(() => {
        const handleMouseClick = (e) => {
            const text = e.target?.innerText || '';
            stopCurrentSpeech();
            speakText(text);
        };

        if (isEnableSpeechText) {
            window.addEventListener('click', handleMouseClick);
        }

        return () => {
            if (isEnableSpeechText) {
                window.removeEventListener('click', handleMouseClick);
            }
        };
    }, [isEnableSpeechText]);

    const stopCurrentSpeech = () => {
        if (window.speechSynthesis.speaking) {
            window.speechSynthesis.cancel(); // Stops the current speech
        }
    };

    const speakText = (text) => {
        if (text.trim() !== '') {
            const utterance = new SpeechSynthesisUtterance(text);
            window.speechSynthesis.speak(utterance);
        }
    };    

    return (
        <div >
            {children}
        </div>
    );
};