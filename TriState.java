public class TriState extends NonResident{
    private static final int FTTuition = 29737;
    private static final int PTTuition = 966;
    private static final double ratio = 0.8;
    private static final int limit = 12;
    private static final int health = 3268;

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
            tuition = 29737 + 3268;
        } else {
            tuition = 966*creditsEnrolled + 3268*0.8;
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
