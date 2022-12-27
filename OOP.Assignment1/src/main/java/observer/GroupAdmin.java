package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender{

    public UndoableStringBuilder usb;
    public ArrayList<Member> subscribers; //is an arraylist as its size changes fairly often.


    public GroupAdmin(){
        this.usb = new UndoableStringBuilder();
        this.subscribers = new ArrayList<>();
    }

    /**
     * This function notifies all subscribers that a string has changed.
     * @param usb is the string that has been updated.
     */

    public void notify(UndoableStringBuilder usb){
        for (Member subscriber: subscribers){
            subscriber.update(usb);
        }
    }

    /**
     * Adds a new subscriber to the member list
     * @param obj is the member added.
     */

    @Override
    public void register(Member obj) {
        subscribers.add(obj);
    }

    /**
     * Removes a subscriber from the member list.
     * @param obj is the member removed.
     */

    @Override
    public void unregister(Member obj) {
        subscribers.remove(obj);

    }

    /**
     * Inserts something then notifies all relevant members.
     * @param offset is where we want to insert the old string
     * @param obj is the string we want to insert
     */

    @Override
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        notify(this.usb);
    }

    /**
     * After appending the new string to the old one notify all members.
     * @param obj is added string to the old one.
     */

    @Override
    public void append(String obj) {
        this.usb.append(obj);
        notify(this.usb);
    }

    /**
     * This function deletes a part of the string then updates all members in the list.
     * @param start is the index before we'd like it to start deleting
     * @param end is the last index deleted.
     */

    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        notify(this.usb);
    }

    /**
     * This function notifies all members in the list if the string has been undone to previous string.
     */

    @Override
    public void undo() {
        this.usb.undo();
        notify(this.usb);
    }
}
