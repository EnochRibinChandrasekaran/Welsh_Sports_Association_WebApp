import React from 'react';
import {
  TableRow,
  TableCell,
  Typography,
  Link
} from '@mui/material';

export const CommonRow = ({ label, value, linkHref = "#" }) => {
  return (
    <TableRow>
      <TableCell>
        <Typography variant="subtitle1" className="font-bold">
          {label}
        </Typography>
      </TableCell>
      <TableCell>
        <Typography variant="body1">{value}</Typography>
      </TableCell>
      <TableCell align="right">
        <Link href={linkHref} underline="none">
          Change
        </Link>
      </TableCell>
    </TableRow>
  );
};