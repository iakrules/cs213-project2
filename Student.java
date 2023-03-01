public abstract class Student implements Comparable<Student> {

    protected Profile profile;
    private Major major;
    private int creditCompleted;
    public abstract double tuitionDue(int creditsEnrolled); // polymorphism
    private static final int fresh = 30;
    private static final int soph = 60;
    private static final int jun = 90;

    public abstract boolean isResident(); // polymorphism

    public Student(){

    }
    public Student(Profile profile) {
        this.profile = profile;
    }
    public Student(Profile profile, Major major, int credit) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = credit;
    }
    public boolean isValid(int creditEnrolled) {
        return false;
    } // polymorphism

    @Override
    public String toString() {
        String res = this.profile.toString();
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student inStudent = (Student) obj;
            if (this.profile.equals(inStudent.profile)) {
                return true;
            }
        }
        return false;
    }
    public Profile getProfile(){
        return this.profile;
    }
    public int compareTo(Student newStudent) {
        return profile.compareTo(newStudent.getProfile());
    }
    /**
     * Public get method for credits completed.
     *
     * @return credits completed as an int.
     */
    public int getCreditCompleted() {
        return this.creditCompleted;
    }

    /**
     * Public method to set major to given major.
     *
     * @return true if major is valid major, false otherwise.
     */
    public boolean setMajor(Major maj) {
        if (validMaj(maj)) {
            this.major = maj;
            return true;
        }
        return false;
    }

    /**
     * Public method to check if major is valid.
     *
     * @param maj object used to check if given major is valid.
     * @return true if major is valid, false otherwise.
     */
    public boolean validMaj(Major maj) {
        if (maj.equals("CS") || maj.equals("BAIT") || maj.equals("ITI") || maj.equals("EE") || maj.equals("MATH")) {
            return true;
        }
        System.out.println("invalid major");
        return false;
    }
    public Major getMajor(){
        return this.major;
    }
    public String getStanding(){
        if(this.creditCompleted < fresh){
            return "FRESH";
        } else if (this.creditCompleted < soph){
            return "SOPH";
        } else if (this.creditCompleted < jun){
            return "JUN";
        } else {
            return "SEN";
        }
    }
}