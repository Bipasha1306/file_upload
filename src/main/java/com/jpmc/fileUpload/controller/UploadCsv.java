package com.jpmc.fileUpload.controller;

import com.jpmc.fileUpload.EdwRepository;
import com.jpmc.fileUpload.entity.EDW;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UploadCsv {

    @Autowired
    EdwRepository edwRepository;

    @PostMapping("/upload")
    public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {
        List<EDW> edwList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> recordList = parser.parseAllRecords(inputStream);
        recordList.forEach(record -> {
            EDW edw = new EDW();
            edw.setAccount_cd(Integer.parseInt(record.getString("account_cd")));
            edw.setAgent_cd(record.getString("agent_cd"));
            edwList.add(edw);
        });
        edwRepository.saveAll(edwList);
        return "upload scuscess";
    }
}
