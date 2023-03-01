public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    public Profile getProfile() {
        return this.profile;
    }
    public int returnCredits(){
        return this.creditsEnrolled;
    }

    public boolean isPartTimeStudent() {
        return returnCredits() < 12;
    }
    @Override
    public boolean equals(Object obj) {
        EnrollStudent other = (EnrollStudent) obj;
        if (this.profile.compareTo(other.profile) == 0 && this.creditsEnrolled == other.creditsEnrolled) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.profile.toString() + ": credits enrolled: " + this.creditsEnrolled;
    }
}