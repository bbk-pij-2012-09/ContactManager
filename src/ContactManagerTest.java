import static org.junit.Assert.assertNotNull;

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
}