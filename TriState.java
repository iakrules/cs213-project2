/**
 * Contains the information about the Tristate object extending the Nonresident class
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class TriState extends NonResident{
    private static final int TUITIONS = 29737;
    private static final int PT = 966;
    private static final double RATIO = 0.8;
    private static final int LIMIT = 12;
    private static final int FEES = 3268;
    private static final int MAXCRED = 16;
    private static final int NY = 4000;
    private static final int CT = 5000;

    private static final int MAXCREDITS = 24;
    private static final int MINCRED = 3;
    private String state;

    /**
     * Constructor for Tristate.
     *
     * @param profile as inputted profile for constructor.
     */
    public TriState(Profile profile) {
        super(profile);
        this.state = "NY";
    }

    /**
     * Constructor for Tristate.
     *
     * @param profile profile of tristate student
     * @param major major of tristate student
     * @param credit amount of credits that the tristate student has
     */
    public TriState(Profile profile, Major major, int credit) {
        super(profile, major, credit);
        this.state = "NY";
    }

    /**
     * calculates the tuition that is due based on credits enrolled
     * @param creditsEnrolled credits that the student is enrolled in
     * @return tuition that the student must pay
     */
    public double tuitionDue(int creditsEnrolled){
        double tuition = 0;
        if(creditsEnrolled >= LIMIT){
            if(creditsEnrolled <= MAXCRED){
                tuition = TUITIONS + FEES;
            } else {
                tuition = TUITIONS + FEES + PT*(creditsEnrolled-MAXCRED);
            }

        } else {
            tuition = PT*creditsEnrolled + FEES*RATIO;
        }
        if(creditsEnrolled < LIMIT){
            return tuition;
        }
        if(this.state.equals("NY")){
            tuition -= NY;
        } else {
            tuition -= CT;
        }
        return tuition;
    }

    /**
     * returns whether the student is a resident
     * @return boolean showing whether the student is a resident or not
     */
    public boolean isResident(){
        return false;
    }

    /**
     * sets the state value of the student to a certain value
     * @param val state value to set the student to
     */
    public void setState(String val){
        this.state = val;
    }

    /**
     * checks whether the student has a valid number of credits to be in the systen
     * @param creditsEnrolled credits enrolled for student input.
     * @return boolean telling if the student's credit number is valid
     */
    public boolean isValid(int creditsEnrolled){
        if(creditsEnrolled >= MINCRED && creditsEnrolled <= MAXCREDITS){
            return true;
        }
        return false;
    }

    /**
     * Method to print Tristate objects with all their information
     * @return String containing the information of the tristate object
     */
    public String toString(){
        //Roy Brooks 8/8/1999 (01:640 MATH SAS) credits completed: 111 (Senior)(non-resident)(tri-state:CT)
        String res = this.profile.toString();
        res += " (" + this.getMajor().code + " " + this.getMajor().maj + " " + this.getMajor().school + ") credits completed: " + this.getCreditCompleted() + " " +this.getStand() + this.printObjectName() + "(tri-state:" + this.state + ")";
        return res;
    }

    /**
     * returns the object name represented by the tristate object
     * @return the object name used for a tristate object
     */
    public String printObjectName() {
        return "(non-resident)";
    }
}
