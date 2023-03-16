import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InternationalTest {

    @Test
    public void test_tuitionDue_case1() {
        Date date1 = new Date ("9/24/2002");
        Profile profile1 = new Profile("Last", "First", date1);
        Major major1 = Major.CS;
        int creditsCompleted1 = 12;
        International international1 = new International(profile1, major1, creditsCompleted1);
        international1.setStudyAbroad(true);

        assertTrue(international1.tuitionDue(international1.getCreditCompleted()) == 5918);
    }

    @Test
    public void test_tuitionDue_case2() {
        Date date2 = new Date ("9/24/2002");
        Profile profile2 = new Profile("Last", "First", date2);
        Major major2 = Major.CS;
        int creditsCompleted2 = 9;
        International international2 = new International(profile2, major2, creditsCompleted2);
        international2.setStudyAbroad(true);

        assertFalse(international2.tuitionDue(international2.getCreditCompleted()) == 1000);
    }
}