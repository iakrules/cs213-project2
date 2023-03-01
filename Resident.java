public class Resident extends Student{
    private int scholarship;
    private static final int TUITIONS = 12536;
    private static final int PT = 404;
    private static final double RATIO = 0.8;
    private static final int LIMIT = 12;
    private static final int FEES = 3268;
    private static final int MAXCRED = 16;

    public Resident(Profile profile) {
        super(profile);
        this.scholarship = 0;
    }
    public Resident(Profile profile, Major major, int credit) {
        super(profile, major, credit);
        this.scholarship = 0;
    }

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
    public boolean isResident(){
        return true;
    }
    public int getScholarship(){
        return this.scholarship;
    }
    public void setScholarship(int scholarships){
        this.scholarship += scholarships;
    }



}