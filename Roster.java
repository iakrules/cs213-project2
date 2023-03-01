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
            this.roster[this.size] = student;
            this.size += 1;
            return true;
        }
        return false;
    }

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

    public boolean change(Profile profile, Major maj) {
        return true;
    }

    public boolean contains(Student student) {
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i].getProfile().equals(student.getProfile())) {
                return true;
            }
        }
        return false;
        // checks to see if the array contains the given student
    }

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
        System.out.println("* Student roster sorted by School, major **");
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.roster[i].toString());
        }
    } // print roster sorted by school major

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
                    if(this.roster[j].getStanding().equals((order[stand]))){
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
                    stand += 1;
                }
            }
        }
    }
    public Student getStud(int i){
        return this.roster[i];
    }

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
    public void giveScholarship(Profile prof, int scholarship){
        Resident stud = new Resident(prof);
        int pos = this.find(stud);
        if(this.roster[pos].isResident()){
            Resident res = (Resident)this.roster[pos];
            res.setScholarship(scholarship);
            this.roster[pos] = res;
        }
    }

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

    public int fProfile(Profile profile) {
        for (int index = 0; index < size; index++) {
            if (profile.equals(roster[index].getProfile())) {
                return index;
            }
        }
        return -1;
    }

    public int getSize() {
        return this.size;
    }

}