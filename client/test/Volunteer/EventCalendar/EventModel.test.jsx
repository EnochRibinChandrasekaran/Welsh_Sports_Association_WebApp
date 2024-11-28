import { expect, describe, test, beforeEach,vi } from "vitest";
import { render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import axios from "axios";
import EventModel from "../../../src/Volunteer/EventCalendar/EventModel";

//mock axios
vi.mock("axios");

describe("EventModel", () => {
  const mockEvent = "1";
  const mockVolunteerID = "123";
  const mockEventInfo = {
    name: "Charity Marathon",
    description: "A marathon for charity.",
    address: "123 Charity St",
    city: "Wales",
    postalCode: "12345",
    landmark: "Near the park",
    organiserName: "Charity Org",
    date: new Date().toISOString(),
    startTime: new Date().toISOString(),
    endTime: new Date().toISOString(),
    rolesNeeded: ["Volunteer", "Support Staff"],
    rewardsOffering: "Free T-Shirt",
    accessibilityAssistance: "Wheelchair Access",
    eventImage: "base64image",
  };

  const setup = (isVisible = true) => {
    render(
      <EventModel
        event={mockEvent}
        isVisible={isVisible}
        toggleModal={vi.fn()}
        volunteerID={mockVolunteerID}
      />
    );
  };

  beforeEach(() => {
    vi.clearAllMocks();

    axios.get.mockResolvedValueOnce({
      data: mockEventInfo,
    });
    axios.get.mockResolvedValueOnce({
      data: false,
    });
  });

  //test component render
  test("renders modal with event details", async () => {
    setup();
    await waitFor(() =>
      expect(screen.getByText(/Charity Marathon/i)).toBeInTheDocument()
    );

    expect(screen.getByText(/A marathon for charity./i)).toBeInTheDocument();
    expect(screen.getByText(/123 Charity St/i)).toBeInTheDocument();
    expect(screen.getByText(/Support Staff/i)).toBeInTheDocument();
  });

  //test success message
  test("renders modal with success message on registration", async () => {
    setup();
    const acceptButton = screen.getByRole("button", { name: /accept/i });
    expect(acceptButton).toBeInTheDocument();
    expect(acceptButton).toBeEnabled();

    axios.put.mockResolvedValueOnce({ status: 200 });
    await userEvent.click(acceptButton);
    await waitFor(() =>
      expect(
        screen.getByText(/Profile Information has been updated/i)
      ).toBeInTheDocument()
    );
  });

  //test failure message
  test("renders modal with error message on failed registration", async () => {
    setup();
    const acceptButton = screen.getByRole("button", { name: /accept/i });
    expect(acceptButton).toBeInTheDocument();
    expect(acceptButton).toBeEnabled();

    axios.put.mockRejectedValueOnce(new Error("Failed to register"));
    await userEvent.click(acceptButton);
    await waitFor(() =>
      expect(screen.getByText(/Please try after later./i)).toBeInTheDocument()
    );
  });

  //test model close
  test("closes the modal when clicked outside", async () => {
    const user = userEvent.setup();
    const toggleModal = vi.fn();
    render(
      <EventModel
        event={mockEvent}
        isVisible={true}
        toggleModal={toggleModal}
        volunteerID={mockVolunteerID}
      />
    );

    await user.click(document.body);
    expect(toggleModal).toHaveBeenCalledTimes(1);
  });

  //test component text
  test("renders modal with roles and accessibility details", async () => {
    setup();

    await waitFor(() =>
      expect(screen.getByText(/Volunteer/i)).toBeInTheDocument()
    );
    expect(screen.getByText(/Wheelchair Access/i)).toBeInTheDocument();
  });

  //test responsiveness
  test('handles window resize', () => {
    setup();

    expect(() => {
      window.dispatchEvent(new Event('resize'));
    }).not.toThrow();
  });
});
