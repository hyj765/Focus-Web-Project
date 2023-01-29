package com.bb.focus.api.service;


import com.bb.focus.api.response.SchoolDto;
import com.bb.focus.common.util.ExcelUtils;
import com.bb.focus.db.entity.applicant.Applicant;
import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.statistics.ApplicantStatistic;
import com.bb.focus.db.entity.statistics.MajorPerApplicant;
import com.bb.focus.db.repository.MajorStatisticRepository;
import com.bb.focus.db.repository.StaticalRepository;
import java.util.Map;
import javax.transaction.Transactional;
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
public class DataProcessServiceImpl implements DataProcessService{

    MajorStatisticRepository majorStatisticRepo;
    StaticalRepository statisticRepo;
    ApplicantService applicantService;
    ExcelUtils exelUtils;
    @Autowired
    public DataProcessServiceImpl(ExcelUtils eutil,ApplicantService aservice, StaticalRepository sRepo, MajorStatisticRepository majorRepo){
        applicantService = aservice;
        exelUtils = eutil;
        statisticRepo = sRepo;
        majorStatisticRepo = majorRepo;
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



    //통계함수들

    // 통계 테이블에서 지원자 평균 나이를 가져오는 함수
    public long GetAvgAge(long processId){
        ApplicantStatistic applicantStatisticLog =statisticRepo.findById(processId);
        return applicantStatisticLog.getAvgApplicantAge();
    }

    // 통계 테이블에서 지원자 남성과 여성 비율을 가져오는 함수
    public Map<String,Integer> GetGenders(long processId){
        Map<String, Integer> Genders = null;
        ApplicantStatistic applicantStatisticLog =statisticRepo.findById(processId);

        Genders.put("female",applicantStatisticLog.getFemaleCount());
        Genders.put("male",applicantStatisticLog.getMaleCount());

        return Genders;
    }

    // 통계 테이블에서 각 지원자 전공 별 인원을 가져오는 함수 반환되는 데이터는 각 학과 Key 인원 Value로 반환됨.
    public Map<String,Integer> GetMajorPerApplicant(long processId){
        Map<String, Integer> StatisticMajorData = null;
        ApplicantStatistic applicantStatisticLog =statisticRepo.findById(processId);

        List<MajorPerApplicant> majorPerApplicantList=applicantStatisticLog.getMajorPerApplicantList();

        for(MajorPerApplicant majorPerApplicant:majorPerApplicantList){
            StatisticMajorData.put(majorPerApplicant.getMajorName(),majorPerApplicant.getApplicantCount());
        }

        return StatisticMajorData;
    }

    // 통계 테이블에서 지원자 평균 수상 횟수를 반환하는 함수
    public long GetAwardPerApplicant(long processId){
        ApplicantStatistic applicantStatisticLog =statisticRepo.findById(processId);
        return applicantStatisticLog.getAvgAwardCount();
    }
    // 통계 테이블에서 지원자 평균 대외활동 수를 반환하는 함수
    public long GetActivityPerApplicant(long processId){
        ApplicantStatistic applicantStatisticLog =statisticRepo.findById(processId);
        return applicantStatisticLog.getAvgApplicantAge();

    }

    //합격자의 테이블 로그를 가져오는 함수.
    public Map<String,Integer> GetResultPerApplicant(long processId){
        Map<String, Integer> ResultPerApplicant = null;
        // 보류


        return null;
    }

    // PDF의 형태로 통계 데이터를 반환해주는 함수.
    public MultipartFile ExtractStaticResultIntoPDF(){




        return null;
    }

    @Transactional
    public boolean setStatisticTable(long processId){
        // 전형 얻어오고 -> 전형 process 넣어주고 -> 각 값들 받아서 set해주고 -> save
        ApplicantStatistic data= new ApplicantStatistic();
        //->data.setProcessId(processId);

        statisticRepo.save(data);




        return true;
    }

    // PROCESSID를 통하여 전형에 맞는 통계 테이블을 호출 후 해당 통계 테이블에 학과 데이터를 추가하는 코드
    @Transactional
    public boolean setMajorPerCountData(long procecssId, String majorName, int count){
       ApplicantStatistic applicantStatistic =statisticRepo.findById(procecssId);
       MajorPerApplicant majorPerApplicant = new MajorPerApplicant();

       majorPerApplicant.setMajorName(majorName);
       majorPerApplicant.setApplicantCount(count);
       majorPerApplicant.setApplicantStatistic(applicantStatistic);

       majorStatisticRepo.save(majorPerApplicant);
       return true;
    }

}
