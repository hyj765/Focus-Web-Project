package com.bb.focus.api.service;

import com.bb.focus.db.entity.admin.ServiceNoticeCategory;
import java.util.List;

public interface ServiceNoticeCategoryService {

  List<ServiceNoticeCategory> findAll();
}