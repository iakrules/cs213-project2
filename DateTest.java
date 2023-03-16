import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateTest {

    @Test
    public void test_isValid_case1() {
        Date date1 = new Date("2/29/2003");
        assertFalse(date1.isValid());
    }
    @Test
    public void test_isValid_case2() {
        Date date2 = new Date("4/31" + "/2003");
        assertFalse(date2.isValid());
    }

    @Test
    public void test_isValid_case3() {
        Date date3 = new Date("3/32/2003");
        assertFalse(date3.isValid());
    }

    @Test
    public void test_isValid_case4() {
        Date date4 = new Date("-1/31/2003");
        assertFalse(date4.isValid());
    }

    @Test
    public void test_isValid_case5() {
        Date date5 = new Date("5/18/2030");
        assertFalse(date5.isValid());
    }

    @Test
    public void test_isValid_case6() {
        Date date6 = new Date("9/24/2002");
        assertTrue(date6.isValid());
    }

    @Test
    public void test_isValid_case7() {
        Date date7 = new Date("2/29/2004");
        assertTrue(date7.isValid());
    }
}