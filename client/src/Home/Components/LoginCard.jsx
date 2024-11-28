import React from "react"; // Importing React

const buttonPrimary =
  "inline-block rounded-md bg-[#CC1B00] px-12 py-3 text-sm font-medium text-white text-center shadow-sm hover:bg-opacity-90 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-[#CC1B00]";

const LoginCard = ({ title, description, buttonText, onClick }) => {
  return (
    <div className="overflow-hidden rounded-lg border border-gray-100 shadow-sm p-6">
      <h2 className="text-2xl font-bold text-gray-900 sm:text-3xl">{title}</h2>
      <p className="mt-4 text-base leading-6 text-gray-600">{description}</p>
      <div className="mt-6">
        <button onClick={onClick} className={buttonPrimary}>
          {buttonText}
        </button>
      </div>
    </div>
  );
};

export default LoginCard;
