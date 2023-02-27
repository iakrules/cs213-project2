public class Resident extends Student{
    private int scholarship;
    private static final int FTTuition = 12536;
    private static final int PTTuition = 404;
    private static final double ratio = 0.8;
    private static final int limit = 12;
    private static final int health = 3268;

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
        if(creditsEnrolled >= limit){
            tuition = FTTuition + health;
        } else {
            tuition = PTTuition*creditsEnrolled + health*ratio;
        }
        return tuition;
    }
    public boolean isResident(){
        return true;
    }
    public int getScholarship(){
        return this.scholarship;
    }



}