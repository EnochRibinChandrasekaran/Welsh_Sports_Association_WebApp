import React from 'react';
import {
    createBrowserRouter,
    RouterProvider
} from 'react-router-dom';
import { VolunteerProvider } from '../Context/Volunteer';

import { routes } from './routes';
import { OrganiserProvider } from '../Context/Organiser';

const router = createBrowserRouter(routes);

function App() {
    return (
        <OrganiserProvider>
            <VolunteerProvider>
                <RouterProvider router={router} />
            </ VolunteerProvider>
        </OrganiserProvider>
    )

}

export default App;
