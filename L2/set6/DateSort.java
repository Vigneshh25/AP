package set6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DateSort {
    public static void main(String[] args) {
        List<Date> dates = new ArrayList<>();
        dates.add(new Date(15, 12, 2020));
        dates.add(new Date(10, 10, 2022));
        dates.add(new Date(20, 5, 2021));

        // sort the dates using a custom comparator
        Collections.sort(dates, new DateComparator());

        // print the sorted dates
        for (Date date : dates) {
            System.out.println(date);
        }
    }
}

class Date {
    int day;
    int month;
    int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}

class DateComparator implements Comparator<Date> {
    @Override
    public int compare(Date d1, Date d2) {
        if (d1.year != d2.year) {
            return d1.year - d2.year;
        } else if (d1.month != d2.month) {
            return d1.month - d2.month;
        } else {
            return d1.day - d2.day;
        }

    }
}

//import java.util.ArrayList;
//        import java.util.List;
//
//public class DateSort {
//    public static void main(String[] args) {
//        List<Date> dates = new ArrayList<>();
//        dates.add(new Date(15, 12, 2020));
//        dates.add(new Date(10, 10, 2022));
//        dates.add(new Date(20, 5, 2021));
//
//        // sort the dates using bubble sort
//        for (int i = 0; i < dates.size() - 1; i++) {
//            for (int j = 0; j < dates.size() - i - 1; j++) {
//                Date date1 = dates.get(j);
//                Date date2 = dates.get(j + 1);
//                if (date1.year > date2.year || (date1.year == date2.year && date1.month > date2.month) || (date1.year == date2.year && date1.month == date2.month && date1.day > date2.day)) {
//                    // swap the dates
//                    dates.set(j, date2);
//                    dates.set(j + 1, date1);
//                }
//            }
//        }
//
//        // print the sorted dates
//        for (Date date : dates) {
//            System.out.println(date);
//        }
//    }
//}
//
//class Date {
//    int day;
//    int month;
//    int year;
//
//    public Date(int day, int month, int year) {
//        this.day = day;
//        this.month = month;
//        this.year = year;
//    }
//
//    @Override
//    public String toString() {
//        return day + "/" + month + "/" + year;
//    }
//}




