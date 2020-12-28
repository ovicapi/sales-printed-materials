import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class SumYear {

	private static Cell cell;
	private static Cell cod_produs;
	private static Cell cell_prod;
	private static Cell cell_an;


	public static ArrayList<Integer> sumQuantityYear (String produs, ArrayList<String> ani) throws InvalidFormatException, FileNotFoundException {

		// Construiesc un ArrayList <String> unde adun toate denumirile pentru produsul selectat din sheetul sheet_denumiri

		ArrayList<String> coduriProdus = new ArrayList<String>();

		ArrayList<Sheet> mySheet = new ArrayList<Sheet>(SelectSheets.selectSheets());

		Sheet sheet_denumiri = mySheet.get(1);

		for (Row row : sheet_denumiri) {
			if (row != null) {
				cell = row.getCell(2);
				if (cell != null) {
					if (cell.getStringCellValue().contentEquals(produs)) {
						cod_produs = row.getCell(0);
						coduriProdus.add(cod_produs.getStringCellValue());
					}
				}
			}
		}

		// In sheet-ul sheet_date, coloana G, caut fiecare cod din ArrayList-ul coduriProdus si adaug la sumQuantityYear cantitatea din dreptul sau de pe coloana F

		Sheet sheet_date = mySheet.get(0);

		// Construiesc un ArrayList de dimensiune egala cu numarul de ani, si stochez in fiecare element cantitatea vanzarilor produsului pentru fiecare an

		ArrayList<Integer> sumQuantityYear = new ArrayList<>();
		for (int i = 0; i < ani.size(); i++) {
			sumQuantityYear.add(0);
		}
		for (Row row : sheet_date) {
			if (row != null) {
				cell_prod = row.getCell(6);
				if (cell_prod != null) {
					for (int i = 0; i < coduriProdus.size(); i++) {
						if (cell_prod.getStringCellValue().contentEquals(coduriProdus.get(i))) {
							cell_an = row.getCell(8);
							if (cell_an != null) {
								for (int j = 0; j < ani.size(); j++) {
									if (cell_an.getStringCellValue().contentEquals(ani.get(j))) {
										if (row.getCell(5) != null && !row.getCell(5).getStringCellValue().contentEquals("")) {
											sumQuantityYear.set(j, sumQuantityYear.get(j) + Integer.parseInt(row.getCell(5).getStringCellValue()));
										}
									}
								}
							}
						}		
					}
				}
			}
		}		
		for (int i = 0; i < sumQuantityYear.size(); i++) {
			sumQuantityYear.set(i, sumQuantityYear.get(i) * (-1));
		}
		return sumQuantityYear;
	}
	
	public static ArrayList<Double> sumValueYear (String produs, ArrayList<String> ani) throws InvalidFormatException, FileNotFoundException {

		// Construiesc un ArrayList <String> unde adun toate denumirile pentru produsul selectat din sheetul sheet_denumiri

		ArrayList<String> coduriProdus = new ArrayList<String>();

		ArrayList<Sheet> mySheet = new ArrayList<Sheet>(SelectSheets.selectSheets());

		Sheet sheet_denumiri = mySheet.get(1);

		for (Row row : sheet_denumiri) {
			if (row != null) {
				cell = row.getCell(2);
				if (cell != null) {
					if (cell.getStringCellValue().contentEquals(produs)) {
						cod_produs = row.getCell(0);
						coduriProdus.add(cod_produs.getStringCellValue());
					}
				}
			}
		}

		// In sheet-ul sheet_date, coloana G, caut fiecare cod din ArrayList-ul coduriProdus si adaug la sumValueYear valoarea din dreptul sau de pe coloana E

		Sheet sheet_date = mySheet.get(0);

		// Construiesc un ArrayList de dimensiune egala cu numarul de ani, si stochez in fiecare element valoarea vanzarilor produsului pentru fiecare an

		ArrayList<Double> sumValueYear = new ArrayList<>();
		for (int i = 0; i < ani.size(); i++) {
			sumValueYear.add(0.0);
		}
		for (Row row : sheet_date) {
			if (row != null) {
				cell_prod = row.getCell(6);
				if (cell_prod != null) {
					for (int i = 0; i < coduriProdus.size(); i++) {
						if (cell_prod.getStringCellValue().contentEquals(coduriProdus.get(i))) {
							cell_an = row.getCell(8);
							if (cell_an != null) {
								for (int j = 0; j < ani.size(); j++) {
									if (cell_an.getStringCellValue().contentEquals(ani.get(j))) {
										if (row.getCell(4) != null && !row.getCell(4).getStringCellValue().contentEquals("")) {
											sumValueYear.set(j, sumValueYear.get(j) + Double.parseDouble(row.getCell(4).getStringCellValue()));
										}
									}
								}
							}
						}		
					}
				}
			}
		}		
		for (int i = 0; i < sumValueYear.size(); i++) {
			sumValueYear.set(i, sumValueYear.get(i) * (-1));
		}
		return sumValueYear;
	}
}