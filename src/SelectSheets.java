import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;

import com.monitorjbl.xlsx.StreamingReader;

public class SelectSheets {
		
	public static ArrayList<Sheet> selectSheets () throws InvalidFormatException, FileNotFoundException {
		

			InputStream myExcel = new FileInputStream(new File("resources/Date_SPM.xlsx"));
			
			Workbook myWorkBook = StreamingReader.builder()
		        .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
		        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
		        .open(myExcel);            // InputStream or File for XLSX file (required)
		
			ArrayList<Sheet> sheets = new ArrayList<Sheet>();
		
			Sheet sheet_date = myWorkBook.getSheet("Date");								// Return the sheet "Date" from the .xlsx workbook  
			sheets.add(sheet_date);
			Sheet sheet_denumiri = (Sheet) myWorkBook.getSheet("Denumiri");				// Return the sheet "Denumiri" from the .xlsx workbook 
			sheets.add(sheet_denumiri);
			Sheet sheet_ListaProduse = (Sheet) myWorkBook.getSheet("ListaProduse");		// Return the sheet "ListaProduse" from the .xlsx workbook 
			sheets.add(sheet_ListaProduse);
			
			return sheets;				
	}
}
