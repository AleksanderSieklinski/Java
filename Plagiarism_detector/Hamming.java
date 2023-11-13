package Plagiarism_detector;

// This class is responsible for comparing two strings and returning the number of differences between them.

public class Hamming {
    int check_char(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                count++;
        }
        return count;
    }

    int compare(String s1, String s2) {
        int count = 0;
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            throw new IllegalArgumentException("Strings cannot be null");
        }
        if (s1.length() != s2.length()) {
            count += Math.abs(s1.length() - s2.length());
            if (s1.length() > s2.length()) {
                count += check_char(s2,s1);
            }
            else {
                count += check_char(s1,s2);
            }
        }
        else {
            count += check_char(s1, s2);
        }
        return count;
    }
    public static void main(String[] args) {
        Hamming h = new Hamming();
        try {
            h.compare(null,"abc");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(h.compare("abc","abc"));
        System.out.println(h.compare("abc","abcd"));
        System.out.println(h.compare("abc","ab"));
        System.out.println(h.compare("ab c","abc"));
        System.out.println(h.compare("abc   d","abcd"));
    }
}
