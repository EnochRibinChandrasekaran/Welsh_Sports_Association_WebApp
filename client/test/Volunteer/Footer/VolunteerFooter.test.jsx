import { expect, describe, test, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import VolunteerFooter from "../../../src/Volunteer/Footer/VolunteerFooter";
//mock imports and child components
vi.mock("./FooterSocialLinks", () => ({
  __esModule: true,
  default: ({ Icon, href }) => (
    <a href={href} data-testid="footer-social-link">
      <Icon />
    </a>
  ),
}));

vi.mock("./EmailSubscriptionForm", () => ({
  __esModule: true,
  default: ({
    email,
    setEmail,
    handleSubmit,
    emailError,
    emailSuccess,
    textBoxClassName,
    buttonClassName,
  }) => (
    <form onSubmit={handleSubmit} data-testid="email-subscription-form">
      <input
        type="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="emailaddress"
        className={textBoxClassName}
      />
      <button type="submit" className={buttonClassName}>
        joinOurSportsNewsletter
      </button>
      {emailError && <span>{emailError}</span>}
      {emailSuccess && <span>{emailSuccess}</span>}
    </form>
  ),
}));

describe("VolunteerFooter", () => {
  const setup = () => {
    const mockSetEmail = vi.fn();
    const mockHandleSubmit = vi.fn((e) => e.preventDefault());
    render(<VolunteerFooter />);
    return { mockSetEmail, mockHandleSubmit };
  };

  //test component render
  test("renders the footer with all sections", () => {
    setup();
    const footer = screen.getByRole("contentinfo");
    const emailForm = screen.getByTestId("email-subscription-form");
    const socialSection = screen.getByText("followus");
    const legalSection = screen.getByText("legal");
    const contactSection = screen.getByText("contact");
    const rightsReserved = screen.getByText("allrightsreserved");

    expect(footer).toBeInTheDocument();
    expect(emailForm).toBeInTheDocument();
    expect(socialSection).toBeInTheDocument();
    expect(legalSection).toBeInTheDocument();
    expect(contactSection).toBeInTheDocument();
    expect(rightsReserved).toBeInTheDocument();
  });

  //test social media render
  test("renders social media links", () => {
    setup();
    const socialLinks = screen.getAllByTestId("footer-social-link");
    const expectedHrefs = [
      "https://twitter.com/welshsportassoc",
      "https://www.linkedin.com/company/welshsportsassociation",
      "#",
      "#",
      "#",
    ];

    expect(socialLinks.length).toBe(5);
    socialLinks.forEach((link, index) => {
      expect(link).toHaveAttribute("href", expectedHrefs[index]);
    });
  });

  //test legal links
  test("renders legal links", () => {
    setup();
    const legalLinks = screen.getAllByRole("link", {
      name: /privacyPolicy|termsAndCondition|accessibility/i,
    });

    expect(legalLinks.length).toBe(3);
    legalLinks.forEach((link) => {
      expect(link).toHaveAttribute("href", "");
    });
  });

  //test contact links
  test("renders contact links", () => {
    setup();
    const contactLinks = screen.getAllByRole("link", {
      name: /locationDescription|admin@wsa\.wales|029 2033 4972/i,
    });

    expect(contactLinks.length).toBe(3);
    expect(contactLinks[0]).toHaveAttribute(
      "href",
      "https://maps.app.goo.gl/r1UpjtHrwY3KQiJj7"
    );
    expect(contactLinks[1]).toHaveAttribute("href", "mailto: admin@wsa.wales");
    expect(contactLinks[2]).toHaveAttribute("href", "tel:02920334972");
  });

  //test email subscription component
  test("renders email subscription form correctly", () => {
    setup();
    const emailInput = screen.getByPlaceholderText("emailaddress");
    const submitButton = screen.getByRole("button", {
      name: "joinOurSportsNewsletter",
    });

    expect(emailInput).toBeInTheDocument();
    expect(emailInput).toHaveAttribute("type", "email");
    expect(submitButton).toBeInTheDocument();
  });

  //test email submission
  test("handles email subscription form submission", async () => {
    const user = userEvent;
    setup();
    const emailInput = screen.getByPlaceholderText("emailaddress");
    const submitButton = screen.getByRole("button", {
      name: "joinOurSportsNewsletter",
    });

    await user.type(emailInput, "test@example.com");
    await user.click(submitButton);
    expect(submitButton).toBeEnabled();
  });

  //test all rights text
  test("displays all rights reserved text", () => {
    setup();
    const rightsReserved = screen.getByText("allrightsreserved");

    expect(rightsReserved).toBeInTheDocument();
  });

  //test responsiveness
  test("handles window resize", () => {
    setup();
    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
