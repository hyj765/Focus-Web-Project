package com.bb.focus.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class EvaluationSheetReq {

  @Getter
  @Setter
  public static class Create{
    @ApiModelProperty(name = "평가지 제목", example = "제목제목")
    private String sheetName;
  }

}
