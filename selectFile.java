import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class selectFile {
	public String fileStr;
	public int fileInx;
	private String dirPath = "Src";
	private String EXT = "jay";
	private static String StartMsg = "Available JAY files to use... \n";
	private static String DirMsg = "JAY files Available: ";
	private static String setMsg = "\nEnter the index number of the JAY file to use... \n";
	private static String noFileMsg = "No JAY Files found... Exiting Program ";
	ArrayList<String> fileList = new ArrayList<String>();
	private int fileCount = 0;
	public void displayDirContents(){
		
		try(Stream<Path> paths = Files.walk(Paths.get(dirPath))) {
			System.out.println(StartMsg);
		    paths.forEach(filePath -> {
		    	String fileUsed = filePath.toString();
		    	String fileExt =  fileUsed.substring(fileUsed.lastIndexOf(".") + 1, fileUsed.length());
		        if (Files.isRegularFile(filePath) && fileExt.equals(EXT)) {
		        	fileCount++;
		        	fileList.add(fileUsed);
		        	System.out.println(fileCount + ": " + filePath);
		        }
		        
		    });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		if(fileCount == 0){
        	System.out.println(noFileMsg);
        	System.exit(1);
        }
		System.out.println("\n" + DirMsg + fileCount);
	}
	
	public String setFileName(){
		Scanner fileInput = new Scanner(System.in);
		System.out.println(setMsg);
		fileInx = fileInput.nextInt();
		fileStr = fileList.get(fileInx-1);
		fileInput.close();	
		return fileStr;
	}
	
	public String getFileName(){
		try{
			 return fileStr;
		} 
		
		catch(NullPointerException e){
			System.out.println("Error - Invaild FileName");
			return "";
		}
	}
	

}
