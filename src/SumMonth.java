import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class SumMonth {

	private static Cell cell;
	private static Cell cod_produs;
	private static Cell cell_prod;
	private static Cell cell_luna;


	public static ArrayList<Integer> sumQuantityMonth (String produs, String an, ArrayList<String> luni) throws InvalidFormatException, FileNotFoundException {

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
		System.out.println(coduriProdus);

		// In sheet-ul sheet_date, coloana G, caut fiecare cod din ArrayList-ul coduriProdus si adaug la sumQuantityMonth cantitatea din dreptul sau de pe coloana F

		Sheet sheet_date = mySheet.get(0);

		// Construiesc un ArrayList de dimensiune egala cu numarul de luni, si stochez in fiecare element cantitatea vanzarilor produsului pentru fiecare luna

		ArrayList<Integer> sumQuantityMonth = new ArrayList<>();
		for (int i = 0; i < luni.size(); i++) {
			sumQuantityMonth.add(0);
		}
		for (Row row : sheet_date) {
			if (row != null) {
				cell_prod = row.getCell(6);
				if (cell_prod != null) {
					for (int i = 0; i < coduriProdus.size(); i++) {
						if (cell_prod.getStringCellValue().contentEquals(coduriProdus.get(i))) {
							if (row.getCell(8) != null && !row.getCell(8).getStringCellValue().contentEquals("")) {
								if(row.getCell(8).getStringCellValue().contentEquals(an)) {
									cell_luna = row.getCell(3);
									if(cell_luna != null) {
										for (int j = 0; j < luni.size(); j++) {
											if (cell_luna.getStringCellValue().contentEquals(luni.get(j))) {
												if (row.getCell(5) != null && !row.getCell(5).getStringCellValue().contentEquals("")) {
													sumQuantityMonth.set(j, sumQuantityMonth.get(j) + Integer.parseInt(row.getCell(5).getStringCellValue()));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < sumQuantityMonth.size(); i++) {
			sumQuantityMonth.set(i, sumQuantityMonth.get(i) * (-1));
		}
		System.out.println(sumQuantityMonth);
		return sumQuantityMonth;
	}

	public static ArrayList<Double> sumValueMonth (String produs, String an, ArrayList<String> luni) throws InvalidFormatException, FileNotFoundException {

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

		// In sheet-ul sheet_date, coloana G, caut fiecare cod din ArrayList-ul coduriProdus si adaug la sumValueMonth valoarea din dreptul sau de pe coloana E

		Sheet sheet_date = mySheet.get(0);

		// Construiesc un ArrayList de dimensiune egala cu numarul de luni, si stochez in fiecare element valoarea vanzarilor produsului pentru fiecare luna

		ArrayList<Double> sumValueMonth = new ArrayList<>();
		for (int i = 0; i < luni.size(); i++) {
			sumValueMonth.add(0.0);
		}
		for (Row row : sheet_date) {
			if (row != null) {
				cell_prod = row.getCell(6);
				if (cell_prod != null) {
					for (int i = 0; i < coduriProdus.size(); i++) {
						if (cell_prod.getStringCellValue().contentEquals(coduriProdus.get(i))) {
							if (row.getCell(8) != null && !row.getCell(8).getStringCellValue().contentEquals("")) {
								if (row.getCell(8).getStringCellValue().contentEquals(an)) {
									cell_luna = row.getCell(3);
									if (cell_luna != null) {
										for (int j = 0; j < luni.size(); j++) {
											if (cell_luna.getStringCellValue().contentEquals(luni.get(j))) {
												if (row.getCell(4) != null && !row.getCell(4).getStringCellValue().contentEquals("")) {
													sumValueMonth.set(j, sumValueMonth.get(j) + Double.parseDouble(row.getCell(4).getStringCellValue()));
												}
											}
										}
									}
								}		
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < sumValueMonth.size(); i++) {
			sumValueMonth.set(i, sumValueMonth.get(i) * (-1));
		}
		System.out.println(sumValueMonth);
		return sumValueMonth;
	}
}