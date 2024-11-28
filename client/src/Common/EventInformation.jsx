import React from 'react';
import { Container, Typography, Table, TableBody, Divider } from '@mui/material';
import { CommonRow } from './CommonRow';
import './EventInformation.css';

export const EventInformation = ({ event }) => {
  if (!event) return <div>No event selected</div>;

  return (
    <Container className="event-details">
      <Typography variant="h4" gutterBottom className="font-bold">
        {event.name}
      </Typography>
      <Divider />
      <Table className="border-separate border-spacing-y-4">
        <TableBody>
          <CommonRow label="Event name" value={event.name} />
          <CommonRow label="Date" value={new Date(event.date).toDateString()} />
          <CommonRow
            label="About"
            value={event.description || "No description available."}
          />
          <CommonRow label="Email address" value={event.contactEmail} />
        </TableBody>
      </Table>
    </Container>
  );
};

export default EventInformation;
