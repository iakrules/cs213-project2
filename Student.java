/**
 * Defines the data and operations used for all students.
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public abstract class Student implements Comparable<Student> {

    private Profile profile;

    /**
     * Constructor for student object using given parameters.
     *
     * @param profile profile of the student as a profile.
     */
    public Student(Profile profile) {
        this.profile = profile;
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
     * Returns string equivalent of the student.
     *
     * @return
     */
    @Override
    public String toString() {
        String res = this.profile.toString();
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
            if (this.profile.equals(inStudent.profile)) {
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