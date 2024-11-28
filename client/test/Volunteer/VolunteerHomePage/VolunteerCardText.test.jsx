import { expect, describe, test, beforeEach, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import VolunteerCardText from "../../../src/Volunteer/VolunteerHomePage/VolunteerCardText";
import volunteeringImage from "../../../src/assets/volunteering.jpg";

// mock i18next useTranslation hook
vi.mock("react-i18next", () => ({
  useTranslation: () => ({
    t: (key) => {
      const translations = {
        supportVolunteer: "Support Volunteers",
        supportDescription: "Description about supporting volunteers.",
        opportunityTitle: "Volunteer Opportunities",
        opportunityDescription: "Explore new opportunities to volunteer.",
      };
      return translations[key];
    },
  }),
}));

describe("VolunteerCardText Component", () => {
  beforeEach(() => {
    render(<VolunteerCardText />);
  });
  //test render first card
  test("renders the first card with image media and correct text content", () => {
    const imageElement = screen.getByAltText("Volunteering Image");

    expect(imageElement).toBeInTheDocument();
    expect(imageElement).toHaveAttribute("src", volunteeringImage);
    expect(screen.getByText("Support Volunteers")).toBeInTheDocument();
    expect(
      screen.getByText("Description about supporting volunteers.")
    ).toBeInTheDocument();
  });
  //test render second card
  test("renders the second card with iframe media and correct text content", () => {
    const iframeElement = screen.getByTitle("Volunteering Video");

    expect(iframeElement).toBeInTheDocument();
    expect(iframeElement).toHaveAttribute(
      "src",
      "https://www.youtube.com/embed/OeDycAFm0Xo"
    );
    expect(screen.getByText("Volunteer Opportunities")).toBeInTheDocument();
    expect(
      screen.getByText("Explore new opportunities to volunteer.")
    ).toBeInTheDocument();
  });

  //test window resize
  test("handles window resize", () => {
    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
