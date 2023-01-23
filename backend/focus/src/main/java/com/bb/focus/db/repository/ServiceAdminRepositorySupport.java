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
        ServiceAdmin serviceAdmin = jpaQueryFactory.select(qServiceAdmin).from(qServiceAdmin)
                .where(qServiceAdmin.userId.eq(userId)).fetchOne();
        if(serviceAdmin == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(serviceAdmin);
    }
}
