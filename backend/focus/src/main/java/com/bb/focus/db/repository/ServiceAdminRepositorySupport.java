package com.bb.focus.db.repository;

import com.bb.focus.db.entity.admin.ServiceAdmin;
import com.bb.focus.db.entity.admin.QServiceAdmin;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ServiceAdminRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QServiceAdmin qServiceAdmin = QServiceAdmin.serviceAdmin;

    public Optional<ServiceAdmin> findServiceAdminByUserId(String userId) {
        System.out.println("step1");
        ServiceAdmin serviceAdmin = jpaQueryFactory.select(qServiceAdmin).from(qServiceAdmin)
                .where(qServiceAdmin.userId.eq(userId)).fetchOne();
        System.out.println("step2");
        if(serviceAdmin == null) {
            System.out.println("step3");
            return Optional.empty();
        }
        System.out.println("step4");
        return Optional.ofNullable(serviceAdmin);
    }
}
