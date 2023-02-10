package com.bb.focus.api.request;

import com.bb.focus.db.entity.applicant.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("InterviewResultReq")
public class InterviewResultReq {
  @ApiModelProperty(name="사용자 번호", example = "1")
  long applicantId;
  @ApiModelProperty(name="합불 여부", example = "p, np")
  String pass;
}
