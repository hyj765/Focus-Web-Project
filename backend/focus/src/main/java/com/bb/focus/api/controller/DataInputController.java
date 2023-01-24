package com.bb.focus.api.controller;

import com.bb.focus.api.response.SchoolDto;
import com.bb.focus.api.service.DataInputService;
import com.bb.focus.api.service.SchoolService;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Data")
public class DataInputController {
    DataInputService DataService;
    SchoolService schoolSerivce;

    //ApplicantService
    //EvaluatorService
    @Autowired
    public DataInputController(DataInputService Dservice, SchoolService scService){
        DataService = Dservice;
        schoolSerivce = scService;

    }

    // 평가자 엑셀 다운로드 하는 함수 Headers 값의 따라 목록이 늘어남.
    @GetMapping("/download/evaluator")
    public ResponseEntity<?> DownloadEvaluatorExcel(HttpServletResponse response){
        response.setHeader("Content-Disposition", "attachment;filename=evaluator_input_here.xlsx");
        response.setContentType("application/octet-stream");
        String[] headers ={"이름","사번","부서","직급","전화번호","이메일"};
        Workbook workbook = DataService.CreateWorkbook(headers);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()),response.getOutputStream());
        }catch(IOException e){
            return new ResponseEntity<String>("Fail to download",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    // 지원자 엑셀 다운로드 하는 함수 Headers 값의 따라 목록이 늘어남.
    @GetMapping("/download/applicant")
    public ResponseEntity<?> DownloadApplicantExcel(HttpServletResponse response){
        response.setHeader("Content-Disposition", "attachment;filename=Applicant_input_here.xlsx");
        response.setContentType("application/octet-stream");
        String[] headers ={"수험번호","이름","성별","생년월일","이메일","전화번호","학위"};
        Workbook workbook = DataService.CreateWorkbook(headers);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()),response.getOutputStream());
        }catch(IOException e){
            return new ResponseEntity<String>("Fail to download",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 지원자 데이터를 엑셀로 받아오는 함수. csv 연동 아직 안됨. xls과 xlxs 두 가지만 가능
    @PostMapping(value = "/input/applicant",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> AddApplicantIntoExcel(@RequestParam MultipartFile file) throws IOException
    {
        try {
            DataService.ReadExcel(file);
        }catch (IOException e){
            return new ResponseEntity<String>("File was Broken",HttpStatus.BAD_REQUEST);
        }catch (InvalidFormatException e){
            return new ResponseEntity<String>("Invalid File Format",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 평가자 데이터를 엑셀로 받아오는 함수. csv 연동 아직 안됨. xls과 xlxs 두 가지만 가능
    @PostMapping(value = "/input/evaluator",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> AddEvaluatorIntoExcel(@RequestParam MultipartFile file) throws IOException
    {
        try {
            DataService.ReadExcel(file);
        }catch (IOException e){
            return new ResponseEntity<String>("File was Broken",HttpStatus.BAD_REQUEST);
        }catch (InvalidFormatException e){
            return new ResponseEntity<String>("Invalid File Format",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 4년제 대학교 데이터를 엑셀로 받아오는 함수. csv 파일에 UTF-8 고정
    @PostMapping("/input/univ")
    public ResponseEntity<?> UniversityIntoExcel(@RequestParam MultipartFile file) {
        List<SchoolDto> univList=null;
        try {
            univList = DataService.ConvertMultiFileIntoList(file);
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }

        for(SchoolDto university: univList){
            schoolSerivce.InsertUniv(university);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 2년제 대학교 데이터를 엑셀로 받아오는 함수. csv 파일에 UTF-8 고정
    @PostMapping("/input/college")
    public ResponseEntity<?> CollegeIntoExcel(@RequestParam MultipartFile file) {
        List<SchoolDto> collegeList = null;
        try {
            collegeList = DataService.ConvertMultiFileIntoList(file);
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }
        for(SchoolDto colloge: collegeList){
            schoolSerivce.InsertCollege(colloge);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 대학원 데이터를 엑셀로 받아오는 함수. csv 파일에 UTF-8 고정
    @PostMapping("/input/graduateschool")
    public ResponseEntity<?> GraduateSchoolIntoExcel(@RequestParam MultipartFile file){
        List<SchoolDto> GraduateList = null;
        try {
            GraduateList = DataService.ConvertMultiFileIntoList(file);
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }

        for(SchoolDto graduateSchool:GraduateList) {
            schoolSerivce.InsertGraduate(graduateSchool);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
