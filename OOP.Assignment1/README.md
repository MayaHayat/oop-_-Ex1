#OOP_Assignment_1.2
###Overview
In this project we've created an observer design pattern that allows subjects to know when it has been changed. In order for the subjects to get the update they have to register to the list of members.
This project implements the undoableStringBuilder class and uses its object as the changing object and the list is created from groupMembers.

###Data Stuctures
The data structures used in this project are:
1. ArrayList for the member list as this data structure is the best fit for a list that constantly changed and allows doubles (as of course two people can have the same name).
2. Stack for implementing the UndoableStringBuilder as we had to implement the undo function. This data structure was the obvious choice as last in first out just like ctr Z works.
3. StringBuilder as it contains most of the functions we needed for the UndoableStringBuilder.

###Implementation
This project is divided into three main parts:
####UndoableStringBuilder
This class implements 6 different functions using stack and StringBuilder.
####ConcreteMember
This class implements the Member in the subscribers list.
The ConceteMember class has one function which is a shallow copy of the UndoableStringBuilder.
####GroupAdmin
This class implements the Sender interface. This class contains a list of all registered members and updates them of any change in the UndoableStringBuilder.
Members can unregister if they don't want to receive updates. Note that once they register they'll receive the current string.

###Algorithms
This project does not contain any complicated algorithms, however every single function has to work perfectly otherwise the whole program won't work as they are all dependent on each other.
For example, if the update function won't work, the notify function(aka UpdateAll) won't work and will result in failed tests.

###Run the project
In order to run this project one would need to enter their IDE and clone the repository:

git clone https://github.com/MayaHayat/oop-_-Ex1.git



