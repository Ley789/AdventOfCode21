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
	    //We know that in.size() > 2, so we can start count increments
		long count = 0;
		long compare = in.get(0);
		for (int i = 1; i < in.size(); i++) {
			if (compare < in.get(i)) count++;
			compare = in.get(i);
		}
		return count;
	}
	
	public static void main(String[] args) {
		List<Integer> input = ReadProblem("InputFile.txt");
		long solution = CountIncrements(input);
		System.out.println(solution);
	}

}
