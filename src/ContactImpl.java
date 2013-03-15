import java.util.List;
import java.util.ArrayList;

public class ContactImpl implements Contact {
	private int id;
	private String name;
	private ArrayList<String> notes;
	
    @Override
	public int getId() {
    	return id;
    }

    @Override
    public String getName() {
    	return name;
    }

    @Override
    public String getNotes() {
    	for (String note: notes)
    		return note;
    }

    @Override
    public void addNotes(String note) {
    	notes.add(note);
    }
}