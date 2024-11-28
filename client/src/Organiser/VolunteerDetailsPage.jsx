import React from 'react';
import { useParams } from 'react-router-dom';
import { Container, Row, Col, Image, Badge } from 'react-bootstrap';

const VolunteerDetailsPage = ({ volunteers }) => {
    const { id } = useParams();

    if (!volunteers || volunteers.length === 0) {
        return <div>Loading...</div>; // or handle this scenario as needed
    }

    const volunteer = volunteers.find(vol => vol.id === parseInt(id, 10));

    if (!volunteer) {
        return <div>Volunteer not found</div>;
    }

    return (
        <Container className="mt-5">
            <Row>
                <Col md={4}>
                    <Image src={volunteer.profilePicture} rounded fluid />
                </Col>
                <Col md={8}>
                    <h2>{volunteer.name}</h2>
                    <p><strong>Email:</strong> {volunteer.email}</p>
                    <p><strong>DOB:</strong> {volunteer.dob} ({volunteer.age})</p>
                    <p>
                        <strong>DBS Certified:</strong>
                        {volunteer.dbsCertified ? (
                            <Badge bg="success">Yes</Badge>
                        ) : (
                            <Badge bg="danger">No</Badge>
                        )}
                    </p>
                    <p><strong>Additional Information:</strong> {volunteer.additionalInfo}</p>
                </Col>
            </Row>
        </Container>
    );
};

export default VolunteerDetailsPage;
