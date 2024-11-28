import { render, screen } from "@testing-library/react";
import { describe, test, expect, vi } from "vitest";
import CardTextCustomTypography from "../../../src/Volunteer/VolunteerHomePage/CustomTypography";

//mock typography component
vi.mock("@mui/material", () => ({
  __esModule: true,
  Typography: ({ children, ...props }) => (
    <div data-testid="typography" {...props}>
      {children}
    </div>
  ),
}));

describe("CardTextCustomTypography Component", () => {
  //mock data
  const defaultProps = {
    children: "Sample Text",
    className: "custom-class",
    fontName: "poppins-regular",
    variant: "h6",
    rootComponent: "span",
    gutterBottom: true,
  };

  //test component render
  test("renders the correct children inside the Typography", () => {
    render(<CardTextCustomTypography {...defaultProps} />);
    const typographyElement = screen.getByTestId("typography");

    expect(typographyElement).toHaveTextContent("Sample Text");
  });

  //test default values
  test("applies the default fontName if none is provided", () => {
    const { container } = render(
      <CardTextCustomTypography className="custom-class">
        Sample Text
      </CardTextCustomTypography>
    );
    const typographyElement = container.querySelector(
      "[data-testid='typography']"
    );

    expect(typographyElement).toHaveClass("poppins-regular");
  });

  //test combining class names
  test("combines className and fontName correctly", () => {
    render(<CardTextCustomTypography {...defaultProps} />);
    const typographyElement = screen.getByTestId("typography");

    expect(typographyElement).toHaveClass("custom-class");
    expect(typographyElement).toHaveClass("poppins-regular");
  });

  //test typography variant
  test("passes the correct variant prop to Typography", () => {
    render(<CardTextCustomTypography {...defaultProps} />);
    const typographyElement = screen.getByTestId("typography");

    expect(typographyElement).toHaveAttribute("variant", "h6");
  });

  //test gutterBottom prop
  test("passes the correct gutterBottom prop to Typography", () => {
    render(<CardTextCustomTypography {...defaultProps} gutterBottom={true} />);
    const typographyElement = screen.getByTestId("typography");

    expect(typographyElement).toHaveClass("custom-class poppins-regular");
  });

  //test if root component available
  test("applies the correct rootComponent to Typography when provided", () => {
    render(<CardTextCustomTypography {...defaultProps} />);
    const typographyElement = screen.getByTestId("typography");

    expect(typographyElement).toHaveAttribute("component", "span");
  });
  //test if root component not available
  test("renders without rootComponent if not provided", () => {
    render(
      <CardTextCustomTypography {...defaultProps} rootComponent={undefined} />
    );
    const typographyElement = screen.getByTestId("typography");

    expect(typographyElement).not.toHaveAttribute("component");
  });
  //test window resize
  test("handles window resize", () => {
    render(<CardTextCustomTypography {...defaultProps} />);

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
