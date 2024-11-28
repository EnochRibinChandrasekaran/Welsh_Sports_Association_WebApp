import { render, screen, waitFor } from "@testing-library/react";
import { describe, it, expect, vi } from "vitest";
import MessagingPage from "../../src/Chat/MessagingPage";

//mock imports
vi.mock("../../src/Volunteer/Header/VolunteerHeader", () => ({
  __esModule: true,
  default: ({ logged, volunteerID }) => (
    <div data-testid="volunteer-header">
      VolunteerHeader - Logged: {String(logged)}, ID: {volunteerID}
    </div>
  ),
}));

vi.mock("../../src/Volunteer/Chat", () => ({
  __esModule: true,
  default: ({ id, type }) => (
    <div data-testid="chat">
      Chat - ID: {id}, Type: {type}
    </div>
  ),
}));

vi.mock("../../src/Volunteer/Footer/VolunteerFooter", () => ({
  __esModule: true,
  default: () => <div data-testid="volunteer-footer">VolunteerFooter</div>,
}));

describe("MessagingPage", () => {
  //test header render
  it("renders the VolunteerHeader with correct props", () => {
    render(<MessagingPage />);

    const headerElement = screen.getByTestId("volunteer-header");
    expect(headerElement).toHaveTextContent(
      "VolunteerHeader - Logged: true, ID: 1"
    );
  });

  //test chat render
  it("renders the Chat component with correct props after loading", async () => {
    render(<MessagingPage />);

    await waitFor(() => {
      const chatElement = screen.getByTestId("chat");
      expect(chatElement).toHaveTextContent("Connecting...");
    });
  });

  //test footer render
  it("renders the VolunteerFooter", () => {
    render(<MessagingPage />);

    const footerElement = screen.getByTestId("volunteer-footer");
    expect(footerElement).toBeInTheDocument();
  });

  //test responsiveness
  it("handles window resize", () => {
    render(<MessagingPage />);

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
