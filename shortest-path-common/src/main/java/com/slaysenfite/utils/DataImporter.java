package com.slaysenfite.utils;

import com.slaysenfite.domain.entity.Planet;
import com.slaysenfite.domain.entity.Route;
import com.slaysenfite.domain.repository.PlanetRepository;
import com.slaysenfite.domain.repository.RouteRepository;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Utility class to persist data from the provided xlsx file into the embedded database
 * after context creation
 */
@Component
public class DataImporter {

    @Value("${xlsxFilename}")
    private String xlsxFilename;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private RouteRepository routeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataImporter.class);
    private XSSFWorkbook workbook;

    @PostConstruct
    public void readAndPersistXlsxData(){
        initializeWorkbook();
        persistPlanetData();
        persistRouteData();
        closeWorkbook();
    }

    private void initializeWorkbook() {
        try {
            workbook = XlsxReader.readXlsx(xlsxFilename);
        } catch (IOException e) {
            LOGGER.error("Failed to create XSSFWorkbook. Exception is {}", e.getMessage());
        }
    }

    private void persistPlanetData() {
        List<Planet> planets = XlsxReader.readPlanetSheet(workbook.getSheet("Planet Names"));
        for (Planet planet : planets) {
            planetRepository.save(planet);
        }
    }

    private void persistRouteData() {
        List<Route> routes = XlsxReader.readRouteSheet(workbook.getSheet("Routes"), workbook.getSheet("Traffic"));
        for (Route route : routes) {
            routeRepository.save(route);
        }
    }

    private void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            LOGGER.error("Failed to close XSSFWorkbook. Exception is {}", e.getMessage());
        }
    }
}
