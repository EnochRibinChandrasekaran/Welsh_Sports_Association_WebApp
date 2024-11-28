import { useState, useEffect, useRef } from 'react';
import Modal from '@mui/material/Modal';
import { Typography, IconButton } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import "../../Volunteer/SignUp.css"
import axios from 'axios';
import { BASE_URL } from '../../apiConfig';

//Component for modal that is used to quick register volunteers
export const QuickRegisterModal = ({ open, handleClose, title, description }) => {

    //Forma modal ref
    const modalRef = useRef(null);

    // Error and form data states
    const [errors, setErrors] =  useState({});
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
    });

    console.log(errors);

    useEffect(() => {
        document.addEventListener("mousedown", handleOutsideClick);
        return () => {
            document.removeEventListener("mousedown", handleOutsideClick);
        };
    });

    // Close the modal when clicking outside the modal
    const handleOutsideClick = (event) => {
        if (modalRef.current && !modalRef.current.contains(event.target)) {
            handleClose();
            clearForm();
        }
    };

    // Handles change when the state renders
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
        validateField(name, value);
    };

    //Validation function to check the values on change
    const validateField = (name, value) => {
        let errMsg = "";

        switch (name) {
            case 'firstName':
            case 'lastName':
                if (!value.trim()) errMsg = "This field is required";
                break;
            case 'email':
                if (!/^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(value)) errMsg = "Invalid email format";
                break;
            case 'phoneNumber':
                if (!/^\d{11}$/.test(value)) errMsg = "Must be 10 digits";
                break;
        }

        setErrors(prev => ({ ...prev, [name]: errMsg }));
    };
    
    // submit fuction to handle on post click
    const handleSubmit = async (e) => {
        e.preventDefault();
        const eventData = {
            ...formData,
            eventID : 1
        }
        await axios
            .post(`${BASE_URL}/api/quickregister`, eventData)
            .then(function (response) {
                alert('Register Successfully for the event')
                handleClose();
                clearForm();
            })
            .catch(function (error) {
                console.error("Error in response:", error);
            });

            
    };

    // To clear the form after signup
    const clearForm = () => {
        setFormData({
            firstName: '',
            lastName: '',
            email: '',
            phone: '',
        });
    }

    return (
        <Modal
            aria-labelledby="quick-register-modal-title"
            aria-describedby="quick-register-modal-description"
            open={open}
            onClose={handleClose}
        >
            <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
                <div ref={modalRef} className="relative w-full max-w-md p-4 bg-white rounded-lg shadow-lg dark:bg-gray-700">
                    <div className="flex justify-between items-center mb-4">
                        <Typography variant="h6" id="quick-register-modal-title">
                            {`Quick Register - ${title}`}
                        </Typography>
                        <IconButton onClick={handleClose}>
                            <CloseIcon />
                        </IconButton>
                    </div>
                    <Typography variant="body2" id="quick-register-modal-description" className="mb-4">
                        {description}
                    </Typography>
                    <form onSubmit={handleSubmit} className="signup-form space-y-4">
                        <div>
                            <label className="block text-sm font-medium text-gray-700">
                                First Name:
                            </label>
                            <input
                                type="text"
                                name="firstName"
                                value={formData.firstName}
                                onChange={handleChange}
                                required
                                className="w-full mt-1 p-2 border rounded-md"
                            />
                        </div>
                        <div>
                            <label className="block text-sm font-medium text-gray-700">
                                Last Name:
                            </label>
                            <input
                                type="text"
                                name="lastName"
                                value={formData.lastName}
                                onChange={handleChange}
                                required
                                className="w-full mt-1 p-2 border rounded-md"
                            />
                        </div>
                        <div>
                            <label className="block text-sm font-medium text-gray-700">
                                Email Address:
                            </label>
                            <input
                                type="email"
                                name="email"
                                value={formData.email}
                                onChange={handleChange}
                                required
                                pattern="[^@\s]+@[^@\s]+\.[^@\s]+"
                                className="w-full mt-1 p-2 border rounded-md"
                            />
                        </div>
                        <div>
                            <label className="block text-sm font-medium text-gray-700">
                                Phone Number:
                            </label>
                            <input
                                type="tel"
                                name="phone"
                                value={formData.phone}
                                onChange={handleChange}
                                required
                                className="w-full mt-1 p-2 border rounded-md"
                            />
                        </div>
                        <button type="submit" className="w-full py-2 mt-4 text-white bg-red-600 rounded-md">
                            Sign Up
                        </button>
                    </form>
                </div>
            </div>
        </Modal>
    );
};