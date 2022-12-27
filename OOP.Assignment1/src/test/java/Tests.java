import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
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
    public void testNotify(){
        ConcreteMember Maya = new ConcreteMember();
        ConcreteMember Shaked = new ConcreteMember();
        GroupAdmin g = new GroupAdmin();
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("Hello");
        g.register(Maya);
        g.notify(usb);
        //We know that update works from test bellow therefore will check if the proccess isn't stuck in the Nofity part:
        assertEquals("Hello", Maya.usb.toString());
        assertEquals(null, Shaked.usb);
        //Note that only Maya was notified


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
        g.append("hi");
        assertEquals(null, Maya.usb);
        assertEquals("hi", Shaked.usb.toString());

    }

    @Test
    public void testInsert() {
        ConcreteMember Shaked = new ConcreteMember();
        ConcreteMember Maya = new ConcreteMember();
        GroupAdmin g = new GroupAdmin();
        UndoableStringBuilder usb = new UndoableStringBuilder();
        g.register(Shaked);
        g.register(Maya);
        g.append("Hello");
        assertEquals("Hello", Maya.usb.toString());
        g.unregister(Maya);

        assertEquals("Hello", Maya.usb.toString());
        assertEquals(1, g.subscribers.size());
        g.insert(0,"Shaked ");
        assertEquals("Shaked Hello", Shaked.usb.toString());
        g.append("hi");
        assertEquals("Shaked Hellohi", Maya.usb.toString());

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

    @Test
    public void testUpdate(){
        GroupAdmin ga = new GroupAdmin();
        ga.append("Trying");
        ConcreteMember cm = new ConcreteMember();
        assertEquals(null, cm.usb);
        ga.register(cm);
        assertEquals(null, cm.usb);

        ga.delete(1,2);
        assertEquals("Tying", cm.usb.toString());
    }


    @Test
    public void testCopy(){
        ConcreteMember Maya = new ConcreteMember();
        ConcreteMember Shaked = new ConcreteMember();
        GroupAdmin g = new GroupAdmin();
        g.register(Maya);
        g.register(Shaked);
        g.append("Trying shallow copy");
        assertEquals(g.usb, Shaked.usb);
        assertEquals(Maya.usb, Shaked.usb);
    }

    @Test
    void JVMUTILT_test() {

       GroupAdmin ga = new GroupAdmin();
        logger.info(() -> JvmUtilities.objectFootprint(ga));
        ConcreteMember Shaked = new ConcreteMember();
        ga.register(Shaked);
        logger.info(() -> JvmUtilities.objectTotalSize(Shaked));
        ga.append("Only Shaked is registered");
        logger.info(() -> JvmUtilities.objectTotalSize(Shaked));
        ga.append("to this list");
        ConcreteMember Maya = new ConcreteMember();
        ga.register(Maya);

        logger.info(()->JvmUtilities.objectTotalSize(Shaked));
        logger.info(()->JvmUtilities.objectTotalSize(Maya));

        ga.append("Now Maya is also added");
        logger.info(()->JvmUtilities.objectTotalSize(Shaked));
        logger.info(()->JvmUtilities.objectTotalSize(Maya));

        logger.info(() -> JvmUtilities.objectFootprint(ga));
        ga.undo();

        logger.info(()->JvmUtilities.objectTotalSize(Shaked));
        logger.info(()->JvmUtilities.objectTotalSize(Maya));

        logger.info(() -> JvmUtilities.objectFootprint(ga));

        ConcreteMember newMember = new ConcreteMember();
        ga.register(newMember);
        logger.info(()->JvmUtilities.objectTotalSize(newMember));
        ga.append("Note that all three members will have the same memory now");

        logger.info(()->JvmUtilities.objectTotalSize(Shaked));
        logger.info(()->JvmUtilities.objectTotalSize(Maya));
        logger.info(()->JvmUtilities.objectTotalSize(newMember));

        ga.unregister(newMember);
        ga.undo();

        logger.info(()->JvmUtilities.objectTotalSize(Shaked));
        logger.info(()->JvmUtilities.objectTotalSize(Maya));
        logger.info(()->JvmUtilities.objectTotalSize(newMember));

    }



}
