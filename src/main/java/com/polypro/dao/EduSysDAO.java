package com.polypro.dao;

import java.util.List;


abstract public class EduSysDAO<EntityType, KeyType> {//generic
    abstract public void insert(EntityType entity);//EntityType: NhanVien, KhoaHoc...
    abstract public void update(EntityType entity);
    abstract public void delete(KeyType id);//KeyType: String, Integer, Double...
    abstract public EntityType selectById(KeyType id);
    abstract public List<EntityType> selectAll();
    abstract protected List<EntityType> selectBySql(String sql, Object...args);
}
