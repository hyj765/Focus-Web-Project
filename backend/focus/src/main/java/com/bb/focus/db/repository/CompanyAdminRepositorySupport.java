package com.bb.focus.db.repository;

import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.company.QCompanyAdmin;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CompanyAdminRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QCompanyAdmin qCompanyAdmin = QCompanyAdmin.companyAdmin;

    public Optional<CompanyAdmin> findCompanyAdminByUserId(String userId) {
        CompanyAdmin companyAdmin = jpaQueryFactory.select(qCompanyAdmin).from(qCompanyAdmin)
                .where(qCompanyAdmin.userId.eq(userId)).fetchOne();
        if(companyAdmin == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(companyAdmin);
    }
}
