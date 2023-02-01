package com.bb.focus.api.service;

import com.bb.focus.api.request.RoomReq;
import com.bb.focus.common.util.EncryptionUtils;
import com.bb.focus.db.entity.admin.Faq;
import com.bb.focus.db.entity.interview.Room;
import com.bb.focus.db.repository.FaqRepository;
import com.bb.focus.db.repository.RoomRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("faqService")
public class FaqServiceImpl implements FaqService {

  @Autowired
  FaqRepository faqRepository;

  @Override
  public List<Faq> findAll() {
    return faqRepository.findAll();
  }

  @Override
  public List<Faq> findByAuthRange(Byte userRole) {
    return faqRepository.findByAuthRange(userRole);
  }
}
