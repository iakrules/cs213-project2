public class Resident extends Student{
    private int scholarship;
    public Resident(Profile profile) {
        super(profile);
        this.scholarship = 0;
    }
    public Resident(Profile profile, Major major, int credit) {
        super(profile, major, credit);
        this.scholarship = 0;
    }

    public double tuitionDue(int tuition){
        return 0;
    }
    public boolean isResident(){
        return false;
    }



}