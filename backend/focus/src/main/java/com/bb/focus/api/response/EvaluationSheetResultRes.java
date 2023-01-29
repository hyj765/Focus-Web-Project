package com.bb.focus.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("EvaluationSheetResultRes")
public class EvaluationSheetResultRes {

    @ApiModelProperty(name="평가지 시퀀스 넘버", example = "1")
    private Long evaluationSheetId;
    @ApiModelProperty(name="평가항목 아이템", example = "1")
    private Long id;

    @ApiModelProperty(name="평가항목 제목", example = "성실성")
    private String content;

    @ApiModelProperty(name="평가항복 최대점수", example = "20")
    private Byte Maxscore;

    @ApiModelProperty(name="해당 항목의 근거",example = "전문성이 뛰어나보임 1 질문에 대해 답을 못함")
    private  String reason;

    @ApiModelProperty(name="해당 항목의 점수",example = "해당 내용의 점수")
    private Byte score;
}
