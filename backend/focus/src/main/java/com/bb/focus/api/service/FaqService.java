package com.bb.focus.api.service;

import com.bb.focus.api.request.RoomReq;
import com.bb.focus.db.entity.admin.Faq;
import com.bb.focus.db.entity.interview.Room;
import java.util.List;

public interface FaqService {

  List<Faq> findAll();

  List<Faq> findByAuthRange(Byte userRole);
}
