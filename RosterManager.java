import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * First, a single, very descriptive sentence describing the class.
 * Then, additional lines of description to elaborate the details if necessary.
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class RosterManager {

    // CHECK THIS incomplete
    public void run() {
        try {
            System.out.println("Roster Manager running...");

            Date today = new Date();
            Roster fin = new Roster();
            File texts = new File("Project1TestCases.txt");
            Scanner scan = new Scanner(texts);
            int count = 1;
            while (true) {
                String wow = scan.nextLine();
                wow = strips(wow);
                String[] elements = wow.split(" ");
                if (elements[0].equals("A")) {
                    // use add method
                    Date newDate = new Date(elements[3]);
                    Profile prof = new Profile(elements[2], elements[1], newDate);
                    Major majors = creator(elements[4]);
                    // System.out.println("ELEMENTS" + elements[5]);
                    for (int j = 0; j < elements.length; j++) {
                        // System.out.println(elements[j]);
                    }
                    boolean isBad = false;
                    for (int i = 0; i < elements[5].length(); i++) {
                        if (Character.isAlphabetic(elements[5].charAt(i))) {
                            isBad = true;
                            break;
                        }
                    }
                    if (isBad) {
                        continue;
                    }
                    Student ptr = new Student(prof, majors, Integer.parseInt(elements[5]));
                    // 0: add 1: First name 2: Last 3: DOB 4: Major 5: Credits
                    // System.out.println(ptr.toString());
                    fin.add(ptr);
                    System.out.println(ptr.getProfile().toString() + " added to the roster");
                    // fin.print();
                } else if (elements[0].equals("P")) {

                } else if (elements[0].equals("PS")) {

                } else if (elements[0].equals("PC")) {

                } else if (elements[0].equals("R")) {
                    // 0: add 1: First name 2: Last 3: DOB
                    if (fin.getSize() <= 0) {
                        System.out.println("Student roster is empty!");
                    }
                    Date place = new Date(elements[3]);
                    Profile profs = new Profile(elements[2], elements[1], place);
                    Student stud = new Student(profs);
                    fin.remove(stud);
                } else if (elements[0].equals("C")) {
                    // 0: C 1: First 2: Last 3: DOB 4: Major

                    Major majo = creator(elements[4]);
                    if (majo == null) {
                        System.out.println("invalid major");
                        continue;
                    }
                    Date place = new Date(elements[3]);
                    Profile profs = new Profile(elements[2], elements[1], place);
                    fin.changeMaj(profs, majo);
                } else if (elements[0].equals("L")) {

                } else if (elements[0].equals("Q")) {
                    System.out.println("Roster Manager Terminated");
                    break;
                } else {
                    System.out.println(elements[0] + " is an invalid command!");
                }
            }
        } catch (FileNotFoundException e) {

        }
    }

    private Major creator(String maj) {
        Major majo = null;
        if (maj.equals("CS")) {
            majo = Major.CS;
        } else if (maj.equals("EE")) {
            majo = Major.EE;
        } else if (maj.equals("MATH")) {
            // ITI BAIT
            majo = Major.MATH;
        } else if (maj.equals("ITI")) {
            majo = Major.ITI;
        } else {
            majo = Major.BAIT;
        }
        return majo;
    }

    private String strips(String init) {

        boolean isSpace = false;
        int i = 0;
        while (i < init.length()) {
            if (init.charAt(i) != ' ') {
                isSpace = false;
                i++;
            } else if (init.charAt(i) == ' ' && isSpace) {
                if (i == init.length() - 1) {
                    init = init.substring(0, i);
                    break;
                } else {
                    init = init.substring(0, i) + init.substring(i + 1);
                }
            } else if (init.charAt(i) == ' ' && !isSpace) {
                isSpace = true;
                i++;
            }
        }
        return init;
    }

}