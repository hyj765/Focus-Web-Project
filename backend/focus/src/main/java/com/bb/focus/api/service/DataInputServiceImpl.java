package com.bb.focus.api.service;


import com.bb.focus.api.response.SchoolDto;
import com.bb.focus.common.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataInputServiceImpl implements DataInputService{

    ExcelUtils exelUtils;
    @Autowired
    public DataInputServiceImpl(ExcelUtils eutil){
        exelUtils = eutil;
    }


    public List<SchoolDto> ConvertMultiFileIntoList(MultipartFile file) throws IOException {
        if(exelUtils.FileNameFilter(file.getOriginalFilename()).equals("invalid")){
            return null;
        }
        List<SchoolDto> schoolDtoList = new ArrayList<>();
        String[] f;
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

        while ((line = br.readLine()) != null) {
            f = line.split(",");

            SchoolDto schoolDto = new SchoolDto();
            schoolDto.setName(f[1]);
            schoolDto.setCampus(f[2]);
            schoolDto.setArea(f[3]);
            schoolDtoList.add(schoolDto);
        }
        br.close();
        return schoolDtoList;
    }
    //엑셀 타입에 따라 파일을 읽어오는 함수
    public Boolean ReadExcel(MultipartFile file) throws IOException,InvalidFormatException {
        String extension;
        if((extension=exelUtils.FileNameFilter(file.getOriginalFilename())) == null){
            return false;
        }
        Workbook workbook = null;
        if(extension == "xlsx"){
            workbook = new XSSFWorkbook(file.getInputStream());
        }else if(extension.equals("xls")){
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        Sheet worksheet = workbook.getSheetAt(0);

        for(int i=1; i<worksheet.getPhysicalNumberOfRows(); ++i){

        }

        return true;
    }

    // 다운로드할 엑셀파일을 만드는 함수
    public Workbook CreateWorkbook(String[] headers){
        return exelUtils.CreateWorkbook(headers);
    }





}
