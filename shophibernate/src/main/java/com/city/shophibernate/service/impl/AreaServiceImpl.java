package com.city.shophibernate.service.impl;

import com.city.shophibernate.model.AreaModel;
import com.city.shophibernate.service.IAreaService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.geom.Area;
import java.util.List;

@Service
@Transactional
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private SessionFactory sessionFactory = null;

    @Override
    public int add(AreaModel am) throws Exception {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().save(am);
        return am.getId();
    }

    @Override
    public int modify(AreaModel am) throws Exception {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().update(am);
        return am.getId();
    }

    @Override
    public int delete(AreaModel am) throws Exception {
        int result=am.getId();
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().delete(am);
        return result;
    }

    @Override
    public AreaModel getByIdWithoutGoods(int id) throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select am from AreaModel am where am.id=:id",AreaModel.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public AreaModel getByIdWithGoods(int id) throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select am from AreaModel am left outer join am.goods gm where am.id=:id",AreaModel.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public List<AreaModel> getByGoodIdWithAreas(int id) throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select am from AreaModel am left outer join am.goods gm where gm.id=:id",AreaModel.class)
                .setParameter("id",id)
                .getResultList();
    }

    @Override
    public List<AreaModel> getListByAll() throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select am from AreaModel am",AreaModel.class)
                .getResultList();
    }

    @Override
    public List<AreaModel> getListByAllWithGoods() throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select am from AreaModel am left outer join am.goods gm",AreaModel.class)
                .getResultList();
    }

    @Override
    public List<AreaModel> getListByAllWithPage(int page, int rows) throws Exception {
//        int i=10/0;
        return sessionFactory.getCurrentSession().createQuery("select am from AreaModel am",AreaModel.class)
                .setFirstResult(rows*(page-1))
                .setMaxResults(rows)
                .getResultList();
    }

    @Override
    public int getCountByAll() throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select count(am.id) from AreaModel am",Long.class)
                .getSingleResult()
                .intValue();
    }

    @Override
    public int getPageCountByAll(int rows) throws Exception {
        int count = getCountByAll();
        return count % rows == 0 ? count / rows : count / rows + 1;
    }
}
