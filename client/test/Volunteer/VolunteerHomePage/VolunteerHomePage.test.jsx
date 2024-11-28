import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { beforeEach, describe, expect, it, vi } from "vitest";
import VolunteerHomePage from "../../../src/Volunteer/VolunteerHomePage/VolunteerHomePage";
import { useAuth0 } from "@auth0/auth0-react";
import { useVolunteer } from "../../../src/Context/Volunteer";
import axios from "axios";
import { BASE_URL } from "../../../src/apiConfig";

//mock imports
vi.mock("@auth0/auth0-react");
vi.mock("../../../src/Context/Volunteer");
vi.mock("axios");

const mockVolunteerContext = {
  volunteerID: 1,
  updateVolunteerID: vi.fn(),
};

const mockAuth0 = {
  user: { sub: "123" },
  isAuthenticated: true,
  getAccessTokenSilently: vi.fn(),
};

useAuth0.mockReturnValue(mockAuth0);
useVolunteer.mockReturnValue(mockVolunteerContext);

vi.mock("../../../src/Volunteer/Header/VolunteerHeader", () => ({
  __esModule: true,
  default: ({ logged, updated, volunteerID }) => (
    <div data-testid="volunteer-header">
      <span>{logged ? "Logged In" : "Logged Out"}</span>
      <span>Updated: {updated ? "True" : "False"}</span>
      <span>Volunteer ID: {volunteerID}</span>
    </div>
  ),
}));

vi.mock("../../../src/Volunteer/Footer/VolunteerFooter", () => ({
  __esModule: true,
  default: () => <div data-testid="volunteer-footer">Footer</div>,
}));

describe("VolunteerHomePage Component", () => {
  beforeEach(() => {
    axios.post.mockResolvedValue({ data: 2 });
  });

  //test component renders
  it("renders VolunteerHeader, VolunteerFooter, and other components", () => {
    render(<VolunteerHomePage />);

    expect(screen.getByTestId("volunteer-header")).toBeInTheDocument();
    expect(screen.getByTestId("volunteer-footer")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("eventSearch")).toBeInTheDocument();
    expect(screen.getByText(/filter/i)).toBeInTheDocument();
  });
  //test updates in search
  it("updates search term on input change", () => {
    render(<VolunteerHomePage />);
    const input = screen.getByPlaceholderText("eventSearch");
    fireEvent.change(input, { target: { value: "community event" } });

    expect(input.value).toBe("community event");
  });
  //test search submit
  it("handles form submission correctly", () => {
    const consoleSpy = vi.spyOn(console, "log");
    render(<VolunteerHomePage />);
    const input = screen.getByPlaceholderText("eventSearch");
    const searchButton = screen.getByTestId("search-button");
    fireEvent.change(input, { target: { value: "charity run" } });
    fireEvent.click(searchButton);

    expect(consoleSpy).toHaveBeenCalledTimes(2);
  });
  //test volunteer auth
  it("fetches user token and saves volunteer data when authenticated", async () => {
    mockAuth0.getAccessTokenSilently.mockResolvedValue("mockAccessToken");
    render(<VolunteerHomePage />);
    await waitFor(() => {
      expect(mockAuth0.getAccessTokenSilently).toHaveBeenCalled();
      expect(axios.post).toHaveBeenCalledWith(
        `${BASE_URL}/api/volunteer/saveVolunteer`,
        {
          firstName: undefined,
          lastName: undefined,
          email: undefined,
          phoneNumber: undefined,
        }
      );

      expect(mockVolunteerContext.updateVolunteerID).toHaveBeenCalledWith(2);
    });
  });

  //test error handling in auth
  it("handles errors during token fetching", async () => {
    const consoleSpy = vi.spyOn(console, "error");
    mockAuth0.getAccessTokenSilently.mockRejectedValue(
      new Error("Error fetching token")
    );
    render(<VolunteerHomePage />);

    await waitFor(() => {
      expect(consoleSpy).toHaveBeenCalledWith(
        "Error fetching token:",
        new Error("Error fetching token")
      );
    });
  });

  //test responsive
  it("handles window resize", () => {
    render(<VolunteerHomePage />);

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
