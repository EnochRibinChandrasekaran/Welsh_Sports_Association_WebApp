import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { beforeEach, describe, expect, it, vi } from "vitest";
import axios from "axios";
import OrganiserInfoTemplate from "../../src/Organiser/OrganiserInfoTemplate";
//mock axios
vi.mock("axios");

const mockData = {
  organiserID: 1,
  editable: true,
  organiserInfo: {
    companyName: "Test Company",
    postCode: "12345",
    address: "123 Main St",
    telephone: "1234567890",
    email: "test@example.com",
    website: "https://example.com",
    foundingDate: "2022-01-01",
    associatedClubs: "Test Club",
    numberOfMembers: 10,
    turnover: "1000000",
    membershipCategory: "premium",
    preferredLanguage: "English",
    charity: true,
    newsletter: true,
    mainContactName: "Enoch Ribin",
    mainContactPosition: "CEO",
    mainContactMobileNumber: "0987654321",
  },
  turnoverOptions: [
    { id: 1, types: "Less than 100k" },
    { id: 2, types: "100k-500k" },
  ],
};

describe("OrganiserInfoTemplate", () => {
  beforeEach(() => {
    axios.get.mockResolvedValueOnce({
      data: mockData.turnoverOptions,
    });
    axios.get.mockResolvedValueOnce({
      data: mockData.organiserInfo,
    });
  });

  //test component render
  it("renders the component correctly", async () => {
    render(
      <OrganiserInfoTemplate
        editable={true}
        organiserID={mockData.organiserID}
        onUpdate={vi.fn()}
      />
    );

    await waitFor(() => {
      expect(screen.getByText("Organisation Info")).toBeInTheDocument();
    });
  });

  //test error validation
  it("displays validation errors for incorrect inputs", async () => {
    render(
      <OrganiserInfoTemplate
        editable={true}
        organiserID={mockData.organiserID}
        onUpdate={vi.fn()}
      />
    );
    const emailInput = screen.getByLabelText("Email");
    fireEvent.change(emailInput, { target: { value: "invalid email" } });
    const saveButton = screen.getByText("Save Changes");
    fireEvent.click(saveButton);

    await waitFor(() => {
      expect(screen.getByText("Invalid email format")).toBeInTheDocument();
    });
  });

  //test disabled
  it("disables form fields when not editable", async () => {
    render(
      <OrganiserInfoTemplate
        editable={false}
        organiserID={mockData.organiserID}
        onUpdate={vi.fn()}
      />
    );
    const emailInput = screen.getByLabelText("Email");
    const saveButton = screen.getByText("Save Changes");

    expect(emailInput).toBeDisabled();
    expect(saveButton).toBeDisabled();
  });

  //test submit
  it("handles form submission correctly", async () => {
    const mockOnUpdate = vi.fn();

    render(
      <OrganiserInfoTemplate
        editable={true}
        organiserID={mockData.organiserID}
        onUpdate={mockOnUpdate}
      />
    );

    const postalCodeInput = screen.getByLabelText("Postal Code");
    fireEvent.change(postalCodeInput, { target: { value: "12345" } });
    const saveButton = screen.getByText("Save Changes");
    fireEvent.click(saveButton);
    await waitFor(() => {
      expect(mockOnUpdate).toHaveBeenCalled();
    });
  });

  //test change password component
  it("opens and closes the Change Password dialog", async () => {
    render(
      <OrganiserInfoTemplate
        editable={true}
        organiserID={mockData.organiserID}
        onUpdate={vi.fn()}
      />
    );
    const changePasswordLink = screen.getByRole("button", {
      name: "Change Password",
    });
    fireEvent.click(changePasswordLink);
    await waitFor(() => {
      expect(
        screen.getByRole("heading", { name: "Change Password" })
      ).toBeInTheDocument();
    });

    const cancelButton = screen.getByRole("button", { name: "Cancel" });
    fireEvent.click(cancelButton);
    await waitFor(() => {
      expect(
        screen.queryByRole("heading", { name: "Change Password" })
      ).not.toBeInTheDocument();
    });
  });

  //test responsiveness
  it("handles window resize", () => {
    render(<OrganiserInfoTemplate />);
    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
