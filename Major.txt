/**
 * Contains all majors with their corresponding class codes and schools.
 * Values are called using the following format:
 * "Major.<SUBJECT>.<CODE/SCHOOL>".
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public enum Major {
    CS("01:198", "SAS"),
    MATH("01:640", "SAS"),
    EE("14:332", "SOE"),
    ITI("04:332", "SC&I"),
    BAIT("33:136", "RBS");

    public final String code;
    public final String school;

    Major(String code, String school) {
        this.code = code;
        this.school = school;
    }
}