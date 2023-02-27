public class International extends NonResident{
    private boolean isStudyAbroad;
    public International(Profile profile) {
        super(profile);
        this.isStudyAbroad = true;
    }
    public International(Profile profile, Major major, int credit) {
        super(profile, major, credit);
        this.isStudyAbroad = true;
    }

}