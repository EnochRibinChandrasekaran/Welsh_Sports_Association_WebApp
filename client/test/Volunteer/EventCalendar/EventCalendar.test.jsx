import { expect, describe, beforeEach, afterEach, test ,vi} from "vitest";
import { render, screen } from "@testing-library/react";
import axios from "axios";
import EventCalendar from "../../../src/Volunteer/EventCalendar/EventCalendar";

//mock imports and child components
vi.mock("axios");

vi.mock("../../../src/Volunteer/Header/VolunteerHeader", () => ({
  __esModule: true,
  default: () => <div data-testid="volunteer-header">Volunteer Header</div>,
}));

vi.mock("../../../src/Volunteer/Footer/VolunteerFooter", () => ({
  __esModule: true,
  default: () => <div data-testid="volunteer-footer">Volunteer Footer</div>,
}));

vi.mock("../../../src/Volunteer/EventCalendar/EventModel", () => ({
  __esModule: true,
  default: ({ event, isVisible, toggleModal, volunteerID }) =>
    isVisible ? (
      <div data-testid="event-modal">
        Event Details for {event} (Volunteer ID: {volunteerID})
        <button onClick={toggleModal}>Close</button>
      </div>
    ) : null,
}));
vi.mock("react-i18next", () => ({
  useTranslation: () => ({
    t: (key) => {
      const translations = {
        home: "home",
        events: "events",
        trophy: "trophy",
        contactus: "contactus",
        chat: "chat",
        Cymraeg: "Cymraeg",
        English: "English",
      };
      return translations[key] || key;
    },
    i18n: {
      changeLanguage: vi.fn(),
    },
  }),
}));

describe("EventCalendar Component", () => {
    //mock data
  const mockEvents = [
    {
      id: "1",
      name: "Charity Marathon",
      date: "2024-10-20",
    },
    {
      id: "2",
      name: "Community Cleanup",
      date: "2024-10-22",
    },
    {
      id: "3",
      name: "Tech Talk",
      date: "2024-09-15",
    },
  ];

  const fixedDate = new Date("2024-10-15T12:00:00Z");

  beforeEach(() => {
    vi.useFakeTimers();
    vi.setSystemTime(fixedDate);
    axios.get.mockResolvedValue({ data: mockEvents });
  });

  afterEach(() => {
    vi.useRealTimers();
  });

  const setup = () => {
    render(<EventCalendar />);
  };

  //test render component
  test("renders header, footer, and calendar grid correctly", async () => {
    setup();
    const monthYearText = `Events Calendar - October 2024`;
    const daysOfWeek = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

    expect(screen.getByTestId("volunteer-header")).toBeInTheDocument();
    expect(screen.getByTestId("volunteer-footer")).toBeInTheDocument();
    expect(screen.getByText(monthYearText)).toBeInTheDocument();
    daysOfWeek.forEach((day) => {
      expect(screen.getByText(day)).toBeInTheDocument();
    });
  });

  //test current date
  test("highlights today's date correctly", async () => {
    setup();
    const todayDay = "15";
    const todayCell = screen.getByText(todayDay).closest("div");

    expect(todayCell).toHaveClass("bg-red-100");
  });

  //test responsiveness
  test('handles window resize', () => {
    setup();

    expect(() => {
      window.dispatchEvent(new Event('resize'));
    }).not.toThrow();
  });
});
