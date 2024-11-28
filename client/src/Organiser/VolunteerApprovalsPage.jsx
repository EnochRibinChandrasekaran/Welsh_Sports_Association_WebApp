import React, { useState, useEffect } from 'react';
import { Card, Button, Row, Col, Container, Modal } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const VolunteerApprovalsPage = ({ volunteers }) => {
    const [volunteerList, setVolunteerList] = useState([]);

    useEffect(() => {
        if (volunteers) {
            setVolunteerList(volunteers.map(volunteer => ({ ...volunteer, status: null, finalised: false })));
        }
    }, [volunteers]);

    const [selectedVolunteer, setSelectedVolunteer] = useState(null);
    const [showModal, setShowModal] = useState(false);

    const handleDecision = (volunteer, decision) => {
        setSelectedVolunteer({ ...volunteer, decision });
        setShowModal(true);
    };

    const finaliseDecision = () => {
        setVolunteerList(volunteerList.map(volunteer =>
            volunteer.id === selectedVolunteer.id ? { ...volunteer, status: selectedVolunteer.decision, finalised: true } : volunteer
        ));
        setShowModal(false);
        setSelectedVolunteer(null);
        // Simulate sending notification to the volunteer
        console.log(`Notification sent to ${selectedVolunteer.name} about ${selectedVolunteer.decision}`);
    };

    const cancelDecision = () => {
        setShowModal(false);
        setSelectedVolunteer(null);
    };

    return (
        <Container className="mt-5">
            <h1 className="text-centre mb-4">Volunteer Approvals</h1>
            <Row>
                {volunteerList.map((volunteer) => (
                    <Col key={volunteer.id} md={6} lg={4} className="mb-4">
                        <Card>
                            <Card.Img variant="top" src={volunteer.profilePicture} alt="Profile" />
                            <Card.Body>
                                <Card.Title>{volunteer.name}</Card.Title>
                                <Card.Text>
                                    <strong>DOB:</strong> {volunteer.dob} ({volunteer.age} years)<br />
                                    <strong>DBS Certified:</strong> {volunteer.dbsCertified ? 'Yes' : 'No'}<br />
                                    <strong>Credentials:</strong> {volunteer.credentials.join(', ')}<br />
                                    <strong>Event:</strong> {volunteer.event}
                                </Card.Text>
                                <div className="d-flex justify-content-between">
                                    {!volunteer.finalised && (
                                        <>
                                            <Button variant="success" onClick={() => handleDecision(volunteer, 'accept')}>Accept</Button>
                                            <Button variant="danger" onClick={() => handleDecision(volunteer, 'reject')}>Reject</Button>
                                        </>
                                    )}
                                </div>
                                {volunteer.finalised && (
                                    <div className={`mt-2 ${volunteer.status === 'accept' ? 'text-success' : 'text-danger'}`}>
                                        <strong>{volunteer.status.charAt(0).toUpperCase() + volunteer.status.slice(1)}ed</strong>
                                    </div>
                                )}
                                <Link to={`/volunteer-details/${volunteer.id}`} className="btn btn-primary mt-2">
                                    View Details
                                </Link>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>

            <Modal show={showModal} onHide={cancelDecision}>
                <Modal.Header closeButton>
                    <Modal.Title>Finalise Decision</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Are you sure you want to {selectedVolunteer?.decision} {selectedVolunteer?.name} for the {selectedVolunteer?.event} event?
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={cancelDecision}>
                        Cancel
                    </Button>
                    <Button variant="primary" onClick={finaliseDecision}>
                        Yes
                    </Button>
                </Modal.Footer>
            </Modal>
        </Container>
    );
};

export default VolunteerApprovalsPage;
