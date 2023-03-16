/**
 * Defines the data and operations used for all students.
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
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
    /**
     * Constructor for student object using given parameters.
     *
     * @param profile profile of the student as a profile.
     * @param major major of the student as a major.
     * @param credit credits taken of the student as an int.
     */
    public Student(Profile profile, Major major, int credit) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = credit;
    }

    /**
     * Checks if Student is valid.
     * @param creditEnrolled credits enrolled for student input.
     * @return true if student is valid, false otherwise.
     */
    public boolean isValid(int creditEnrolled) {
        return false;
    } // polymorphism

    /**
     * Returns string equivalent of the student.
     *
     * @return string equivalent of the student.
     */
    @Override
    public String toString() {
        String res = this.profile.toString();
        res += " (" + this.getMajor().code + " " + this.getMajor().maj + " " + this.getMajor().school + ") credits completed: " + this.getCreditCompleted() + "Standing: " +this.getStanding();
        return res;
        //System.out.println("Roy Brooks 8/8/1999 (01:640 MATH SAS) credits completed: 111 (Senior)");
    }

    /**
     * Compares object student with the given student.
     *
     * @param obj String with inputted student that is being compared.
     * @return true of both object students are equal, false otherwise.
     */
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

    /**
     * Gets the standing of the student by class.
     * @return standing of the student in a string format.
     */
    public String getStand(){
        if(this.getStanding().equals("SEN")){
            return "(Senior)";
        } else if(this.getStanding().equals("JUN")){
            return "(Junior)";
        } else if (this.getStanding().equals("SOPH")){
            return "(Sophomore)";
        } else {
            return "(Freshman)";
        }
    }

    /**
     * Public set method for credits completed.
     * @param credit credits completed input.
     */
    public void setCreditCompleted(int credit){
        this.creditCompleted = credit;
    }

    /**
     * Public get method for profile.
     *
     * @return profile as a Profile.
     */
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
    public String printObjectName() {
        return "(Student)";
    }

    /**
     * Public method to check if major is valid.
     *
     * @param majo object used to check if given major is valid.
     * @return true if major is valid, false otherwise.
     */
    public boolean validMaj(Major majo) {
        //String wow = maj.maj;
        if (majo.maj.equals("CS") || majo.maj.equals("BAIT") || majo.maj.equals("ITI") || majo.maj.equals("EE") || majo.maj.equals("MATH")) {
            return true;
        }
        return false;
    }

    /**
     * Public get method for major.
     *
     * @return major as a Major.
     */
    public Major getMajor(){
        return this.major;
    }

    /**
     * Public get method for standing.
     *
     * @return standing as a String.
     */
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