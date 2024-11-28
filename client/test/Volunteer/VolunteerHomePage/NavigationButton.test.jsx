import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { describe, test, vi, expect } from "vitest";
import NavigationButton from "../../../src/Volunteer/VolunteerHomePage/NavigationButton";
//mock react icons
vi.mock("react-icons/md", async (originalModule) => {
  return {
    ...(await originalModule()),
    MdNavigateBefore: vi.fn(({ onClick, color, className }) => (
      <div
        data-testid="navigate-before"
        className={className}
        onClick={onClick}
        style={{ color }}
      />
    )),
    MdNavigateNext: vi.fn(({ onClick, color, className }) => (
      <div
        data-testid="navigate-next"
        className={className}
        onClick={onClick}
        style={{ color }}
      />
    )),
  };
});

describe("NavigationButton Component", () => {
  //mock data
  const defaultProps = {
    commonClassName: "test-common-class",
    commonStyles: { display: "flex", justifyContent: "center" },
    buttonColor: "blue",
    navigationClassName: "test-navigation-class",
    handlePrev: vi.fn(),
    handleNext: vi.fn(),
  };

  //test buttons
  test("renders both navigation buttons with correct styles and classes", () => {
    render(<NavigationButton {...defaultProps} />);
    const container = screen.getByTestId("navigate-before").closest("div");
    const prevButton = screen.getByTestId("navigate-before");
    const nextButton = screen.getByTestId("navigate-next");

    expect(container).toHaveClass("test-navigation-class");
    expect(container).toHaveStyle({ display: "block" });
    expect(prevButton).toHaveStyle({ color: "rgb(0, 0, 255)" });
    expect(nextButton).toHaveStyle({ color: "rgb(0, 0, 255)" });
    expect(prevButton).toHaveClass("test-navigation-class");
    expect(nextButton).toHaveClass("test-navigation-class");
  });
  //test handlePrev
  test("calls handlePrev when previous button is clicked", async () => {
    const user = userEvent.setup();
    render(<NavigationButton {...defaultProps} />);
    const prevButton = screen.getByTestId("navigate-before");
    await user.click(prevButton);

    expect(defaultProps.handlePrev).toHaveBeenCalled();
  });

  //test handleNext
  test("calls handleNext when next button is clicked", async () => {
    const user = userEvent.setup();
    render(<NavigationButton {...defaultProps} />);
    const nextButton = screen.getByTestId("navigate-next");
    await user.click(nextButton);

    expect(defaultProps.handleNext).toHaveBeenCalled();
  });

  //test window resize
  test("handles window resize", () => {
    render(<NavigationButton {...defaultProps} />);

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
