import { render, screen } from "@testing-library/react";
import { vi, describe, it, expect } from "vitest";
import HeaderNavigationLinks from "../../../src/Volunteer/Header/HeaderNavigationLinks";
//mock i18next
vi.mock("react-i18next", () => ({
  useTranslation: () => ({
    t: (str) => str,
  }),
}));

describe("HeaderNavigationLinks Component", () => {
  //mock data
  const links = [
    { href: "/home", label: "Home" },
    { href: "/about", label: "About" },
    { href: "/contact", label: "Contact" },
  ];

  //test component render
  it("renders the correct number of links", () => {
    render(<HeaderNavigationLinks links={links} />);
    const listItems = screen.getAllByRole("listitem");

    expect(listItems).toHaveLength(links.length);
  });

  //test links render
  it("renders links with correct href and translated label", () => {
    render(<HeaderNavigationLinks links={links} />);

    links.forEach((link) => {
      const linkElement = screen.getByRole("link", { name: link.label });
      expect(linkElement).toBeInTheDocument();
      expect(linkElement).toHaveAttribute("href", link.href);
    });
  });

  //test responsive
  it("handles window resize", () => {
    render(<HeaderNavigationLinks links={links} />);

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
