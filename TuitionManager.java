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
                    if(elements.length != 6){
                        System.out.println("Not enough information in AR command");
                        continue;
                    }
                    Resident ptr = (Resident)makeStud(elements[1], elements[2], elements[3], elements[4], elements[5], "R");
                    if(ptr == null){
                        continue;
                    }
                    boolean isAdded = fin.add(ptr);
                    if (isAdded) {
                        System.out.println(ptr.getProfile().toString() + " added to the roster");
                    }
                } else if (elements[0].equals("AN")){
                    //AN Leo Jones 4/21/2006 ITI 20
                    //0: command 1: First name 2: Last name 3: dob 4: major 5: Credits
                    if(elements.length != 6){
                        System.out.println("Not enough information in AR command");
                        continue;
                    }
                    NonResident ptr = (NonResident)makeStud(elements[1], elements[2], elements[3], elements[4], elements[5], "N");
                    if(ptr == null){
                        continue;
                    }
                    boolean isAdded = fin.add(ptr);
                    if(isAdded){
                        System.out.println(ptr.getProfile().toString() + " added to the roster");
                    }
                }else if (elements[0].equals("AT")){
                    //AT Emma Miller 2/28/2003 CS 15 NY
                    //0: command 1: First name 2: Last name 3: dob 4: major 5: Credits 6: State
                    if(elements.length != 7){
                        System.out.println("Not enough information in AR command");
                        continue;
                    }
                    TriState ptr = (TriState)makeStud(elements[1], elements[2], elements[3], elements[4], elements[5], "T");
                    if(ptr == null){
                        continue;
                    }
                    if(!elements[6].equals("NY")  || !elements[6].equals("NJ") || !elements[6].equals("CT")){
                        System.out.println("Not a val");
                    }
                    boolean isAdded = fin.add(ptr);
                    if(isAdded){
                        System.out.println(ptr.getProfile().toString() + " added to the roster");
                    }
                } else if (elements[0].equals("AI")){
                    //AI Oliver Chang 11/30/2000 BAIT 78 true
                    //0: command 1: First name 2: Last name 3: dob 4: major 5: Credits 6: Isstudyabroad
                    if(elements.length != 7 && elements.length != 6){
                        System.out.println("Not enough information in AI command");
                        continue;
                    }
                    International ptr = (International) makeStud(elements[1], elements[2], elements[3], elements[4], elements[5], "I");
                    if(ptr == null){
                        continue;
                    }
                    if(elements.length == 6){
                        ptr.setStudyAbroad(false);
                    } else {
                        if(elements[6].equals("true")){

                        } else if(elements[6].equals("false")){
                            ptr.setStudyAbroad(false);
                        } else{
                            System.out.println("not a valid boolean");
                        }
                    }
                    boolean isAdded = fin.add(ptr);
                    if(isAdded){
                        System.out.println(ptr.getProfile().toString() + " added to the roster");
                    }
                }else if (elements[0].equals("D")) {
                String fname = elements[1];
                String lname = elements[2];
                Date dob = new Date(elements[3]);

                Profile nProfile = new Profile(fname, lname, dob);
                if (!enroll.isEnrolled(nProfile)) {
                    System.out.println(nProfile.toString() + " is not enrolled");
                    return;
                }

                int index = enroll.fProfile(nProfile);
                enroll.remove(enroll.getEnrolledStudents(index));
                System.out.println(nProfile.toString() + " dropped");
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
                }else if(elements[0].equals("S")){
                    //give scholarship
                    //input S Roy Brooks 9/9/1999 10000
                    Date dob = new Date(elements[3]);
                    Profile prof = new Profile(elements[2], elements[1], dob);
                    fin.giveScholarship(prof, Integer.parseInt(elements[4]));
                }else if(elements[0].equals("PE")){
                    if(enroll.getSize() == 0){
                        System.out.println("No Students currently enrolled");
                    }
                    enroll.print();
                }else if(elements[0].equals("PT")){
                    for(int i = 0; i < enroll.getSize(); i++){
                        Profile prof = enroll.getEnrolledStudents(i).getProfile();
                        int cred = enroll.getEnrolledStudents(i).returnCredits();
                        for(int j = 0; j < fin.getSize(); j++){
                            if(fin.getStud(j).getProfile().equals(prof)){
                                double tuition = fin.getStud(j).tuitionDue(cred);
                                System.out.println(enroll.getEnrolledStudents(i) + " Tuition = " + tuition);
                            }
                        }
                    }
                } else if (elements[0].equals("LS")){
                    File mext = new File(elements[1]);
                    Scanner scanner = new Scanner(mext);
                    while(true){
                        String wows = scanner.nextLine();
                        String[] element = wows.split(",");
                        if(element[0].equals("R")){
                            if(element.length != 6){
                                System.out.println("Not enough information in AR command");
                                continue;
                            }
                            Resident ptr = (Resident)makeStud(element[1], element[2], element[3], element[4], element[5], "R");
                            if(ptr == null){
                                continue;
                            }
                            boolean isAdded = fin.add(ptr);
                            if(isAdded){
                                System.out.println(ptr.getProfile().toString() + " added to the roster");
                            }
                        } else if (element[0].equals("I")){
                            if(element.length != 7 && element.length != 6){
                                System.out.println("Not enough information in AI command");
                                continue;
                            }
                            International ptr = (International) makeStud(element[1], element[2], element[3], element[4], element[5], element[0]);
                            if(ptr == null){
                                continue;
                            }
                            if(element.length == 6){
                                ptr.setStudyAbroad(false);
                            } else {
                                if(element[6].equals("true")){

                                } else if(element[6].equals("false")){
                                    ptr.setStudyAbroad(false);
                                } else{
                                    System.out.println("not a valid boolean");
                                }
                            }
                            boolean isAdded = fin.add(ptr);
                            if(isAdded){
                                System.out.println(ptr.getProfile().toString() + " added to the roster");
                            }
                        } else if (element[0].equals("T")){
                            if(element.length != 7){
                                System.out.println("Not enough information in T command");
                                continue;
                            }
                            TriState ptr = (TriState)makeStud(element[1], element[2], element[3], element[4], element[5], "T");
                            if(ptr == null){
                                continue;
                            }
                            if(!element[6].equals("NY")  || !element[6].equals("NJ") || !element[6].equals("CT")){
                                System.out.println("Not a val");
                            }
                            boolean isAdded = fin.add(ptr);
                            if(isAdded){
                                System.out.println(ptr.getProfile().toString() + " added to the roster");
                            }
                        } else if (element[0].equals("N")){
                            if(element.length != 6){
                                System.out.println("Not enough information in AR command");
                                continue;
                            }
                            NonResident ptr = (NonResident)makeStud(element[1], element[2], element[3], element[4], element[5], "N");
                            if(ptr == null){
                                continue;
                            }
                            boolean isAdded = fin.add(ptr);
                            if(isAdded){
                                System.out.println(ptr.getProfile().toString() + " added to the roster");
                            }
                        }
                        if(!scanner.hasNextLine()){
                            System.out.println("Students loaded in");
                            break;
                        }
                    }
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
    private Student makeStud(String element1, String element2, String element3, String element4, String element5, String code){
        Date newDate = new Date(element3);
        if (!newDate.isValid()) {
            System.out.println("DOB invalid: " + newDate.toString() + " not a valid calendar date!");
            return null;
        }
        Profile prof = new Profile(element2, element1, newDate);
        Major majors = creator(element4);
        if (majors == null) {
            System.out.println("Major code invalid: " + element4);
            return null;
        }
        boolean isBad = false;
        for (int i = 0; i < element5.length(); i++) {
            if (Character.isAlphabetic(element5.charAt(i))) {
                isBad = true;
                break;
            }
        }
        if (isBad) {
            System.out.println("Credits completed invalid: not an integer!");
            return null;
        }
        if (Integer.parseInt(element5) < 0) {
            System.out.println("Credits completed invalid: cannot be negative!");
            return null;
        }
        Student ptr;
        if(code.equals("R")){
            ptr = new Resident(prof, majors, Integer.parseInt(element5));

        } else if (code.equals("T")){
            ptr = new TriState(prof, majors, Integer.parseInt(element5));
        } else if (code.equals("I")){
            ptr = new International(prof, majors, Integer.parseInt(element5));
        } else {
            ptr = new NonResident(prof, majors, Integer.parseInt(element5));
        }
        if (!ptr.getProfile().getdob().checkSixteen()) {
            System.out.println(
                    "DOB invalid: " + ptr.getProfile().getdob().toString() + " younger than 16 years old.");
            return null;
        }
        return ptr;
    }

}