import java.io.*;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

/**
 *	A class to manage your contacts and meetings.
 */
public class ContactManagerImpl implements ContactManager {
    private static final String FILENAME = "/users/fitzjackson/Dropbox/Development/CM/contacts.txt";

    private int contactId;
	private Set<Contact> contacts;
	private List<Meeting> meetings;

	public ContactManagerImpl() {
		contactId = 0; // id whatever it was last time sort out persistence mechanism

        // Check if the file and folder exists and can be read
        if (!new File(FILENAME).exists()) {
            contacts = new HashSet<>();
            meetings = new ArrayList<>();
        } else
            try (ObjectInputStream oInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILENAME)));) {
                contacts = (Set<Contact>) oInputStream.readObject();
                meetings = (List<Meeting>) oInputStream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("On read error " + ex);
            }
	}

    /**
     *	Create a new contact with the specified name and notes.
     *
     *	@param name the name of the contact.
     *	@param notes notes to be added about the contact.
     *	@throws NullPointerException if the name or the notes are null
     */
    @Override
    public void addNewContact(String name, String notes) throws NullPointerException {
        if (name == null) {
            throw new NullPointerException("You tried to add a new contact however the name is missing");
        } else if (notes == null) {
            throw new NullPointerException("You tried to add a new contact however notes are missing");
        } else {
            ContactImpl contact = new ContactImpl(++contactId, name, notes);
            contacts.add(contact);
        }
    }

     /**
     *	Returns a list containing the contacts that correspond to the IDs.
     *
     *	@param ids an arbitrary number of contact IDs
     *	@return a list containing the contacts that correspond to the IDs.
     *	@throws IllegalArgumentException if any of the IDs does not correspond to a real contact
     */
    @Override
    public Set<Contact> getContacts(int... ids) throws IllegalArgumentException {
        Set<Contact> result = new HashSet<Contact>();
        for (Integer id : ids) {
            //Check if the id matches any of the contacts
            for (Contact contact : contacts) {
                if (contact.getId() == id) {
                    result.add(contact);
                    break;
                }
            }
        }
        if (result.size() == ids.length) {
            return result;
        } else {
            throw new IllegalArgumentException("ID(s) do not correspond to a real contact");
        }
    }

    /**
     *	Returns a list with the contacts whose name contains that string.
     *
     *	@param name the string to search for
     *	@return a list with the contacts whose name contains that string.
     *	@throws NullPointerException if the parameter is null
     */
    @Override
    public Set<Contact> getContacts(String name) throws NullPointerException {
        Set<Contact> result = new HashSet<Contact>();
        if (name == null) {
            throw new NullPointerException("You tried to get a contact however the name is missing");
        } else {
            for (Contact contact : contacts) {
                if (contact.getName() == name) {
                    result.add(contact);
                }
            }
        }
        return result;
    }

    /**
     *	Add a new meeting to be held in the future.
     *
     *	@param contacts a list of contacts that will participate in the meeting
     *	@param date the date on which the meeting will take place
     *	@return the ID for the meeting
     *	@throws IllegalArgumentException if the meeting is set for a time in the past,
     *	of if any contact is unknown / non-existent
     */
    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {
        if (isInThePast(date)) {
            throw new IllegalArgumentException("the meeting is set for a time in the past");
        } else if (!(isExistingContacts(contacts))) {
            throw new IllegalArgumentException("the contact is unknown /non-existent");
        } else {
            MeetingImpl meeting = new FutureMeetingImpl(contacts, date);
            meetings.add(meeting);
            return meeting.getId();
        }
    }

    /**
     *	Returns the FUTURE meeting with the requested ID, or null if there is none.
     *
     *	@param id the ID for the meeting
     *	@return the meeting with the requested ID, or null if it there is none.
     *	@throws IllegalArgumentException if there is a meeting with that ID happening in the past
     */
    @Override
    public FutureMeeting getFutureMeeting(int id) {
        for (Meeting meeting : meetings) {
            if (meeting.getId() == id) {
                if (isInThePast(meeting.getDate())) {
                    throw new IllegalArgumentException("A meeting with that ID happening in the past");
                } else {
                    return (FutureMeeting) meeting;
                }
            }
        }
        return null;
    }

    /**
     *	Returns the list of future meetings scheduled with this contact.
     *
     *	If there are none, the returned list will be empty. Otherwise,
     *	the list will be chronologically sorted and will not contain any
     *	duplicates.
     *
     *	@param contact one of the user's contacts
     *	@return the list of future meeting(s) scheduled with this contact (maybe empty).
     *	@throws IllegalArgumentException if the contact does not exist
     */
    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException {
        TreeSet<FutureMeeting> contactMeetings = new TreeSet<FutureMeeting>();
        if(contacts.contains(contact)) {
            for (Meeting meeting : meetings) {
                if ((meeting.getContacts()).contains(contact)) {
                    contactMeetings.add(getFutureMeeting(meeting.getId()));
                }
            }
            return new ArrayList<Meeting>(contactMeetings);
        } else {
            throw new IllegalArgumentException("the contact " + contact + "does not exist");
        }
    }

    /**
     *	Returns the list of meetings that are scheduled for, or that took
     *	place on, the specified date
     *
     *	If there are none, the returned list will be empty. Otherwise,
     *	the list will be chronologically sorted and will not contain any
     *	duplicates.
     *
     *	@param date the date
     *	@return the list of meetings
     */
    @Override
    public List<Meeting> getFutureMeetingList(Calendar date) {
        HashSet<FutureMeeting> contactMeetings = new HashSet<>();
        for (Meeting meeting : meetings) {
            if (isSameDate(meeting.getDate(), date)) {
                contactMeetings.add(getFutureMeeting(meeting.getId()));
            }
        }
        return new ArrayList<Meeting>(contactMeetings);
    }

    /**
     *	Returns the meeting with the requested ID, or null if it there is none.
     *
     *	@param id the ID for the meeting
     *	@return the meeting with the requested ID, or null if it there is none.
     */
    @Override
    public Meeting getMeeting(int id) {
        for (Meeting meeting: meetings) {
            if (meeting.getId() == id) {
                return meeting;
            }
        }
        return null;
    }

    /**
     *	Create a new record for a meeting that took place in the past.
     *
     *	@param contacts a list of participants
     *	@param date the date on which the meeting took place
     *	@param  text messages  to be  added  about  the meeting.
     *	@throws IllegalArgumentException if the list of contacts is
     *	empty, or any of the contacts does not exist
     *	@throws NullPointerException if any of the arguments is null
     */
    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) throws IllegalArgumentException, NullPointerException {
        if (!(isExistingContacts(contacts))) {
            throw new IllegalArgumentException("the contact is unknown / non-existent");
        } else if (contacts == null || date == null || text == null) {
            throw new NullPointerException("some of the arguments are null");
        } else {
            MeetingImpl meeting = new PastMeetingImpl(contacts, date, text);
            meetings.add(meeting);
        }
    }

    /**
     *	Returns the PAST meeting with the requested ID, or null if it there is none.
     *
     *	@param id the ID for the meeting
     *	@return the meeting with the requested ID, or null if it there is none.
     *	@throws IllegalArgumentException if there is a meeting with that ID happening in the future
     */
    @Override
    public PastMeeting getPastMeeting(int id) {
        for (Meeting meeting: meetings) {
            if (meeting.getId() == id) {
                if (!isInThePast(meeting.getDate())) {
                    throw new IllegalArgumentException("A meeting with that ID happening in the future");
                } else {
                    return (PastMeeting) meeting;
                }
            }
        }
        return null;
    }

    /**
     *	Returns the list of past meetings in which this contact has participated.
     *
     *	If there are none, the returned list will be empty. Otherwise,
     *	the list will be chronologically sorted and will not contain any
     *	duplicates.
     *
     *	@param contact one of the user's contacts
     *	@return the list of future meeting(s) scheduled with this contact (maybe empty).
     *	@throws IllegalArgumentException if the contact does not exist
     */
    @Override
    public List<PastMeeting> getPastMeetingList(Contact contact) {
        TreeSet<PastMeeting> contactMeetings = new TreeSet<PastMeeting>();
        if(contacts.contains(contact)) {
            for (Meeting meeting : meetings) {
                if ((meeting.getContacts()).contains(contact)) {
                    contactMeetings.add(getPastMeeting(meeting.getId()));
                }
            }
            return new ArrayList<PastMeeting>(contactMeetings);
        } else {
            throw new IllegalArgumentException("the contact " + contact + "does not exist");
        }
    }

    /**
     *	Add notes to a meeting.
     *
     *	This method is used when a future meeting takes place, and is
     *	then converted to a past meeting (with notes).
     *
     *	It can be also used to add notes to a past meeting at a later date.
     *
     *	@param id the ID of the meeting
     *	@param  text messages  to be  added  about  the meeting.
     *	@throws IllegalArgumentException if the meeting does not exist
     *	@throws IllegalStateException if the  meeting is set  for  a  date  in  the  future
     *	@throws NullPointerException if the notes are null
     */
    @Override
    public void addMeetingNotes(int id, String text) throws IllegalArgumentException, IllegalStateException, NullPointerException {
        Meeting meeting = getMeeting(id);
        if (meeting == null) {
            throw new IllegalArgumentException("Meeting does not exist");
        } else {
            if (text == null) {
                throw new NullPointerException("notes are null");
            } else {
                if (meeting instanceof FutureMeeting) {
                    if (isInThePast(meeting.getDate())) {
                        meeting = new PastMeetingImpl(getFutureMeeting(id), text);
                    } else {
                        throw new IllegalStateException("meeting is set for a date in the future");
                    }
                } else {
                    meeting = new PastMeetingImpl(getPastMeeting(id), getPastMeeting(id).getNotes() + ", " + text);
                }
                if (meetings.contains(meeting)) {
                    meetings.remove(meeting);
                    meetings.add(meeting);
                }
            }
        }
    }

    /**
     *	Save all data to disk.
     *
     *	This method must be executed when the program is
     *	closed and when/if the user requests it.
     */
    @Override
    public void flush() {
        try (ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILENAME)));) {
            os.writeObject(contacts);
            os.writeObject(meetings);
        } catch (IOException ex) {
            System.err.println("error " + ex + " when writing to file: " +FILENAME);
        }
    }

    /**
     *	Checks if all contacts exist
     *
     *	@param contacts a list of contacts
     *	@return the true if all contacts currently exist.
     */
    private boolean isExistingContacts(Set<Contact> contacts) {
        for (Contact contact : contacts) {
            if (!contacts.contains(contact)) {
                return false;
            }
        }
        return contacts.size() > 0;
    }

    /**
     *	Checks if meeting is in the past
     *
     *	@param date the date on which the meeting will take place
     *	@return the true if all date is in the past.
     */
    private boolean isInThePast(Calendar date) {
        Calendar currentDate = Calendar.getInstance();
        return date.before(currentDate);
    }

    /**
     *	Checks if the dates are the same
     *
     *	@param date1 the first date to be compared date2
     *	@param date2 the second to be compared to date1
     *	@return the true if dates are the same.
     */
    private boolean isSameDate(Calendar date1, Calendar date2) {
        return date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
               date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) &&
               date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH);
    }
}