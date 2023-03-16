/**
 * COMMENT
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class NonResident extends Student{
    private static final int FTTuition = 29737;
    private static final int PTTuition = 966;
    private static final double ratio = 0.8;
    private static final int limit = 12;
    private static final int health = 3268;
    private static final int MAXCRED = 16;
    private static final int MAXSTUDY = 12;
    private static final int MAXCREDITS = 24;
    private static final int MINCRED = 3;

    /**
     * Constructor placeholder for NonResident.
     */
    public NonResident(){
    }

    /**
     * Constructor placeholder for NonResident.
     *
     * @param profile profile of inputted non-resident.
     */
    public NonResident(Profile profile) {
        super(profile);
    }

    /**
     * Constructor for NonResident student using profile, major, and credits.
     *
     * @param profile inputted profile of the non-resident student as a Profile.
     * @param major inputted major of the non-resident student as a Major.
     * @param credit inputted credits of the non-resident student as an integer.
     */
    public NonResident(Profile profile, Major major, int credit) {
        super(profile, major, credit);
    }
    public double tuitionDue(int creditsEnrolled){
        double tuition;
        if(creditsEnrolled >= limit){
            tuition = FTTuition + health;
            if(creditsEnrolled > MAXCRED){
                tuition += PTTuition*(creditsEnrolled-MAXCRED);
            }
        } else {
            tuition = PTTuition*creditsEnrolled + health*ratio;
        }
        return tuition;
    }

    /**
     * Checks if enrolled non-resident student is a valid student based on credits enrolled.
     *
     * @param creditsEnrolled credits enrolled for student input.
     * @return true if student is a valid student, false otherwise.
     */
    public boolean isValid(int creditsEnrolled){
        if(creditsEnrolled<= MAXCREDITS & creditsEnrolled >= MINCRED){
            return true;
        }
        return false;
    }

    /**
     * Prints object name Non-Resident.
     *
     * @return a string that says "(Non-Resident)".
     */
    public String printObjectName() {
        return "(Non-Resident)";
    }

    /**
     * Checks if student is a resident.
     *
     * @return false because student is non-resident and therefore is not a resident.
     */
    public boolean isResident(){
        return false;
    }

    /**
     * Converts student to a string that incorporates name, date of birth, major, credits completed, year of enrollment, and status.
     *
     * @return a string with all student information included.
     */
    @Override
    public String toString(){
        String res = this.profile.toString();
        res += " (" + this.getMajor().code + " " + this.getMajor().maj + " " + this.getMajor().school + ") credits completed: " + this.getCreditCompleted() + " " +this.getStand() + this.printObjectName() + "(non-resident)";
        return res;
        //Roy Brooks 9/9/1999 (04:547 ITI SC&I) credits completed: 100 (Senior)(non-resident)
    }
}
