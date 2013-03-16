import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class ContactManagerImpl implements ContactManager {
	
	private int id;
	private Set<Contact> contacts;
	//private List<Meetings> meeting;
	
	private ContactImpl contact;
	
	public ContactManagerImpl() {
		id = 0; // id whatever it was last time sort out persistence mechanism
		contacts = new HashSet<Contact>(); //for now will see if order matters
	}
	
	    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
    	return 0; // TO BE REVISED
    }

    @Override
    public PastMeeting getPastMeeting(int id) {
    	return null; // TO BE REVISED    	
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) {
    	return null; // TO BE REVISED    	
    }

    @Override
    public Meeting getMeeting(int id) {
    	return null; // TO BE REVISED  	
    }

    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
    	return null; // TO BE REVISED    	
    }

    @Override
    public List<Meeting> getFutureMeetingList(Calendar date) {
    	return null; // TO BE REVISED       	
    }

    @Override
    public List<PastMeeting> getPastMeetingList(Contact  contact) {
    	return null; // TO BE REVISED       	
    }

    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
    	// TO BE REVISED       	    	
    }

    @Override
    public void addMeetingNotes(int id, String text) {
    	// TO BE REVISED       	   	    	
    }

    /**
     * @throws NullPointerException if the name or the notes are null
    */
    @Override
    public void addNewContact(String name, String notes) throws NullPointerException {
		id++;
		try {
    			ContactImpl contact = new ContactImpl(id, name, notes);
    			contacts.add(contact);
    		}
    		catch (NullPointerException exception) {
    			if (name == null) {
    				System.out.println("You attempted to add a new contact however name is missing");
    			}
    			else {
    				System.out.println("You attempted to add a new contact however notes are missing");
    			}
    		}
    }

    @Override
    public Set<Contact> getContacts(int... ids) {
    	return null; // TO BE REVISED       	    	
    }

    @Override
    public Set<Contact> getContacts(String  name) {
    	return null; // TO BE REVISED       	    	
    }

    @Override
    public void flush() {
    	// TO BE REVISED       	    	
    }
}