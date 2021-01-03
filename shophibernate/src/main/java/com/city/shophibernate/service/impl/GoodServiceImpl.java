package com.city.shophibernate.service.impl;

import com.city.shophibernate.model.AreaModel;
import com.city.shophibernate.model.GoodInfoModel;
import com.city.shophibernate.model.GoodModel;
import com.city.shophibernate.service.IGoodService;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GoodServiceImpl implements IGoodService {

    @Autowired
    private SessionFactory sessionFactory = null;

    @Override
    public int add(GoodModel gm) throws Exception {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().save(gm);
        GoodInfoModel gim = gm.getGoodInfo();
        gim.setGood(gm);
        sessionFactory.getCurrentSession().save(gim);
        return gm.getId();
    }

    @Override
    public int modify(GoodModel gm) throws Exception {
        sessionFactory.getCurrentSession().clear();
        GoodInfoModel gim = gm.getGoodInfo();
        System.out.println(gm.getId());
        gim.setId(gm.getId());
        if (gim.getPhoto() != null) {
            sessionFactory.getCurrentSession().update(gim);
        }
        sessionFactory.getCurrentSession().update(gm);
        return gm.getId();
    }

    @Override
    public int delete(GoodModel gm) throws Exception {
        int result = gm.getId();
        deleteBehaves(gm.getId());
        GoodInfoModel gim = gm.getGoodInfo();
        gim.setId(gm.getId());
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().delete(gim);
        sessionFactory.getCurrentSession().delete(gm);
        return result;

    }

    @Override
    public GoodModel getByIdWithoutCategory(int id) throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select gm from GoodModel gm where gm.id=:id", GoodModel.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public GoodModel getByIdWithCategory(int id) throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select gm from GoodModel gm left outer join gm.category cm where gm.id=:id", GoodModel.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public GoodModel getByIdWithCategoryAndGoodInfoAndAreas(int id) throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select gm from GoodModel gm " +
                "left outer join gm.category cm " +
                "left outer join gm.goodInfo gim " +
                "left outer join gm.areas gam where gm.id=:id", GoodModel.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<GoodModel> getListByAll() throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select gm from GoodModel gm", GoodModel.class)
                .getResultList();
    }

    @Override
    public List<GoodModel> getListByAllWithCategory() throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select gm from GoodModel gm left outer join gm.category cm", GoodModel.class)
                .getResultList();
    }

    @Override
    public List<GoodModel> getListByAllWithPage(int page, int rows, int category) throws Exception {
        String hql = "select gm from GoodModel gm where 1=1";
        if (category != 0) {
            hql += " and gm.category.id=:category";
        }
        Query<GoodModel> query = sessionFactory.getCurrentSession().createQuery(hql, GoodModel.class);
        if (category != 0) {
            query.setParameter("category", category);
        }
        return query.setFirstResult(rows * (page - 1))
                .setMaxResults(rows)
                .getResultList();
    }

    @Override
    public int getCountByAll(int category) throws Exception {
        String hql = "select count(gm.id) from GoodModel gm where 1=1";
        if (category != 0) {
            hql += " and gm.category.id=:category";
        }
        Query<Long> query = sessionFactory.getCurrentSession().createQuery(hql, Long.class);
        if (category != 0) {
            query.setParameter("category", category);
        }
        return query.getSingleResult()
                .intValue();
    }

    @Override
    public int getPageCountByAll(int rows, int category) throws Exception {
        int count = getCountByAll(category);
        return count % rows == 0 ? count / rows : count / rows + 1;
    }

    @Override
    public int addArea(int id, int AreaId) throws Exception {
        sessionFactory.getCurrentSession().clear();
        GoodModel gm = sessionFactory.getCurrentSession().get(GoodModel.class, id);
        gm.getAreas().clear();
        AreaModel am = sessionFactory.getCurrentSession().get(AreaModel.class, AreaId);
        gm.getAreas().add(am);
        return gm.getId();
    }

    @Override
    public int addAreas(int id, int[] AreaIds) throws Exception {
        sessionFactory.getCurrentSession().clear();
        GoodModel gm = sessionFactory.getCurrentSession().get(GoodModel.class, id);
        gm.getAreas().clear();
        for (int AreaId : AreaIds) {
            AreaModel am = sessionFactory.getCurrentSession().get(AreaModel.class, AreaId);
            gm.getAreas().add(am);
        }
        return gm.getId();
    }

    @Override
    public int deleteBehaves(int id) throws Exception {
        sessionFactory.getCurrentSession().clear();
        GoodModel gm = sessionFactory.getCurrentSession().get(GoodModel.class, id);
        gm.getAreas().clear();
        return gm.getId();
    }

    @Override
    public GoodInfoModel getByIdWithPhoto(int id) throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select gim from GoodInfoModel gim where gim.id=:id", GoodInfoModel.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
