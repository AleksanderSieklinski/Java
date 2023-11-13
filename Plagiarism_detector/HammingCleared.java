package Plagiarism_detector;

// This class is responsible for widening the scope of the comparison by removing all whitespaces and underscores from the strings.

public class HammingCleared extends Hamming {
    private String cleared(String s){
        return s.replaceAll("[ \t\n_]", "");
    }
    @Override
    int compare (String s1, String s2){
        s1 = cleared(s1);
        s2 = cleared(s2);
        return super.compare(cleared(s1),cleared(s2));
    }
    public static void main(String[] args) {
        HammingCleared h = new HammingCleared();
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