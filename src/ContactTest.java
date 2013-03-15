import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ContactTest {
    public Contact contact;

    public ContactTest(Contact contact) {
        this.contact = contact;
    }

	@Test
	public void testId() {
        assertNotNull(contact.getId());
		assertNotSame(1, contact.getId()); ///TEST FOR UNIQUE
	}

	@Test
	public void testName() {
        assertNotNull(contact.getName());
	}

	@Test
	public void testNotes(String note) {
        assertNotNull(contact.getName());
	}

	@Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
    	return Arrays.asList(
    			new Object[]{new ContactImpl()},
				new Object[]{new ContactImpl()} //temp
    	);
    }
}