package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class A1DaoTest {

  @Autowired
  A1Dao a1Dao;

  @Autowired
  DataSource ds;

  @Test
  public void insertTest() throws Exception {
    //아래 두 쿼리가 성공했을 때만 커밋 되게 하려면?
    // Tx 매니저 생성
    PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
    TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
    // Tx 시작

    try {
      a1Dao.deleteAll();
      a1Dao.insert(1,100);
      a1Dao.insert(1,200);
      tm.commit(status);
    } catch (Exception e) {
      e.printStackTrace();
      tm.rollback(status);
    } finally {
    }
  }
}