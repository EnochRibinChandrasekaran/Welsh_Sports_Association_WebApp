import { render, screen, waitFor } from "@testing-library/react";
import { describe, expect, it, vi } from "vitest";
import userEvent from "@testing-library/user-event";
import VolunteerInfo from "../../../src/Volunteer/VolunteerInfo/VolunteerInfo";

//mock imports
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

vi.mock(
  "../../../src/Volunteer/VolunteerInfo/VolunteerProfileInfoTemplate",
  () => ({
    __esModule: true,
    default: ({ editable, onUpdate, volunteerID }) => (
      <div data-testid="volunteer-info">
        <span>Editable: {editable ? "True" : "False"}</span>
        <span>Volunteer ID: {volunteerID}</span>
        <button onClick={onUpdate} data-testid="update-button">
          Update Info
        </button>
      </div>
    ),
  })
);

describe("VolunteerInfo Component", () => {
  //test component render
  it("renders VolunteerHeader, VolunteeerInfo, and VolunteerFooter correctly", () => {
    render(<VolunteerInfo />);
    const volunteerIDElements = screen.getAllByText("Volunteer ID: 1");

    expect(screen.getByTestId("volunteer-header")).toBeInTheDocument();
    expect(screen.getByText("Logged In")).toBeInTheDocument();
    expect(screen.getByText("Updated: False")).toBeInTheDocument();
    expect(volunteerIDElements).toHaveLength(2); // Expect two elements with 'Volunteer ID: 1'
    expect(screen.getByTestId("volunteer-info")).toBeInTheDocument();
    expect(screen.getByText("Editable: True")).toBeInTheDocument();
    expect(screen.getByTestId("volunteer-footer")).toBeInTheDocument();
  });
  //test volunteer header component
  it("passes correct props to VolunteerHeader", () => {
    render(<VolunteerInfo />);
    const header = screen.getByTestId("volunteer-header");
    const volunteerIDElements = screen.getAllByText("Volunteer ID: 1");

    expect(header).toBeInTheDocument();
    expect(screen.getByText("Logged In")).toBeInTheDocument();
    expect(screen.getByText("Updated: False")).toBeInTheDocument();
    expect(volunteerIDElements).toHaveLength(2);
  });
  //test handle update
  it("updates state when handleUpdate is called", async () => {
    render(<VolunteerInfo />);
    const updateButton = screen.getByTestId("update-button");
    userEvent.click(updateButton);

    await waitFor(() => {
      expect(screen.getByText("Updated: True")).toBeInTheDocument();
    });
  });
  //test window resize
  it("handles window resize", () => {
    render(<VolunteerInfo />);

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
