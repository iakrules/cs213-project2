/**
 * First, a single, very descriptive sentence describing the class.
 * Then, additional lines of description to elaborate the details if necessary.
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class Roster {
    private Student[] roster;
    private int size;

    // CHECK THIS placeholder, incomplete
    int test;

    public Roster() {
        this.roster = new Student[1];
        this.size = 0;
    }

    private int find(Student student) {
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i].getProfile().equals(student.getProfile())) {
                return i;
            }
        }
        // search given student in roster
        return -1;
    }

    private void grow() {
        // grow main array by 4
        Student[] newProf = new Student[this.roster.length + 4];
        for (int i = 0; i < this.size; i++) {
            newProf[i] = this.roster[i];
        }
        this.roster = newProf;
        // make a new array with a size greater by 4 and add everything to it (O(n))
    }

    public boolean add(Student student) {
        // add student to end of array
        if (!student.getProfile().getdob().isValid() || student.getCreditCompleted() < 0) {
            return false;
        }
        if (contains(student)) {
            return false;
        }

        if (this.size == this.roster.length) {
            grow();
        }
        if (student.getProfile().getdob().checkSixteen()) {
            System.out.println("Successful addition");
            this.roster[this.size] = student;
            this.size += 1;
        }
        return false;
        // return res;
        // return false;
    }

    public boolean remove(Student student) {
        int pos = this.find(student);
        if (pos == -1) {
            System.out.println("Student not in Roster!");
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

    public boolean change(Profile profile, Major maj) {
        return true;
    }

    public boolean contains(Student student) {
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i].equals(student)) {
                return true;
            }
        }
        return false;
        // checks to see if the array contains the given student
    }

    public void print() {
        System.out.println("size of roster =" + this.size);
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.roster[i].toString());
        }
        // print roster sorted by profiles
    }

    public void printBySchoolMajor() {

    } // print roster sorted by school major

    public void printByStanding() {
        // print roster sorted by standing
    }

    public boolean changeMaj(Profile prof, Major maj) {
        Student stud = new Student(prof);
        int pos = this.find(stud);
        if (pos != -1) {
            System.out.println(this.roster[pos].toString());
        }
        if (pos == -1) {
            System.out.println("Changemaj fail: student not in array");
            return false;
        } else {
            this.roster[pos].setMajor(maj);
            System.out.println(this.roster[pos].toString());
            return true;
        }
    }

    public int getSize() {
        return this.size;
    }
}