import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
public class ArrayFromExcelToFeedCombo {
	public static ArrayList<String> GetExcelTableIntoArrayListString(Sheet s, boolean debug) throws InvalidFormatException, FileNotFoundException {
		ArrayList<String> OUT = new ArrayList<String>(); 
		ArrayList<Sheet> mySheet = new ArrayList<Sheet>(SelectSheets.selectSheets());
		Sheet sheet_ListaProduse = mySheet.get(2);
		Iterator<Row> rowIterator = sheet_ListaProduse.iterator(); 				// Get iterator to all the rows in current sheet 
		int count = 1;															// Traversing over each row of the .xlsx file 
		while (rowIterator.hasNext()) { 
			Row row = rowIterator.next();
			if(debug)System.out.print(count + ". \t");
			Cell cell = row.getCell(0);
			String c = cell.getStringCellValue();
			if(debug)System.out.print(c + "\t");
			OUT.add(c);
			count++; 
		}
		return OUT;
	}
}
