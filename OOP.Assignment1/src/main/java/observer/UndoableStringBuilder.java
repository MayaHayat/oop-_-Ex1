package observer;

import java.util.Stack;

/*
Use the class you've implemented in previous assignment
 */
public class UndoableStringBuilder {
    private Stack<StringBuilder> st = new Stack<StringBuilder>();
    private StringBuilder sb = new StringBuilder();

    /**
     * Empty constructor
     */
    public UndoableStringBuilder (){
    }




    /**
     * This function converts to string so one can read it.
     * @return the string
     */

    public String toString(){
        try{
            return this.st.peek()+"";
        }
        catch(Exception e) {
            return "";
        }

    }


    /**
     * This function appends a string or a character sequence
     * @param str - receive the string to append
     * @return what we get after appending the new string.
     */

    public UndoableStringBuilder append(String str){
        sb.append(str);
        st.push(new StringBuilder(sb));
        return this;
    }

    /**
     * This function deletes the characters between the start and end-1 index
     * @param start - first index to delete
     * @param end - first index that doesn't get deleted
     * @return the new word/ sentence without the specified indexes.
     */

    public UndoableStringBuilder delete(int start, int end) {
        try{
            sb.delete(start, end);
            st.push(new StringBuilder(sb));

        }
        catch (Exception e){
            System.out.println("Please make sure both numbers are in correct range and first number isn't larger than second.");
            e.printStackTrace();;
        }
        return this;
    }

    /**
     * Insert the given string into the chosen place
     * @param offset - where the string inserted
     * @param str - what string is being inserted
     * @return the new string placed correctly.
     */

    public UndoableStringBuilder insert(int offset, String str){
        try{
            sb.insert(offset, str);
            st.push(new StringBuilder(sb));
        }
        catch(Exception e){
            System.out.println("Please make sure the number is in correct range.");
            e.printStackTrace();

        }
        return this;
    }

    /**
     * This function replaces indicated indexes with the new string
     * @param start - the first index that gets replaced
     * @param end - the first index that does not get replaced
     * @param str - the string that replaces the given indexes
     * @return the string after having replaced the indicated indexes.
     */

    public UndoableStringBuilder replace(int start,int end, String str){
        try {
            sb.replace(start, end, str);
            st.push(new StringBuilder(sb));
        }
        catch (StringIndexOutOfBoundsException e){
            System.out.println("Please make sure both numbers are in correct range and first number isn't larger than second.");
            e.printStackTrace();
        }
        catch (NullPointerException e){
            System.out.println("Please entre a valid string");
            e.printStackTrace();
        }

        return this;
    }

    /**
     * This function reverses the string (letter by letter)
     * @return the reversed string.
     */

    public UndoableStringBuilder reverse(){
        sb.reverse();
        st.push(new StringBuilder(sb));
        return this;
    }

    /**
     * This function allows the used to go back to the last string by poping out the stored strings.
     * @return the last string stored in the stack.
     */

    public void undo(){
        try{
            if (st.isEmpty())
                System.out.println("");
            st.pop();
        }
        catch (Exception e){
            e.printStackTrace();

        }

    }
}

