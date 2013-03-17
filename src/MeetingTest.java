import java.util.Set;
import java.util.Calendar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore; //********************************************* REMOVE LATER
import org.junit.Test;

public class MeetingTest {

    @Test
    public void testId() {
        MeetingImpl meeting = new MeetingImpl(1, 2013, 12, 1);
        assertNotNull(meeting.getId());
        assertEquals(1, meeting.getId());
        //insert a test to ensure that the ID passed in is unique
        //read the list of current meeting and compare it against the id passed in
        //assertNotEquals(????, meeting.getId()); ///TEST FOR UNIQUE
    }

    @Test
    public void testDate() {
        MeetingImpl meeting = new MeetingImpl(1, 2013, 12, 1);
        Calendar meetingCalendar = meeting.getDate();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2013, 12, 1);
        assertEquals(calendar.get(Calendar.DAY_OF_MONTH), meetingCalendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(calendar.get(Calendar.MONTH), meetingCalendar.get(Calendar.MONTH));
        assertEquals(calendar.get(Calendar.YEAR), meetingCalendar.get(Calendar.YEAR));
    }

    @Test
    public void testContacts() {
        MeetingImpl meeting = new MeetingImpl(1, 2013, 12, 1);
        Set<Contact> contacts = meeting.getContacts();

    }
}