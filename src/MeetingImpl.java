import java.util.Calendar;
import java.util.Set;
import java.util.HashSet;

/**
 *	A class to represent meetings
 *
 *	Meetings have unique IDs, scheduled date and a list of participating contacts
 */
public class MeetingImpl implements Meeting {

    private int id;
    private Set<Contact> contacts;
    private Calendar calendar;

    public MeetingImpl(int id, int year, int month, int day) {
        this.id = id;
        contacts = new HashSet<Contact>();
        this.calendar = Calendar.getInstance();
        calendar.set(year, month, day);
    }

    /**
     *	Return the details of people that attended the meeting.
     *
     *	The list contains a minimum of one contact (if there were
     *	just two people: the user and the contact) and may contain an
     *	arbitrary number of them.
     *
     *	@return the details of people that attended the meeting.
     */
    @Override
    public Set<Contact> getContacts() {
		return contacts;
	}

    /**
     *	Return the date of the meeting.
     *
     *	@return the date of the meeting.
     */
    @Override
    public Calendar getDate() {
		return calendar;
    }

    /**
     *	Returns the id of the meeting.
     *
     *	@return the id of the meeting.
     */
    @Override
    public int getId() {
        return id;
    }
}