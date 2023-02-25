public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    public Profile getProfile() {
        return this.profile;
    }
    public int returnCredits(){
        return this.creditsEnrolled;
    }
    public String toString(){
        return this.profile.toString() + ": credits enrolled: " + this.creditsEnrolled;
    }
}