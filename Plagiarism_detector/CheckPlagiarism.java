package Plagiarism_detector;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

// This class is responsible for comparing two files and returning the result of the comparison.
// It also has a method for comparing all files in a directory.

public class CheckPlagiarism {
    String CompareFiles(String f1, String f2) throws FileNotFoundException {
        int threshold=1;
        HammingCleared h = new HammingCleared();
        ArrayList<Integer> results = new ArrayList<>();
        List<String> lines1 = load_file(f1);
        List<String> lines2 = load_file(f2);
        List<String> largerList = lines1.size() > lines2.size() ? lines1 : lines2;
        List<String> smallerList = lines1.size() > lines2.size() ? lines2 : lines1;
        for (int i = 0; i < largerList.size(); i++) {
            int min = 10000;
            for (int j = 0; j < smallerList.size(); j++) {
                try {
                    if (h.compare(largerList.get(i), smallerList.get(j)) < min) {
                        min = h.compare(largerList.get(i), smallerList.get(j));
                    }
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    min = 0;
                    break;
                }
            }
            results.add(min);
        }
        double avg = results.stream().mapToInt(Integer::intValue).sum() / results.size();
        if (avg < threshold) {
            return "Plagiarism detected!";
        }
        return "No plagiarism detected!";
    }

    List<String> load_file(String f) throws FileNotFoundException {
        File file = new File(f);
        Scanner sc = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        sc.close();
        return lines;
    }

    public void checkAllFilesInDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            for (int j = i + 1; j < files.length; j++) {
                try{
                String result = CompareFiles(files[i].getAbsolutePath(), files[j].getAbsolutePath());
                System.out.println("Comparison of " + files[i].getName() + " and " + files[j].getName() + ": " + result);
                }
                catch (FileNotFoundException e){
                    System.out.println("Access denied to file " + files[i].getName() + " or " + files[j].getName());
                    continue;
                }
            }
        }
    }
    public static void main(String[] args) {
        CheckPlagiarism c = new CheckPlagiarism();
        try {
            c.checkAllFilesInDirectory("C:\\Users\\aleks\\Documents\\Java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
