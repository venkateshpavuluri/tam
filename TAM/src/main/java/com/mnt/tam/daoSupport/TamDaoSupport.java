package com.mnt.tam.daoSupport;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class TamDaoSupport extends HibernateDaoSupport{
	@Autowired
    public void termDao(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }

}
