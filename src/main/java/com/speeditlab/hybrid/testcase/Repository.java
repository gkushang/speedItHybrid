package com.speeditlab.hybrid.testcase;

import java.util.Iterator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speeditlab.hybrid.excel.Excel;
import com.speeditlab.hybrid.exception.SpeedItException;
import com.speeditlab.hybrid.locators.Locator;
import com.speeditlab.hybrid.utils.SpeetItUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by kugajjar on 2/8/15.
 */
public class Repository extends Excel
{
    private static final String LOCATOR_FILE = "locators/Locators.xlsx";
    public static final String CELLS = "cells";
    public static final String ROWS = "rows";
    private final ObjectMapper MAPPER = new ObjectMapper();
    private Locator locator;

    public Repository(String locatorSheet)
    {
        super(SpeetItUtils.getAbsolutePath(LOCATOR_FILE), locatorSheet);
        _initLocator();
    }


    private void _initLocator()
    {
        try
        {
            JSONObject json = new JSONObject();

            JSONArray rows = new JSONArray();
            for (Iterator<Row> rowsIT = worksheet.rowIterator(); rowsIT.hasNext(); )
            {
                Row row = rowsIT.next();
                JSONObject jRow = new JSONObject();

                JSONArray cells = new JSONArray();
                for (Iterator<Cell> cellsIT = row.cellIterator(); cellsIT.hasNext(); )
                {
                    Cell cell = cellsIT.next();
                    cells.put(cell.getStringCellValue());
                }

                jRow.put(CELLS, cells);
                rows.put(jRow);
            }

            json.put(ROWS, rows);

            this.locator = MAPPER.readValue(json.toString(), Locator.class);
        }
        catch (Exception e)
        {
            throw new SpeedItException("Error in processing Locator Sheet");
        }
    }

    public Locator getLocator()
    {
        return locator;
    }
}
