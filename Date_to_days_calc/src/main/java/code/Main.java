package code;

import java.util.Scanner;

public class Main {
    static Boolean isValidMonth(String monthInput) {
        for (MonthOfYear month : MonthOfYear.values()) {
            if (month.name().equalsIgnoreCase(monthInput)) {
                return true;
            }
        }
        return false;
    }
    static String monthInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj miesiąc (po angielsku):");
        String monthInput = scanner.nextLine();
        while (!isValidMonth(monthInput)) {
            System.out.println("Nieprawidłowy miesiąc. Spróbuj ponownie:");
            monthInput = scanner.nextLine();
        }
        scanner.close();
        return monthInput;
    }
    static Integer dayInput(MonthOfYear month) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj dzień:");
        Integer day = scanner.nextInt();
        while (day < 1 || day > month.getNumDays()) {
            System.out.println("Nieprawidłowy dzień. Spróbuj ponownie:");
            day = scanner.nextInt();
        }
        scanner.close();
        return day;
    }
    static Integer totalDays(Integer day, MonthOfYear month) {
        Integer totalDays = day;
        for (MonthOfYear m : MonthOfYear.values()) {
            if (m == month) {
                break;
            }
            totalDays += m.getNumDays();
        }
        return totalDays;
    }
    static MonthOfYear inWhichMonth() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer dnia w roku:");
        Integer totalDays = scanner.nextInt();
        while (totalDays < 1 || totalDays > 365) {
            System.out.println("Nieprawidłowy numer dnia. Spróbuj ponownie:");
            totalDays = scanner.nextInt();
        }
        Integer days = 0;
        MonthOfYear month = null;
        for (MonthOfYear m : MonthOfYear.values()) {
            days += m.getNumDays();
            if (totalDays <= days) {
                month = m;
                break;
            }
        }
        scanner.close();
        return month;
    }
    public static void main(String[] args) {
        String monthInput = monthInput();
        MonthOfYear month = MonthOfYear.valueOf(monthInput.toUpperCase());
        Integer day = dayInput(month);
        Integer totalDays = totalDays(day, month);
        System.out.println("Numer dnia w roku: " + totalDays);
        MonthOfYear monthOfYear = inWhichMonth();
        System.out.println("Miesiąc: " + monthOfYear);
    }
}