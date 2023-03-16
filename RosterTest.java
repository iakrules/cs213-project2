import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RosterTest {

    @Test
    public void test_add_case1() {
        Roster roster1 = new Roster();

        Date date1 = new Date ("9/24/2002");
        Profile profile1 = new Profile("Last", "First", date1);
        Major major1 = Major.CS;
        int creditsCompleted1 = 12;
        International international1 = new International(profile1, major1, creditsCompleted1);
        international1.setStudyAbroad(true);

        assertTrue(roster1.add(international1));
    }

    @Test
    public void test_add_case2() {
        Roster roster2 = new Roster();

        Date date2 = new Date ("9/24/2002");
        Profile profile2 = new Profile("Last", "First", date2);
        Major major2 = Major.CS;
        int creditsCompleted1 = -2;
        International international2 = new International(profile2, major2, creditsCompleted1);
        international2.setStudyAbroad(true);

        assertFalse(roster2.add(international2));
    }

    @Test
    public void test_add_case3() {
        Roster roster3 = new Roster();

        Date date3 = new Date ("9/24/2024");
        Profile profile3 = new Profile("Last", "First", date3);
        Major major3 = Major.CS;
        int creditsCompleted3 = 12;
        TriState triState3 = new TriState(profile3, major3, creditsCompleted3);
        triState3.setState("CT");
        assertFalse(roster3.add(triState3));
    }

    @Test
    public void test_add_case4() {
        Roster roster4 = new Roster();

        Date date4 = new Date ("9/24/2002");
        Profile profile4 = new Profile("Last", "First", date4);
        Major major4 = Major.CS;
        int creditsCompleted4 = 12;
        TriState triState4 = new TriState(profile4, major4, creditsCompleted4);

        assertTrue(roster4.add(triState4));
    }
}