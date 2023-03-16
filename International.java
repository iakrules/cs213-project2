/**
 * Defines the data and operations used for all international students.
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class International extends NonResident{
    private boolean isStudyAbroad;
    private static final int HEALTH = 2650;
    private static final int TUITIONS = 29737;
    private static final int FEES = 3268;
    private static final int PT = 966;
    private static final int MAXCRED = 16;
    private static final int MAXSTUDY = 12;
    private static final int MAXCREDITS = 24;

    /**
     * Initializer for international object using profile.
     * @param profile inputted profile for international object.
     */
    public International(Profile profile) {
        super(profile);
        this.isStudyAbroad = true;
    }//must have at least 12 credit hours, max is 21 credits
    // if over 16 credits, pay those credit amounts as a part time student
    //other students cannot have less than 3 credits
    //if is study abroad, the max is instead 12 credits and they pay no tuition, only fees

    /**
     * Constructor for international object using profile, major, and credits.
     *
     * @param profile profile of international student as a profile.
     * @param major major of international student as a major.
     * @param credit credits completed of international student as an integer.
     */
    public International(Profile profile, Major major, int credit) {
        super(profile, major, credit);
        this.isStudyAbroad = true;
    }

    /**
     * Prints International Study Abroad
     *
     * @return a string that says "(International Study Abroad)"
     */
    public String printInternationalStudyAbroad() {return "(International Study Abroad)";}

    /**
     * Checks and returns the tuition due for international students.
     *
     * @param creditsEnrolled inputted credits enrolled for international student.
     * @return the tuition due from student as a double.
     */
    public double tuitionDue(int creditsEnrolled){
        double tuition;
        if(this.isStudyAbroad){
            tuition =FEES + HEALTH;
        } else {
            if(creditsEnrolled > MAXCRED){
                tuition = TUITIONS + (creditsEnrolled - MAXCRED)*PT + HEALTH + FEES;
            } else {
                tuition = TUITIONS + HEALTH + FEES;
            }
        }
        return tuition;
    }

    /**
     * Checks if student is a resident.
     *
     * @return false, student is an international student and therefore is not a resident.
     */
    public boolean isResident(){
        return false;
    }

    /**
     * Constructor for study abroad for international student.
     * @param study inputted value for study abroad.
     */
    public void setStudyAbroad(boolean study){
        this.isStudyAbroad = study;
    }

    /**
     * Checks and returns if a student is studying abroad.
     *
     * @return true if student is studying abroad, false otherwise.
     */
    public boolean isStudyAbroad(){
        return this.isStudyAbroad;
    }

    /**
     * Checks if international student is a valid student based on if they study abroad and how many credits enrolled.
     * @param creditEnrolled credits enrolled for student input.
     * @return true if student is a valid student, false otherwise.
     */
    public boolean isValid(int creditEnrolled) {
        if(this.isStudyAbroad){
            if(creditEnrolled > 0 && creditEnrolled <= MAXSTUDY){
                return true;
            }
            return false;
        } else {
            if(creditEnrolled >= MAXSTUDY && creditEnrolled <= MAXCREDITS){
                return true;
            }
            return false;
        }
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
     * Converts student to a string that incorporates name, date of birth, major, credits completed, year of enrollment, and status.
     *
     * @return a string with all student information included.
     */
    @Override
    public String toString(){
        String res = this.profile.toString();
        res += " (" + this.getMajor().code + " " + this.getMajor().maj + " " + this.getMajor().school + ") credits completed: " + this.getCreditCompleted() + " " +this.getStand() + this.printObjectName() + "(non-resident)" + "(international)";
        return res;
        //Mary Lindsey 12/1/2001 (33:136 BAIT RBS) credits completed: 89 (Junior)(non-resident)(international)
    }

}