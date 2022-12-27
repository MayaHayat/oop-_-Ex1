package observer;

public class ConcreteMember implements Member{

    public UndoableStringBuilder usb;

    /**
     * This function is a shallow copy of the UndoableStringBuilder object.
     * @param usb is the object we want to shallow copy from.
     */

    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }
}
