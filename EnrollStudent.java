/**
 * COMMENT
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;
    public EnrollStudent(Profile profile, int creditsEnrolled) {
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }
    public Profile getProfile() {
        return this.profile;
    }
    public int returnCredits(){
        return this.creditsEnrolled;
    }
    @Override
    public boolean equals(Object obj) {
        EnrollStudent other = (EnrollStudent) obj;
        if (this.profile.compareTo(other.profile) == 0 && this.creditsEnrolled == other.creditsEnrolled) {
            return true;
        }
        return false;
    }
    public boolean isPartTimeStudent() {
        return returnCredits() < 12;
    }
    public String toString(){
        return this.profile.toString() + ": credits enrolled: " + this.creditsEnrolled;
    }
    public void setCreditCompleted(int creditsEnrolled) {
        this.creditsEnrolled = creditsEnrolled;
    }



}