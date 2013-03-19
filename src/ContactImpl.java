/**
 *	A contact is a person we are making business with or may do in the future.
 *
 *	Contacts have an ID (unique), a name (probably unique, but maybe
 *	not), and notes that the user may want to save about them.
 */
public class ContactImpl implements Contact {

	private int id;
	private String name;
	private String notes;
	
	public ContactImpl(int id, String name, String notes) {
		this.id = id;
		this.name = name;
		this.notes = notes;
	}

    /**
     *	Returns the ID of the contact.
     *
     *	@return the ID of the contact.
     */
    @Override
	public int getId() {
    	return id;
    }

    /**
     *	Returns the name of the contact.
     *
     *	@return the name of the contact.
     */
    @Override
    public String getName() {
    	return name;
    }

    /**
     *	Add notes about the contact.
     *
     *	@param note the notes to be added
     */
    @Override
    public void addNotes(String note) {
        this.notes += " " + note; // look at implementing as an ArrayList later
    }

    /**
     *	Returns our notes about the contact, if any.
     *
     *	If we have not written anything about the contact, the empty
     *	string is returned.
     *
     *	@return a string with notes about the contact, maybe empty.
     */
    @Override
    public String getNotes() {
    	return notes;
    }
}