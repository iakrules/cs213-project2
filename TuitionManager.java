import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TuitionManager {
    public void run() {
        try {
            System.out.println("Tuition Manager running...");
            Roster fin = new Roster();
            Enrollment enroll = new Enrollment();
            File texts = new File("Project2TestCases.txt");
            Scanner scan = new Scanner(texts);
            while (true) {
                String wow = scan.nextLine();
                wow = strips(wow);
                String[] elements = wow.split(" ");
                if (elements[0].equals("AR")) {
                    Date newDate = new Date(elements[3]);
                    if (!newDate.isValid()) {
                        System.out.println("DOB invalid: " + newDate.toString() + " not a valid calendar date!");
                        continue;
                    }
                    Profile prof = new Profile(elements[2], elements[1], newDate);
                    Major majors = creator(elements[4]);
                    if (majors == null) {
                        System.out.println("Major code invalid: " + elements[4]);
                        continue;
                    }
                    boolean isBad = false;
                    for (int i = 0; i < elements[5].length(); i++) {
                        if (Character.isAlphabetic(elements[5].charAt(i))) {
                            isBad = true;
                            break;
                        }
                    }
                    if (isBad) {
                        System.out.println("Credits completed invalid: not an integer!");
                        continue;
                    }
                    if (Integer.parseInt(elements[5]) < 0) {
                        System.out.println("Credits completed invalid: cannot be negative!");
                        continue;
                    }
                    Resident ptr = new Resident(prof, majors, Integer.parseInt(elements[5]));
                    if (fin.contains(ptr)) {
                        System.out.println(ptr.getProfile().toString() + " is already in the roster.");
                        continue;
                    }
                    boolean isAdded = fin.add(ptr);
                    if (!ptr.getProfile().getdob().checkSixteen()) {
                        System.out.println(
                                "DOB invalid: " + ptr.getProfile().getdob().toString() + " younger than 16 years old.");
                        continue;
                    }
                    if (isAdded) {
                        System.out.println(ptr.getProfile().toString() + " added to the roster");
                    }
                } else if (elements[0].equals("P")) {
                    if (fin.getSize() <= 0) {
                        System.out.println("Student roster is empty!");
                        continue;
                    }
                    fin.print();
                } else if (elements[0].equals("PS")) {
                    if (fin.getSize() <= 0) {
                        System.out.println("Student roster is empty!");
                        continue;
                    }
                    System.out.println("Command PS");
                } else if (elements[0].equals("PC")) {
                    if (fin.getSize() <= 0) {
                        System.out.println("Student roster is empty!");
                        continue;
                    }
                    fin.printBySchoolMajor();
                } else if (elements[0].equals("R")) {
                    if (fin.getSize() <= 0) {
                        System.out.println("Student roster is empty!");
                        continue;
                    }
                    Date place = new Date(elements[3]);
                    Profile profs = new Profile(elements[2], elements[1], place);
                    Resident stud = new Resident(profs);
                    boolean result = fin.remove(stud);
                    if (!result) {
                        System.out.println(profs.toString() + " is not in the roster.");
                    } else {
                        System.out.println(profs.toString() + " removed from the roster.");
                    }
                } else if (elements[0].equals("C")) {

                    Major majo = creator(elements[4]);
                    if (majo == null) {
                        System.out.println("Major code invalid: " + elements[4]);
                        continue;
                    }
                    Date place = new Date(elements[3]);
                    Profile profs = new Profile(elements[2], elements[1], place);
                    Resident stud = new Resident(profs);
                    if (!fin.contains(stud)) {
                        System.out.println(stud.getProfile().toString() + " is not in the roster.");
                        continue;
                    }
                    boolean worked = fin.changeMaj(profs, majo);
                    if (worked) {
                        System.out.println(stud.getProfile().toString() + " major changed to " + majo);
                    }
                } else if (elements[0].equals("L")) {
                    String schools = elements[1].toUpperCase();
                    fin.printUseSchool(schools);
                    System.out.println("Command L");
                } else if (elements[0].equals("Q")) {
                    System.out.println("Roster Manager Terminated");
                    break;
                } else if (elements[0].equals("")) {
                    continue;
                } else {
                    System.out.println(elements[0] + " is an invalid command!");
                }
            }
        } catch (FileNotFoundException e) {
        }
    }

    private Major creator(String maj) {
        Major majo = null;
        if (maj.toUpperCase().equals("CS")) {
            majo = Major.CS;
        } else if (maj.toUpperCase().equals("EE")) {
            majo = Major.EE;
        } else if (maj.toUpperCase().equals("MATH")) {
            // ITI BAIT
            majo = Major.MATH;
        } else if (maj.toUpperCase().equals("ITI")) {
            majo = Major.ITI;
        } else if (maj.toUpperCase().equals("BAIT")) {
            majo = Major.BAIT;
        }
        return majo;
    }

    /**
     *
     * @param init
     * @return
     */
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