/**
 * Defines the profile of a student.
 * Creates first and last names, date of birth of student.
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob; // use the Date class described in (f)

    /**
     * Constuctor for Profile class that creates an object with given parameters.
     *
     * @param lname last name of the student as a string.
     * @param fname first name of the student as a string.
     * @param dob   date of birth of the student as a date.
     */
    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * Public get method for date of birth variable.
     *
     * @return date of birth as a date.
     */
    public Date getdob() {
        return this.dob;
    }

    /**
     * Returns string equivalent of the profile.
     *
     * @return profile in a string with the following format: "[first name] [last
     *         name] [dob]".
     */
    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob.toString();
    }

    /**
     * Compares object profile with the given profile.
     *
     * @param obj String with inputted profile that is being compared.
     * @return true of both object profiles are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile input = (Profile) obj;
            if (this.lname.equals(input.lname) && this.fname.equals(input.fname)
                    && this.dob.compareTo(input.dob) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares two object profiles to each other.
     *
     * @param newProfile the object to be compared.
     * @return 1 if the profile is "greater", -1 if the profile is "lesser" to
     *         compared profile
     */
    @Override
    public int compareTo(Profile newProfile) {
        if (this.lname != newProfile.lname) {
            return 1;
        } else if (this.lname == newProfile.lname) {
            if (this.fname != newProfile.fname) {
                return 1;
            } else if (this.fname == newProfile.fname) {
                if (this.dob != newProfile.dob) {
                    return 1;
                } else if (this.dob == newProfile.dob) {
                    return 0; // profiles are same
                }
            }
        }
        return -1;
    }
}