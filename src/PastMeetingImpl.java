import java.util.Set;
import java.util.Calendar;

/**
 *	A meeting that was held in the past.
 *
 *	It includes your notes about what happened and what was agreed.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    private String notes;

    public PastMeetingImpl(FutureMeeting futureMeeting, String notes) {
        super(futureMeeting.getId(), futureMeeting.getDate(), futureMeeting.getContacts());
        this.notes = notes;
    }

    public PastMeetingImpl(Calendar date, Set<Contact> contacts) {
        super(date, contacts);
    }

    /**
     *	Returns the notes from the meeting.
     *
     *	If there are no notes, the empty string is returned.
     *
     *	@return the notes from the meeting.
     */
    @Override
    public String getNotes() {
    	return notes;
    }
}
