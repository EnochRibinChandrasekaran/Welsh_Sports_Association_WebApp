// Service used to help automate email sending for Contact Form: https://www.emailjs.com/

import React, { useState } from "react";
import emailjs from "emailjs-com";
import "./NewContactUs.css";
import VolunteerHeader from "../Volunteer/Header/VolunteerHeader";
import VolunteerFooter from "../Volunteer/Footer/VolunteerFooter";
import axios from "axios";

const Contact = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    subject: "",
    message: "",
  });

  const [responseMessage, setResponseMessage] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Replace these placeholders with your EmailJS information
    const serviceId = "DissContact";
    const templateId = "template_4u1ow3d";
    const userId = "5eWMfVdoqY-PyY3Sr";

    emailjs
      .send(serviceId, templateId, formData, userId)
      .then(async (response) => {
        console.log("SUCCESS!", response.status, response.text);
        try {
          await axios.post(
            "http://localhost:8080/home/contactus/addContact",
            formData
          );
        } catch (error) {
          console.log("ERROR!", error);
        }

        if (response.status === 200) {
          console.log("Data stored successfully:", response.data);
        }
        setResponseMessage("Your message has been sent successfully.");
        setFormData({
          name: "",
          email: "",
          subject: "",
          message: "",
        });

        // axios.post(serviceId, userId, {})
      })
      .catch((error) => {
        console.error("FAILED...", error);
        setResponseMessage(
          "There was an error sending your message. Please try again later."
        );
      });
  };

  return (
    <div className="form-container">
      <VolunteerHeader logged={true} volunteerID={1} />
      <main className="main-content">
        <div className="contact-form-container">
          <h2>Contact Us</h2>
          <form onSubmit={handleSubmit} className="contact-form">
            <div className="form-group">
              <label>Name:</label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Email:</label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Subject:</label>
              <input
                type="text"
                name="subject"
                value={formData.subject}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Message:</label>
              <textarea
                name="message"
                value={formData.message}
                onChange={handleChange}
                required
              ></textarea>
            </div>
            <button
              className="w-full bg-[#cc1b00] text-white hover:bg-[#dc1b00]  px-4 py-2 rounded-md text-sm tracking-wide transition-all"
              type="submit"
            >
              Send Message
            </button>
          </form>
          {responseMessage && (
            <p className="response-message">{responseMessage}</p>
          )}
        </div>
      </main>
      <VolunteerFooter />
    </div>
  );
};

export default Contact;
