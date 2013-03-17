import java.util.Calendar;
import java.util.Set;

/**
 *	A class to represent meetings
 *
 *	Meetings have unique IDs, scheduled date and a list of participating contacts
 */
public abstract class MeetingImpl implements Meeting {

    private int id;
    private Set<Contact> contacts;
    private Calendar date;
    private static int uniqueId;

    static {
        uniqueId = 0;
    }

    protected MeetingImpl(int id, Calendar date, Set<Contact> contacts){
        this.id = id;
        this.date = date;
        this.contacts = contacts;
    }

    public MeetingImpl(Calendar date, Set<Contact> contacts){
        this(++uniqueId, date, contacts);
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
		return date;
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