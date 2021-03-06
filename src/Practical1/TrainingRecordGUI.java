// GUI and main program for the Training Record
package Practical1;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(15);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField repetition = new JTextField(2);
    private JTextField recovery = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField where = new JTextField(5);
    private JTextField terrain = new JTextField(4);
    private JTextField tempo = new JTextField(2);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labrepetition = new JLabel(" Repetition:");//
    private JLabel labrecovery = new JLabel(" Recovery:");//
    private JLabel labwhere = new JLabel(" Where:");//
    private JLabel labtempo = new JLabel(" Tempo:");//
    private JLabel labterrain = new JLabel(" Terrain:");//
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton add_sprint = new JButton("Add Sprint");//
    private JButton add_swim = new JButton("Add swim");//
    private JButton add_cycle = new JButton("Add cycle");//
    private JButton lookUpByDate = new JButton("Look Up");//
    private JButton findAllByDate = new JButton("Find By Date");//added the button
    private JButton remove = new JButton("Remove element");//
    private JButton findAllByName = new JButton("Find By Name");//
    private JButton weeklyDistance = new JButton("Weekly distance");//added the button
    private final String array[] = new String[]{"Select: ", "Swim", "Cycle", "Sprint"};//
    private JComboBox comboBox = new JComboBox(array);//


    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(20, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();


    } // main

    // set up the GUI
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(comboBox);
        comboBox.addActionListener(this);
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(labwhere);//
        add(where);//the where button

        add(labrecovery);//
        add(recovery);//recovery button

        add(labrepetition);
        add(repetition);//added the repetititon button

        add(labterrain);
        add(terrain);//added the terrain method

        add(labtempo);
        add(tempo);//added the tempo method
        add_swim.addActionListener(this);//enable the action listener for add swim button
        add_cycle.addActionListener(this);//enable the action listener for add cycle button
        add_sprint.addActionListener(this);//enable the action listener for add sprint button
        add(remove);
        remove.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(outputArea);
        add(findAllByDate);
        findAllByDate.addActionListener(this);
        add(findAllByName);
        findAllByName.addActionListener(this);
        add(weeklyDistance);//last button
        weeklyDistance.addActionListener(this);//last button
        outputArea.setEditable(false);
        setSize(1000, 500);
        setVisible(true);
        blankDisplay();

    }

    public void actionPerformed(ActionEvent event) {
        String message = "";


        if (event.getSource() == comboBox) {

            comboBox.removeItem("Select: ");
            switch (comboBox.getSelectedIndex()) {
                case 0:  // if swim is selected , change the button and add disable the fields

                    add(add_swim);
                    remove(add_sprint);
                    remove(add_cycle);
                    where.setEditable(true);
                    repetition.setEditable(false);
                    tempo.setEditable(false);
                    terrain.setEditable(false);
                    recovery.setEditable(false);
                    break;
                case 1: //if cycle is selected , change the button and add disable the fields
                    add(add_cycle);
                    remove(add_swim);
                    remove(add_sprint);
                    terrain.setEditable(true);
                    tempo.setEditable(true);
                    recovery.setEditable(false);
                    repetition.setEditable(false);
                    where.setEditable(false);
                    break;
                case 2: //if sprint is selected , change the button and add disable the fields
                    add(add_sprint);
                    remove(add_swim);
                    remove(add_cycle);
                    recovery.setEditable(true);
                    repetition.setEditable(true);
                    where.setEditable(false);
                    terrain.setEditable(false);
                    tempo.setEditable(false);
                    break;
                default:
                    break;

            }
        }
        if (event.getSource() == add_swim) {
            message = addEntrySwim("swimming"); //button addSwim
        }
        if (event.getSource() == add_cycle) {
            message = addEntryCycle("cycling");//button addCycle
        }
        if (event.getSource() == add_sprint) {
            message = addEntrySprint("running"); //button addSprint
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == findAllByDate) {
            message = findAllByDate();
        }
        if (event.getSource() == remove) {
            message = remove();
        }
        if (event.getSource() == findAllByName) {
            message = findAllByName();
        }
        if (event.getSource() == weeklyDistance) {
            message = "still not ready :)";
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntrySwim(String what) {
        String message = "Record added\n";
        System.out.println("Adding " + what + " entry to the records");

        try {
            String n = name.getText();
            if (n.isEmpty()) {
                return "Name can not be empty";

            }
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            float km = java.lang.Float.parseFloat(dist.getText());
            int h = Integer.parseInt(hours.getText());
            int mm = Integer.parseInt(mins.getText());
            int s = Integer.parseInt(secs.getText());

            String where1 = where.getText();

            SwimEntry swim = new SwimEntry(n, d, m, y, h, mm, s, km, where1);
            if (myAthletes.duplicate(swim)) {
                return "Cannot add the same athlete twice"; //checking for duplicate before adding

            }
            if (myAthletes.dateValidation(d, m, y)) {
                return "Date is not valid"; //validating the date before adding
            }

            myAthletes.addEntry(swim);

        } catch (IllegalArgumentException ex) {
            return "One of the boxes not filled or incorrect input \n" +
                    "Record could not be added".toUpperCase();

        }


        return message;
    }


    public String addEntrySprint(String what) {

        String message = "Record added\n";
        System.out.println("Adding " + what + " entry to the records");

        try {

            String n = name.getText();
            if (n.isEmpty()) {
                return "Name can not be empty";

            }

            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            float km = java.lang.Float.parseFloat(dist.getText());
            int h = Integer.parseInt(hours.getText());
            int mm = Integer.parseInt(mins.getText());
            int s = Integer.parseInt(secs.getText());
            int rep = Integer.parseInt(repetition.getText());
            int rec = Integer.parseInt(recovery.getText());


            SprintEntry sprint = new SprintEntry(n, d, m, y, h, mm, s, km, rep, rec);
            if (myAthletes.duplicate(sprint)) {
                return "Cannot add the same athlete twice";//stopping from adding the record with that athlete in it

            }
            if (myAthletes.dateValidation(d, m, y)) {
                return "Date is not valid";  //validating the date before adding
            }

            myAthletes.addEntry(sprint);

        } catch (IllegalArgumentException ex) {
            message = "One of the boxes not filled or incorrect input \n" +
                    "Record could not be added".toUpperCase();
            return message;
        }
        return message;
    }

    public String addEntryCycle(String what) {

        String message = "Record added\n";
        System.out.println("Adding " + what + " entry to the records");

        try {
            String n = name.getText();
            if (n.isEmpty()) {
                return "Name can not be empty";//handling the case where the name is not given

            }
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            float km = java.lang.Float.parseFloat(dist.getText());
            int h = Integer.parseInt(hours.getText());
            int mm = Integer.parseInt(mins.getText());
            int s = Integer.parseInt(secs.getText());
            String terr = terrain.getText();
            String temp = tempo.getText();

            CycleEntry cycleEntry = new CycleEntry(n, d, m, y, h, mm, s, km, terr, temp);
            if (myAthletes.duplicate(cycleEntry)) {
                return "Cannot add the same athlete twice";//validation for the name
            }
            if (myAthletes.dateValidation(d, m, y)) {
                return "Date is not valid";//validation for the date
            }
            myAthletes.addEntry(cycleEntry);//if everything is correct add the record


        } catch (IllegalArgumentException ex) {
            return "One of the boxes not filled or incorrect input \n" +
                    "Record could not be added".toUpperCase();

        }


        return message;
    }

    public String lookupEntry() {
        try {
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            String result = myAthletes.lookupEntry(d, m, y);
            return result;

        } catch (NumberFormatException ex) {
            return "No entries found for display";
        }
    }

    //        public boolean validation(String name, int d,int m,int y){
//        if (findAllByName()==val)
//        }
    public String findAllByDate() {

        try {

            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            String res = myAthletes.findAllByDate(d, m, y);
            return res;

        } catch (NumberFormatException ex) {
            return "No entries found for display";//handling the case of no entries
        }

    }

    public String findAllByName() {
        String n = name.getText();
        outputArea.setText("looking up record ...");

        String message = myAthletes.findAllByName(n);

        return message;
    }

    public String remove() {
        try {

            String n = name.getText();
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            outputArea.setText("The entries are removed ...");
            String message = myAthletes.remove(n, d, m, y);
            return message;
        } catch (IllegalArgumentException ex) {
            return "No entries to remove"; //handling the case of no entries to remove
        }

    }
//    public String weeklyDistance() {
//        String n = name.getText();
//        outputArea.setText("looking up record ...");
//
//        String message = myAthletes.weeklyDistance(n);
//
//        return message;
//    }

    public void blankDisplay() {

        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        repetition.setText("");//set to blank
        where.setText("");//set to blank
        recovery.setText("");//set to blank
        terrain.setText("");//set to blank
        tempo.setText("");//set to blank

    }// blankDisplay

    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

