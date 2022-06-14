//package com.fastcampus.ch3;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//
////@Component ("engine")
//class Engine{} //<bean id="engine" class="com.fastcampus.ch3.Engine"/>
//@Component class SuperEngine extends Engine{}
//@Component class TurboEngine extends Engine{}
//@Component
//class Door{}
//@Component
//class Car{
//  @Value("red")
//  String color;
//  @Value("100") int oil;
////  @Autowired //byType -> 주로 사용
////  @Qualifier("superEngine") // 이름이 같을 때 특정 객체를 지정하여 생성 가능
//  @Resource(name="superEngine") // byName 위와 같은 결과, 방법은 다름
//  Engine engine; //byType로 작동 -> 타입으로 먼저 검색, 여러 개면 이름으로 검색
//  @Autowired Door[] doors;
//
//  public Car() {} // xml에서 <constructor-arg>를 사용할 때는 기본 생성자 수동정의 필
//
//  public Car(String color, int oil, Engine engine, Door[] doors) {
//    this.color = color;
//    this.oil = oil;
//    this.engine = engine;
//    this.doors = doors;
//  }
//
//  public void setColor(String color) {
//    this.color = color;
//  }
//
//  public void setOil(int oil) {
//    this.oil = oil;
//  }
//
//  public void setEngine(Engine engine) {
//    this.engine = engine;
//  }
//
//  public void setDoors(Door[] doors) {
//    this.doors = doors;
//  }
//  @Override
//  public String toString() {
//    return "Car{" +
//        "color='" + color + '\'' +
//        ", oil=" + oil +
//        ", engine=" + engine +
//        ", doors=" + Arrays.toString(doors) +
//        '}';
//  }
//}
//
//public class SpringDiTest {
//  public static void main(String[] args) {
//    ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
////    Car car = (Car) ac.getBean("car"); // byName. 아래와 같은 문장
//    Car car = (Car) ac.getBean("car"); // byName. 아래와 같은 문장
////    Car car2 = ac.getBean("car",Car.class); // 타입 정보를 주고 형변환 생략
////    Engine engine = (Engine) ac.getBean("engine"); // byName
////    Engine engine = (Engine) ac.getBean(Engine.class); // byType -> 에러, Engine 타입이 여러 개
////    Engine engine = (SuperEngine) ac.getBean("superEngine");
////    Door door = (Door) ac.getBean("door"); // byName
//
////    car.setColor("red");
////    car.setOil(100);
////    car.setEngine(engine);
////    car.setDoors(new Door[]{ac.getBean("door", Door.class),(Door)ac.getBean("door")});
//
//    System.out.println("car = " + car);
////    System.out.println("engine = " + engine);
//  }
//}
