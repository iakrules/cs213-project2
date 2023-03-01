public class TriState extends NonResident{
    private static final int TUITIONS = 29737;
    private static final int PT = 966;
    private static final double RATIO = 0.8;
    private static final int LIMIT = 12;
    private static final int FEES = 3268;
    private static final int MAXCRED = 16;
    private static final int NY = 4000;
    private static final int CT = 5000;
//4000 discount for New York 5000 for connecticut tristate
    private String state;
    public TriState(Profile profile) {
        super(profile);
        this.state = "NY";
    }
    public TriState(Profile profile, Major major, int credit) {
        super(profile, major, credit);
        this.state = "NY";
    }
    public double tuitionDue(int creditsEnrolled){
        double tuition = 0;
        if(creditsEnrolled >= 12){
            if(creditsEnrolled <= 16){
                tuition = TUITIONS + FEES;
            } else {
                tuition = TUITIONS + FEES + PT*(creditsEnrolled-MAXCRED);
            }

        } else {
            tuition = PT*creditsEnrolled + FEES*RATIO;
        }
        if(this.state.equals("NY")){
            tuition -= NY;
        } else {
            tuition -= CT;
        }
        return tuition;
    }
    public boolean isResident(){
        return false;
    }
    public void setState(String val){
        this.state = val;
    }

}
