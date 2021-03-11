/*
 * Copyright: 2020 jinguo.tech Inc. All rights reserved.
 * 注意：本内容所有权归属于jinguo.tech 未征得作者同意情况下:
 * 不得擅自将其用于商业或其他环境，否则将会承担相应的法律责任。
 *
 * @projectName guli-parent
 * @packageName PACKAGE_NAME
 * @fileName Test1
 * @author jinguo
 * @email felix@jinguo.tech
 * @date 2021-03-10 00:31:20
 *
 */

/**
 * @author jinguo
 * @fileName Test1
 * @description 模拟连接查询
 * @email felix@jinguo.tech
 * @date 2021-03-10 00:31:20
 */
public class Test1 {
    public static class Table1 {
        int a;

        public Table1(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "Table1{" +
                    "a=" + a +
                    '}';
        }

        public Table1 build(int a) {
            return new Table1(a);
        }
    }

    public static class Table2 {
        int b;

        public Table2(int b) {
            this.b = b;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "Table2{" +
                    "b=" + b +
                    '}';
        }

        public Table2 build(int b) {
            return new Table2(b);
        }
    }

    public static class Record<R1, R2> {
        R1 r1;
        R2 r2;

        public Record(R1 r1, R2 r2) {
            this.r1 = r1;
            this.r2 = r2;
        }

        public R1 getR1() {
            return r1;
        }

        @Override
        public String toString() {
            return "Record{" +
                    "r1=" + r1 +
                    ", r2=" + r2 +
                    '}';
        }

        public void setR1(R1 r1) {
            this.r1 = r1;
        }

        public R2 getR2() {
            return r2;
        }

        public void setR2(R2 r2) {
            this.r2 = r2;
        }

        public static <R1,R2> Record<R1,R2> build(R1 r1,R2 r2){
                return new Record(r1,r2);
        }

    }


}