package com.bb.focus.api.controller;

import com.bb.focus.api.request.RoomReq;
import com.bb.focus.api.response.FaqRes;
import com.bb.focus.api.service.FaqService;
import com.bb.focus.api.service.RoomService;
import com.bb.focus.common.model.response.BaseResponseBody;
import com.bb.focus.db.entity.admin.Faq;
import com.bb.focus.db.entity.interview.Room;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "FAQ API", tags = {"FAQ"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

  private final FaqService faqService;

  @ApiOperation(value = "FAQ 리스트 조회", notes = "유저 계정에 따라 서로 다른 FAQ 리스트를 반환한다.")
  @GetMapping("/")
  public ResponseEntity<?> getFaqList(
      @ApiParam(value = "유저의 권한(기업 관리자 : 2, 평가자 : 3, 지원자 : 4", required = true) Byte userRole) {

    List<Faq> faqList = new ArrayList<>();
    List<FaqRes> results = new ArrayList<>();

    switch (userRole){
      case 2 : // 기업 관리자
        faqList = faqService.findAll();
        break;
      case 3 : // 평가자
        faqList = faqService.findByAuthRange((byte)3);
        break;
      case 4: // 지원자
        faqList = faqService.findByAuthRange((byte)4);
        break;
    }
    for(Faq faq : faqList){
      results.add(new FaqRes(
          faq.getId(),
          faq.getServiceAdmin().getId(),
          faq.getTitle(),
          faq.getCreatedAt(),
          faq.getContent(),
          faq.getAuthRange()
      ));
    }

    return ResponseEntity.status(200).body(results);
  }

}
