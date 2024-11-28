import React from "react";
import { useTranslation } from "react-i18next";

const EmailSubscriptionForm = ({
  email,
  setEmail,
  handleSubmit,
  emailError,
  emailSuccess,
  textBoxClassName,
  buttonClassName,
}) => {
  //Extracting translation function
  const { t } = useTranslation();
  return (
    <form onSubmit={handleSubmit} data-testid="email-subscription-form">
      <div className="flex">
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder={t("emailaddress")}
          required
          className={textBoxClassName}
        />
        <button type="submit" className={buttonClassName}>
          {t("joinOurSportsNewsletter")}
        </button>
      </div>
      {emailError && <span className="text-xs text-red-500">{emailError}</span>}
      {emailSuccess && (
        <span className="text-xs text-green-500">{emailSuccess}</span>
      )}
    </form>
  );
};

export default EmailSubscriptionForm;
