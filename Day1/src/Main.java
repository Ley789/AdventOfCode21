import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
	//convention filename is in the same folder as the executable
	public static List<Integer> ReadProblem (String filename){
        Path p = Paths.get(System.getProperty("user.dir"));
        p = p.resolve(filename);
        List<Integer> res = new ArrayList<>();
        File f = p.toFile();
	    f.setReadOnly();
        try (Scanner scan = new Scanner (f)) {
		    while (scan.hasNextLine()) {
             res.add(Integer.parseInt(scan.nextLine()));
		    }
        } catch (FileNotFoundException ex) {
        	System.out.println("Read conventions bro");
        }
      return res;
	}

	public static long CountIncrements(List<Integer> in) {
		if (in.size() <= 1) return 0;
	    //We know that in.size() >= 2, so we can start count increments
		long count = 0;
		long comparePre = in.get(0);
		long compareAft;
		for (int i = 1; i < in.size(); i++) {
			compareAft = in.get(i);
			if (comparePre < compareAft) count++;
			comparePre = compareAft;
		}
		return count;
	}
	
	public static long CountThreeIncrements(List<Integer> in) {
		if (in.size() <= 2) return 0;
	    //We know that in.size() >= 3, so we can start count increments
		long count = 0;
		long comparePre = in.get(0) + in.get(1) + in.get(2);
		long compareAft = 0;
		for (int i = 3; i < in.size(); i++) {
			compareAft = in.get(i - 2) + in.get(i - 1) + in.get(i);
			if (comparePre < compareAft) count++;
			comparePre = compareAft;
		}
		return count;
	}
	
	public static void main(String[] args) {
		List<Integer> input = ReadProblem("InputFile.txt");
		long solutionDay1 = CountIncrements(input);
		long solutionDay2 = CountThreeIncrements(input);
		System.out.println(solutionDay1);
		System.out.println(solutionDay2);
	}

}
