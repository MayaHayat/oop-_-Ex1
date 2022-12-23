import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests_new {

    @Test
    public void testNotify(){

    }

    @Test
    public void testRegister(){

    }

    @Test
    public void testUnregister(){

    }

    @Test
    public void testInsert(){

    }

    @Test
    public void testAppend(){
        ConcreteMember Maya = new ConcreteMember();
        ConcreteMember Shaked = new ConcreteMember();
        GroupAdmin g = new GroupAdmin();
        g.register(Maya);
        g.register(Shaked);
        g.append("This is first trial");
        assertEquals("This is first Trial", Shaked.usb.toString());

    }

    @Test
    public void testUndo(){

    }
}
