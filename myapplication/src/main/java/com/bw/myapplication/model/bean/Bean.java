package com.bw.myapplication.model.bean;

/*
 *@auther: 封英超
 *@Date: 2019/12/30
 *@Time:11:06
 *@Description:${DESCRIPTION}
 *
 */public class Bean {
     String name;
     int age;

    public Bean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
