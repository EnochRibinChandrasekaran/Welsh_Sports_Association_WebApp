import { expect, describe, test,vi } from "vitest";
import { render, screen } from "@testing-library/react";
import EmailSubscriptionForm from "../../../src/Volunteer/Footer/EmailSubscriptionForm";

describe("EmailSubscriptionForm", () => {
  //mock data
  const setup = ({
    email = "",
    setEmail = vi.fn(),
    handleSubmit = vi.fn(),
    emailError = "",
    emailSuccess = "",
    textBoxClassName = "",
    buttonClassName = "",
  } = {}) => {
    render(
      <EmailSubscriptionForm
        email={email}
        setEmail={setEmail}
        handleSubmit={handleSubmit}
        emailError={emailError}
        emailSuccess={emailSuccess}
        textBoxClassName={textBoxClassName}
        buttonClassName={buttonClassName}
      />
    );
    const emailInput = screen.getByPlaceholderText("emailaddress");
    const submitButton = screen.getByRole("button", {
      name: "joinOurSportsNewsletter",
    });
    return { emailInput, submitButton, setEmail, handleSubmit };
  };

  //test component render
  test("renders email input and submit button", () => {
    setup();
    const emailInput = screen.getByPlaceholderText("emailaddress");
    const submitButton = screen.getByRole("button", {
      name: "joinOurSportsNewsletter",
    });

    expect(emailInput).toBeInTheDocument();
    expect(submitButton).toBeInTheDocument();
  });
//test placeholder
  test("displays the correct placeholder text", () => {
    setup();
    const emailInput = screen.getByPlaceholderText("emailaddress");

    expect(emailInput).toBeInTheDocument();
  });

  //test error message
  test("displays error message when emailError prop is provided", () => {
    setup({ emailError: "Please enter a valid email address." });
    const errorMessage = screen.getByText(
      "Please enter a valid email address."
    );

    expect(errorMessage).toBeInTheDocument();
    expect(errorMessage).toHaveClass("text-red-500");
  });

  //test success message
  test("displays success message when emailSuccess prop is provided", () => {
    setup({ emailSuccess: "Thank you for signing up!" });
    const successMessage = screen.getByText("Thank you for signing up!");

    expect(successMessage).toBeInTheDocument();
    expect(successMessage).toHaveClass("text-green-500");
  });

  //test idle state
  test("does not display error or success messages when not provided", () => {
    setup();
    const errorMessage = screen.queryByText(
      "Please enter a valid email address."
    );
    const successMessage = screen.queryByText("Thank you for signing up!");

    expect(errorMessage).not.toBeInTheDocument();
    expect(successMessage).not.toBeInTheDocument();
  });

  //test input class
  test("input has correct classes", () => {
    const textBoxClass = "test-textbox-class";
    setup({ textBoxClassName: textBoxClass });
    const emailInput = screen.getByPlaceholderText("emailaddress");

    expect(emailInput).toHaveClass(textBoxClass);
  });

  //test button class
  test("button has correct classes", () => {
    const buttonClass = "test-button-class";
    setup({ buttonClassName: buttonClass });
    const submitButton = screen.getByRole("button", {
      name: "joinOurSportsNewsletter",
    });

    expect(submitButton).toHaveClass(buttonClass);
  });

  //test responsive
  test("handles window resize", () => {
    setup();

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});

