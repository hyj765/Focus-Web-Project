package com.bb.focus.api.response;

import com.bb.focus.db.entity.admin.ServiceAdmin;
import com.bb.focus.db.entity.admin.ServiceNoticeCategory;
import com.bb.focus.db.entity.evaluator.Evaluator;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@ApiModel("ServiceNoticeRes")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceNoticeRes {

  @ApiModelProperty(name="서비스 공지사항 시퀀스 넘버", example = "1")
  private Long id;

  @ApiModelProperty(name="작성한 서비스 관리자의 시퀀스 넘버", example = "1")
  private Long serviceAdminId;

  @ApiModelProperty(name="해당 글의 카테고리 시퀀스 넘버", example = "1")
  private Long categoryId;

  @ApiModelProperty(name="제목", example = "1")
  private String title;

  @ApiModelProperty(name="작성 시각", example = "1")
  private LocalDateTime createdAt;

  @ApiModelProperty(name="내용", example = "1")
  private String content;
}
