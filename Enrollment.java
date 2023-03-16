/**
 * Defines the data and operations used for all student enrollments.
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    /**
     * Initializes EnrollStudent.
     */
    public Enrollment() {
        this.enrollStudents = new EnrollStudent[1];
        this.size = 0;
    }

    /**
     * Finds and returns student enrollment as an integer.
     *
     * @param enrollStudents inputted students to be found.
     * @return placement of student if it matches searched student, -1 otherwise.
     */
    private int find(EnrollStudent enrollStudents) {
        for (int i = 0; i < this.size; i++) {
            if (this.enrollStudents[i].getProfile().equals(enrollStudents.getProfile())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Grows the main array by 4.
     */
    private void grow() {
        // grow main array by 4
        EnrollStudent[] newProf = new EnrollStudent[this.enrollStudents.length + 4];
        for (int i = 0; i < this.size; i++) {
            newProf[i] = this.enrollStudents[i];
        }
        this.enrollStudents = newProf;
        // make a new array with a size greater by 4 and add everything to it (O(n))
    }

    /**
     * Enrolls student into the end of array given date of birth is valid and student is older than 16.
     *
     * @param enrollStudent student to be enrolled into end of array.
     */
    public void add(EnrollStudent enrollStudent) {
        // add student to end of array
        if (!enrollStudent.getProfile().getdob().isValid()) {
            return;
        }
        if (contains(enrollStudent)) {
            return;
        }

        if (this.size == this.enrollStudents.length) {
            grow();
        }
        if (enrollStudent.getProfile().getdob().checkSixteen()) {
            this.enrollStudents[this.size] = enrollStudent;
            this.size += 1;
        }
        return;
    } // add to the end of array
    // move the last one in the array to replace the deleting index position

    /**
     * Removes student from the array given the student is enrolled.
     *
     * @param enrollStudent student to be removed from array.
     */
    public void remove(EnrollStudent enrollStudent) {
        int pos = this.find(enrollStudent);
        if (pos == -1) {
            System.out.println("Student not in Enrollment!");
            return;
        }
        if(pos == this.size-1){
            this.enrollStudents[pos] = null;
            this.size -= 1;
            return;
        }
        this.enrollStudents[pos] = this.enrollStudents[this.size-1];
        this.enrollStudents[this.size-1] = null;
        this.size -= 1;
    }

    /**
     * Checks if student is enrolled for removal.
     *
     * @param profile profile of student to be checked in roster.
     * @return true if student is enrolled, false otherwise.
     */
    public boolean isEnrolled(Profile profile) {
        for (int i = 0; i < size; i++) {
            if (enrollStudents[i].getProfile().equals(profile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds profile from given input to matched profile.
     *
     * @param profile profile inputted for location.
     * @return i as location of profile if found, -1 if not found.
     */
    public int fProfile(Profile profile) {
        for (int i = 0; i < size; i++) {
            if (enrollStudents[i].getProfile().equals(profile)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get method for enrolled students.
     *
     * @param i as position of enrolled student.
     * @return i as location of enrolled student.
     */
    public EnrollStudent getEnrolledStudents(int i) {
        return enrollStudents[i];
    }

    /**
     * Checks if roster contains enrolled student.
     *
     * @param enrollStudent student to be checked for enrollment in the roster.
     * @return true if student is contained in roster, false otherwise.
     */
    public boolean contains(EnrollStudent enrollStudent) {
        for (int i = 0; i < this.size; i++) {
            if (this.enrollStudents[i].equals(enrollStudent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints enrolled students as a string.
     */
   public void print() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.enrollStudents[i].toString());
        }
    } // print the array as is without sorting

    /**
     * Get method for size of roster.
     * @return size as an integer.
     */
    public int getSize(){
        return this.size;
    }
}