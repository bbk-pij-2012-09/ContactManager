import java.util.Calendar;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.sun.corba.se.impl.logging.ORBUtilSystemException;
import org.junit.Ignore;
import org.junit.Test;


public class ContactManagerTest {

    //1--------------------------------------------------------------------------------
    @Test(expected = NullPointerException.class)
    public void testAddNewContactNameMissing() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        //The following line is supposed to throw a NullPointerException
        contactManager.addNewContact(null, "The Six Million Dollar Man");
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewContactNotesMissing() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        //The following line is supposed to throw a NullPointerException
        contactManager.addNewContact("Steve Austin", null);
    }
    //2--------------------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIDsDoesNotMatch() {
        final int UNKNOWN_CONTACT = 1000;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        //The last line is supposed to throw a IllegalArgumentException
        @SuppressWarnings("contacts is unused")
        Set<Contact> contacts = contactManager.getContacts(UNKNOWN_CONTACT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIDsDoNotMatch() {
        final int FIRST_CONTACT_JUST_ADDED = 1;
        final int SECOND_CONTACT_JUST_ADDED = 2;
        final int UNKNOWN_CONTACT = 1000;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewContact("Mikey Jikey", "Vice President");
        //The last line is supposed to throw a IllegalArgumentException
        @SuppressWarnings("contacts is unused")
        Set<Contact> contacts = contactManager.getContacts(FIRST_CONTACT_JUST_ADDED, SECOND_CONTACT_JUST_ADDED, UNKNOWN_CONTACT);
    }

    @Test(expected = NullPointerException.class)
    public void testGetContactNameMissing() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        //The last line is supposed to throw a NullPointerException
        @SuppressWarnings("actual is unused")
        Set<Contact> actual = contactManager.getContacts((String) null);
    }
    //3--------------------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testAddFutureMeetingMeetingInThePast() {
        final int CONTACT_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(1999, 12, 31);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addFutureMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFutureMeetingContactMissing() {
        final int UNKNOWN_CONTACT = 1000;
        Calendar date = Calendar.getInstance();
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addFutureMeeting(contactManager.getContacts(UNKNOWN_CONTACT), date);
    }
    //4--------------------------------------------------------------------------------
    @Test
    public void testGetFutureMeetingMeetingExists() {
        final int CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(2014, 1, 1);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addFutureMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date);
        assertNotNull(contactManager.getFutureMeeting(MEETING_JUST_ADDED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFutureMeetingMeetingInThePast() {
        final int CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(1999, 12, 31);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addFutureMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date);
        contactManager.getFutureMeeting(MEETING_JUST_ADDED);
    }

    //5--------------------------------------------------------------------------------
    @Test(expected=IllegalArgumentException.class)
    public void testGetFutureMeetingListContactMissing() {
        final int CONTACT_JUST_ADDED = 1;
        final int UNKNOWN_CONTACT = 1000;
        Calendar date = Calendar.getInstance();
        date.set(2014, 1, 1);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(UNKNOWN_CONTACT);
        contactManager.addFutureMeeting(contacts, date);
        for (Contact contact : contacts) {
            List<Meeting> meetings = contactManager.getFutureMeetingList(contact);
        }
    }

    @Test
    public void testGetFutureMeetingListNotEmpty() {
        final int CONTACT_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(2014, 1, 1);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);
        contactManager.addFutureMeeting(contacts, date);
        for (Contact contact : contacts) {
            List<Meeting> meetings = contactManager.getFutureMeetingList(contact);
            assertEquals(1, meetings.size());
        }
    }

    @Test
    public void testGetFutureMeetingListEmpty() {
        final int CONTACT_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);
        for (Contact contact : contacts) {
            List<Meeting> meetings = contactManager.getFutureMeetingList(contact);
            assertEquals(0, meetings.size());
        }
    }

    @Test
    public void testGetFutureMeetingListSorted() {
        final int CONTACT_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);

        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(2014, 1, 3);
        contactManager.addFutureMeeting(contacts, expectedDate1);

        Calendar expectedDate2 = Calendar.getInstance();
        expectedDate2.set(2014, 1, 4);
        contactManager.addFutureMeeting(contacts, expectedDate2);

        Calendar expectedDate3 = Calendar.getInstance();
        expectedDate3.set(2014, 1, 2);
        contactManager.addFutureMeeting(contacts, expectedDate3);

        for (Contact contact : contacts) {
            List<Meeting> meetings = contactManager.getFutureMeetingList(contact);
            Meeting meeting = meetings.get(0);
            Calendar actualDate = meeting.getDate();

            assertEquals(expectedDate3.get(Calendar.DAY_OF_MONTH), actualDate.get(Calendar.DAY_OF_MONTH));
            assertEquals(expectedDate3.get(Calendar.MONTH), actualDate.get(Calendar.MONTH));
            assertEquals(expectedDate3.get(Calendar.YEAR), actualDate.get(Calendar.YEAR));

            meeting = meetings.get(1);
            actualDate = meeting.getDate();
            assertEquals(expectedDate1.get(Calendar.DAY_OF_MONTH), actualDate.get(Calendar.DAY_OF_MONTH));
            assertEquals(expectedDate1.get(Calendar.MONTH), actualDate.get(Calendar.MONTH));
            assertEquals(expectedDate1.get(Calendar.YEAR), actualDate.get(Calendar.YEAR));

            meeting = meetings.get(2);
            actualDate = meeting.getDate();
            assertEquals(expectedDate2.get(Calendar.DAY_OF_MONTH), actualDate.get(Calendar.DAY_OF_MONTH));
            assertEquals(expectedDate2.get(Calendar.MONTH), actualDate.get(Calendar.MONTH));
            assertEquals(expectedDate2.get(Calendar.YEAR), actualDate.get(Calendar.YEAR));
        }
    }

    @Test
    public void testGetFutureMeetingListNoDuplicates() {
        final int CONTACT_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);

        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(2015, 1, 1);
        contactManager.addFutureMeeting(contacts, expectedDate1);

        Calendar expectedDate2 = Calendar.getInstance();
        expectedDate2.set(2015, 1, 1);
        contactManager.addFutureMeeting(contacts, expectedDate2);

        Calendar expectedDate3 = Calendar.getInstance();
        expectedDate3.set(2014, 12, 25);
        contactManager.addFutureMeeting(contacts, expectedDate3);

        Calendar expectedDate4 = Calendar.getInstance();
        expectedDate4.set(2020, 12, 25);
        contactManager.addFutureMeeting(contacts, expectedDate4);

        Calendar expectedDate5 = Calendar.getInstance();
        expectedDate5.set(2014, 12, 25);
        contactManager.addFutureMeeting(contacts, expectedDate5);

        for (Contact contact : contacts) {
            List<Meeting> meetings = contactManager.getFutureMeetingList(contact);
            final int UNIQUE_MEETINGS = 3;
            assertEquals(UNIQUE_MEETINGS, meetings.size());
        }
    }

    /*
    @Ignore
    @Test
    public void testGetFutureMeetingListByDate() {
        final int FIRST_CONTACT_JUST_ADDED = 1;
        final int SECOND_CONTACT_JUST_ADDED = 2;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewContact("Nancy Drew", "Personal Assistant");
        Set<Contact> contacts = contactManager.getContacts(FIRST_CONTACT_JUST_ADDED, SECOND_CONTACT_JUST_ADDED);

        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(2014, 1, 3);
        contactManager.addFutureMeeting(contacts, expectedDate1);

        Calendar expectedDate2 = Calendar.getInstance();
        expectedDate2.set(2014, 1, 4);
        contactManager.addFutureMeeting(contacts, expectedDate2);

        Calendar expectedDate3 = Calendar.getInstance();
        expectedDate3.set(2014, 1, 2);
        contactManager.addFutureMeeting(contacts, expectedDate3);

        for (Contact contact : contacts) {
            List<Meeting> meetings = contactManager.getFutureMeetingList(expectedDate1);

            Meeting meeting = meetings.get(0);
            Calendar actualDate = meeting.getDate();
            assertEquals(expectedDate3.get(Calendar.DAY_OF_MONTH), actualDate.get(Calendar.DAY_OF_MONTH));
            assertEquals(expectedDate3.get(Calendar.MONTH), actualDate.get(Calendar.MONTH));
            assertEquals(expectedDate3.get(Calendar.YEAR), actualDate.get(Calendar.YEAR));

            meeting = meetings.get(1);
            actualDate = meeting.getDate();
            assertEquals(expectedDate1.get(Calendar.DAY_OF_MONTH), actualDate.get(Calendar.DAY_OF_MONTH));
            assertEquals(expectedDate1.get(Calendar.MONTH), actualDate.get(Calendar.MONTH));
            assertEquals(expectedDate1.get(Calendar.YEAR), actualDate.get(Calendar.YEAR));

            meeting = meetings.get(2);
            actualDate = meeting.getDate();
            assertEquals(expectedDate2.get(Calendar.DAY_OF_MONTH), actualDate.get(Calendar.DAY_OF_MONTH));
            assertEquals(expectedDate2.get(Calendar.MONTH), actualDate.get(Calendar.MONTH));
            assertEquals(expectedDate2.get(Calendar.YEAR), actualDate.get(Calendar.YEAR));
        }
    }

    //6--------------------------------------------------------------------------------
    @Ignore
    @Test
    public void testGetMeetingIDsMatch() {
    }
    //7--------------------------------------------------------------------------------
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testAddNewPastContactMissing() {
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void testAddNewPastContactListEmpty() {
    }
    //8--------------------------------------------------------------------------------
    @Ignore
    @Test
    public void testGetPastMeetingMeetingMissing() {
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testGetPastMeetingMeetingInTheFuture() {
    }
    //9--------------------------------------------------------------------------------
    @Ignore
    @Test
    public void testGetPastMeetingListEmpty() {
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testGetPastMeetingListContactDoesNotExist() {
    }
    //10--------------------------------------------------------------------------------
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testAddMeetingNotesMeetingDoesNotExist() {
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void testAddMeetingNotesMeetingInTheFuture() {
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void testAddMeetingNotesAreNull() {
    }
    //--------------------------------------------------------------------------------
    */
}
