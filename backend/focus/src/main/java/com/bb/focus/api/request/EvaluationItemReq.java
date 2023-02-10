package com.bb.focus.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("EvaluationItemReq")
public class EvaluationItemReq {

    @ApiModelProperty(name="평가 항목이름", example = "성실성")
    private String content;

    @ApiModelProperty(name="평가항목 최대점수", example = "100")
    private Byte score;

}
