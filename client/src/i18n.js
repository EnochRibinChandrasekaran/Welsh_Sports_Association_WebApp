import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import enJSON from "../src/content/en.json";
import cyJSON from "../src/content/cy.json";
i18n.use(initReactI18next).init({
  resources: {
    en: { ...enJSON },
    cy: { ...cyJSON },
  },
  lng: "en", // default lang
});
