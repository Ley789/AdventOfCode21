import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
	enum Command{
		F,
		D,
		U
	}
	//vPosOffset checks how deep we are, so it cannot be negative
	public static class SubmarinC {
		public long magnitute = 0;
		public Command type;
		public SubmarinC() {};
		public String toString() {
			return "offset change is " + magnitute + " and the tag is " + type;
			
		}
	}
	
	public static class Position {
		long hPos = 0;
		long vPos = 0;
	}
	
	public static class Submarine{
		public Position p;
		public int aim = 0;
		Submarine (){
			p = new Position();
		}
		Submarine (Position initPos){
			p = initPos;
		}
		
		//Task 1; ignoring aim
		public void SubmarinCommanTask1(SubmarinC c) {
			switch(c.type) {
			case F:
				p.hPos += c.magnitute;
				break;
			case D:
				p.vPos += c.magnitute;
				break;
			case U:
				p.vPos -= c.magnitute;
				//Remember we are a submarine, we cannot be higher then 0
				if (p.vPos < 0) p.vPos = 0;
				break;
			}
		}
		
		//Task 2; works with aim
		public void SubmarinCommanTask2(SubmarinC c) {
			switch(c.type) {
			case F:
				p.hPos += c.magnitute;
				p.vPos += aim * c.magnitute;
				if (p.vPos < 0) p.vPos = 0;
				break;
			case D:
				aim += c.magnitute;
				break;
			case U:
				aim -= c.magnitute;
				break;
			}
		}
	}


	

	public static void main(String[] args) {
		List<SubmarinC> input = ReadProblem("InputFile.txt");
        Submarine driveTask1 = new Submarine();
        Submarine driveTask2 = new Submarine();
		for (SubmarinC command : input) 
		{ 
			driveTask1.SubmarinCommanTask1(command);
			driveTask2.SubmarinCommanTask2(command);
		}
		System.out.println(driveTask1.p.hPos * driveTask1.p.vPos);
		System.out.println(driveTask2.p.hPos * driveTask2.p.vPos);
	}
	
	
	
	
	//convention filename is in the same folder as the executable
	public static List<SubmarinC> ReadProblem (String filename){
        Path p = Paths.get(System.getProperty("user.dir"));
        p = p.resolve(filename);
        List<SubmarinC> res = new ArrayList<>();
        File f = p.toFile();
	    f.setReadOnly();
        try (Scanner scan = new Scanner (f)) {
		    while (scan.hasNextLine()) {
			    String[] r = scan.nextLine().split(" ");
			    if (r.length != 2) { throw new RuntimeException("Input file not correct"); }
			    SubmarinC pos = new SubmarinC();
		    	pos.magnitute = Long.parseLong(r[1]);
			    if (r[0].matches("forward"))   pos.type = Command.F;
			    else if (r[0].matches("up"))   pos.type = Command.U;
			    else if (r[0].matches("down")) pos.type = Command.D;
			    else { throw new RuntimeException("Input file not correct"); }
	            res.add(pos);
			}
        } catch (FileNotFoundException ex) {
        	System.out.println("Read conventions bro");
        }
      return res;
	}

}
