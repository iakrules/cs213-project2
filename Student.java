/**
 * Defines the data and operations used for all students.
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class Student {
    private Profile profile;
    private Major major; // Major is an enum type

    private int creditCompleted;

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
     * ?????????????????????????????
     *
     * @param profile
     */
    public Student(Profile profile) {
        this.profile = profile;
        this.major = Major.CS;
        this.creditCompleted = 0;
    }

    /**
     * Public get method for profile.
     *
     * @return profile as a Profile.
     */
    public Profile getProfile() {
        return this.profile;
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

    /**
     * Returns string equivalent of the student.
     *
     * @return
     */
    @Override
    public String toString() {
        String res = this.profile.toString();
        res += this.major;
        res += String.valueOf(this.creditCompleted);
        return res;
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
            if ((this.profile.equals(inStudent.profile)) && this.major == inStudent.major) {
                return true;
            }
        }
        return false;
    }

    /**
     * ?????????????????????????????????
     *
     * @param newStudent
     * @return
     */
    public int compareTo(Student newStudent) {
        return profile.compareTo(newStudent.getProfile());
    }
}