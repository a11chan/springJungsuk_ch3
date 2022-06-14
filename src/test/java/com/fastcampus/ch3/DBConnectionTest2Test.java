package com.fastcampus.ch3;

import com.mysql.cj.protocol.Resultset;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.*;
import java.sql.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test {

  @Autowired
  DataSource ds; //JUnit TEST에서는 IV를 공유하지 않음, 별도 객체 생성하여 실행

  @Test
  public void insertUserTest() throws Exception {
    User user = new User("jkl","1234","abc","aaa@gmail.com", new java.util.Date(), "fb",new java.util.Date());
    deleteAll();
    int rowCnt = insertUser(user);

    assertTrue(rowCnt==1);
  }

  @Test
  public void selectUserTest() throws Exception {
    deleteAll();
    User user = new User("jkl","1234","abc","aaa@gmail.com", new java.util.Date(), "fb",new java.util.Date());
    int rowCnt = insertUser(user);
    User user2 = selectUser("jkl");
    assertTrue(user2.getId().equals("jkl"));
  }

  @Test
  public void deleteUserTest() throws Exception {
    deleteAll();
    int rowCnt = deleteUser("jkl");
    assertTrue(rowCnt==0);

    User user = new User("jkl","1234","abc","aaa@gmail.com", new java.util.Date(), "fb",new java.util.Date());
    rowCnt = insertUser(user);
    assertTrue(rowCnt==1);

    rowCnt = deleteUser(user.getId());
    assertTrue(rowCnt==1);

    assertTrue(selectUser(user.getId())==null);
  }

  @Test
  public void updateUserTest() throws Exception {
    deleteAll();
    User user = new User("jkl","1234","abc","aaa@gmail.com", new java.util.Date(), "fb",new java.util.Date());
    insertUser(user);
    int rowCnt = updateUserPwd("jkl","qwerty");
    assertTrue(rowCnt==1);
  }

  // 매개변수로 받은 사용자 정보로 user_info테이블을 update하는 메서드
  public int updateUserPwd(String id, String newPwd) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "update user_info set pwd = ? where id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1,newPwd);
    pstmt.setString(2,id);
    return pstmt.executeUpdate();
  }

  public int deleteUser(String id) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "delete from user_info where id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql); //SQL Injection 공격 방지, 성능향상
    pstmt.setString(1,id);
//    int rowCnt = pstmt.executeUpdate(); //insert, delete, update
    return pstmt.executeUpdate();
  }

  public User selectUser(String id) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "select * from user_info where id = ?";

    PreparedStatement pstmt = conn.prepareStatement(sql); //SQL Injection 공격 방지, 성능향상
    pstmt.setString(1,id);
    ResultSet rs = pstmt.executeQuery(); // select의 결과는 ResultSet

    if(rs.next()) {
      User user = new User();
      user.setId(rs.getString(1));
      user.setPwd(rs.getString(2));
      user.setName(rs.getString(3));
      user.setEmail(rs.getString(4));
      user.setBirth(new java.util.Date(rs.getDate(5).getTime()));
      user.setSns(rs.getString(6));
      user.setReg_date(new java.util.Date(rs.getTimestamp(7).getTime()));

      return user;
    }
    return null;
  }

  private void deleteAll() throws Exception {
    Connection conn = ds.getConnection();
//    insert into user_info values ('uiop','1234','sam','aaa@gmail.com','1988-01-02','youtube',now());
    String sql = "delete from user_info";
    PreparedStatement pstmt = conn.prepareStatement(sql); //SQL Injection 공격 방지, 성능향상
    pstmt.executeUpdate(); //insert, delete, update
  }

  //사용자 정보를 user_info 테이블에 저장하는 메서드
  public int insertUser(User user) throws Exception {
    Connection conn = ds.getConnection();
//    insert into user_info values ('uiop','1234','sam','aaa@gmail.com','1988-01-02','youtube',now());
    String sql = "insert into user_info values (?,?,?,?,?,?,now())";

    PreparedStatement pstmt = conn.prepareStatement(sql); //SQL Injection 공격 방지, 성능향상
    pstmt.setString(1,user.getId());
    pstmt.setString(2,user.getPwd());
    pstmt.setString(3,user.getName());
    pstmt.setString(4,user.getEmail());
    pstmt.setDate(5,new Date(user.getBirth().getTime()));
    pstmt.setString(6,user.getSns());
    
    int rowCnt = pstmt.executeUpdate(); //insert, delete, update

    return rowCnt;
  }

  @Test
  public void jdbcConnectionTest() throws Exception {

//    ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//    DataSource ds = ac.getBean(DataSource.class);

    Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

    System.out.println("conn = " + conn);
    assertTrue(conn!=null);
  }
}