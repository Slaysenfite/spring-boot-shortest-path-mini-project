package com.slaysenfite.utils;

import com.slaysenfite.domain.entity.Planet;
import com.slaysenfite.domain.entity.Route;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class XlsxReaderTest {

    private static final String FILEPATH = "data/HR-Offsite-AssignmentV30.xlsx";
    private static final String FILEPATH_INCORRECT_TYPE = "dummmy/incorrect-type.csv";

    @Test
    void Given_XlsxDataFile_When_DataIsRequiredFromTheFile_Then_FileIsNotNull() throws IOException {
        XSSFWorkbook workbook = XlsxReader.readXlsx(FILEPATH);
        assertNotNull(workbook);

        assertNotNull(workbook.getSheet("Traffic"));
        assertNotNull(workbook.getSheet("Planet Names"));
        assertNotNull(workbook.getSheet("Routes"));
    }

    @Test
    void Given_NonExistentXlsxDataFile_When_DataIsRequiredFromTheFile_Then_FileNotFoundExceptionIsThrown() {
        assertThrows(NotOfficeXmlFileException.class, () -> {
            XlsxReader.readXlsx(FILEPATH_INCORRECT_TYPE);
        });
    }

    @Test
    void Given_DataFileOfIncorrectType_When_DataIsReadforXlxsReader_Then_ExceptionIsThrown() {
        assertThrows(FileNotFoundException.class, () -> {
            XlsxReader.readXlsx("data/does-not-exist.xlsx");
        });
    }

    @Test
    void Given_XlsxDataFile_When_DataIsRequiredFromTheFile_Then_XlsxFileCanBeRead() throws IOException {
        XSSFWorkbook workbook = XlsxReader.readXlsx(FILEPATH);
        List<Planet> planets = XlsxReader.readPlanetSheet(workbook.getSheet("Planet Names"));
        List<Route> routes = XlsxReader.readRouteSheet(workbook.getSheet("Routes"), workbook.getSheet("Traffic"));

        assertNotNull(planets);
        assertEquals(38, planets.size());
        assertEquals("Ursae Majoris", planets.get(36).getName());

        assertNotNull(routes);
        assertEquals(45, routes.size());
        assertEquals(Double.valueOf(1.10), routes.get(44).getDelay());
    }
}
