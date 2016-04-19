package nk;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;

public class MyCSVReader {
	private CSVReader csvReader;
	public static List<String[]> completeList;
	private List<String[]> reducedList;
	private List<Integer> bereich;
	
	public MyCSVReader() throws IOException
	{
		csvReader = new CSVReader(new FileReader("src/main/resources/cologne_data.csv"), ',' , '\'', 1);
		reducedList = new ArrayList<String[]>();
		bereich = new ArrayList<Integer>();
	}
	
	public void listeKuerzen() throws IOException
	{
		System.out.println(csvReader.toString()+"");
		//String[] liste = csvReader.readNext();
		completeList = csvReader.readAll();
		//System.out.println(csvReader.getSkipLines() + "");
		for(int i = 0; i < completeList.size(); i++){
			reducedList.add(new String[]{completeList.get(i)[0], completeList.get(i)[3], completeList.get(i)[4]});
		}
		/**for(int i = 0; i < liste.size(); i++)
		{
			System.out.print(reducedList.get(i)[0] + " ");
			System.out.print(reducedList.get(i)[1] + " ");
			System.out.println(reducedList.get(i)[2]);
		}**/
	}
	
	public void bereicheBerechnen()
	{
		bereich.add(0);
		int i = 0;
		for(i = 1; i < reducedList.size(); i++)
		{
			if(reducedList.get(i-1)[0].compareTo(reducedList.get(i)[0]) != 0)
			{
				bereich.add(i);
			}
		}
		bereich.add(i);
		//System.out.println(bereich.size());
	}
	
	public List<String[]> zeitbereicheUnterscheiden(int i)
	{
		int start = this.bereich.get(i);
		int end = this.bereich.get(i+1);
		List<String[]> shortList = new ArrayList<String[]>();
		for(int j = start; j < end; j++)
		{
			shortList.add(reducedList.get(j));
		}
		
		return shortList;
	}
	
	public void zeitbereicheAusgeben(int i)
	{
		List<String[]> shortList = zeitbereicheUnterscheiden(i);
		
		for(int a = 0; a < shortList.size(); a++)
		{
			System.out.print(shortList.get(a)[0] + " ");
			System.out.print(shortList.get(a)[1] + " ");
			System.out.println(shortList.get(a)[2]);
		}
	}
	
}
