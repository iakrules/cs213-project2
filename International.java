public class International extends NonResident{
    private boolean isStudyAbroad;
    private static final int HEALTH = 2650;
    private static final int TUITIONS = 29737;
    private static final int FEES = 3268;
    private static final int PT =966;
    private static final int MAXCRED = 16;
    public International(Profile profile) {
        super(profile);
        this.isStudyAbroad = true;
    }//must have at least 12 credit hours, max is 21 credits
    // if over 16 credits, pay those credit amounts as a part time student
    //other students cannot have less than 3 credits
    //if is study abroad, the max is instead 12 credits and they pay no tuition, only fees
    public International(Profile profile, Major major, int credit) {
        super(profile, major, credit);
        this.isStudyAbroad = true;
    }
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
    public boolean isResident(){
        return false;
    }
    public void setStudyAbroad(boolean study){
        this.isStudyAbroad = study;
    }
    public boolean isStudyAbroad(){
        return this.isStudyAbroad;
    }

}