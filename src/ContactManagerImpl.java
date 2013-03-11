/**
 * 
 */
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 *	A class to manage your contacts and meetings.
 */
public class ContactManagerImpl implements ContactManager {
	/**
	 * 
	 */
    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
    	return 0; // TO BE REVISED
    }

	/**
	 * 
	 */
    @Override
    public PastMeeting getPastMeeting(int id) {
    	return null; // TO BE REVISED    	
    }

	/**
	 * 
	 */
    @Override
    public FutureMeeting getFutureMeeting(int id) {
    	return null; // TO BE REVISED    	
    }

	/**
	 * 
	 */
    @Override
    public Meeting getMeeting(int id) {
    	return null; // TO BE REVISED  	
    }

	/**
	 * 
	 */
    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
    	return null; // TO BE REVISED    	
    }

	/**
	 * 
	 */
    @Override
    public List<Meeting> getFutureMeetingList(Calendar date) {
    	return null; // TO BE REVISED       	
    }

	/**
	 * 
	 */
    @Override
    public List<PastMeeting> getPastMeetingList(Contact  contact) {
    	return null; // TO BE REVISED       	
    }

	/**
	 * 
	 */
    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
    	// TO BE REVISED       	    	
    }

	/**
	 * 
	 */
    @Override
    public void addMeetingNotes(int id, String text) {
    	// TO BE REVISED       	   	    	
    }

	/**
	 * 
	 */
    @Override
    public void addNewContact(String name, String notes) {
    	// TO BE REVISED       	    	
    }

	/**
	 * 
	 */
    @Override
    public Set<Contact> getContacts(int... ids) {
    	return null; // TO BE REVISED       	    	
    }

	/**
	 * 
	 */
    @Override
    public Set<Contact> getContacts(String  name) {
    	return null; // TO BE REVISED       	    	
    }

	/**
	 * 
	 */
    @Override
    public void flush() {
    	// TO BE REVISED       	    	
    }
}