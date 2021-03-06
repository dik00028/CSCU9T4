// An implementation of a Training Record as an ArrayList
package Practical1;


import java.util.*;
import java.util.stream.Collectors;


public class TrainingRecord {
    private List<Entry> tr;

    public TrainingRecord() {
        tr = new ArrayList<>();

    }

    // add a record to the list
    public void addEntry(Entry e) {
        tr.add(e);
    }

    // addClass

    public boolean duplicate(Entry e) {

        return tr.stream().anyMatch(current -> current.getName().equalsIgnoreCase(e.getName()) &&
                current.getMonth() == (e.getMonth()) &&
                current.getYear() == (e.getYear()) &&
                current.getDay() == (e.getDay())
                && current.getClass() == e.getClass());//checking for duplicate
    }

    public boolean dateValidation(int d, int m, int y) {
        //validating the ranges that the dates can be inserted by the user
        return (d < 1 || d > 31)
                || (m < 1 || m > 12)
                || (y < 1950 || y > 2021);
    }

    // look up the entry of a given day and month
    public String lookupEntry(int d, int m, int y) {
        String look = tr.stream()
                .filter(current ->
                        current.getDay() == d
                                && current.getMonth() ==
                                m && current.getYear() == y)
                .map(Entry::getEntry).collect(Collectors.joining());//returning a string of an entry based on the
        // condition in the filter
        return look.equals("") ? "No entries for the given date" : look;
    } // lookupEntry

    public String remove(String name, int d, int m, int y) {

        tr.removeIf(current -> current.getName().equalsIgnoreCase(name)
                && current.getDay() == d
                && current.getMonth() == m
                && current.getYear() == y);// removing if the condition is matched
        String result = tr.stream().map(Entry::getEntry)
                .collect(Collectors.joining());//just for storing the entries
        return result.equals("") ? "No remaining entries" : result;
    }

    public String findAllByDate(int d, int m, int y) {
        String findDate = tr.stream() //iterating through the elements
                .filter(current -> current.getDay() == d && current.getMonth() == m && current.getYear() == y)// filtering based on the condition
                .map(Entry::getEntry)
                .collect(Collectors.joining());//collecting the elements as a string based on the filter condition

        return findDate.equals("") ? "Entry doesn't exist" : findDate;
    }

    public String findAllByName(String name) {

        String allName = tr.stream()
                .filter(current -> current.getName().equalsIgnoreCase(name))
                .map(Entry::getEntry)
                .collect(Collectors.joining());//returning a String based on the condition in the filter

        return allName.equals("") ? "No entries for the name given" : allName;//  if the result are not matching
    }


    // Count the number of entries
    public int getNumberOfEntries() {
        return tr.size();
    }
    public void clearAllEntries() {
        tr.clear();
    }

    // Clear all entries
   


}
// TrainingRecord/
////////////////////////////////duplicate ////////////////////////////////

//        for (Entry current : tr) {
//            /*checking if and the date matches for a new entry coming */
//            if (current.getName().equalsIgnoreCase(e.getName()) &&
//                    current.getMonth() == (e.getMonth()) &&
//                    current.getYear() == (e.getYear()) &&
//                    current.getDay() == (e.getDay())
//                    && current.getClass() == e.getClass()) {
//                return true;
//            }
//        }
//        return false;
//
//        return tr.stream().anyMatch(current -> current.getName().equalsIgnoreCase(e.getName()) &&
//                current.getMonth() == (e.getMonth()) &&
//                current.getYear() == (e.getYear()) &&
//                current.getDay() == (e.getDay())
//                && current.getClass() == e.getClass());//returning a boolean based on the conditions
/////////////////////////////////////////////////////////////////////////////////////
//////////////////////lookupentry/////////////////////////
//        ListIterator<Entry> iter = tr.listIterator();
//
//        String result = "No entries found";
//        while (iter.hasNext()) {
//            Entry current = iter.next();
//            if (current.getDay() == d && current.getMonth() == m && current.getYear() == y)
//                result = current.getEntry();
//        }
/////////////////////////////////////////////////////////////

////////////////////////remove////////////////////////

//        ListIterator<Entry> iter = tr.listIterator();
//        String result = "";
//
//        for (Entry current : tr) {
//
//            current = iter.next();
//            if (current.getName().equalsIgnoreCase(name)
//                    && current.getDay() == d
//                    && current.getMonth() == m
//                    && current.getYear() == y) {
//
//                iter.remove(); //removing the element at which the if statement is true
//                current = iter.next();//skipping to the next element
//            }
//            result = result.concat(current.getEntry());//displaying only the remaining at the result
//        }
///////////////////////////////////////////////////////////////////


//////find all by date////////////////////////////

//        ListIterator<Entry> iter = tr.listIterator();
//        String result = "";
//        for (Entry current : tr) {
//            current = iter.next();
//            if (current.getDay() == d
//                    && current.getMonth() == m
//                    && current.getYear() == y) {
//                result = result.concat(current.getEntry()); //adding the new entry to the result message when the if statement is true
//            }
//       return result }
/////////////////////////////////////////////////////////////////////////

//*findallbyname
//        ListIterator<Entry> iter = tr.listIterator();
//        String result = "";
//        for (Entry current : tr) {
//            current = iter.next();
//            if (current.getName().equalsIgnoreCase(name)) {
//                result = result.concat(current.getEntry());//displaying the message for all the entries with the same name
//            }
//        }