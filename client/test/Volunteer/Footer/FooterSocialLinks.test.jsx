import { it, expect, describe } from "vitest";
import { render, screen } from "@testing-library/react";
import FooterSocialLinks from "../../../src/Volunteer/Footer/FooterSocialLinks";

describe("FooterSocialLinks", () => {
  //test anchor tag render
  it("should render an achor tag with href", () => {
    render(<FooterSocialLinks Icon="FaHome" href="/volunteer" />);
    const link = screen.getByRole("link");
    expect(link).toBeInTheDocument();
    expect(link).toHaveAttribute("href", "/volunteer");
  }),
    //test image render
    it("should render an achor tag with icon", () => {
      render(<FooterSocialLinks Icon="FaHome" href="/volunteer" />);
      const link = screen.getByRole("link");
      expect(link).toBeInTheDocument();
      expect(link).toContainHTML("fahome");
    });

  //test responsive
  it("handles window resize", () => {
    render(<FooterSocialLinks Icon="FaHome" href="/volunteer" />);

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
