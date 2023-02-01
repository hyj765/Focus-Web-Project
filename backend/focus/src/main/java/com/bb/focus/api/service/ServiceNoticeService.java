package com.bb.focus.api.service;

import com.bb.focus.api.request.RoomReq;
import com.bb.focus.db.entity.admin.ServiceNotice;
import com.bb.focus.db.entity.interview.Room;
import java.security.Provider.Service;
import java.util.List;
import java.util.Optional;

public interface ServiceNoticeService {
  ServiceNotice save(ServiceNotice serviceNotice);

  List<ServiceNotice> findAll();

  Optional<ServiceNotice> findById(Long id);
}
