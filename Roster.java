/**
 *Defines the properties of a Roster Object
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class Roster {
    private Student[] roster;
    private int size;

    // CHECK THIS placeholder, incomplete
    int test;

    /**
     * Roster default constructor
     */
    public Roster() {
        this.roster = new Student[1];
        this.size = 0;
    }

    /**
     * Finds and identifies student as Student.
     *
     * @param student as inputted student for search.
     * @return i for position of found student, -1 if student is not found.
     */
    private int find(Student student) {
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i].getProfile().equals(student.getProfile())) {
                return i;
            }
        }
        // search given student in roster
        return -1;
    }

    /**
     * Grows the length of the Roster object whenever the number of elements in the roster is equal to the array capacity
     */
    private void grow() {
        // grow main array by 4
        Student[] newProf = new Student[this.roster.length + 4];
        for (int i = 0; i < this.size; i++) {
            newProf[i] = this.roster[i];
        }
        this.roster = newProf;
        // make a new array with a size greater by 4 and add everything to it (O(n))
    }

    /**
     * Takes in a student and adds it to the roster, given that it isn't already in the array, under 16, or has an invalid credit amount.
     * @param student the student to add
     * @return boolean showing whether the addition was successful or not
     */
    public boolean add(Student student) {
        // add student to end of array
        if (!student.getProfile().getdob().isValid() || student.getCreditCompleted() < 3) {
            return false;
        }
        if (contains(student)) {
            return false;
        }
        if (this.size == this.roster.length) {
            grow();
        }
        if (student.getProfile().getdob().checkSixteen()) {
            this.roster[this.size] = student;
            this.size += 1;
            return true;
        }
        return false;
    }

    /**
     * Takes in a student and removes it from the array given that it is in the array
     * @param student the student to remove
     * @return boolean showing whether the removal was successful or not
     */
    public boolean remove(Student student) {
        int pos = this.find(student);
        if (pos == -1) {
            return false;
        }

        for (int i = pos; i < this.size - 1; i++) {
            this.roster[i] = this.roster[i + 1];
        }
        this.size -= 1;
        // remove given student from the array while maintaining order
        // use find method to find the int location of the student, remove it, make all
        // the others come to the previous and return
        return true;
    }

    /**
     * Shows whether the given student is in the roster or not
     * @param student the student to look for in the roster
     * @return boolean showing whether student is contained in the roster
     */
    public boolean contains(Student student) {
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i].getProfile().equals(student.getProfile())) {
                return true;
            }
        }
        return false;
        // checks to see if the array contains the given student
    }

    /**
     * prints the Student roster sorted by last name, then first name, then Date of Birth
     */
    public void print() {
        int n = this.size;
        for (int j = 1; j < n; j++) {
            Student key = this.roster[j];
            int i = j - 1;
            while ((i > -1) && (this.roster[i].compareTo(key) > 0)) {
                Student ptr = this.roster[i + 1];
                this.roster[i + 1] = this.roster[i];
                this.roster[i] = ptr;
                i--;
            }
            this.roster[i + 1] = key;
        }
        System.out.println("* Student roster sorted by last name, first name, DOB **");
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.roster[i].toString());
        }
        // print roster sorted by profiles
    }

    /**
     * Prints the roster sorted by School, then Major
     */
    public void printBySchoolMajor() {
        int n = this.size;
        for (int j = 1; j < n; j++) {
            Student key = this.roster[j];
            int i = j - 1;
            while ((i > -1) && (compareBySS(this.roster[i], key) > 0)) {
                this.roster[i + 1] = this.roster[i];
                i--;
            }
            this.roster[i + 1] = key;
        }
        System.out.println("** Student roster sorted by School, major **");
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.roster[i].toString());
        }
    } // print roster sorted by school major

    /**
     * Prints the student roster sorted by alphabetical standing(Freshman, Junior, Senior, Sophomore)
     */
    public void printByStanding() {
        int stand = 0;
        String[] order = {"FRESH", "JUN", "SEN", "SOPH"};
        boolean change = false;
        for(int i = 0; i < this.size; i++){//loops through
            if(stand == 3){
                break;
            }
            if(this.roster[i].getStanding().equals(order[stand])){
                continue;
            } else {
                for(int j = i; j < this.size; j++){
                    if(this.roster[j].getStanding().equals(order[stand])){
                        System.out.println(this.roster[j]);
                        for(int k = j; k > i; k--){
                            Student ptr = this.roster[k];
                            this.roster[k] = this.roster[k-1];
                            this.roster[k-1] = ptr;
                        }
                        if(j == this.size-1){
                            change = true;
                        }
                        break;
                    } else if (j == this.size-1){
                        change = true;
                        break;
                    }
                }
                if(change){
                    i -=1;
                    stand += 1;
                    change = false;
                }
            }
        }
        System.out.println("** Student roster sorted by standing **");
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.roster[i].toString());
        }
        System.out.println("* end of roster *");
    }

    /**
     * returns the student at the given index in the roster
     * @param i location to look for the student
     * @return the student at the given index
     */
    public Student getStud(int i){
        return this.roster[i];
    }

    /**
     * Changes the major of the given student to the given major if the major is a valid one and if the student is in the roster
     * @param prof profile of student to change major
     * @param maj major to change current major to
     * @return boolean showing whether the Major change was successful or not
     */
    public boolean changeMaj(Profile prof, Major maj) {
        Resident stud = new Resident(prof);
        int pos = this.find(stud);
        if (pos == -1) {
            return false;
        } else {
            if (this.roster[pos].getMajor().equals(maj)) {
                return false;
            }
            this.roster[pos].setMajor(maj);
            return true;
        }
    }

    /**
     * Finds the index location of the given profile
     * @param profile profile to look for
     * @return the int giving index location of the input profile
     */
    public int fProfile(Profile profile) {
        for (int index = 0; index < this.size; index++) {
            if (profile.equals(this.roster[index].getProfile())) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Takes in a profile for a student as well as a scholarship and assigns them the scholarship amount given that they
     * are residents and they are contained in the roster.
     * @param prof profile of student
     * @param scholarship scholarship amount to give
     */
    public void giveScholarship(Profile prof, int scholarship){
        Resident stud = new Resident(prof);
        int pos = this.find(stud);
        if(this.roster[pos].isResident()){
            Resident res = (Resident)this.roster[pos];
            res.setScholarship(scholarship);
            this.roster[pos] = res;
        }
    }

    /**
     * compares two students to each other based on school and then major
     * @param stud1 first student
     * @param stud2 second student to compare
     * @return 0 if student 1 and 2 are equal, -1 if student 2 is greater, 1 if student 1 is greater
     */
    public int compareBySS(Student stud1, Student stud2) {
        if (stud1.getMajor().school.equals(stud2.getMajor().school)) {
            if (stud1.getMajor().equals(stud2.getMajor())) {
                return 0;
            } else {
                char[] arr1 = stud1.getMajor().maj.toCharArray();
                char[] arr2 = stud2.getMajor().maj.toCharArray();
                if (arr2.length > arr1.length) {
                    for (int i = 0; i < arr1.length; i++) {
                        if (arr1[i] > arr2[i]) {
                            return 1;
                        } else if (arr1[i] < arr2[i]) {
                            return -1;
                        }
                    }
                    return -1;
                } else {
                    for (int i = 0; i < arr2.length; i++) {
                        if (arr1[i] > arr2[i]) {
                            return 1;
                        } else if (arr1[i] < arr2[i]) {
                            return -1;
                        }
                    }
                    return 1;
                }
            }
        } else {
            char[] arr1 = stud1.getMajor().school.toCharArray();
            char[] arr2 = stud2.getMajor().school.toCharArray();
            if (arr2.length > arr1.length) {
                for (int i = 0; i < arr1.length; i++) {
                    if (arr1[i] > arr2[i]) {
                        return 1;
                    } else if (arr1[i] < arr2[i]) {
                        return -1;
                    }
                }
                return -1;
            } else {
                for (int i = 0; i < arr2.length; i++) {
                    if (arr1[i] > arr2[i]) {
                        return 1;
                    } else if (arr1[i] < arr2[i]) {
                        return -1;
                    }
                }
                return 1;
            }
        }
    }

    /**
     * sets the student at a certain index value to the input student
     * @param pos position to input
     * @param stud student to input
     */
    public void setStudent(int pos, Student stud){
        this.roster[pos] = stud;
    }

    /**
     * Prints the students within a given school ordered by first name, last name, and date of birth
     * @param school the school of students to print
     */
    public void printUseSchool(String school) {
        int counter = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i].getMajor().school.equals(school)) {
                Student ptr = this.roster[counter];
                this.roster[counter] = this.roster[i];
                this.roster[i] = ptr;
                counter += 1;
            }
        }
        int n = counter + 1;
        for (int j = 1; j < n; j++) {
            Student key = this.roster[j];
            int i = j - 1;
            while ((i > -1) && (this.roster[i].compareTo(key) > 0)) {
                Student ptr = this.roster[i + 1];
                this.roster[i + 1] = this.roster[i];
                this.roster[i] = ptr;
                i--;
            }
            this.roster[i + 1] = key;
        }
        System.out.println("* Student roster sorted by last name, first name, DOB **");
        for (int i = 0; i < counter + 1; i++) {
            System.out.println(this.roster[i].toString());
        }

    }

    /**
     * returns the size of the Roster
     * @return size of roster
     */
    public int getSize() {
        return this.size;
    }

}