package com.bw.fyc1227;

/*
 *@auther: 封英超
 *@Date: 2019/12/28
 *@Time:11:54
 *@Description:${DESCRIPTION}
 *
 */public class Bean {
     String name;
     int age;

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

    public Bean(String name, int age) {
        this.name = name;
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
