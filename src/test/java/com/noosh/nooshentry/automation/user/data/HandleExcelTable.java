package com.noosh.nooshentry.automation.user.data;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;

public class HandleExcelTable {

	public String[][] getTableArray(String xlFilePath, String sheetName, String tableName){
        String[][] tabArray=null;
        try{
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            Sheet sheet = workbook.getSheet(sheetName);

            int startRow,startCol, endRow, endCol,ci,cj;
            jxl.Cell tableStart= sheet.findCell(tableName);

            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();
            jxl.Cell tableEnd= sheet.findCell(tableName, startCol+1, startRow+1, 100, 64000,  false);                               

            endRow = tableEnd.getRow();
            endCol = tableEnd.getColumn();

            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
            ci=0;

            for (int i=startRow+1;i<endRow;i++,ci++){
                cj=0;
                for (int j=startCol+1;j<endCol;j++,cj++){
                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
                }
            }
        }
        catch (Exception e)    {
            System.out.println("error in getTableArray()");
        }
        return(tabArray);
    }			
}

