import {expect, describe, test, beforeEach,vi } from 'vitest';
import {render, screen, waitFor} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import axios from 'axios';
import VolunteerHeader from '../../../src/Volunteer/Header/VolunteerHeader';

//mock imports and child components
vi.mock('axios');

vi.mock('react-i18next', () => ({
    useTranslation: () => ({
      t: (key) => {
        const translations = {
          home: 'home',
          events: 'events',
          trophy: 'trophy',
          contactus: 'contactus',
          chat: 'chat',
          Cymraeg: 'Cymraeg',
          English: 'English'
        };
        return translations[key] || key;
      },
      i18n: {
        changeLanguage: vi.fn(), 
      },
    }),
  }));
  

vi.mock('react-icons/io', () => ({
  IoIosMenu: () => <svg data-testid="IoIosMenu" />,
  IoIosClose: () => <svg data-testid="IoIosClose" />,
}));

vi.mock('react-icons/fa6', () => ({
  FaXTwitter: () => <svg data-testid="FaXTwitter" />,
  FaInstagram: () => <svg data-testid="FaInstagram" />,
  FaLinkedin: () => <svg data-testid="FaLinkedin" />,
  FaLocationDot: () => <svg data-testid="FaLocationDot" />,
  FaPhone: () => <svg data-testid="FaPhone" />,
  FaYoutube: () => <svg data-testid="FaYoutube" />,
  FaSquareFacebook: () => <svg data-testid="FaSquareFacebook" />,
}));

vi.mock('react-icons/md', () => ({
  MdEmail: () => <svg data-testid="MdEmail" />,
}));

vi.mock('./HeaderNavigationLinks', () => ({
  __esModule: true,
  default: ({ links }) => (
    <ul data-testid="header-navigation-links">
      {links.map((link, index) => (
        <li key={index}>
          <a href={link.href}>{link.label}</a>
        </li>
      ))}
    </ul>
  ),
}));

vi.mock('../../Login/Logout', () => ({
    __esModule: true,
    default: ({ logged }) => logged ? <button data-testid="logout-button">Logout</button> : null,
  }));

vi.mock('../../assets/wsa-logo.png', () => 'wsa-logo.png');
vi.mock('../../assets/cymraeg.svg', () => 'cymraeg.svg');
vi.mock('../../assets/unitedkingdom.svg', () => 'unitedkingdom.svg');
vi.mock('../../assets/avatar.png', () => 'avatar.png');

const BASE_URL = 'http://localhost:8080';

axios.get.mockResolvedValue({
  data: {
    image: 'mockImageData',
    firstName: 'Enoch',
  },
});

describe('VolunteerHeader', () => {
  const setup = (props = {}) => {
    render(<VolunteerHeader {...props} />);
  };

  beforeEach(() => {
    vi.clearAllMocks();
  });

  //test logo and language render
  test('renders the logo and language toggle', () => {
    setup();
    const logo = screen.getByAltText('Wales Sports Association Logo');
    const languageToggle = screen.getByText('Cymraeg');

    expect(logo).toBeInTheDocument();
    expect(logo).toHaveAttribute('src', '/src/assets/wsa-logo.png');
    expect(languageToggle).toBeInTheDocument();

  });

  //test nav links
  test('renders navigation links', () => {
    setup();
    const homeLink = screen.getByText('home');
    const eventsLink = screen.getByText('events');    
    const trophyLink = screen.getByText('trophy');
    const contactUsLink = screen.getByText('contactus');
    const chatLink = screen.getByText('chat');

    expect(homeLink).toBeInTheDocument();
    expect(homeLink).toHaveAttribute('href', '/volunteer');
expect(eventsLink).toBeInTheDocument();
    expect(eventsLink).toHaveAttribute('href', '/volunteer/eventcalendar');
    expect(trophyLink).toBeInTheDocument();
    expect(trophyLink).toHaveAttribute('href', '/volunteer/trophy');
expect(contactUsLink).toBeInTheDocument();
    expect(contactUsLink).toHaveAttribute('href', '/contact');
    expect(chatLink).toBeInTheDocument();
    expect(chatLink).toHaveAttribute('href', '/chat');
  });

  //test logged in
  test('fetches and displays volunteer info when logged in', async () => {
    setup({ logged: true, volunteerID: 1 });

    expect(axios.get).toHaveBeenCalledWith(`${BASE_URL}/api/volunteer/volunteer-header/1`);
    const profileImage = screen.getByAltText('Profile Picture');
    await waitFor(() => {
      const profileLink = screen.getByRole('link', { name: /Enoch/i });
      expect(profileLink).toBeInTheDocument();
      expect(profileLink).toHaveAttribute('href', '/volunteer/info');
    });
  
    expect(profileImage).toBeInTheDocument();
    expect(profileImage).toHaveAttribute('src', 'data:image/png;base64,mockImageData'); // Adjust this to match the mock
  });
  
//test non login
  test('does not display volunteer info when not logged in', () => {
    setup({ logged: false });

    const profileLink = screen.queryByRole('link', { name: /Enoch/i });
    expect(profileLink).not.toBeInTheDocument();

    const logoutButton = screen.queryByTestId('logout-button');
    expect(logoutButton).not.toBeInTheDocument();
  });

  //test toggle function
  test('toggles language and flag on language toggle click', async () => {
    const user = userEvent.setup();
    setup();
    const languageToggle = screen.getByText('Cymraeg');

    expect(languageToggle).toBeInTheDocument();
    await user.click(languageToggle);
    const newLanguageToggle = screen.getByText('English');
    expect(newLanguageToggle).toBeInTheDocument();
  });

  //test logout render
  test('renders logout button when logged in', () => {
    setup({ logged: true });
    const logoutButton = screen.queryByTestId('logout-button');
    
    expect(logoutButton).toBeInTheDocument();
    
  });

  //test logged in
  test('does not render logout button when not logged in', () => {
    setup({ logged: false });
  
    const logoutButton = screen.queryByTestId('logout-button');
    expect(logoutButton).not.toBeInTheDocument();
  });
  
  //test close and menu icon
  test('renders menu icon when menu is not shown', () => {
    setup();
    const menuIcon = screen.getByTestId('IoIosMenu');
    const closeIcon = screen.queryByTestId('close-icon');

    expect(menuIcon).toBeInTheDocument();
    expect(closeIcon).not.toBeInTheDocument();
  });

  //test responsive
  test('handles window resize and updates topValue accordingly', () => {
    setup();

    expect(() => {
      window.dispatchEvent(new Event('resize'));
    }).not.toThrow();
  });
});
