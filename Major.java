/**
 * Contains all majors with their corresponding class codes and schools.
 * Values are called using the following format:
 * "Major.<SUBJECT>.<CODE/SCHOOL>".
 *
 * @author Ishaan Keswani, Akhil Thalasila
 */
public enum Major {
    CS("01:198", "SAS", "CS"),
    MATH("01:640", "SAS", "MATH"),
    EE("14:332", "SOE", "EE"),
    ITI("04:332", "SC&I", "ITI"),
    BAIT("33:136", "RBS", "BAIT");

    public final String code;
    public final String school;

    public final String maj;

    Major(String code, String school, String maj) {
        this.code = code;
        this.school = school;
        this.maj = maj;
    }
}