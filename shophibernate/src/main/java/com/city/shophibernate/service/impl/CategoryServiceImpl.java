package com.city.shophibernate.service.impl;

import com.city.shophibernate.model.CategoryModel;
import com.city.shophibernate.service.ICategoryService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private SessionFactory sessionFactory = null;

    @Override
    public int add(CategoryModel cm) throws Exception {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().save(cm);
        return cm.getId();
    }

    @Override
    public int modify(CategoryModel cm) throws Exception {
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().update(cm);
        return cm.getId();
    }

    @Override
    public int delete(CategoryModel cm) throws Exception {
        int result=cm.getId();
        sessionFactory.getCurrentSession().clear();
        sessionFactory.getCurrentSession().delete(cm);
        return result;
    }

    @Override
    public CategoryModel getByIdWithoutGoods(int id) throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select cm from CategoryModel cm where cm.id=:id",CategoryModel.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public CategoryModel getByIdWithGoods(int id) throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select cm from CategoryModel cm left outer join cm.goods gm where cm.id=:id",CategoryModel.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public List<CategoryModel> getListByAll() throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select cm from CategoryModel cm",CategoryModel.class)
                .getResultList();
    }

    @Override
    public List<CategoryModel> getListByAllWithGoods() throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select cm from CategoryModel cm left outer join cm.goods gm",CategoryModel.class)
                .getResultList();
    }

    @Override
    public List<CategoryModel> getListByAllWithPage(int page, int rows) throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select cm from CategoryModel cm",CategoryModel.class)
                .setFirstResult(rows*(page-1))
                .setMaxResults(rows)
                .getResultList();
    }

    @Override
    public int getCountByAll() throws Exception {
        return sessionFactory.getCurrentSession().createQuery("select count(cm.id) from CategoryModel cm",Long.class)
                .getSingleResult()
                .intValue();
    }

    @Override
    public int getPageCountByAll(int rows) throws Exception {
        int count = getCountByAll();
        return count % rows == 0 ? count / rows : count / rows + 1;
    }
}
