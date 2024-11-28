import React from 'react'
import VolunteerHeader from '../Volunteer/Header/VolunteerHeader'
import VolunteerFooter from '../Volunteer/Footer/VolunteerFooter'
import Chat from './Chat'

const MessagingPage = () => {
  return (
    <>
   <VolunteerHeader logged={true} volunteerID={1}/>
   <Chat id={1} type={"volunteer"}/>
   <VolunteerFooter />
    </>
  )
}

export default MessagingPage