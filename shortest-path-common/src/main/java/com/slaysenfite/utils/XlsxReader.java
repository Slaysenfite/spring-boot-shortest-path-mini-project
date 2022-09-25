package com.slaysenfite.utils;

import com.slaysenfite.domain.entity.Planet;
import com.slaysenfite.domain.entity.Route;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for working with Excels .xlsx files
 */
public class XlsxReader {

    private XlsxReader() {
    }

    /**
     * Method takes in a path for .xlsx file and changes it to a workable Java object
     *
     * @param filename - String
     * @return Apache POI XSSFWorkbook
     * @throws IOException
     */
    public static XSSFWorkbook readXlsx(String filename) throws IOException {
        try(FileInputStream fileInputStream = new FileInputStream(ResourceUtils.getFile("classpath:" + filename))) {
            return new XSSFWorkbook(fileInputStream);
        }
    }

    /**
     * Methods reads planet data from and xlsx sheet and returns a list of Planet objects
     *
     * @param planetSheet - XSSFSheet
     * @return list of Planet objects
     */
    public static List<Planet> readPlanetSheet(XSSFSheet planetSheet) {
        List<Planet> planets = new ArrayList<>();
        Iterator<Row> rowIterator = planetSheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            addRowToPlanetList(planets, rowIterator);
        }
        return planets;
    }

    /**
     * Method reads in data from route and traffic sheets and returns a list of Route entites
     *
     * @param routeSheet
     * @param trafficSheet
     * @return list of Route objects
     */
    public static List<Route> readRouteSheet(XSSFSheet routeSheet, XSSFSheet trafficSheet) {
        List<Route> routes = new ArrayList<>();
        Iterator<Row> routeRowIterator = routeSheet.iterator();
        Iterator<Row> trafficRowIterator = trafficSheet.iterator();
        routeRowIterator.next();
        trafficRowIterator.next();
        while (routeRowIterator.hasNext() && trafficRowIterator.hasNext()) {
            addRowsToRouteToList(routes, routeRowIterator, trafficRowIterator);
        }
        return routes;
    }

    private static void addRowsToRouteToList(List<Route> routes, Iterator<Row> routeRowIterator, Iterator<Row> trafficRowIterator) {
        DataFormatter dataFormatter = new DataFormatter();
        Row routeRow = routeRowIterator.next();
        Row trafficRow = trafficRowIterator.next();
        routes.add(new Route(
            Integer.parseInt(dataFormatter.formatCellValue(routeRow.getCell(0))),
            routeRow.getCell(1).getStringCellValue(),
            routeRow.getCell(2).getStringCellValue(),
            routeRow.getCell(3).getNumericCellValue(),
            trafficRow.getCell(3).getNumericCellValue()
        ));
    }

    private static void addRowToPlanetList(List<Planet> planets, Iterator<Row> rowIterator) {
        Row row = rowIterator.next();
        planets.add(new Planet(row.getCell(0).getStringCellValue(),
            row.getCell(1).getStringCellValue()));
    }
}
