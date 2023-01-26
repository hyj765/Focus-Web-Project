package com.bb.focus.db.repository;

import com.bb.focus.db.entity.company.CompanyAdmin;
import com.bb.focus.db.entity.company.QCompanyAdmin;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyAdmincustomRepositoryImpl implements CompanyAdminCustomRepository{
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QCompanyAdmin qCompanyAdmin = QCompanyAdmin.companyAdmin;

    public Optional<CompanyAdmin> findCompanyAdminByUserId(String userId) {
        CompanyAdmin companyAdmin = jpaQueryFactory.select(qCompanyAdmin).from(qCompanyAdmin)
                .where(qCompanyAdmin.userId.eq(userId)).fetchOne();
        if (companyAdmin == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(companyAdmin);
    }

    public List<CompanyAdmin> findAll() {
        List<CompanyAdmin> companyAdminList = jpaQueryFactory.select(qCompanyAdmin).from(qCompanyAdmin).fetch();
        if (companyAdminList == null) {
            return null;
        }
        return companyAdminList;
    }

    public Long updateCompanyAdminByUserId(CompanyAdmin companyAdmin) {
        Long companyAdminId = jpaQueryFactory.update(qCompanyAdmin).where(qCompanyAdmin.userId.eq(companyAdmin.getUserId()))
                .set(qCompanyAdmin.companyName, companyAdmin.getCompanyName())
                .set(qCompanyAdmin.userId, companyAdmin.getUserId())
                .set(qCompanyAdmin.email, companyAdmin.getEmail())
                .set(qCompanyAdmin.startDate, companyAdmin.getStartDate())
                .set(qCompanyAdmin.endDate, companyAdmin.getEndDate())
                .set(qCompanyAdmin.industry, companyAdmin.getIndustry())
                .set(qCompanyAdmin.logoImage, companyAdmin.getLogoImage())
                .set(qCompanyAdmin.name, companyAdmin.getName())
                .set(qCompanyAdmin.skinColor, companyAdmin.getSkinColor())
                .execute();

        return companyAdminId;
    }
}
