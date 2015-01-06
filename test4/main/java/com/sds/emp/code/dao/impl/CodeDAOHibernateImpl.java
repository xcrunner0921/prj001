package com.sds.emp.code.dao.impl;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sds.emp.code.dao.CodeDAO;

/**
 * This CodeDAO implements class contains
 * IDynamicHibernateService to get
 * IDynamicHibernateService services , findCodeList
 * method for functionality on Code.
 * @author Taeho Kim
 */
@Repository("codeDAOHibernate")
public class CodeDAOHibernateImpl extends HibernateDaoSupport implements
        CodeDAO {

    @Resource
    private SessionFactory sessionFactory;

    @PostConstruct
    public void initialize() {
        setSessionFactory(this.sessionFactory);
    }

    public Collection findCodeList(String codeType) throws Exception {

        return (this.getHibernateTemplate().findByNamedQueryAndNamedParam(
            "getCodeList", "codeType", codeType));

    }
}
