package com.bb.focus.api.service;

import com.bb.focus.api.request.ServiceAdminRegisterPostReq;
import com.bb.focus.db.entity.admin.ServiceAdmin;
import com.bb.focus.db.repository.ServiceAdminRepository;
import com.bb.focus.db.repository.ServiceAdminRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("serviceAdminService")
public class ServiceAdminServiceImpl implements ServiceAdminService {

    @Autowired
    ServiceAdminRepository serviceAdminRepository;

    @Autowired
    ServiceAdminRepositorySupport serviceAdminRepositorySupport;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ServiceAdmin createUser(ServiceAdminRegisterPostReq serviceAdminRegisterInfo) {
        ServiceAdmin serviceAdmin = new ServiceAdmin();
        serviceAdmin.setUserId(serviceAdminRegisterInfo.getId());
        serviceAdmin.setPwd(passwordEncoder.encode(serviceAdminRegisterInfo.getPassword()));
        return serviceAdminRepository.save(serviceAdmin);
    }

    @Override
    public ServiceAdmin getServiceAdminByUserId(String userId) {
        System.out.println("ServiceAdminServiceImpl까지 도달 >> "+"userId : "+userId);
        ServiceAdmin serviceAdmin = serviceAdminRepositorySupport.findServiceAdminByUserId(userId).orElse(new ServiceAdmin()); // 이 형식이 빈 ServiceAdmin이어야하는데 형식이 달라서 안되고 있는 상황
        System.out.println("ServiceAdminServiceImpl에 serviceAdmin값 받아온 상태 >> "+serviceAdmin.toString());
        return serviceAdmin;
    }
}
