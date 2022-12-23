package observer;

public class ConcreteMember implements Member{

    public UndoableStringBuilder usb;


    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }
}
