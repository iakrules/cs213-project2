public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public Enrollment() {
        this.enrollStudents = new EnrollStudent[1];
        this.size = 0;
    }

    private int find(EnrollStudent enrollStudents) {
        for (int i = 0; i < this.size; i++) {
            if (this.enrollStudents[i].getProfile().equals(enrollStudents.getProfile())) {
                return i;
            }
        }
        return -1;
    }

    private void grow() {
        // grow main array by 4
        EnrollStudent[] newProf = new EnrollStudent[this.enrollStudents.length + 4];
        for (int i = 0; i < this.size; i++) {
            newProf[i] = this.enrollStudents[i];
        }
        this.enrollStudents = newProf;
        // make a new array with a size greater by 4 and add everything to it (O(n))
    }

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
            System.out.println("Successful addition");
            this.enrollStudents[this.size] = enrollStudent;
            this.size += 1;
        }
        return;
    } // add to the end of array
      // move the last one in the array to replace the deleting index position

    public void remove(EnrollStudent enrollStudent) {
        int pos = this.find(enrollStudent);
        if (pos == -1) {
            System.out.println("Student not in Roster!");
            return;
        }

        for (int i = pos; i < this.size - 1; i++) {
            this.enrollStudents[i] = this.enrollStudents[i + 1];
        }
        this.size -= 1;
        return;
    }

    public boolean contains(EnrollStudent enrollStudent) {
        for (int i = 0; i < this.size; i++) {
            if (this.enrollStudents[i].equals(enrollStudent)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        System.out.println("size of roster =" + this.size);
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.enrollStudents[i].toString());
        }
    } // print the array as is without sorting
}