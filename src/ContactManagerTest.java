import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;


public class ContactManagerTest {

    //1--------------------------------------------------------------------------------
    @Test(expected = NullPointerException.class)
    public void testAddNewContactNameMissing1() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        //The following line is supposed to throw a NullPointerException
        contactManager.addNewContact(null, "The Six Million Dollar Man");
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewContactNotesMissing1() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        //The following line is supposed to throw a NullPointerException
        contactManager.addNewContact("Steve Austin", null);
    }

    @Ignore
    @Test
    public void testAddNewContactReadFromFile() {
        final int CONTACT_JUST_ADDED = 1;
        final int SECOND_CONTACT_JUST_ADDED = 2;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewContact("Mikey Jikey", "Vice President");
        //contactManager.flush();
        //contactManager = new ContactManagerImpl();
        //assertEquals(2, contactManager.getContactSize());
    }

    //2--------------------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIDsDoesNotMatch2() {
        final int UNKNOWN_CONTACT = 1000;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        //The last line is supposed to throw a IllegalArgumentException
        @SuppressWarnings("contacts is unused")
        Set<Contact> contacts = contactManager.getContacts(UNKNOWN_CONTACT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIDsDoNotMatch2() {
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
    public void testGetContactNameMissing2() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        //The last line is supposed to throw a NullPointerException
        @SuppressWarnings("actual is unused")
        Set<Contact> actual = contactManager.getContacts((String) null);
    }
    //3--------------------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testAddFutureMeetingMeetingInThePast3() {
        final int CONTACT_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(1999, 11, 31);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addFutureMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFutureMeetingContactMissing3() {
        final int UNKNOWN_CONTACT = 1000;
        Calendar date = Calendar.getInstance();
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addFutureMeeting(contactManager.getContacts(UNKNOWN_CONTACT), date);
    }
    //4--------------------------------------------------------------------------------
    @Test
    public void testGetFutureMeetingMeetingExists4() {
        final int CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(2014, 0, 1);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addFutureMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date);
        assertNotNull(contactManager.getFutureMeeting(MEETING_JUST_ADDED));
    }

    @Test
    public void testGetFutureMeetingMeetingMissing4() {
        final int CONTACT_JUST_ADDED = 1;
        final int UNKNOWN_MEETING = 1000;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(2014, 0, 1);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addFutureMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date);
        assertNull(contactManager.getFutureMeeting(UNKNOWN_MEETING));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFutureMeetingMeetingInThePast4() {
        final int CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(1999, 11, 31);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addFutureMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date);
        contactManager.getFutureMeeting(MEETING_JUST_ADDED);
    }

    //5--------------------------------------------------------------------------------
    @Test(expected=IllegalArgumentException.class)
    public void testGetFutureMeetingListContactMissing5() {
        final int CONTACT_JUST_ADDED = 1;
        final int UNKNOWN_CONTACT = 1000;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(2014, 0, 1);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(UNKNOWN_CONTACT);
        contactManager.addFutureMeeting(contacts, date);
        for (Contact contact : contacts) {
            List<Meeting> meetings = contactManager.getFutureMeetingList(contact);
        }
    }

    @Test
    public void testGetFutureMeetingListNotEmpty5() {
        final int CONTACT_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(2014, 0, 1);
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
    public void testGetFutureMeetingListEmpty5() {
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
    public void testGetFutureMeetingListSorted5() {
        final int CONTACT_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);

        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.SECOND, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(2014, 0, 3);
        contactManager.addFutureMeeting(contacts, expectedDate1);

        Calendar expectedDate2 = Calendar.getInstance();
        expectedDate2.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate2.set(Calendar.MINUTE, 0);
        expectedDate2.set(Calendar.SECOND, 0);
        expectedDate2.set(Calendar.MILLISECOND, 0);
        expectedDate2.set(2014, 0, 4);
        contactManager.addFutureMeeting(contacts, expectedDate2);

        Calendar expectedDate3 = Calendar.getInstance();
        expectedDate3.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate3.set(Calendar.MINUTE, 0);
        expectedDate3.set(Calendar.SECOND, 0);
        expectedDate3.set(Calendar.MILLISECOND, 0);
        expectedDate3.set(2014, 0, 2);
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
    public void testGetFutureMeetingListNoDuplicates5() {
        final int CONTACT_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);

        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.SECOND, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(2015, 0, 1);
        contactManager.addFutureMeeting(contacts, expectedDate1);

        Calendar expectedDate2 = Calendar.getInstance();
        expectedDate2.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate2.set(Calendar.MINUTE, 0);
        expectedDate2.set(Calendar.SECOND, 0);
        expectedDate2.set(Calendar.MILLISECOND, 0);
        expectedDate2.set(2015, 0, 1);
        contactManager.addFutureMeeting(contacts, expectedDate2);

        Calendar expectedDate3 = Calendar.getInstance();
        expectedDate3.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate3.set(Calendar.MINUTE, 0);
        expectedDate3.set(Calendar.SECOND, 0);
        expectedDate3.set(Calendar.MILLISECOND, 0);
        expectedDate3.set(2014, 11, 25);
        contactManager.addFutureMeeting(contacts, expectedDate3);

        Calendar expectedDate4 = Calendar.getInstance();
        expectedDate4.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate4.set(Calendar.MINUTE, 0);
        expectedDate4.set(Calendar.SECOND, 0);
        expectedDate4.set(Calendar.MILLISECOND, 0);
        expectedDate4.set(2020, 11, 25);
        contactManager.addFutureMeeting(contacts, expectedDate4);

        Calendar expectedDate5 = Calendar.getInstance();
        expectedDate5.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate5.set(Calendar.MINUTE, 0);
        expectedDate5.set(Calendar.SECOND, 0);
        expectedDate5.set(Calendar.MILLISECOND, 0);
        expectedDate5.set(2014, 11, 25);
        contactManager.addFutureMeeting(contacts, expectedDate5);

        for (Contact contact : contacts) {
            List<Meeting> meetings = contactManager.getFutureMeetingList(contact);
            final int UNIQUE_MEETINGS = 3;
            assertEquals(UNIQUE_MEETINGS, meetings.size());
        }
    }

    @Test
    public void testGetFutureMeetingListByDate5() {
        final int FIRST_CONTACT_JUST_ADDED = 1;
        final int SECOND_CONTACT_JUST_ADDED = 2;
        final int THIRD_CONTACT_JUST_ADDED = 3;

        ContactManagerImpl contactManager = new ContactManagerImpl();
        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.SECOND, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(2015, 0, 1); //1st Jan 2015
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewContact("Mikey Jikey", "Vice President");
        contactManager.addNewContact("Babara Jones", "Personal Assistant");
        Set<Contact> contacts = contactManager.getContacts(FIRST_CONTACT_JUST_ADDED, SECOND_CONTACT_JUST_ADDED, THIRD_CONTACT_JUST_ADDED);
        contactManager.addFutureMeeting(contacts, expectedDate1);
        //1 meeting in total on 1st Jan 2015 with Steve, Mikey and Barbara at this point

        contacts = contactManager.getContacts(FIRST_CONTACT_JUST_ADDED);
        contactManager.addFutureMeeting(contacts, expectedDate1);
        //2 meetings in total on 1st Jan 2015 with Steve Austin at this point

        contacts = contactManager.getContacts(SECOND_CONTACT_JUST_ADDED, THIRD_CONTACT_JUST_ADDED);
        Calendar expectedDate2 = Calendar.getInstance();
        expectedDate2.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate2.set(Calendar.MINUTE, 0);
        expectedDate2.set(Calendar.SECOND, 0);
        expectedDate2.set(Calendar.MILLISECOND, 0);
        expectedDate2.set(2014, 11, 25); //25th Dec 2014
        contactManager.addFutureMeeting(contacts, expectedDate2);
        //1 meeting in total on 25 Dec 2014 with Mikey and Barbara at this point

        contacts = contactManager.getContacts(FIRST_CONTACT_JUST_ADDED, THIRD_CONTACT_JUST_ADDED);
        Calendar expectedDate3 = Calendar.getInstance();
        expectedDate3.set(Calendar.HOUR_OF_DAY, 3);
        expectedDate3.set(Calendar.MINUTE, 0);
        expectedDate3.set(Calendar.SECOND, 0);
        expectedDate3.set(Calendar.MILLISECOND, 0);
        expectedDate3.set(2013, 9, 23); //23 Oct 2013 for Steve and Barbara

        Calendar expectedDate4 = Calendar.getInstance();
        expectedDate4.set(Calendar.HOUR_OF_DAY, 2);
        expectedDate4.set(Calendar.MINUTE, 0);
        expectedDate4.set(Calendar.SECOND, 0);
        expectedDate4.set(Calendar.MILLISECOND, 0);
        expectedDate4.set(2013, 9, 23); //23 Oct 2013 for Mikey and Barbara

        Calendar expectedDate5 = Calendar.getInstance();
        expectedDate5.set(Calendar.HOUR_OF_DAY, 1);
        expectedDate5.set(Calendar.MINUTE, 0);
        expectedDate5.set(Calendar.SECOND, 0);
        expectedDate5.set(Calendar.MILLISECOND, 0);
        expectedDate5.set(2013, 9, 23); //23 Oct 2013 for Steve, Mikey and Barbara
        contactManager.addFutureMeeting(contacts, expectedDate3);
        //1 meeting in total on 23 Oct 2013 with Steve and Barbara at this point

        contacts = contactManager.getContacts(SECOND_CONTACT_JUST_ADDED, THIRD_CONTACT_JUST_ADDED);
        contactManager.addFutureMeeting(contacts, expectedDate4);
        //2 meetings in total on 23 Oct 2013 with Mikey and Barbara at this point

        contacts = contactManager.getContacts(FIRST_CONTACT_JUST_ADDED, SECOND_CONTACT_JUST_ADDED, THIRD_CONTACT_JUST_ADDED);
        contactManager.addFutureMeeting(contacts, expectedDate5);
        //3 meeting in total on 23 Oct 2013 with Steve, Mikey and Barbara at this point

        List<Meeting> meetings = contactManager.getFutureMeetingList(expectedDate1);
        int meetingSize = meetings.size();
        /*
        System.out.println();
        System.out.println(meetingSize + " are stored for 1st Jan 2015 i.e.:");
        for (Meeting meeting : meetings) {
            System.out.print("    " + meeting.getDate().get(Calendar.DAY_OF_MONTH) + "/");
            System.out.print(meeting.getDate().get(Calendar.MONTH) + "/");
            System.out.println(meeting.getDate().get(Calendar.YEAR));
            contacts = meeting.getContacts();
            for (Contact contact : contacts) {
                System.out.println("        " + contact.getName());
            }
        }
        System.out.println("(1)");
        System.out.println();
        */
        assertEquals(2, meetingSize);

        meetings = contactManager.getFutureMeetingList(expectedDate2);
        meetingSize = meetings.size();
        /*
        System.out.println();
        System.out.println(meetingSize + " are stored for 25th Dec 2014 i.e.:");
        for (Meeting meeting : meetings) {
            System.out.print("    " + meeting.getDate().get(Calendar.DAY_OF_MONTH) + "/");
            System.out.print(meeting.getDate().get(Calendar.MONTH) + "/");
            System.out.println(meeting.getDate().get(Calendar.YEAR));
            contacts = meeting.getContacts();
            for (Contact contact : contacts) {
                System.out.println("        " + contact.getName());
            }
        }
        System.out.println("(2)");
        System.out.println();
        */
        assertEquals(1, meetingSize);

        meetings = contactManager.getFutureMeetingList(expectedDate3);
        meetingSize = meetings.size();
        /*
        System.out.println();
        System.out.println(meetingSize + " are stored for 23 Oct 2013 i.e.:");
        for (Meeting meeting : meetings) {
            System.out.print("    " + meeting.getDate().get(Calendar.DAY_OF_MONTH)+ "/");
            System.out.print(meeting.getDate().get(Calendar.MONTH) + "/");
            System.out.println(meeting.getDate().get(Calendar.YEAR));
            contacts = meeting.getContacts();
            for (Contact contact : contacts) {
                System.out.println("        " + contact.getName());
            }
        }
        System.out.println("3");
        System.out.println();
        contacts = (meetings.get(0)).getContacts();
        for (Contact contact : contacts) {
            System.out.println("------        " + contact.getName());
        }

        System.out.println();
        contacts = (meetings.get(1)).getContacts();
        for (Contact contact : contacts) {
            System.out.println("------        " + contact.getName());
        }

        System.out.println();
        contacts = (meetings.get(2)).getContacts();
        for (Contact contact : contacts) {
            System.out.println("------        " + contact.getName());
        }
        */
        assertEquals(3, meetingSize);
    }
    //6--------------------------------------------------------------------------------
    @Test
    public void testGetMeetingIDsMatch6() {
        final int FIRST_CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.SECOND, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(2015, 0, 1); //1st Jan 2015
        contactManager.addFutureMeeting(contactManager.getContacts(FIRST_CONTACT_JUST_ADDED), expectedDate1);
        Meeting meeting = contactManager.getMeeting(MEETING_JUST_ADDED);
        assertEquals(MEETING_JUST_ADDED, meeting.getId());
        //**** NOT WORKING WHEN RUN ALL TESTS

    }

    @Test
    public void testGetMeetingUnknownIDReturnsNull6() {
        final int FIRST_CONTACT_JUST_ADDED = 1;
        final int MEETING_UNKNNOWN = 1000;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.SECOND, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(2015, 0, 1); //1st Jan 2015
        contactManager.addFutureMeeting(contactManager.getContacts(FIRST_CONTACT_JUST_ADDED), expectedDate1);
        Meeting meeting = contactManager.getMeeting(MEETING_UNKNNOWN);
        assertNull(meeting);
    }
    //7--------------------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testAddNewPastContactListEmpty7() {
        final int MEETING_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        Set<Contact> contacts = new HashSet<Contact>();;
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(2015, 0, 1); //1st Jan 2015
        contactManager.addNewPastMeeting(contacts, expectedDate1, "Positive response to a re-make of the original series");
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastContactMissing7() {
        final int MEETING_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(2015, 0, 1); //1st Jan 2015
        contactManager.addNewPastMeeting(null, expectedDate1, "Positive response to a re-make of the original series");
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastDateMissing7() {
        final int FIRST_CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewPastMeeting(contactManager.getContacts(FIRST_CONTACT_JUST_ADDED), null, "Positive response to a re-make of the original series");
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastTextMissing7() {
        final int FIRST_CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(2015, 0, 1); //1st Jan 2015
        contactManager.addNewPastMeeting(contactManager.getContacts(FIRST_CONTACT_JUST_ADDED), expectedDate1, null);
    }
    //8--------------------------------------------------------------------------------
    @Test
    public void testGetPastMeetingMeetingExists8() {
        final int CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(1999, 11, 31);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewPastMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date, "Another series is a go!");
        assertNotNull(contactManager.getPastMeeting(MEETING_JUST_ADDED));
    }

    @Test
    public void testGetPastMeetingMeetingMissing8() {
        final int CONTACT_JUST_ADDED = 1;
        final int UNKNOWN_MEETING = 1000;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(1999, 11, 31);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewPastMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date, "Arrange investment");
        assertNull(contactManager.getPastMeeting(UNKNOWN_MEETING));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPastMeetingMeetingInTheFuture8() {
        final int CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(2020, 11, 31);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewPastMeeting(contactManager.getContacts(CONTACT_JUST_ADDED), date, "Schedule press conference");
        contactManager.getPastMeeting(MEETING_JUST_ADDED);
    }

    //9--------------------------------------------------------------------------------
    @Test(expected=IllegalArgumentException.class)
    public void testGetPastMeetingListContactMissing9() {
        final int CONTACT_JUST_ADDED = 1;
        final int UNKNOWN_CONTACT = 1000;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(1999, 11, 31);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(UNKNOWN_CONTACT);
        contactManager.addNewPastMeeting(contacts, date, "Lee Majors has agreed a pay cut");
        for (Contact contact : contacts) {
            List<PastMeeting> meetings = contactManager.getPastMeetingList(contact);
        }
    }

    @Test
    public void testGetPastMeetingListNotEmpty9() {
        final int CONTACT_JUST_ADDED = 1;
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(1999, 11, 31);
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);
        contactManager.addNewPastMeeting(contacts, date, "Discussed new script");
        for (Contact contact : contacts) {
            List<PastMeeting> meetings = contactManager.getPastMeetingList(contact);
            assertEquals(1, meetings.size());
        }
    }

    @Test
    public void testGetPastMeetingListEmpty9() {
        final int CONTACT_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);
        for (Contact contact : contacts) {
            List<PastMeeting> meetings = contactManager.getPastMeetingList(contact);
            assertEquals(0, meetings.size());
        }
    }

    @Test
    public void testGetPastMeetingListSorted9() {
        final int CONTACT_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);

        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.SECOND, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(1999, 11, 5);
        contactManager.addNewPastMeeting(contacts, expectedDate1, "Steve is admitted to hospital in the second scene");

        Calendar expectedDate2 = Calendar.getInstance();
        expectedDate2.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate2.set(Calendar.MINUTE, 0);
        expectedDate2.set(Calendar.SECOND, 0);
        expectedDate2.set(Calendar.MILLISECOND, 0);
        expectedDate2.set(1999, 11, 9);
        contactManager.addNewPastMeeting(contacts, expectedDate2, "Steve is restored to full health");

        Calendar expectedDate3 = Calendar.getInstance();
        expectedDate3.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate3.set(Calendar.MINUTE, 0);
        expectedDate3.set(Calendar.SECOND, 0);
        expectedDate3.set(Calendar.MILLISECOND, 0);
        expectedDate3.set(1999, 11, 1);
        contactManager.addNewPastMeeting(contacts, expectedDate3, "Steve falls and is injured in the script");

        for (Contact contact : contacts) {
            List<PastMeeting> meetings = contactManager.getPastMeetingList(contact);

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

    //10--------------------------------------------------------------------------------
    @Test
    public void testAddMeetingNotes() {
        final int CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);
        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(1999, 11, 31);
        contactManager.addNewPastMeeting(contacts, expectedDate1, "Visit again in 3 months");
        contactManager.addMeetingNotes(MEETING_JUST_ADDED, "And rewrite script scene 4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMeetingNotesMeetingDoesNotExist() {
        final int CONTACT_JUST_ADDED = 1;
        final int UNKNOWN_MEETING = 1000;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);
        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(1999, 11, 31);
        contactManager.addNewPastMeeting(contacts, expectedDate1, "I was talking");
        contactManager.addMeetingNotes(UNKNOWN_MEETING, "He talked back");
    }

    @Test(expected = IllegalStateException.class)
    public void testAddMeetingNotesMeetingStillInTheFuture() {
        final int CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);
        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(2014, 0, 1);
        contactManager.addFutureMeeting(contacts, expectedDate1);
        contactManager.addMeetingNotes(MEETING_JUST_ADDED, "Great meeting has taken place");
    }

    @Test(expected = NullPointerException.class)
    public void testAddMeetingNotesNotesAreMissing() {
        final int CONTACT_JUST_ADDED = 1;
        final int MEETING_JUST_ADDED = 1;
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        Set<Contact> contacts = contactManager.getContacts(CONTACT_JUST_ADDED);
        Calendar expectedDate1 = Calendar.getInstance();
        expectedDate1.set(Calendar.HOUR_OF_DAY, 0);
        expectedDate1.set(Calendar.MINUTE, 0);
        expectedDate1.set(Calendar.MILLISECOND, 0);
        expectedDate1.set(1999, 11, 31);
        contactManager.addNewPastMeeting(contacts, expectedDate1, "I was talking");
        contactManager.addMeetingNotes(MEETING_JUST_ADDED, null);
    }
    //--------------------------------------------------------------------------------
}
