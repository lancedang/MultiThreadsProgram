package com.dang.book1.chapter02;

/**
 * Created by Dangdang on 2017/4/2.
 */
class PublicClass {
    private String name;
    private String pwd;

    class PrivateClass {
        private String age;
        private String sex;

        public void setAge(String age) {
            this.age = age;
        }

        public String getAge() {
            return age;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSex() {
            return sex;
        }

        public void printPublicClassInfo() {
            System.out.println("name = " + name + "; pwd = " + pwd);
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }
}

class PublicClass2 {
    private static String name;
    private static String pwd;

    static class PrivateClass2 {
        String age;
        String sex;

        public void setAge(String age) {
            this.age = age;
        }

        public String getAge() {
            return age;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSex() {
            return sex;
        }

        public void printPublicClassInfo() {
            System.out.println("name = " + name + " ; pwd = " + pwd);
        }

    }

    public static void setPwd(String pwd) {
        PublicClass2.pwd = pwd;
    }

    public static String getPwd() {
        return pwd;
    }

    public static void setName(String name) {
        PublicClass2.name = name;
    }

    public static String getName() {
        return name;
    }
}

public class InnerClassDemo {
    public static void main2(String[] sr) {
        PublicClass publicClass = new PublicClass();

        publicClass.setName("name");
        publicClass.setPwd("pwd");

        PublicClass.PrivateClass privateClass = publicClass.new PrivateClass();

        privateClass.setAge("age");
        privateClass.setSex("b");

        System.out.println("name = " + publicClass.getName() + "; pwd = " + publicClass.getPwd());
        privateClass.printPublicClassInfo();
        System.out.println("age = " + privateClass.getAge() + "; sex = " + privateClass.getSex());

    }

    public static void main(String[] sr) {
        PublicClass2 publicClass = new PublicClass2();
        publicClass.setName("name");
        publicClass.setPwd("pwd");

        System.out.println("name = " + publicClass.getName() + "; pwd = " + publicClass.getPwd());

        PublicClass2.PrivateClass2 privateClass2 = new PublicClass2.PrivateClass2();
        privateClass2.setSex("sex");
        privateClass2.setAge("agge");

        System.out.println("age = " + privateClass2.getAge()+ "; sex = " + privateClass2.getSex());

    }
}
