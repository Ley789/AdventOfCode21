import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
	//vPosOffset checks how deep we are, so it cannot be negative
	public static class Position {
		public long hPosOffset = 0;
		public long vPosOffset = 0;
		public Position() {};
		public String toString() {
			return "horizontal offset change is " + hPosOffset + " and vertical offset change is " + vPosOffset;
			
		}
		public void addHorizontalOffset(long delta) {
			this.hPosOffset += delta;
		}
		
		public void addVerticalOffset(long delta) {
			long res = vPosOffset + delta;
			if (res <= 0) res = 0;
			this.vPosOffset = res;
		}
		
		public void addPosition(Position other) {
			this.addHorizontalOffset(other.hPosOffset);
			this.addVerticalOffset(other.vPosOffset);
		}
	}
	
	//convention filename is in the same folder as the executable
	public static List<Position> ReadProblem (String filename){
        Path p = Paths.get(System.getProperty("user.dir"));
        p = p.resolve(filename);
        List<Position> res = new ArrayList<>();
        File f = p.toFile();
	    f.setReadOnly();
        try (Scanner scan = new Scanner (f)) {
		    while (scan.hasNextLine()) {
			    String[] r = scan.nextLine().split(" ");
			    if (r.length != 2) { throw new RuntimeException("Input file not correct"); }
			    Position pos = new Position();
			    if (r[0].matches("forward"))   pos.hPosOffset = Long.parseLong(r[1]);
			    else if (r[0].matches("up"))   pos.vPosOffset = -Long.parseLong(r[1]);
			    else if (r[0].matches("down")) pos.vPosOffset = Long.parseLong(r[1]);
			    else { throw new RuntimeException("Input file not correct"); }
	            res.add(pos);
			}
        } catch (FileNotFoundException ex) {
        	System.out.println("Read conventions bro");
        }
      return res;
	}


	public static void main(String[] args) {
		List<Position> input = ReadProblem("InputFile.txt");
		Position endPos = new Position();
		for (Position p : input) 
		{ 
		   endPos.addPosition(p);
		}
		System.out.println(endPos);
		System.out.println(endPos.hPosOffset * endPos.vPosOffset);
		
	}

}
