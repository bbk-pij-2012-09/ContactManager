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
		if (name == null) 
			throw new NullPointerException("You tried to add a new contact however the name is missing");
		else if (notes == null)
			throw new NullPointerException("You tried to add a new contact however the notes are missing");
		else {
			id++;
			ContactImpl contact = new ContactImpl(id, name, notes);
			contacts.add(contact);
		}
    }
    
    @Override
    public Set<Contact> getContacts(int... ids) throws IllegalArgumentException {
    	for (int id : ids) {
    		if (!contacts.contains(id))
    			throw new IllegalArgumentException("Not there!");
    	}
    	return null;
	    	
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