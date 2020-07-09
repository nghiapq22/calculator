package com.calculator;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void latoantuTest1_kiemtrtoantu() {
        Calculator a = new Calculator();
        boolean expected1 = a.laToanTu1('+');
        boolean expected2 = a.laToanTu1('-');
        boolean expected3 = a.laToanTu1('*');
        boolean expected4 = a.laToanTu1('/');
        boolean expected5 = a.laToanTu1('t');
        Assert.assertEquals(expected1,true);
        Assert.assertEquals(expected2,true);
        Assert.assertEquals(expected3,true);
        Assert.assertEquals(expected4,true);
        Assert.assertEquals(expected5,false);
    }

    @Test
    public void latoantuTest_chuanhoachuoi(){
        Calculator a = new Calculator();
        boolean expected1 = a.laToanTu('+');
        boolean expected2 = a.laToanTu('-');
        boolean expected3 = a.laToanTu('*');
        boolean expected4 = a.laToanTu('/');
        boolean expected5 = a.laToanTu('(');
        boolean expected6 = a.laToanTu(')');
        boolean expected7 = a.laToanTu('t');
        Assert.assertEquals(expected1,true);
        Assert.assertEquals(expected2,true);
        Assert.assertEquals(expected3,true);
        Assert.assertEquals(expected4,true);
        Assert.assertEquals(expected5,true);
        Assert.assertEquals(expected6,true);
        Assert.assertEquals(expected7,false);
    }


    @Test
    public void kiemTraNgoacTest(){
        Calculator a = new Calculator();
        boolean expected1 = a.kiemTraNgoac("(3-3)*4");
        boolean expected2 = a.kiemTraNgoac("((3-");
        boolean expected3 = a.kiemTraNgoac("((3-4)-4+3)-(88");
        boolean expected4 = a.kiemTraNgoac("(3-2)-((((33");
        Assert.assertEquals(expected1, true);
        Assert.assertNotEquals(expected2,true);
        Assert.assertNotEquals(expected3,true);
        Assert.assertNotEquals(expected4,true);
    }

    @Test
    public void kiemTraKiTuTest(){
        Calculator a = new Calculator();
        boolean expected1 = a.kiemTraKiTu("3-4+2");
        boolean expected2 = a.kiemTraKiTu("4-3+gg+*$&&");
        boolean expected3 = a.kiemTraKiTu("");
        boolean expected4 = a.kiemTraKiTu("^%%^*()");
        Assert.assertEquals(expected1, true);
        Assert.assertNotEquals(expected2, true);
        Assert.assertNotEquals(expected3, true);
        Assert.assertNotEquals(expected4, true);
    }

    @Test
    public void kiemTraToanTuTest(){
        Calculator a = new Calculator();
        boolean expected1 = a.kiemTraToanTu("(1-2)-(2+3)");
        boolean expected2 = a.kiemTraToanTu("2+3-");
        boolean expected3 = a.kiemTraToanTu("(1-2)--(2+3");
        boolean expected4 = a.kiemTraToanTu("(1-2)(2+3");
        boolean expected5 = a.kiemTraToanTu("(1-2-)-(2+3");
        boolean expected6 = a.kiemTraToanTu("(-2)-(2+3");
        boolean expected7 = a.kiemTraToanTu("()-(2+3");
        Assert.assertEquals(expected1, true);
        Assert.assertEquals(expected2, false);
        Assert.assertEquals(expected3, false);
        Assert.assertEquals(expected4, false);
        Assert.assertEquals(expected5, false);
        Assert.assertEquals(expected6, false);
        Assert.assertEquals(expected7, false);
    }

    @Test
    public void chuanHoaChuoiTest(){
        Calculator a = new Calculator();
        String input = "11+2*3";
        String input1 = "(2-3)*4-444";
        String[] actual = a.chuanHoaChuoi(input);
        String[] actual1 = a.chuanHoaChuoi(input1);
        String[] e = {"11", "+", "2","*","3"};
        String[] e1 = {"1","1", "+", "2","*","3"};
        String[] e2 = {"(", "2", "-","3",")","*", "4", "-","444"};
        String[] e3 = {"(", "2", "-","3",")","*", "4", "-","44","4"};
        Assert.assertArrayEquals(e, actual);
        Assert.assertNotEquals(e1, actual);
        Assert.assertArrayEquals(e2, actual1);
        Assert.assertNotEquals(e3, actual);
    }

    @Test
    public void hauToTest(){
        Calculator a = new Calculator();
        String[] input = {"11", "+", "2","*","3"};;
        String[] input1 = {"1","*","2","+","3","*","(","(","4","-","5",")","+","6",")","/","7" };
        String[] actual = a.hauTo(input);
        String[] actual1 = a.hauTo(input1);
        String[] e = {"","11","2", "3", "*", "+"};
        String[] e1 = {"","11","2", "3", "+", "*"};
        String[] e2 = {"","1","2","*","3","4","5","-","6","+","*","7","/","+" };

        Assert.assertArrayEquals(e, actual);
        Assert.assertNotEquals(e1, actual);
        Assert.assertArrayEquals(e2, actual1);

    }

    @Test
    public void congTest(){
        Calculator a = new Calculator();
        double actual = a.cong(3,2);
        double expected = 5;
        double unexpected = 2;
        Assert.assertEquals(expected, actual, 0);
        Assert.assertEquals(15,a.cong(10,5) , 0);
        Assert.assertEquals(88, a.cong(11,77), 0);
        Assert.assertNotEquals(unexpected, actual);
    }

    @Test
    public void truTest(){
        Calculator a = new Calculator();
        Assert.assertEquals(1,a.tru(3.002,2),0.01);
        Assert.assertNotEquals(2,a.tru(3.002,2),0.01);
    }

    @Test
    public void nhanTest(){
        Calculator a =new Calculator();
        Assert.assertEquals(6,a.nhan(3,2), 0.1);
        Assert.assertNotEquals(7,a.nhan(3,2), 0.1);
    }

    @Test
    public void chiaTest() throws MyException {
        Calculator a = new Calculator();
        try{
            Assert.assertEquals(3,a.chia(6,2), 0.1);
            Assert.assertNotEquals(2,a.chia(6,2), 0.1);
        }
        catch (Exception e){
            throw new MyException("loi chia cho 0");
        }
    }

    @Test
    public void ketQuaTest(){
        Calculator a = new Calculator();
        String expected = "1.0";
        String expected1 = "2.0";
        String expected2 = "14.0";
        String[] input = {"","3","2","-"};
        String[] input2 = {"","9","2","-","5","6","2","/","-","*"};
        Assert.assertEquals(expected,a.ketQua(input));
        Assert.assertNotEquals(expected1,a.ketQua(input));
        Assert.assertEquals(expected2,a.ketQua(input2));
        Assert.assertNotEquals(expected1,a.ketQua(input2));
    }
}
