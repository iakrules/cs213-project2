/**
 * COMMENT
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class Resident extends Student{
    private int scholarship;
    private static final int TUITIONS = 12536;
    private static final int PT = 404;
    private static final double RATIO = 0.8;
    private static final int LIMIT = 12;
    private static final int FEES = 3268;
    private static final int MAXCRED = 16;
    private static final int MAXCREDITS = 24;
    private static final int MINCRED = 3;

    /**
     * Resident constructor
     * @param profile profile of resident student
     */
    public Resident(Profile profile) {
        super(profile);
        this.scholarship = 0;
    }

    /**
     * Constructor for Resident student using profile, major, and credits.
     *
     * @param profile inputted profile of the resident student as a Profile.
     * @param major inputted major of the resident student as a Major.
     * @param credit inputted credits of the resident student as an integer.
     */
    public Resident(Profile profile, Major major, int credit) {
        super(profile, major, credit);
        this.scholarship = 0;
    }
    /**
     * calculates the tuition that is due for a Resident based on credits enrolled
     * @param creditsEnrolled credits that the student is enrolled in
     * @return tuition that the student must pay
     */
    public double tuitionDue(int creditsEnrolled){
        double tuition;
        if(creditsEnrolled >= LIMIT){
            if(creditsEnrolled <= MAXCRED){
                tuition = TUITIONS + FEES;
            } else {
                tuition = TUITIONS + FEES + (creditsEnrolled-MAXCRED)*PT;
            }
        } else {
            tuition = PT*creditsEnrolled + FEES*RATIO;
        }
        return tuition;
    }

    /**
     * returns whether the student is a resident
     * @return boolean based on whether the student is a resident
     */
    public boolean isResident(){
        return true;
    }

    /**
     * Setter method to adjust the scholarship values
     * @param scholarships int value of the scholarship value to be added
     */
    public void setScholarship(int scholarships){
        this.scholarship += scholarships;
    }

    /**
     * Returns a string name to represent a resident
     * @return String representing the Resident object
     */
    public String printObjectName() {
        return "(Resident)";
    }

    /**
     * returns a string representing all the information for a Resident.
     * @return a string representing all the information for a Resident.
     */
    @Override
    public String toString(){
        String res = this.profile.toString();
        res += " (" + this.getMajor().code + " " + this.getMajor().maj + " " + this.getMajor().school + ") credits completed: " + this.getCreditCompleted() + " " +this.getStand() + this.printObjectName() + "(resident)";
        return res;
    }

    /**
     * Checks whether the credit amounts are valid for the resident
     * @param creditsEnrolled credits enrolled for student input.
     * @return boolean representing whether the credit amount is valid
     */
    public boolean isValid(int creditsEnrolled){
        if(creditsEnrolled >= MINCRED && creditsEnrolled <= MAXCREDITS){
            return true;
        }
        return false;
    }



}