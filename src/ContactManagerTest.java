import java.security.URIParameter;
import java.util.Calendar;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class ContactManagerTest {

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

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIDsDoesNotMatch() {
        final int UNKNOWN_CONTACT = 1000;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");

        //The last line is supposed to throw a IllegalArgumentException
        @SuppressWarnings("contacts is unused")
        Set<Contact> contacts = contactManager.getContacts(UNKNOWN_CONTACT);
    }



    @Test(expected = NullPointerException.class)
    public void testGetContactNameMissing() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");

        //The last line is supposed to throw a NullPointerException
        @SuppressWarnings("actual is unused")
        Set<Contact> actual = contactManager.getContacts((String) null);
    }










    @Test(expected = IllegalArgumentException.class)
    public void testAddFutureMeetingMeetingInThePast() {
        final int CONTACT_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(2014, 1, 1);
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
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addFutureMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date);
        contactManager.getFutureMeeting(MEETING_JUST_ADDED);
    }


    //UP TO HERE ***********
    @Test(expected = IllegalArgumentException.class)
    public void testGetFutureMeetingListContactMissing() {
        Calendar date = Calendar.getInstance();
        date.set(2014, 1, 1);

    }



    @Test
    public void testGetFutureMeetingListEmpty() {
    }

    @Test
    public void testGetFutureMeetingListSorted() {
    }

    @Test
    public void testGetFutureMeetingNoDuplicates() {
    }










    @Test
    public void testGetMeetingIDsMatch() {
    }










    @Test(expected = IllegalArgumentException.class)
    public void testAddNewPastContactMissing() {
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastContactListEmpty() {
    }



    @Test
    public void testPastMeetingMeetingMissing() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPastMeetingMeetingInTheFuture() {
    }



    @Test
    public void testPastMeetingListEmpty() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPastMeetingListContactDoesNotExist() {
    }



    @Test(expected = IllegalArgumentException.class)
    public void testAddMeetingNotesMeetingDoesNotExist() {
    }

    @Test(expected = IllegalStateException.class)
    public void testAddMeetingNotesMeetingInTheFuture() {
    }

    @Test(expected = NullPointerException.class)
    public void testAddMeetingNotesAreNull() {
    }
}
