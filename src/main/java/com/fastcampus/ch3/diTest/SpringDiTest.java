package com.fastcampus.ch3.diTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component("engine")
class Engine{}
@Component class SuperEngine extends Engine {}
@Component class TurboEngine extends Engine {}
@Component
class Door{}
@Component
class Car{
  @Value("red")String color;
  @Value("100") int oil;
  @Autowired // by type
  @Qualifier("superEngine")
  //@Resource(name="superEngine") // by Name
  Engine engine;

  @Autowired Door[] doors;

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getOil() {
    return oil;
  }

  public void setOil(int oil) {
    this.oil = oil;
  }

  public Engine getEngine() {
    return engine;
  }

  public void setEngine(Engine engine) {
    this.engine = engine;
  }

  public Door[] getDoors() {
    return doors;
  }

  public void setDoors(Door[] doors) {
    this.doors = doors;
  }

  @Override
  public String toString() {
    return "Car{" +
        "color='" + color + '\'' +
        ", oil=" + oil +
        ", engine=" + engine +
        ", doors=" + Arrays.toString(doors) +
        '}';
  }
}


public class SpringDiTest {
  public static void main(String[] args) {
    ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//    Car car = (Car) ac.getBean("car"); // 아래와 같음
    Car car = (Car) ac.getBean("car", Car.class);

//    Engine engine = (Engine) ac.getBean("engine");
//    Engine engine = (Engine) ac.getBean("superEngine");
//    Door door = (Door) ac.getBean("door");

//    car.setColor("red");
//    car.setOil(100);
//    car.setEngine(engine);
//    car.setDoors(new Door[]{ac.getBean("door",Door.class),ac.getBean("door",Door.class)});

    System.out.println("car = " + car);
//    car = com.fastcampus.ch3.diTest.Car@957e06
//    car2 = com.fastcampus.ch3.diTest.Car@957e06
//    기본적으로 Singleton 방식으로 객체 생성하므로 내용이 같으면 주소값 동일
//    주소를 다르게 하고 싶으면 config.xml파일에서 bean의 scope를 prototype으로.
  }
}
