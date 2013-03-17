import java.util.Set;
import java.util.Calendar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MeetingTest {

    @Test
    public void testId() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewContact("Mikey Jikey", "Vice President");
        Calendar date = Calendar.getInstance();
        MeetingImpl meeting = new FutureMeetingImpl(date, contactManager.getContacts(1, 2));
        assertNotNull(meeting.getId());
        assertEquals(1, meeting.getId());
        //insert a test to ensure that the ID passed in is unique
        //read the list of current meeting and compare it against the id passed in
        //assertNotEquals(????, meeting.getId()); ///TEST FOR UNIQUE
    }

    @Test
    public void testDateNotMissing() {
        Calendar date = Calendar.getInstance();
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewContact("Mikey Jikey", "Vice President");
        MeetingImpl meeting = new FutureMeetingImpl(date, contactManager.getContacts(1, 2));
        Calendar meetingDate = meeting.getDate();
        assertNotNull(meetingDate);
    }

    @Test
    public void testDateEquals() {
        Calendar date = Calendar.getInstance();
        date.set(2013, 12, 1);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewContact("Mikey Jikey", "Vice President");
        MeetingImpl meeting = new FutureMeetingImpl(date, contactManager.getContacts(1, 2));
        Calendar meetingDate = meeting.getDate();
        assertEquals(date.get(Calendar.DAY_OF_MONTH), meetingDate.get(Calendar.DAY_OF_MONTH));
        assertEquals(date.get(Calendar.MONTH), meetingDate.get(Calendar.MONTH));
        assertEquals(date.get(Calendar.YEAR), meetingDate.get(Calendar.YEAR));
    }

    @Test
    public void testContacts() {
        Calendar date = Calendar.getInstance();
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewContact("Mikey Jikey", "Vice President");
        MeetingImpl meeting = new FutureMeetingImpl(date, contactManager.getContacts(1, 2));
        Set<Contact> contacts = meeting.getContacts();
        assertNotNull(contacts);
    }
}