import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    public void testRegister(){
        ConcreteMember Maya = new ConcreteMember();
        ConcreteMember Shaked = new ConcreteMember();
        GroupAdmin g = new GroupAdmin();
        g.register(Maya);
        g.register(Shaked);
        assertEquals(2, g.subscribers.size());
    }

    @Test
    public void testUnregister(){
        ConcreteMember Maya = new ConcreteMember();
        ConcreteMember Shaked = new ConcreteMember();
        GroupAdmin g = new GroupAdmin();
        g.register(Maya);
        g.register(Shaked);
        assertEquals(2, g.subscribers.size());
        g.unregister(Maya);
        assertEquals(1, g.subscribers.size());

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
        assertEquals("This is first trial", Shaked.usb.toString());
    }

    @Test
    public void testDelete(){
        ConcreteMember Maya = new ConcreteMember();
        ConcreteMember Shaked = new ConcreteMember();
        GroupAdmin g = new GroupAdmin();
        g.register(Maya);
        g.register(Shaked);
        g.append("This is first trial");
        assertEquals("This is first trial", Shaked.usb.toString());
        g.delete(4,7);
        assertEquals("This first trial", Shaked.usb.toString());
    }

    @Test
    public void testUndo(){
        ConcreteMember Maya = new ConcreteMember();
        GroupAdmin g = new GroupAdmin();
        g.register(Maya);
        g.append("One");
        g.append("Two");
        assertEquals("OneTwo", Maya.usb.toString());
        g.undo();
        assertEquals("One", Maya.usb.toString());

    }



}
