import React, { useState, useEffect, useRef } from "react";
import { Box, Card, CardContent, CardActions, Button } from "@mui/material";
import CardTextMediaContent from "./MediaContent";
import CardTextCustomTypography from "./CustomTypography";
import NavigationButton from "./NavigationButton";
import { useTranslation } from "react-i18next";
import EventModel from "../EventCalendar/EventModel";
import axios from "axios";
import { BASE_URL } from "../../apiConfig";
const UpcomingEventsCardText = () => {
  //Extracting translation function
  const { t } = useTranslation();
  //State to manage navigation button position
  const [bottomStyle, setBottomStyle] = useState("");
  //State to handle card visibility
  const [currentIndex, setCurrentIndex] = useState(0);
  //Event Model Trigger states
  // To hold the selected event
  const [selectedEvent, setSelectedEvent] = useState(null);
  // To control the modal display
  const [isModalVisible, setIsModalVisible] = useState(false);

  //DOM element Ref for dynamic styles based on div mesurements
  const cardDivRef = useRef(null);

  const [cards, setCards] = useState([]);

  // Effect to adjust the position of the navigation button based on window size
  useEffect(() => {
    const fetchUpcomingEvents = async () => {
      try {
        const response = await axios.get(
          `${BASE_URL}/api/volunteer/upcoming-events`
        );
        setCards(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchUpcomingEvents();
  }, []);

  useEffect(() => {
    if (cardDivRef.current) {
      const width = window.innerWidth;
      const height = cardDivRef.current.offsetHeight;
      const bottomValue = height / 2;
      if (width < 767) {
        setBottomStyle(`0px`);
      } else {
        setBottomStyle(`${bottomValue}px`);
      }
    }
  }, [cardDivRef.current]);
  useEffect(() => {
    if (currentIndex > cards.length - 3) {
      setCurrentIndex(0);
    }
  }, [currentIndex, cards.length]);

  //Funtion to go to next card
  const handleNext = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % cards.length);
  };

  //Function to go to prev card
  const handlePrev = () => {
    setCurrentIndex(
      (prevIndex) => (prevIndex - 1 + cards.length) % cards.length
    );
  };

  const toggleModal = () => {
    setIsModalVisible(!isModalVisible);
  };

  const showEventDetails = (event) => {
    setSelectedEvent(event.id);
    toggleModal();
  };
  return (
    <>
      <div className="flex justify-center w-full">
        <div className="w-[90%] text-start pt-4 pb-2">
          <span className="text-xl lg:text-2xl font-semibold">
            {t("upcomingEvent")}
          </span>
        </div>
      </div>
      <div className="flex items-center justify-center pt-2">
        <div className="md:w-11/12 justify-items-center">
          <div className="relative">
            <div className="flex md:space-x-4 space-x-0 md:space-y-0  space-y-2 md:flex-row flex-col">
              {cards
                .slice(currentIndex, currentIndex + 3)
                .map((card, index) => (
                  <Box
                    className="md:w-1/3 w-full rounded-lg shadow-md border-none"
                    key={index}
                    ref={cardDivRef}
                  >
                    <Card
                      className="flex flex-col justify-between h-full"
                      style={{ height: "34rem" }}
                    >
                      <CardTextMediaContent
                        type="img"
                        style={{ height: "20.185rem", objectFit: "cover" }}
                        src={`data:image/png;base64,${card.eventImage}`}
                        altText={card.name}
                      />

                      <CardContent>
                        <div className="flex flex-row justify-between gap-x-4">
                          <CardTextCustomTypography
                            variant="h6"
                            rootComponent="div"
                            gutterBottom
                          >
                            {card.name}
                          </CardTextCustomTypography>
                          <div>
                            <CardTextCustomTypography variant="subtitle1">
                              {card.date}
                            </CardTextCustomTypography>
                            <CardTextCustomTypography variant="subtitle2">
                              {card.dayOfWeek}
                            </CardTextCustomTypography>
                          </div>
                        </div>
                        <CardTextCustomTypography variant="subtitle1">
                          {card.city}
                        </CardTextCustomTypography>
                        <CardTextCustomTypography variant="subtitle1">
                          Postcode: {card.postalCode}
                        </CardTextCustomTypography>
                        <CardTextCustomTypography variant="body2">
                          {card.description}
                          {/* 80 char */}
                        </CardTextCustomTypography>
                      </CardContent>
                      <CardActions
                        style={{
                          justifyContent: "flex-start",
                          marginTop: "auto",
                        }}
                      >
                        <Button
                          variant="outlined"
                          sx={{
                            border: "2px solid #cc1b00",
                            color: "#000001",
                            "&:hover": {
                              backgroundColor: "#f5f5f5",
                              border: "2px solid #cc1b22",
                              color: "#cc1b00",
                            },
                          }}
                          onClick={() => showEventDetails(card)}
                        >
                          {t("viewMore")}
                        </Button>
                      </CardActions>
                    </Card>
                  </Box>
                ))}
            </div>
            <NavigationButton
              commonClassName="flex items-center md:justify-between justify-center md:space-x-0 space-x-4 relative navigationDiv"
              commonStyles={{ bottom: bottomStyle }}
              navigationClassName="w-6 h-6 rounded-full bg-gray-300 md:mr-[-1rem] mt-1 transition ease-in-out delay-50 hover:-translate-y-1 hover:scale-110 duration-100"
              buttonColor="#cc1b00"
              handleNext={handleNext}
              handlePrev={handlePrev}
            />
            {selectedEvent && (
              <EventModel
                event={selectedEvent}
                isVisible={isModalVisible}
                toggleModal={toggleModal}
              />
            )}
          </div>
        </div>
      </div>
    </>
  );
};

export default UpcomingEventsCardText;
