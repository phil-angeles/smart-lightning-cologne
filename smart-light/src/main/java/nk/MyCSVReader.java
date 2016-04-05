package nk;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.flink.api.java.ExecutionEnvironment;

import com.opencsv.CSVReader;



public class MyCSVReader {
	CSVReader csvReader;
	final ExecutionEnvironment env;
	List<String[]> reducedList;
	
	public MyCSVReader() throws IOException
	{
		env = ExecutionEnvironment.getExecutionEnvironment();
		csvReader = new CSVReader(new FileReader("src/main/resources/cologne_data.csv"), ',' , '\'', 1);
		reducedList = new ArrayList<String[]>();
	}
	
	public void stringAusgeben() throws IOException
	{
		System.out.println(csvReader.toString()+"");
		//String[] liste = csvReader.readNext();
		List<String[]> liste = csvReader.readAll();
		System.out.println(csvReader.getSkipLines() + "");
		for(int i = 0; i < liste.size(); i++){
			reducedList.add(new String[]{liste.get(i)[0], liste.get(i)[3], liste.get(i)[4]});
		}
		for(int i = 0; i < liste.size(); i++)
		{
			System.out.print(reducedList.get(i)[0] + " ");
			System.out.print(reducedList.get(i)[1] + " ");
			System.out.println(reducedList.get(i)[2]);
		}
	}
	
	public static void main(String[] args) throws IOException{
		MyCSVReader reader = new MyCSVReader();
		reader.stringAusgeben();
	}
	
	
}
