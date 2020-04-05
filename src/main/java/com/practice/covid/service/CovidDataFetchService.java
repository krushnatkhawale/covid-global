package com.practice.covid.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.practice.covid.model.TSDeaths;
import com.practice.covid.util.GitUtils;
import com.practice.covid.util.Utils;
import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidDataFetchService {

    private static final String COVID_DATA_REPOSITORY_URL = "https://github.com/CSSEGISandData/COVID-19.git";
    private static final String BRANCH_NAME = "refs/heads/master";
    final String firstChild = "csse_covid_19_data";
    final String secondChild = "csse_covid_19_time_series";
    final String globalDataFileName = "time_series_covid19_deaths_global";

    public List<TSDeaths> getCovidData() {

        try {

            try (Git git = GitUtils.checkoutOrGetLatest(COVID_DATA_REPOSITORY_URL, BRANCH_NAME)) {

                File reqFiles = getDataFiles(git);
                System.out.println("Reading file: " + reqFiles.getName());


                List<TSDeaths> dataRows;
                try (
                        Reader reader = Files.newBufferedReader(reqFiles.toPath());
                        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
                ) {

                    dataRows = csvReader.readAll().stream().map(Utils::lineToPojo).collect(Collectors.toList());
                }

                return dataRows;
            }
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException("Error while cloning repository", e);
        }
        return null;
    }

    private File getDataFiles(Git git) {
        File workTree = git.getRepository().getWorkTree();
        System.out.println("WorkTree: " + workTree.getAbsolutePath());
        File[] files = workTree.listFiles(file -> file.getName().equals(firstChild));
        File firstChild = files[0];
        File[] firstChildFiles = firstChild.listFiles(file -> file.getName().equals(secondChild));
        File secondChild = firstChildFiles[0];
        return secondChild.listFiles()[4];
    }
}
