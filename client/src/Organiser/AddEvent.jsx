import React, { useEffect, useState } from "react"; // Importing useState hook from React to manage state
import Select from "react-select"; // Importing Select component from react-select for multi-select dropdowns
import DatePicker from "react-datepicker"; // Importing DatePicker component from react-datepicker
import "react-datepicker/dist/react-datepicker.css"; // Importing the styles for react-datepicker
import axios from "axios"; // Importing axios for file upload
import Autocomplete from "react-google-autocomplete"; // Importing Autocomplete for Google Places API (https://guillermodlpa.com/blog/guide-address-place-autocomplete-input-with-google-apis-and-react)
import { BASE_URL } from "../apiConfig";

export default function AddEvent() {
  console.log("Component Rendered!");

  // Defining state variables for form inputs
  const [selectedRoles, setSelectedRoles] = useState([]); // State to manage selected roles
  const [selectedAccessibilty, setSelectedAccessibility] = useState([]);
  const [eventDate, setEventDate] = useState(null); // State to manage event date
  const [startTime, setStartTime] = useState(null); // State to manage start time
  const [endTime, setEndTime] = useState(null); // State to manage end time
  const [mainImage, setMainImage] = useState(null); // State to manage main image
  const [mainImageUrl, setMainImageUrl] = useState(null); // State to manage main image preview URL
  const [rewards, setRewards] = useState([]); // State to manage the list of rewards
  const [rewardInput, setRewardInput] = useState(""); // State to manage the current reward input
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    address: "",
    city: "",
    postalCode: "",
    landmark: "",
    dbsRequired: false, // State to manage the DBS Required checkbox\
  }); // State to manage other form data
  const [errors, setErrors] = useState({}); // State to manage form validation errors
  const [successMessage, setSuccessMessage] = useState(""); // State to manage success message
  const GOOGLE_API_KEY = process.env.REACT_APP_GOOGLE_API_KEY; // Stores Google API Key
  const [accessibilityOptions, setAccessibilityOptions] = useState([]);
  const [desiredPositionOptions, setDesiredPositionOptions] = useState([]);
  const organiserID = 1;
  useEffect(() => {
    const fetchOptions = async () => {
      try {
        const [positions, accessibility] = await Promise.all([
          axios.get(`${BASE_URL}/api/roles`),
          axios.get(`${BASE_URL}/api/accessibility`),
        ]);
        const rolesData = positions.data;
        const formattedRoles = rolesData.map((role) => ({
          label: role.title,
          value: role.title,
        }));
        const accessibilityData = accessibility.data;
        const formattedAccess = accessibilityData.map((role) => ({
          label: role.name,
          value: role.name,
        }));
        setDesiredPositionOptions(formattedRoles);
        setAccessibilityOptions(formattedAccess);
      } catch (error) {
        console.error("Error fetching options: ", error);
      }
    };
    fetchOptions();
  }, []);
  // Common class names for the input fields
  const input =
    "block w-full rounded-md border-2 px-3.5 py-2 text-gray-900  border-slate-400 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:border-black sm:text-sm sm:leading-6";

  // Handle main image selection
  const onMainImageChange = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onloadend = () => {
      let base64String = reader.result;
      if (base64String.includes(",")) {
        base64String = base64String.split(",")[1];
      }
      setMainImage(base64String);
    };
    reader.readAsDataURL(file);
    setMainImageUrl(URL.createObjectURL(file));
  };

  // Handle form input changes
  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  // Handle reward input and adding to the list
  const handleRewardInputChange = (e) => {
    setRewardInput(e.target.value);
  };

  const handleRewardKeyDown = (e) => {
    if (e.key === "Enter" && rewardInput.trim() !== "") {
      e.preventDefault();
      setRewards([...rewards, rewardInput.trim()]);
      setRewardInput("");
    }
  };

  const removeReward = (index) => {
    setRewards(rewards.filter((_, i) => i !== index));
  };

  // Handle the selection from Google Places Autocomplete
  const handlePlaceSelect = (place) => {
    const address = place.formatted_address;
    let city = "";
    let postalCode = "";
    let addressComponent = "";

    // Loop through the address components to find the desired types
    place.address_components.forEach((component) => {
      const types = component.types;

      if (types.includes("locality")) {
        city = component.long_name;
      }

      if (types.includes("postal_code")) {
        postalCode = component.long_name;
      }

      if (types.includes("route")) {
        addressComponent = component.long_name;
      }

      // Fallback for city if not found under 'locality'
      if (
        !city &&
        (types.includes("administrative_area_level_1") ||
          types.includes("administrative_area_level_2"))
      ) {
        city = component.long_name;
      }
    });

    setFormData((prevData) => ({
      ...prevData,
      address: addressComponent ? addressComponent : address,
      city: city,
      postalCode: postalCode,
    }));
  };

  // Validate form fields
  const validate = () => {
    const newErrors = {};
    if (!formData.title) newErrors.title = "Event Title is required";
    if (!eventDate) newErrors.eventDate = "Event Date is required";
    if (!startTime) newErrors.startTime = "Start Time is required";
    if (!endTime) newErrors.endTime = "End Time is required";
    if (!formData.description)
      newErrors.description = "Description is required";
    if (!mainImage) newErrors.mainImage = "Main Image is required";
    // if (!formData.address) newErrors.address = "Address is required";
    if (!formData.city) newErrors.city = "City is required";
    if (!formData.postalCode) newErrors.postalCode = "Postal Code is required";
    if (!formData.landmark) newErrors.landmark = "Landmark is required";
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent default form submission
    if (!validate()) return; // Validate form before submitting

    const eventData = new FormData(); // Create a new FormData object
    const accessibilityProvided = selectedAccessibilty.map((role) => {
      return role.value;
    });
    console.log(accessibilityProvided);

    eventData.append("accessibilityAssistance", accessibilityProvided);

    eventData.append("name", formData.title); // Append title to formData
    eventData.append("description", formData.description); // Append description to formData
    eventData.append("date", eventDate.toISOString().split("T")[0]); // Convert to yyyy-MM-dd
    const combinedStartTime = `${
      eventDate.toISOString().split("T")[0]
    }T${startTime}`;
    const combinedEndTime = `${
      eventDate.toISOString().split("T")[0]
    }T${endTime}`;
    eventData.append("startTime", combinedStartTime); // Append start time to formData
    eventData.append("endTime", combinedEndTime); // Append end time to formData
    eventData.append("address", formData.address); // Append address to formData
    eventData.append("city", formData.city); // Append city to formData
    eventData.append("postalCode", formData.postalCode); // Append postal code to formData
    eventData.append("landmark", formData.landmark); // Append landmark to formData
    eventData.append("dbsRequired", formData.dbsRequired); // Append DBS Required to formData
    eventData.append("base64Image", mainImage); // Append main image to formData

    // Convert selected roles to a comma-separated string and append to formData
    const rolesNeeded = selectedRoles.map((role) => role.value).join(", ");
    eventData.append("rolesNeeded", rolesNeeded);

    // Convert rewards to a comma-separated string and append to formData
    eventData.append("rewardsOffering", rewards);

    eventData.append("organiserID", organiserID);

    try {
      console.log("Form data being sent:", eventData);
      await axios
        .post(`${BASE_URL}/api/events`, eventData) // Submit form data to the server
        .then(function (response) {
          console.log("Response from server:", response);
          setSuccessMessage("Event created successfully!"); // Set success message

          // Reset form fields after successful submission
          setFormData({
            title: "",
            description: "",
            address: "",
            city: "",
            postalCode: "",
            landmark: "",
            dbsRequired: false,
          });
          setEventDate(null);
          setStartTime(null);
          setEndTime(null);
          setSelectedRoles([]);
          setMainImage(null);
          setMainImageUrl(null);
          setRewards([]);
          setSelectedAccessibility([]);
          document.getElementById("mainImageUpload").value = "";
        })
        .catch(function (error) {
          console.error("Error in response:", error);
        });
    } catch (error) {
      console.error("There was an error submitting the form!", error);
    }
  };

  return (
    <div className="isolate bg-white px-6 py-24 sm:py-32 lg:px-8 poppins-regular">
      <div
        aria-hidden="true"
        className="absolute inset-x-0 top-[-10rem] -z-10 transform-gpu overflow-hidden blur-3xl sm:top-[-20rem]"
      ></div>
      <div className="mx-auto w-full max-w-3xl shadow-lg rounded-lg p-3">
        {/* Container for the form */}
        <div className="mx-auto max-w-2xl text-center">
          {/* Header section */}
          <h2 className="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">
            Create Event
          </h2>
          {successMessage && (
            <div
              className="mt-4 bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative"
              role="alert"
            >
              <span className="block sm:inline">{successMessage}</span>
              <span
                className="absolute top-0 bottom-0 right-0 px-4 py-3"
                onClick={() => setSuccessMessage("")}
              >
                <svg
                  className="fill-current h-6 w-6 text-green-500"
                  role="button"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                >
                  <title>Close</title>
                  <path d="M14.348 5.652a1 1 0 0 0-1.414 0L10 8.586 7.066 5.652a1 1 0 0 0-1.414 1.414L8.586 10l-2.934 2.934a1 1 0 1 0 1.414 1.414L10 11.414l2.934-2.934a1 1 0 0 0 0-1.414z" />
                </svg>
              </span>
            </div>
          )}
        </div>

        {/* Form starts here */}
        <form
          className="mx-auto mt-16 max-w-xl sm:mt-20"
          onSubmit={handleSubmit}
        >
          <div className="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
            {/* Event Title input field */}
            <div className="sm:col-span-2">
              <label
                htmlFor="title"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Event Title
              </label>
              <div className="mt-2.5">
                <input
                  id="title"
                  name="title"
                  type="text"
                  className={input}
                  value={formData.title}
                  onChange={handleChange}
                />
                {errors.title && (
                  <p className="text-red-500 text-xs mt-1">{errors.title}</p>
                )}
              </div>
            </div>

            {/* Event Date picker */}
            <div className="sm:col-span-2">
              <label
                htmlFor="eventDate"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Event Date
              </label>
              <div className="mt-2.5">
                <DatePicker
                  selected={eventDate}
                  onChange={(date) => setEventDate(date)}
                  className={input}
                  placeholderText="Select event date"
                  dateFormat="MMMM d, yyyy"
                  wrapperClassName="w-full"
                />
                {errors.eventDate && (
                  <p className="text-red-500 text-xs mt-1">
                    {errors.eventDate}
                  </p>
                )}
              </div>
            </div>

            {/* Start Time picker */}
            <div className="sm:col-span-2">
              <label
                htmlFor="startTime"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Start Time
              </label>
              <div className="mt-2.5">
                <input
                  id="startTime"
                  name="startTime"
                  type="time"
                  className={input}
                  value={startTime || ""}
                  onChange={(e) => setStartTime(e.target.value)}
                />
                {errors.startTime && (
                  <p className="text-red-500 text-xs mt-1">
                    {errors.startTime}
                  </p>
                )}
              </div>
            </div>

            {/* End Time picker */}
            <div className="sm:col-span-2">
              <label
                htmlFor="endTime"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                End Time
              </label>
              <div className="mt-2.5">
                <input
                  id="endTime"
                  name="endTime"
                  type="time"
                  className={input}
                  value={endTime || ""}
                  onChange={(e) => setEndTime(e.target.value)}
                />
                {errors.endTime && (
                  <p className="text-red-500 text-xs mt-1">{errors.endTime}</p>
                )}
              </div>
            </div>

            {/* Event Description textarea */}
            <div className="sm:col-span-2">
              <label
                htmlFor="description"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Event Description
              </label>
              <div className="mt-2.5">
                <textarea
                  id="description"
                  name="description"
                  rows={4}
                  className={input}
                  value={formData.description}
                  onChange={handleChange}
                />
                {errors.description && (
                  <p className="text-red-500 text-xs mt-1">
                    {errors.description}
                  </p>
                )}
              </div>
            </div>

            {/* Event Main Image file upload */}
            <div className="sm:col-span-2">
              <label
                htmlFor="mainImageUpload"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Event Main Image
              </label>
              <div className="mt-2.5 flex flex-col space-y-4">
                <input
                  id="mainImageUpload"
                  type="file"
                  accept="image/*"
                  onChange={onMainImageChange}
                  className="block text-sm text-gray-900"
                />
                {mainImageUrl && (
                  <div className="flex flex-wrap gap-4">
                    <img
                      src={mainImageUrl}
                      alt="Main Image Preview"
                      className="h-32 w-32 object-cover"
                    />
                  </div>
                )}
                {errors.mainImage && (
                  <p className="text-red-500 text-xs mt-1">
                    {errors.mainImage}
                  </p>
                )}
              </div>
            </div>

            {/* Address input field */}
            <div className="sm:col-span-2">
              <label
                htmlFor="address"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Address
              </label>
              <div className="mt-2.5 relative">
                <Autocomplete
                  apiKey={GOOGLE_API_KEY}
                  onPlaceSelected={(place) => handlePlaceSelect(place)}
                  className={input}
                  options={{
                    types: ["geocode", "establishment"],
                    componentRestrictions: { country: "uk" },
                  }}
                  value={formData.address}
                  placeholder="Search for location"
                />

                {errors.address && (
                  <p className="text-red-500 text-xs mt-1">{errors.address}</p>
                )}
              </div>
            </div>

            {/* City input field */}
            <div className="sm:col-span-2">
              <label
                htmlFor="city"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                City
              </label>
              <div className="mt-2.5">
                <input
                  id="city"
                  name="city"
                  type="text"
                  className={input}
                  value={formData.city}
                  onChange={handleChange}
                />
                {errors.city && (
                  <p className="text-red-500 text-xs mt-1">{errors.city}</p>
                )}
              </div>
            </div>

            {/* Postal Code input field */}
            <div className="sm:col-span-2">
              <label
                htmlFor="postalCode"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Postal Code
              </label>
              <div className="mt-2.5">
                <input
                  id="postalCode"
                  name="postalCode"
                  type="text"
                  className={input}
                  value={formData.postalCode}
                  onChange={handleChange}
                />
                {errors.postalCode && (
                  <p className="text-red-500 text-xs mt-1">
                    {errors.postalCode}
                  </p>
                )}
              </div>
            </div>

            {/* Landmark input field */}
            <div className="sm:col-span-2">
              <label
                htmlFor="landmark"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Landmark
              </label>
              <div className="mt-2.5">
                <input
                  id="landmark"
                  name="landmark"
                  type="text"
                  className={input}
                  value={formData.landmark}
                  onChange={handleChange}
                />
                {errors.landmark && (
                  <p className="text-red-500 text-xs mt-1">{errors.landmark}</p>
                )}
              </div>
            </div>

            {/* DBS Required checkbox */}
            <div className="sm:col-span-2">
              <div className="mt-1 flex items-center">
                <input
                  id="dbsRequired"
                  name="dbsRequired"
                  type="checkbox"
                  checked={formData.dbsRequired}
                  onChange={handleChange}
                  className="h-4 w-4 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500"
                />
                <label
                  htmlFor="dbsRequired"
                  className="ml-2 text-sm font-semibold leading-6 text-gray-900"
                >
                  DBS Required?
                </label>
              </div>
            </div>

            {/* Accessibility Assistance Provided textarea */}
            <div className="sm:col-span-2">
              <label
                htmlFor="accessibilityAssistance"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Accessibility Assistance Provided
              </label>
              <div className="relative mt-2.5">
                <div className="flex items-center">
                  <Select
                    id="accessibilityAssistance"
                    name="accessibilityAssistance"
                    isMulti
                    options={accessibilityOptions}
                    className="block w-full"
                    classNamePrefix="select"
                    value={selectedAccessibilty}
                    onChange={setSelectedAccessibility}
                  />
                </div>
              </div>
            </div>

            {/* Role Needed multi-select dropdown */}
            <div className="sm:col-span-2">
              <label
                htmlFor="role"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Roles Needed
              </label>
              <div className="relative mt-2.5">
                <div className="flex items-center">
                  <Select
                    id="role"
                    name="role"
                    isMulti
                    options={desiredPositionOptions}
                    className="block w-full  "
                    classNamePrefix="select"
                    value={selectedRoles}
                    onChange={setSelectedRoles}
                  />
                </div>
              </div>
            </div>

            {/* Rewards input field */}
            <div className="sm:col-span-2">
              <label
                htmlFor="rewardInput"
                className="block text-sm font-semibold leading-6 text-gray-900"
              >
                Rewards Offering
              </label>
              <div className="mt-2.5 flex flex-col space-y-2">
                <input
                  id="rewardInput"
                  type="text"
                  className={input}
                  value={rewardInput}
                  onChange={handleRewardInputChange}
                  onKeyDown={handleRewardKeyDown}
                  placeholder="Type a reward and press enter"
                />
                <ul className="mt-2">
                  {rewards.map((reward, index) => (
                    <li
                      key={index}
                      className="flex items-center justify-between py-2 px-3 bg-gray-100 rounded-md mb-2"
                    >
                      {reward}
                      <button
                        type="button"
                        className="text-red-500"
                        onClick={() => removeReward(index)}
                      >
                        Remove
                      </button>
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          </div>

          {/* Submit button */}
          <div className="mt-10">
            <button
              type="submit"
              className="block w-full rounded-md px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-sm hover:bg-opacity-90 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2"
              style={{ backgroundColor: "#CC1B00", outlineColor: "#CC1B00" }}
            >
              Create Event
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
