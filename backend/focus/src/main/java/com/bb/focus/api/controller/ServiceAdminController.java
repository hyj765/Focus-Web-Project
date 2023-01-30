package com.bb.focus.api.controller;

import com.bb.focus.api.request.CompanyAdminRegisterPostReq;
import com.bb.focus.api.request.ServiceAdminRegisterPostReq;
import com.bb.focus.api.response.CompanyAdminRes;
import com.bb.focus.api.response.ServiceAdminRes;
import com.bb.focus.api.service.CompanyAdminService;
import com.bb.focus.api.service.ServiceAdminService;
import com.bb.focus.common.auth.FocusUserDetails;
import com.bb.focus.common.model.response.BaseResponseBody;
import com.bb.focus.db.entity.admin.ServiceAdmin;
import com.bb.focus.db.entity.company.CompanyAdmin;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"ServiceUser"})
@RestController
@RequestMapping("/serviceusers")
public class ServiceAdminController {

    @Autowired
    ServiceAdminService serviceAdminService;

    @Autowired
    CompanyAdminService companyAdminService;

    @PostMapping()
    @ApiOperation(value = "관리자 계정 생성", notes = "<strong>아이디와 패스워드</strong>를 통해 관리자의 계정을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) ServiceAdminRegisterPostReq registerInfo) {

        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        ServiceAdmin serviceAdmin = serviceAdminService.createUser(registerInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PostMapping("/accounts/")
    @ApiOperation(value = "기업 관리자 계정 생성", notes = "<strong>아이디와 패스워드</strong>를 통해 기업관리자의 계정을 생성해준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> registerCompanyAdmin(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) CompanyAdminRegisterPostReq registerInfo) {

        CompanyAdmin companyAdmin = companyAdminService.createCompanyAdmin(registerInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/accounts/")
    @ApiOperation(value = "기업 계정 리스트 정보 조회", notes = "서비스 관리자가 조회할 수 있으며, 등록되어 있는 기업 계정 리스트 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<CompanyAdminRes>> getCompanyAdminInfoList() {
        List<CompanyAdmin> companyAdminList = companyAdminService.getAll();
        List<CompanyAdminRes> companyAdminResList = new ArrayList<>();
        for (CompanyAdmin ca : companyAdminList) {
            companyAdminResList.add(CompanyAdminRes.of(ca));
        }
        return ResponseEntity.status(200).body(companyAdminResList);
    }


    @GetMapping("/accounts/{company-admin-id}")
    @ApiOperation(value = "기업 계정 정보 조회", notes = "서비스 관리자가 조회할 수 있으며, 등록되어 있는 기업 계정 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<CompanyAdminRes> getCompanyAdminInfo(String userId) {
        CompanyAdmin companyAdmin = companyAdminService.getCompanyAdminByUserId(userId);
        return ResponseEntity.status(200).body(CompanyAdminRes.of(companyAdmin));
    }

    @PutMapping("/accounts/{company-admin-id}")
    @ApiOperation(value = "기업 계정 정보 조회", notes = "서비스 관리자가 조회할 수 있으며, 등록되어 있는 기업 계정 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateCompanyAdminInfo(
            @RequestBody @ApiParam(value = "기업 관리자 정보 수정 내역", required = true) CompanyAdminRegisterPostReq updateInfo) {
        Long companyAdminId = companyAdminService.updateCompanyAdminByUserInfo(updateInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
    @GetMapping("/me")
    public ResponseEntity<ServiceAdminRes> getUserInfo(@ApiIgnore Authentication authentication) {
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */
        FocusUserDetails userDetails = (FocusUserDetails) authentication.getDetails();
        Long id = userDetails.getUser().getId();
        ServiceAdmin serviceAdmin = serviceAdminService.getServiceAdminById(id);

        return ResponseEntity.status(200).body(ServiceAdminRes.of(serviceAdmin));
    }
}
