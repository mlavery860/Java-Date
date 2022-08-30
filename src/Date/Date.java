package Date;

/**
 * 
 */
public class Date {

    /**
     * 
     */
    public static final class DateUtilMethods {
        private static final int DAYSINMONTH[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        /**
         * 
         * @param month
         * @return
         */
        public static boolean isValidMonth(int month) {
            if (month < 1 || month > 12)
                return false;
            return true;
        }

        /**
         * 
         * @param day
         * @param month
         * @param year
         * @return
         */
        public static boolean isValidDayForMonth(int day, int month, int year) {
            if (day <= 0)
                return false;
            if (day < 1 || day > 31)
                return false;
            if (month == 2 && isLeapYear(year))
                return day <= 29;
            return DAYSINMONTH[month] >= day;
        }

        /**
         * 
         * @param year
         * @return
         */
        public static boolean isLeapYear(int year) {
            if (!isWholeNumber(year / 4.0))
                return false;
            if (!isWholeNumber(year / 100.0))
                return true;
            if (isWholeNumber(year / 400.0))
                return true;
            return false;
        }

        /**
         * 
         * @param month
         * @param year
         * @return
         */
        public static int getNumberOfDaysInMonth(int month, int year) {
            if (month != 1)
                return DAYSINMONTH[month];
            if (isLeapYear(year))
                return DAYSINMONTH[1] + 1;
            return DAYSINMONTH[1];
        }

        /**
         * Used to identify if a double is a whole number or not.
         * 
         * @param num the number being checked
         * @return true if whole number, else false
         */
        private static boolean isWholeNumber(double num) {
            return num - (int) num == 0 ? true : false;
        }

        /**
         * Converts the parsed integer to it's ordinal counterpart.
         * 
         * @param num the number to be converted to ordinal.
         * @return the parsed values ordinal counterpart
         */
        public static String getOrdinal(int num) {
            String ordinals[] = { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
            return num + ordinals[num % 10];
        }

        /**
         * Simple function to check if a number requires
         * 
         * @param num
         * @return
         */
        public static String getLeadingZeroString(int num, int expectedLength) {
            String leadingZeros = "";
            for (int i = 10; i <= Math.pow(i, expectedLength); i *= 10) {
                if (num < i) {
                    leadingZeros.concat("0");
                }
            }
            return leadingZeros;
        }

        /**
         * 
         * @param num
         * @return
         */
        public static boolean needsLeadingZero(int num) {
            return num < 10;
        }

    }

    private static String days[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

    public int day;
    public int month;
    public String monthName;
    public int year;

    /**
     * 
     */
    public Date() {
        this.day = 1;
        this.month = 1;
        this.year = 1999;
    }

    /**
     * 
     * @param day
     * @param month
     * @param year
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * 
     * @return
     */
    public String getDate() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DateUtilMethods.getLeadingZeroString(this.day, 2));
        stringBuilder.append(this.day);
        stringBuilder.append("-");
        stringBuilder.append(DateUtilMethods.getLeadingZeroString(this.month, 2));
        stringBuilder.append(this.month);
        stringBuilder.append("-");
        stringBuilder.append(DateUtilMethods.getLeadingZeroString(this.year, 4));
        stringBuilder.append(this.year);
        return stringBuilder.toString();
    }

    /**
     * d - The day of the month (from 01 to 31)
     * D - A textual representation of a day (three letters)
     * j - The day of the month without leading zeros (1 to 31)
     * l (lowercase 'L') - A full textual representation of a day
     * N - The ISO-8601 numeric representation of a day (1 for Monday, 7 for Sunday)
     * S - The day of the month with an ordinal suffix (e.g. 1st, 22nd, 23rd, 14th)
     * w - Numeric representation of the day (0 is Monday, 6 for Sunday)
     * z - The day of the year (from 0 through 365)
     * F - A full textual representation of a month (January through December)
     * m - A numeric representation of a month (from 01 to 12)
     * M - A short textual representation of a month (three letters)
     * n - A numeric representation of a month, without leading zeros (1 to 12)
     * t - The number of days in the given month
     * L - Whether it's a leap year (1 if it is a leap year, 0 otherwise)
     * o - The ISO-8601 year number
     * Y - A four digit representation of a year
     * y - A two digit representation of a year
     * 
     * @param format
     * @return
     */
    public String getDate(String format) {
        return format.replace("d", DateUtilMethods.needsLeadingZero(this.day) ? "0" + this.day : "" + this.day) //
                .replace("D", this.getDayOfWeek().substring(0, 3)) //
                .replace("j", Integer.toString(this.day))
                .replace("l", this.getDayOfWeek())
                .replace("N", this.getDayOfWeek())
                .replace("S", DateUtilMethods.getOrdinal(this.day))
                .replace("w", Integer.toString(this.getDayOfWeekIndex()))
                .replace("Y", Integer.toString(this.year))
                .replace("n", Integer.toString(this.month));
    }

    /**
     * 
     * @param check
     * @return
     */
    public int dateDifference(Date check) {
        int checkYear = check.year;
        int checkMonth = check.month;
        int checkDay = check.day;

        if (this.year > checkYear) {
            return -1;
        }
        if (this.year == checkYear
                && ((this.month > checkMonth) || (this.month == checkMonth && this.day >= checkDay))) {
            return -1;
        }

        int totalDaysBetween = 0;

        if (this.year == checkYear) {
            for (int month = this.month + 1; month < checkMonth; month++) {
                totalDaysBetween += DateUtilMethods.getNumberOfDaysInMonth(month, this.year);
            }
        } else {
            for (int month = this.month; month < 12; month++) {
                totalDaysBetween += DateUtilMethods.getNumberOfDaysInMonth(month, this.year);
            }
            for (int month = 0; month < checkMonth - 1; month++) {
                totalDaysBetween += DateUtilMethods.getNumberOfDaysInMonth(month, checkYear);
            }
        }
        if (this.month != checkMonth) {
            totalDaysBetween += DateUtilMethods.getNumberOfDaysInMonth(this.month - 1, this.year) - this.day;
            totalDaysBetween += checkDay;
        } else {
            totalDaysBetween += this.day > checkDay ? this.day - checkDay : checkDay - this.day;
        }

        for (int year = this.year + 1; year < checkYear; year++) {
            totalDaysBetween += DateUtilMethods.isLeapYear(year) ? 366 : 365;
        }
        return totalDaysBetween;
    }

    /**
     * 
     * @return
     */
    public int getDayOfWeekIndex() {
        Date begin = new Date(25, 7, 2022);
        int dayIndex = 0;
        int daysBetween = 0;

        // If this date after 2022 or the date is in 2022 but after 25/07
        // Else all other cases
        if (this.year > 2022 || (this.year == 2022 && ((this.month > 7) || (this.month == 7 && this.day >= 25)))) {
            daysBetween = begin.dateDifference(this);
            dayIndex = daysBetween % 7;
        } else {
            daysBetween = this.dateDifference(begin);
            dayIndex = 7 - (daysBetween % 7);
        }
        return dayIndex;
    }

    /**
     * 
     * @return
     */
    public String getDayOfWeek() {
        int dayIndex = this.getDayOfWeekIndex();
        return days[dayIndex];
    }

    public int getDayOfYear() {
        int dayOfYear = 0;
        for (int i = 1; i < this.month; i++)
            dayOfYear += DateUtilMethods.getNumberOfDaysInMonth(i - 1, this.year);
        dayOfYear += this.day;
        return dayOfYear;
    }

}