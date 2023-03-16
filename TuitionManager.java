import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Contains the main running methods of the tuition manager
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */

public class TuitionManager {
    private static final int maxScholarship = 10000;
    private static final int minScholarship = 0;

    /**
     * Method to run the tuition manager
     */
    public void run() {
        try {
            System.out.println("Tuition Manager running...");
            Roster fin = new Roster();
            Enrollment enroll = new Enrollment();
            File texts = new File("Project2TestCases.txt");
            Scanner scan = new Scanner(System.in);
            while (true) {
                String wow = scan.nextLine();
                wow = strips(wow);
                String[] elements = wow.split(" ");
                if (elements[0].equals("AR")) {
                    if(elements.length != 6){
                        System.out.println("Missing data in line command.");
                        continue;
                    }
                    Resident ptr = (Resident)makeStud(elements[1], elements[2], elements[3], elements[4], elements[5], "R");
                    if(ptr == null){
                        continue;
                    }
                    if (fin.contains(ptr)) {
                        System.out.println(ptr.getProfile().toString() + " is already in the roster.");
                        continue;
                    }
                    boolean isAdded = fin.add(ptr);
                    if (isAdded) {
                        System.out.println(ptr.getProfile().toString() + " added to the roster");
                    }
                } else if (elements[0].equals("AN")){
                    if(elements.length != 6){
                        System.out.println("Missing data in line command.");
                        continue;
                    }
                    NonResident ptr = (NonResident)makeStud(elements[1], elements[2], elements[3], elements[4], elements[5], "N");
                    if(ptr == null){
                        continue;
                    }
                    if (fin.contains(ptr)) {
                        System.out.println(ptr.getProfile().toString() + " is already in the roster.");
                        continue;
                    }
                    boolean isAdded = fin.add(ptr);
                    if(isAdded){
                        System.out.println(ptr.getProfile().toString() + " added to the roster");
                    }
                }else if (elements[0].equals("AT")){
                    if(elements.length < 6){
                        System.out.println("Missing data in line command");
                        continue;
                    }
                    if(elements.length == 6){
                        System.out.println("Missing the state code");
                        continue;
                    }
                    TriState ptr = (TriState)makeStud(elements[1], elements[2], elements[3], elements[4], elements[5], "T");
                    if(ptr == null){
                        continue;
                    }
                    if(elements[6].toUpperCase().equals("NY")){
                        ptr.setState("NY");
                    } else if (elements[6].toUpperCase().equals("CT")){
                        ptr.setState("CT");
                    } else {
                        System.out.println(elements[6] + ": Invalid state code.");
                        continue;
                    }
                    if (fin.contains(ptr)) {
                        System.out.println(ptr.getProfile().toString() + " is already in the roster.");
                        continue;
                    }
                    boolean isAdded = fin.add(ptr);
                    if(isAdded){
                        System.out.println(ptr.getProfile().toString() + " added to the roster");
                    }
                } else if (elements[0].equals("AI")){
                    if(elements.length != 7 && elements.length != 6){
                        System.out.println("Missing data in line command.");
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
                    if (fin.contains(ptr)) {
                        System.out.println(ptr.getProfile().toString() + " is already in the roster.");
                        continue;
                    }
                    boolean isAdded = fin.add(ptr);
                    if(isAdded){
                        System.out.println(ptr.getProfile().toString() + " added to the roster");
                    }
                }else if (elements[0].equals("D")) {
                    String fname = elements[1];
                    String lname = elements[2];
                    Date dob = new Date(elements[3]);

                    Profile nProfile = new Profile(lname, fname, dob);
                    if (!enroll.isEnrolled(nProfile)) {
                        System.out.println(nProfile.toString() + " is not enrolled");
                        continue;
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
                    fin.printByStanding();
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

                    Major majo = creator(elements[4].toUpperCase());
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
                    System.out.println("Tuition Manager Terminated");
                    break;
                } else if (elements[0].equals("S")) {
                    Date dob;
                    String fName;
                    String lName;
                    int scholarship;
                    try {
                        fName = elements[1];
                        lName = elements[2];
                        dob = new Date(elements[3]);
                        Profile student_profile = new Profile(lName, fName, dob);
                        if (!enroll.isEnrolled(student_profile)) {
                            System.out.println(student_profile.toString() + " is not in the roster");
                            continue;
                        }
                        scholarship = Integer.parseInt(elements[4]);
                        if (scholarship > maxScholarship || scholarship <= minScholarship) {
                            System.out.println(scholarship + ": invalid amount");
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Missing data in command line");
                        continue;
                    } catch (NumberFormatException e) {
                        System.out.println("Amount is not an integer.");
                        continue;
                    }

                    Profile nProfile = new Profile(elements[2], elements[1], new Date(elements[3]));
                    int rosterIndex = fin.fProfile(nProfile);
                    int enrollIndex = enroll.fProfile(nProfile);
                    if (!fin.getStud(rosterIndex).isResident()) {
                        System.out.println(
                                nProfile.toString() + "( Non-Resident) is not eligible for the scholarship.");
                        continue;
                    }
                    if (enroll.getEnrolledStudents(enrollIndex).isPartTimeStudent()) {
                        System.out.println(nProfile.toString() + " part time student "
                                + "is not eligible for the scholarship");
                        continue;
                    }
                    Resident res = (Resident) fin.getStud(rosterIndex);
                    res.setScholarship(Integer.parseInt(elements[4]));
                    fin.setStudent(rosterIndex, res);
                    System.out.println(nProfile.toString() + ": scholarship amount updated");

                }  else if (elements[0].equals("E")) {
                    int numberOfCredits = 0;
                    String fname = elements[1];
                    String lname = elements[2];
                    Date dob = new Date(elements[3]);
                    Profile userProfile = new Profile(lname, fname, dob);
                    try {
                        numberOfCredits = Integer.parseInt(elements[4]);
                        int index = fin.fProfile(userProfile);
                        if(index == -1) { System.out.println("Cannot enroll: " + userProfile.toString() + " is not in the roster"); continue; }
                        Student userStudent = fin.getStud(index);
                        if (userStudent instanceof International) {
                            International internationalStudent = (International) userStudent;
                            if (!internationalStudent.isValid(numberOfCredits)) {
                                if (internationalStudent.isStudyAbroad()) {
                                    System.out.println(internationalStudent.printInternationalStudyAbroad() + " " + numberOfCredits +  ": invalid credit hours");
                                    continue;
                                }
                                System.out.println(userStudent.printObjectName() + " " + numberOfCredits + ": invalid credit hours"); continue; }
                        } else {
                            if (!userStudent.isValid(numberOfCredits)) { System.out.println(userStudent.printObjectName() + " " + numberOfCredits + ": invalid credit hours"); continue; }
                        }
                    } catch (NumberFormatException ex) { System.out.println("Credits enrolled is not an integer."); continue; }
                    catch (ArrayIndexOutOfBoundsException a) { System.out.println("Missing data in command line."); continue; }
                    Profile newProfile = new Profile(lname, fname, dob);
                    EnrollStudent nStudent = new EnrollStudent(newProfile, numberOfCredits);
                    int indexInEnroll = enroll.fProfile(newProfile);

                    if(indexInEnroll != -1) {
                        enroll.getEnrolledStudents(indexInEnroll).setCreditCompleted(numberOfCredits);
                        System.out.println(newProfile.toString() + " enrolled " + numberOfCredits + " credits");
                        continue;
                    }
                    enroll.add(nStudent);
                    System.out.println(newProfile.toString() + " enrolled " + numberOfCredits + " credits");
                }else if(elements[0].equals("PE")){
                    if(enroll.getSize() == 0){
                        System.out.println("No Students currently enrolled");
                    }
                    enroll.print();
                }else if(elements[0].equals("PT")){
                    if(fin.getSize() == 0){
                        System.out.println("Student roster is empty!");
                    }
                    for(int i = 0; i < enroll.getSize(); i++){
                        Profile prof = enroll.getEnrolledStudents(i).getProfile();
                        int cred = enroll.getEnrolledStudents(i).returnCredits();
                        for(int j = 0; j < fin.getSize(); j++){
                            if(fin.getStud(j).getProfile().equals(prof)){
                                double tuition = fin.getStud(j).tuitionDue(cred);
                                System.out.println(fin.getStud(j) + "Credits Enrolled:"+enroll.getEnrolledStudents(i).returnCredits() +" Tuition = " + tuition);
                            }
                        }
                    }
                    System.out.println("* end of tuition due *");
                } else if (elements[0].equals("LS")){
                    File mext = new File(elements[1]);
                    Scanner scanner = new Scanner(mext);
                    while(true){
                        String wows = scanner.nextLine();
                        String[] element = wows.split(",");
                        if(element[0].equals("R")){
                            if(element.length != 6){
                                continue;
                            }
                            Resident ptr = (Resident)makeStud(element[1], element[2], element[3], element[4], element[5], "R");
                            if(ptr == null){
                                continue;
                            }
                            boolean isAdded = fin.add(ptr);
                        } else if (element[0].equals("I")){
                            if(element.length != 7 && element.length != 6){
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
                                    continue;
                                    //System.out.println("not a valid boolean");
                                }
                            }
                            boolean isAdded = fin.add(ptr);
                        } else if (element[0].equals("T")){
                            if(element.length != 7){
                                continue;
                            }
                            TriState ptr = (TriState)makeStud(element[1], element[2], element[3], element[4], element[5], "T");
                            if(ptr == null){
                                continue;
                            }
                            if(element[6].equals("NY")){

                            } else if (element[6].equals("CT")){
                                ptr.setState("CT");
                            } else {
                                continue;
                            }
                            boolean isAdded = fin.add(ptr);
//                            if(isAdded){
//                                System.out.println(ptr.getProfile().toString() + " added to the roster");
//                            }
                        } else if (element[0].equals("N")){
                            NonResident ptr = (NonResident)makeStud(element[1], element[2], element[3], element[4], element[5], "N");
                            if(ptr == null){
                                continue;
                            }
                            boolean isAdded = fin.add(ptr);
                        }
                        if(!scanner.hasNextLine()){
                            System.out.println("Students loaded to the roster.");
                            break;
                        }
                    }
                } else if (elements[0].equals("SE")) {
                    System.out.println("*** list of students eligible for graduation ***");
                    int creditsToGraduate = 120;
                    for (int i = 0; i < enroll.getSize(); i++) {
                        Profile nProfile = enroll.getEnrolledStudents(i).getProfile();
                        int creditsEnrolled = enroll.getEnrolledStudents(i).returnCredits();
                        int index = fin.fProfile(nProfile);
                        int creditsCompleted = fin.getStud(index).getCreditCompleted();
                        creditsCompleted = creditsCompleted + creditsEnrolled;
                        fin.getStud(index).setCreditCompleted(creditsCompleted);
                        if (creditsCompleted >= creditsToGraduate) {
                            System.out.println(fin.getStud(index).toString());
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

    /**
     *Creates a major object from a string value
     * @param maj string value of major
     * @return major object based on the string
     */
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
     * strips any extra spaces from the input
     * @param init string to strip spaces from
     * @return new stripped string
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

    /**
     * makes a new student based on the input elements while checking for Student validity
     * @param element1 first name element
     * @param element2 Last name element
     * @param element3 Date element
     * @param element4 Major element
     * @param element5 Credits element
     * @param code Type of student element
     * @return new Student object containing the values given
     */
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