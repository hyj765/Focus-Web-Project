package com.bb.focus.api.response;

import com.bb.focus.db.entity.admin.ServiceAdmin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class ServiceAdminRes {
	@ApiModelProperty(name="User ID")
	String userId;
	
	public static ServiceAdminRes of(ServiceAdmin serviceAdmin) {
		ServiceAdminRes res = new ServiceAdminRes();
		res.setUserId(serviceAdmin.getUserId());
		return res;
	}
}
