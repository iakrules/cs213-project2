import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    private String inDate;

    private static final int JAN = 1;
    private static final int FEB = 2;
    private static final int MAR = 3;
    private static final int MAY = 5;
    private static final int JUL = 7;
    private static final int AUG = 8;
    private static final int OCT = 10;
    private static final int DEC = 12;

    private static final int MINY = 1923;
    private static final int MAXY = 2023;
    private static final int MINDINM = 1;
    private static final int MAXDINM = 31;
    private static final int DINM = 30;
    private static final int DINLM = 29;

    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;

    public Date() {
        Calendar today = Calendar.getInstance();
        this.year = today.get(Calendar.YEAR);
        this.month = ((today.get(Calendar.MONTH)) + 1); // months begin at 0, add 1 to adjust
        this.day = today.get(Calendar.DAY_OF_MONTH);
    }

    public Date(String date) {
        String inDate[] = date.split("/", 3); // limits split to 3
        this.month = Integer.parseInt(inDate[0]);
        this.day = Integer.parseInt(inDate[1]);
        this.year = Integer.parseInt(inDate[2]);
        this.inDate = date;
    }

    public boolean isValid() {
        boolean validBounds = this.withinBounds(); // uses method to check if date is within bounds
        boolean validDay = this.beforeToday(); // uses method to check if date is before today
        boolean validDinM = this.daysInMonth(); // uses method to check if date has correct amount of days for month

        if (validBounds && validDay && validDinM)
            return true;
        else
            return false;
    }

    private boolean withinBounds() {
        if (this.year < MINY || this.year > MAXY) {
            return false;
        }
        if (this.month > DEC || this.month < JAN) {
            return false;
        }
        if (this.day > MAXDINM || this.day < MINDINM) {
            return false;
        }
        return true;
    }

    private boolean beforeToday() {
        Date today = new Date();
        if (this.year == today.year) {
            if (this.month > today.month) {
                return false;
            } else if (this.month == today.month) {
                if (this.day > today.day) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean daysInMonth() {
        if (this.day == MAXDINM) {
            if (this.month != JAN && this.month != MAR && this.month != MAY && this.month != JUL && this.month != AUG
                    && this.month != OCT && this.month != DEC) {
                return false;
            }
        }
        if (this.day == DINM) {
            if (this.month == FEB) {
                return false;
            }
        }
        if (this.day == DINLM && this.month == FEB) {
            return this.leapCheck(); // uses leapCheck to check if year is a leap year
        }
        return true;
    }

    private boolean leapCheck() {
        if (this.year % QUADRENNIAL == 0) {
            if (this.year % CENTENNIAL == 0) {
                if (this.year % QUATERCENTENNIAL == 0) {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public boolean checkSixteen() {
        int validAge = 16;
        Date today = new Date();
        today.year = today.year - validAge;

        if (this.compareTo(today) <= 0) // if difference is equal to 16 years or less, return true
            return true;
        return false;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    @Override
    public String toString() {
        String MandD = "";
        MandD += this.month + "/" + this.day + "/" + this.year;
        return MandD;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;
            boolean newMonth = date.month == this.month;
            boolean newDay = date.day == this.day;
            boolean newYear = date.year == this.year;
            return (newMonth && newDay && newYear); // returns matching date.
        }
        return false;
    }

    @Override
    public int compareTo(Date newDate) {
        if (this.year > newDate.year) {
            return 1;
        } else if (this.year == newDate.year) {
            if (this.month > newDate.month) {
                return 1;
            } else if (this.month == newDate.month) {
                if (this.day > newDate.day) {
                    return 1;
                } else if (this.day == newDate.day) {
                    return 0;
                }
            }
        }
        return -1; // returns before date
    }

    public static void main(String[] args) {
        // invalid dates
        String[] invalid = new String[] { "2/29/2003", "4/31" + "/2003", "3/32/2003", "-1/31/2003", "5/18/2030",
                "9/24/2002", "2/29/2004" };
        for (int i = 0; i < invalid.length; i++) {
            Date dob = new Date(invalid[i]);
            if (!dob.isValid()) { // checks valid date of birth
                System.out.println("DOB invalid: " + dob + " not a valid calendar " + "date!");
            } else if (!dob.checkSixteen()) { // checks if over 16 years old
                System.out.println("DOB invalid: " + dob + " younger than 16 years old.");
            } else {
                System.out.println(dob + " is a valid date.");
            }
        }
    }
}