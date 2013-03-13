import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

@RunWith(value=Parameterized.class)
public class InterfaceTesting {     
	public Contact contact;

    public InterfaceTesting(Contact contact) {
        this.contact = contact;
    }
    
    @Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
        		new Object[]{new ContactImpl()}, 
        		new Object[]{new ContactImpl()}
        		);
    }
    
    @Test
    public final void testGetId() {
        assertNotNull(contact.getId());
    }

    @Test
    public final void testGetName() {
        assertNotNull(contact.getName());
    }
}