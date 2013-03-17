import java.util.Set;

import org.junit.Ignore; //********************************************* REMOVE LATER
import org.junit.Test;


public class ContactManagerTest {

    @Test(expected=NullPointerException.class)
    public void testAddNewContactNameMissing() {
        ContactManagerImpl contactManager = new ContactManagerImpl();

        //The following line is supposed to throw a NullPointerException
        contactManager.addNewContact(null, "The Six Million Dollar Man");
    }

    @Test(expected=NullPointerException.class)
    public void testAddNewContactNotesMissing() {
        ContactManagerImpl contactManager = new ContactManagerImpl();

        //The following line is supposed to throw a NullPointerException
        contactManager.addNewContact("Steve Austin", null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAllIDsDoNotMatchContacts() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");
        contactManager.addNewContact("Mikey Jikey", "Vice President");

        //The last line is supposed to throw a IllegalArgumentException
        @SuppressWarnings("contacts is unused")
        Set<Contact> contacts = contactManager.getContacts(1, 2, 3);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIDDoesNotMatchContacts() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");

        //The last line is supposed to throw a IllegalArgumentException
        @SuppressWarnings("contacts is unused")
        Set<Contact> contacts = contactManager.getContacts(77);
    }

    @Test(expected=NullPointerException.class)
    public void testGetContactNameMissing() {
        ContactManagerImpl contactManager = new ContactManagerImpl();
        contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");

        //The last line is supposed to throw a NullPointerException
        @SuppressWarnings("actual is unused")
        Set<Contact> actual = contactManager.getContacts((String) null);
    }

}