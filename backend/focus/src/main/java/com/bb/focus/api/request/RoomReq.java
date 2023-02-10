package com.bb.focus.api.request;

import com.bb.focus.db.entity.interview.Interview;
import com.bb.focus.db.entity.interview.InterviewRoom;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 관련 API ([POST] /login) 요청에 필요한 리퀘스트 바디 정의.
 */

public class RoomReq {

    @Getter
    @Setter
    public static class Create {
        @ApiModelProperty(name = "면접 ID", example = "1")
        Long interviewId;
    }


}
