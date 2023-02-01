package com.bb.focus.api.service;

import com.bb.focus.db.entity.admin.ServiceNotice;
import com.bb.focus.db.repository.ServiceNoticeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceNoticeService")
public class ServiceNoticeServiceImpl implements ServiceNoticeService {

  @Autowired
  ServiceNoticeRepository serviceNoticeRepository;


  @Override
  public ServiceNotice save(ServiceNotice serviceNotice) {
    return serviceNoticeRepository.save(serviceNotice);
  }

  @Override
  public List<ServiceNotice> findAll() {
    return serviceNoticeRepository.findAll();
  }

  @Override
  public Optional<ServiceNotice> findById(Long id) {
    return serviceNoticeRepository.findById(id);
  }
}
