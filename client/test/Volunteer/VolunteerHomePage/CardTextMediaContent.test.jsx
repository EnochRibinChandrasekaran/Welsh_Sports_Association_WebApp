import { render, screen } from "@testing-library/react";
import { expect, test, describe, vi } from "vitest";
import CardTextMediaContent from "../../../src/Volunteer/VolunteerHomePage/MediaContent";
///mock CardMedia component
vi.mock("@mui/material", async (originalModule) => {
  return {
    ...(await originalModule()),
    CardMedia: vi.fn((props) => <div data-testid="card-media" {...props} />),
  };
});

describe("CardTextMediaContent component", () => {
  //mock data
  const defaultProps = {
    mediaClassName: "test-class",
    sx: { padding: 2 },
    style: { borderRadius: "5px" },
    altText: "Test image",
    src: "https://example.com/image.jpg",
  };

  //test img component render
  test('renders an image when type is "img"', () => {
    render(<CardTextMediaContent {...defaultProps} type="img" />);
    const mediaElement = screen.getByTestId("card-media");

    expect(mediaElement).toHaveAttribute("component", "img");
    expect(mediaElement).toHaveAttribute("alt", "Test image");
    expect(mediaElement).toHaveAttribute(
      "image",
      "https://example.com/image.jpg"
    );
    expect(mediaElement).toHaveClass("test-class");
  });

  //test iframe component render
  test('renders an iframe when type is "iframe"', () => {
    render(<CardTextMediaContent {...defaultProps} type="iframe" />);
    const mediaElement = screen.getByTestId("card-media");

    expect(mediaElement).toHaveAttribute("component", "iframe");
    expect(mediaElement).toHaveAttribute(
      "src",
      "https://example.com/image.jpg"
    );
    expect(mediaElement).toHaveClass("test-class");
  });
  //test prop passing
  test("applies correct className, sx, and style props", () => {
    render(<CardTextMediaContent {...defaultProps} type="img" />);
    const mediaElement = screen.getByTestId("card-media");

    expect(mediaElement).toHaveClass("test-class");
    expect(mediaElement).toHaveStyle({ borderRadius: "5px" });
  });
  //test window resize
  test("handles window resize", () => {
    render(<CardTextMediaContent {...defaultProps} />);

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
