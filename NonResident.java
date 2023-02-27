public class NonResident extends Student{
    private static final int FTTuition = 29737;
    private static final int PTTuition = 966;
    private static final double ratio = 0.8;
    private static final int limit = 12;
    private static final int health = 3268;
    public NonResident(){

    }

    public NonResident(Profile profile) {
        super(profile);
    }
    public NonResident(Profile profile, Major major, int credit) {
        super(profile, major, credit);
    }
    public double tuitionDue(int creditsEnrolled){
        double tuition;
        if(creditsEnrolled >= limit){
            tuition = FTTuition + health;
        } else {
            tuition = PTTuition*creditsEnrolled + health*ratio;
        }
        return tuition;
    }
    public boolean isResident(){
        return false;
    }
}
