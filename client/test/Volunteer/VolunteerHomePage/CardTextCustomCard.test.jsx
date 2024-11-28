import { render, screen } from "@testing-library/react";
import { describe, test, expect } from "vitest";
import { vi } from "vitest";
import CardTextCustomCard from "../../../src/Volunteer/VolunteerHomePage/CardTextCustomCard";

//mock imports and child components
vi.mock("../../../src/Volunteer/VolunteerHomePage/MediaContent", () => ({
  __esModule: true,
  default: ({ mediaClassName, sx, type, src, altText }) => (
    <div
      data-testid="media-content"
      className={mediaClassName}
      data-type={type}
    >
      <img src={src} alt={altText} style={sx} />
    </div>
  ),
}));

vi.mock("../../../src/Volunteer/VolunteerHomePage/CustomTypography", () => ({
  __esModule: true,
  default: ({ children, className, variant }) => (
    <div data-testid="typography" className={className} data-variant={variant}>
      {children}
    </div>
  ),
}));

describe("CardTextCustomCard Component", () => {
  const defaultProps = {
    mediaClassName: "media-class",
    mediaSX: { width: "100px" },
    mediaType: "image",
    mediaSrc: "/path/to/image.jpg",
    mediaAltText: "Sample Image",
    contentClassName: "content-class",
    title: "Sample Title",
    description: "Sample Description",
    cardVersion: "1",
  };
  //test render component version 1
  test("renders card version 1 with media first and content after", () => {
    render(<CardTextCustomCard {...defaultProps} cardVersion="1" />);
    const mediaContent = screen.getByTestId("media-content");
    const typographyElements = screen.getAllByTestId("typography");

    expect(mediaContent).toBeInTheDocument();
    expect(typographyElements[0]).toHaveTextContent("Sample Title");
    expect(typographyElements[1]).toHaveTextContent("Sample Description");
  });
  //test render component version 2
  test("renders card version 2 with content first and media after", () => {
    render(<CardTextCustomCard {...defaultProps} cardVersion="2" />);
    const mediaContent = screen.getByTestId("media-content");
    const typographyElements = screen.getAllByTestId("typography");

    expect(typographyElements[0]).toHaveTextContent("Sample Title");
    expect(typographyElements[1]).toHaveTextContent("Sample Description");
    expect(mediaContent).toBeInTheDocument();
  });
  //test render invalid version
  test("does not render anything for an invalid card version", () => {
    render(<CardTextCustomCard {...defaultProps} cardVersion="invalid" />);

    expect(screen.queryByTestId("media-content")).not.toBeInTheDocument();
    expect(screen.queryByTestId("typography")).not.toBeInTheDocument();
  });
  //test media component
  test("passes correct props to media component", () => {
    render(<CardTextCustomCard {...defaultProps} cardVersion="1" />);
    const mediaContent = screen.getByTestId("media-content");
    const imgElement = screen.getByRole("img");

    expect(mediaContent).toHaveClass("media-class");
    expect(mediaContent).toHaveAttribute("data-type", "image");
    expect(imgElement).toHaveAttribute("src", "/path/to/image.jpg");
    expect(imgElement).toHaveAttribute("alt", "Sample Image");
    expect(imgElement).toHaveStyle("width: 100px");
  });
  //test card text component
  test("passes correct title and description to typography component", () => {
    render(<CardTextCustomCard {...defaultProps} cardVersion="1" />);
    const typographyElements = screen.getAllByTestId("typography");

    expect(typographyElements[0]).toHaveTextContent("Sample Title");
    expect(typographyElements[0]).toHaveAttribute("data-variant", "h6");
    expect(typographyElements[1]).toHaveTextContent("Sample Description");
    expect(typographyElements[1]).toHaveAttribute("data-variant", "body2");
  });
  //test window resize
  test("handles window resize", () => {
    render(<CardTextCustomCard {...defaultProps} />);

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
