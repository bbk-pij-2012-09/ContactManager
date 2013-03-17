//Test the INTERFACE ***
//Test the INTERFACE ***
//Test the INTERFACE ***
//Test the INTERFACE ***
//Test the INTERFACE ***

import org.junit.Ignore;

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

	@Ignore
	@Test
	public void testIDMatchContacts() {
		ContactManagerImpl contactManager = new ContactManagerImpl();
		contactManager.addNewContact("Steve Austin", "The Six Million Dollar Man");

		//The following line is supposed to throw a IllegalArgumentException
		contactManager.getContacts(1); //, 2, 3);
		
	}

}