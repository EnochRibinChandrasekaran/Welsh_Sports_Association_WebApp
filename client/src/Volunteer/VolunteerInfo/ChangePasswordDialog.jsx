import React from 'react';
import {
  Button,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle
} from "@mui/material";

const ChangePasswordDialog = ({
  open,
  onClose,
  passwords,
  onChange,
  onSubmit,
  errors
}) => {
  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Change Password</DialogTitle>
      <DialogContent>
        <TextField
          margin="dense"
          label="Old Password"
          type="password"
          fullWidth
          variant="outlined"
          name="oldPassword"
          value={passwords.oldPassword}
          onChange={onChange}
        />
        <TextField
          margin="dense"
          label="New Password"
          type="password"
          fullWidth
          variant="outlined"
          name="newPassword"
          value={passwords.newPassword}
          onChange={onChange}
          error={!!errors.newPassword}
          helperText={errors.newPassword}
        />
        <TextField
          margin="dense"
          label="Confirm New Password"
          type="password"
          fullWidth
          variant="outlined"
          name="confirmNewPassword"
          value={passwords.confirmNewPassword}
          onChange={onChange}
          error={!!errors.confirmNewPassword}
          helperText={errors.confirmNewPassword}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="error">Cancel</Button>
        <Button onClick={onSubmit} variant="outlined" color="error">Update Password</Button>
      </DialogActions>
    </Dialog>
  );
};

export default ChangePasswordDialog;
