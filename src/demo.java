import Date.Date;
public class demo {
    public static void main(String[] args) {
        Date date0 = new Date(29, 8, 2022);

        Date date1 = new Date(1, 1, 2077);
        System.out.println(date1.getDayOfWeek() + "\n");

        Date date2 = new Date(25, 9, 2077);
        System.out.println(date2.getDayOfWeek() + "\n");

        System.out.println(date2.getDate("j-Y-j"));
        System.out.println(date0.dateDifference(date1));
    }
}