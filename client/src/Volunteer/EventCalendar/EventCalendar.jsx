import React, { useState, useEffect } from "react";
import VolunteerHeader from "../Header/VolunteerHeader";
import VolunteerFooter from "../Footer/VolunteerFooter";
import { MdNavigateNext, MdNavigateBefore } from "react-icons/md";
import EventModel from "./EventModel";
import axios from "axios";
import { BASE_URL } from "../../apiConfig";


const daysOfWeek = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
const monthNames = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

const EventCalendar = () => {
  // State to manage the current data, month,year, daysInMonth
  // and number of events per daY
  const volunteerID = 1;
  const [currentDate, setCurrentDate] = useState(new Date());
  const [month, setMonth] = useState(currentDate.getMonth());
  const [year, setYear] = useState(currentDate.getFullYear());
  const [daysInMonth, setDaysInMonth] = useState([]);
  const [maxEventsPerDay, setMaxEventsPerDay] = useState(0);
  const [selectedEvent, setSelectedEvent] = useState(null); // To hold the selected event
  const [isModalVisible, setIsModalVisible] = useState(false); // To control the modal display
  const [events, setEvents] = useState([]);

  useEffect(() => {
    const fetchEventsCalendar = async () => {
      try {
        const response = await axios.get(
          `${BASE_URL}/api/volunteer/event-calendar`
        );
        setEvents(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchEventsCalendar();
    calculateDays();
    setCurrentDate(new Date());
  }, [month, year]);

  //Calculate the number of days in a month and
  //max number of events in the day for height calculation
  const calculateDays = () => {
    const weeks = [];
    const firstDayOfMonth = new Date(year, month, 1);
    const firstDayOfWeek = firstDayOfMonth.getDay();
    const numDays = new Date(year, month + 1, 0).getDate();
    let maxEvents = 1;
    let day = 1;
    for (let i = 0; i < (numDays + firstDayOfWeek) / 7; i++) {
      weeks[i] = [];
      for (let j = 0; j < 7; j++) {
        if ((i === 0 && j < firstDayOfWeek) || day > numDays) {
          weeks[i][j] = undefined;
        } else {
          const eventsCount = findEventsForDate(day).length;
          maxEvents = Math.max(maxEvents, eventsCount);
          weeks[i][j] = day++;
        }
      }
    }
    setDaysInMonth(weeks);
    setMaxEventsPerDay(maxEvents);
  };
  // Used to find the events for a particular day
  const findEventsForDate = (day) => {
    const dateString = `${year}-${String(month + 1).padStart(2, "0")}-${String(
      day
    ).padStart(2, "0")}`;
    return events.filter((event) => event.date === dateString);
  };

  //Handle Month navigation
  //changes months and years
  const handleNextMonth = () => {
    if (month === 11) {
      setMonth(0);
      setYear((prevYear) => prevYear + 1);
    } else {
      setMonth((prevMonth) => prevMonth + 1);
    }
  };
  //Handle Month navigation
  //changes months and years
  const handlePrevMonth = () => {
    if (month === 0) {
      setMonth(11); // December
      setYear((prevYear) => prevYear - 1); // Previous year
    } else {
      setMonth((prevMonth) => prevMonth - 1);
    }
  };
  //check if the date is same as today
  const isToday = (day) => {
    const today = new Date();
    console.log(
      day === today.getDate() &&
        month === today.getMonth() &&
        year === today.getFullYear()
    );
    return (
      day === today.getDate() &&
      month === today.getMonth() &&
      year === today.getFullYear()
    );
  };

  //height calculation based on number of events
  const baseHeight = 40;
  const dayCellHeight = `${baseHeight + maxEventsPerDay * 30}px`;

  //Event Model toggle and trigger function
  const toggleModal = () => {
    setIsModalVisible(!isModalVisible);
  };
  const showEventDetails = (event) => {
    setSelectedEvent(event);
    toggleModal();
  };

  console.log(volunteerID);
  return (
    <>
      <VolunteerHeader logged={true} volunteerID={volunteerID}/>
      <div className="m-auto sm:p-3 md:p-5 rounded poppins-regular md:w-[95%]">
        <div className="flex justify-between items-center mb-3 md:mb-5">
          <button
            onClick={handlePrevMonth}
            className="whitespace-nowrap bg-gray-50 text-[#000001] hover:text-red-700 px-2 py-1 rounded border-2 border-rose-700 mx-1
            hover:bg-white flex text-base font-semibold"
          >
            <MdNavigateBefore size={24} />
            Prev
          </button>
          <div className="flex justify-between items-center px-1 md:px-0 items-center">
            <span className="text-base md:text-xl lg:text-2xl font-semibold">
              Events Calendar - {monthNames[month]} {year}
            </span>
          </div>
          <button
            onClick={handleNextMonth}
            className="whitespace-nowrap bg-gray-50 text-[#000001] hover:text-red-700 px-2 py-1 rounded border-2 border-rose-700 mx-1
            hover:bg-white flex text-base font-semibold"
          >
            Next
            <MdNavigateNext size={24} />
          </button>
        </div>
        <div className="grid grid-cols-7 md:gap-2 text-center">
          {daysOfWeek.map((day) => (
            <div key={day} className="font-bold">
              {day}
            </div>
          ))}
          {daysInMonth.map((week) =>
            week.map((day, idx) => {
              const dayEvents = findEventsForDate(day);
              const isCurrentDay = isToday(day);

              return (
                <div
                  key={idx}
                  style={{ height: dayCellHeight }}
                  className={` ${
                    day
                      ? "bg-neutral-100 hover:bg-neutral-50 hover:border-red-200"
                      : "bg-neutral-300"
                  } ${
                    isCurrentDay ? "border-5  bg-red-100" : ""
                  } border border-gray-300 p-1`}
                >
                  {day}
                  {dayEvents.map((event) => (
                    <div
                      key={event.name}
                      className="whitespace-normal overflow-hidden text-ellipsis bg-gray-50 text-[#000001] 
                      md:rounded border-2 border-rose-700 duration-100
                      hover:text-red-700 hover:bg-white hover:-translate-y-1 hover:scale-110  md:mt-1 text-xs md:text-sm md:p-1 font-medium  
                      cursor-pointer break-words transition ease-in-out delay-50 "
                      data-model-target="static-modal"
                      data-model-toggle="static-modal"
                      type="button"
                      onClick={() => showEventDetails(event.id)}
                    >
                      {event.name}
                    </div>
                  ))}
                </div>
              );
            })
          )}
        </div>
      </div>
      {selectedEvent && (
        <EventModel
          event={selectedEvent}
          isVisible={isModalVisible}
          toggleModal={toggleModal}
          volunteerID={volunteerID}
        />
      )}
      <VolunteerFooter />
    </>
  );
};

export default EventCalendar;
