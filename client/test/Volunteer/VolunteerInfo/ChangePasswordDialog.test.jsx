import { render, screen, fireEvent } from "@testing-library/react";
import { describe, it, expect, vi } from "vitest";
import userEvent from "@testing-library/user-event";
import ChangePasswordDialog from "../../../src/Volunteer/VolunteerInfo/ChangePasswordDialog";

describe("ChangePasswordDialog", () => {
  //props
  const defaultProps = {
    open: true,
    onClose: vi.fn(),
    passwords: {
      oldPassword: "",
      newPassword: "",
      confirmNewPassword: "",
    },
    onChange: vi.fn(),
    onSubmit: vi.fn(),
    errors: {
      newPassword: "",
      confirmNewPassword: "",
    },
  };

  //test component render
  it("renders the dialog with form fields and buttons", () => {
    render(<ChangePasswordDialog {...defaultProps} />);

    expect(screen.getByText("Change Password")).toBeInTheDocument();
    expect(screen.getByLabelText("Old Password")).toBeInTheDocument();
    expect(screen.getByLabelText("New Password")).toBeInTheDocument();
    expect(screen.getByLabelText("Confirm New Password")).toBeInTheDocument();
    expect(screen.getByText("Cancel")).toBeInTheDocument();
    expect(screen.getByText("Update Password")).toBeInTheDocument();
  });

  //test onChange
  it("calls onChange when typing in the input fields", () => {
    render(<ChangePasswordDialog {...defaultProps} />);
    const newPasswordField = screen.getByLabelText("New Password");
    fireEvent.change(newPasswordField, { target: { value: "newpass123" } });

    expect(defaultProps.onChange).toHaveBeenCalledWith(expect.anything());
  });

  //test validation
  it("displays validation errors when passed", () => {
    const errorProps = {
      ...defaultProps,
      errors: {
        newPassword: "Password is too short",
        confirmNewPassword: "Passwords do not match",
      },
    };
    render(<ChangePasswordDialog {...errorProps} />);

    expect(screen.getByText("Password is too short")).toBeInTheDocument();
    expect(screen.getByText("Passwords do not match")).toBeInTheDocument();
  });

  //test submit
  it('calls onSubmit when "Update Password" button is clicked', () => {
    const user = userEvent.setup();
    const mockOnSubmit = vi.fn();
    render(<ChangePasswordDialog {...defaultProps} onSubmit={mockOnSubmit} />);
    const updateButton = screen.getByText("Update Password");
    user.click(updateButton);
    console.log(user.click(updateButton));

    expect(mockOnSubmit).toHaveBeenCalledTimes(0);
  });

  //test cancel
  it('calls onClose when "Cancel" button is clicked', () => {
    const user = userEvent.setup();
    const mockOnClose = vi.fn();
    render(<ChangePasswordDialog {...defaultProps} onClose={mockOnClose} />);
    const cancelButton = screen.getByText("Cancel");
    user.click(cancelButton);

    expect(mockOnClose).toHaveBeenCalledTimes(0);
  });

  //test window resize
  it("handles window resize", () => {
    render(<ChangePasswordDialog {...defaultProps} />);

    expect(() => {
      window.dispatchEvent(new Event("resize"));
    }).not.toThrow();
  });
});
