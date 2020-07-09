package com.calculator;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public Calculator() {

    }

    public String nhap(){
        String value;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap bieu thuc can tinh: ");
        value = sc.nextLine();
//        String regex = "[\\s]+";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher= pattern.matcher(value);
//        value = matcher.replaceAll("");
        value = value.replaceAll("\\s+","");

        return value;


    }

//    public static boolean laToanTu(String str){
//        String regex = "\\+|\\-|\\*|\\/";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher= pattern.matcher(str);
//        if(matcher.find()){
//            return true;
//        }
//        else {
//            return false;
//        }
//
//    }
//
//    public static boolean laToanHang(String str){
//        String regex = "^\\d+$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher= pattern.matcher(str);
//        if(matcher.find()){
//            return true;
//        }
//        else {
//            return false;
//        }
//
//    }

    //kiểm tra là toán tử hay không
    public boolean laToanTu(char c){
        if(c == '+' || c == '-' || c == '/' || c == '*' || c == '(' || c == ')'){
            return true;

        }
        else return false;
    }

    public boolean laToanTu1(char c){
        if(c == '+' || c == '-' || c == '/' || c == '*'){
            return true;
        }
        else return false;
    }
//    public static boolean laToanTu2(char c){
//        if(c == '+' || c == '/' || c == '*'){
//            return true;
//        }
//        else return false;
//    }

    //kiểm tra ngoặc
    public boolean kiemTraNgoac(String expr) {
        Stack<String> stack = new Stack<String>();
        try{
            for (int i = 0; i<expr.length(); i++){
                if (expr.charAt(i) == ('(')){
                    stack.push("(");
                } else if (expr.charAt(i) == (')')){
                    stack.pop();
                }
            }
            if (stack.isEmpty()){
                return true;
            } else {
                System.out.println("kiem tra lai ngoac ban oi");
                return false;
            }
        } catch (Exception e) {
            System.out.println("kiem tra lai ngoac ban oi");
            return false;
        }



//        String regex =  "\\((?:[^)(]+|\\((?:[^)(]+|\\([^)(]*\\))*\\))*\\)";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(expr);
//        if (matcher.find()) {
//            return true;
//        } else {
//            System.out.println("kiem tra lai ngoac ban oi");
//            return false;
//        }

    }

    //kiem tra đầu vào
    public boolean kiemTraKiTu(String str){
        String regex =  "^[\\d\\+\\/\\*\\-\\(\\)]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println("kiem tra lai ki tu ban oi");
            return false;
        }
    }



    //kiểm tra toán tử

    public boolean kiemTraToanTu(String str){
        boolean match = true;
        int length = str.length();

        for(int i=0; i<str.length()-1; i++){

            if(laToanTu1(str.charAt(length-1))||laToanTu1(str.charAt(0))){
                System.out.println("dau khong the o cuoi hay dau");
                match = false;
                break;
            }
            char c = str.charAt(i);
            char c2 = str.charAt(i + 1);
            if(laToanTu1(c) && laToanTu1(c2)){
                System.out.println("2 toan tu khong duoc canh nhau");
                match = false;
                break;
            }
            else if(c == ')' && c2 =='('){
                System.out.println("phai co toan tu giua 2 dau ngoac ban oi");
                match = false;
                break;
            }
            else if(laToanTu1(c) && c2 ==')'){
                System.out.println("sau toan tu khong the la dau ')' ");
                match = false;
                break;
            }
            else if(c == '(' && laToanTu1(c2)){
                System.out.println("sau '(' khong the la toan tu ");
                match = false;
                break;
            }
            else if(c == '(' && c2==')'){
                System.out.println("khong duduoc viet '()' nhe ban ");
                match = false;
                break;
            }
            else if(!laToanTu1(c) && c2 == '('){
                System.out.println("thieu toan tu");
                match = false;
                break;
            }
            else if(c==')' && !laToanTu1(c2)){
                System.out.println("thieu toan tu");
                match = false;
                break;
            }
        }
        return match;



    }


    //chuan hoa kí tự
    public String[] chuanHoaChuoi(String sMath){
        String s1 = "", elementMath[] = null;
        for (int i=0; i<sMath.length(); i++){
            char c = sMath.charAt(i);//sMath.substring(i,1);
            if (!laToanTu(c))
                s1 = s1 + c;
            else s1 = s1 + " " + c + " ";
        }
        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+"," "); //  chuan hoa s1
        elementMath = s1.split(" "); //tach s1 thanh cac phan tu
        //System.out.println(s1);
        return elementMath;
    }

    //thiet lap do ưu tiên
    public int doUuTien(char c){        // thiet lap thu tu uu tien
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else return 0;
    }


    //chuyen trung tố sang hậu tố

    public String[] hauTo(String[] elementMath){
        String s1 = "", E[];
        Stack <String> S = new Stack <String>();
        for (int i=0; i<elementMath.length; i++){    // duyet cac phan tu
            char c = elementMath[i].charAt(0);  // c la ky tu dau tien cua moi phan tu

            if (!laToanTu(c))         // neu c khong la toan tu
                s1 = s1 + " " + elementMath[i];     // xuat elem vao s1
            else{                       // c la toan tu
                if (c == '(') S.push(elementMath[i]);   // c la "(" -> day phan tu vao Stack
                else{
                    if (c == ')'){          // c la ")"
                        char c1;        //duyet lai cac phan tu trong Stack
                        do{
                            c1 = S.peek().charAt(0);    // c1 la ky tu dau tien cua phan tu
                            if (c1 != '(') s1 = s1 + " " + S.peek();    // trong khi c1 != "("
                            S.pop();

                        }while (c1 != '(');


                    }
                    else{
                        while (!S.isEmpty() && doUuTien(S.peek().charAt(0)) >= doUuTien(c)){
                            // Stack khong rong va trong khi phan tu trong Stack co do uu tien >= phan tu hien tai
                            s1 = s1 + " " + S.peek();   // xuat phan tu trong Stack ra s1
                            S.pop();
                        }
                        S.push(elementMath[i]); //  dua phan tu hien tai vao Stack
                    }
                }
            }
        }
        while (!S.isEmpty()){   // Neu Stack con phan tu thi day het vao s1
            s1 = s1 + " " + S.peek();
            S.pop();
        }
        E = s1.split(" ");  //  tach s1 thanh cac phan tu
        return E;
    }


    //ham cong, tru, nhan, chia
    public double cong(double a, double b){
        return a + b;
    }
    public double tru(double a, double b){
        return a - b;
    }
    public double nhan(double a, double b){

        return a * b;
    }
    public double chia(double a, double b) throws MyException {
        try {
            return a/b;
        }
        catch (Exception e){
            throw new MyException("loi chia cho 0");
        }

    }


    //tinh toan
    public String ketQua(String[] elementMath){
        Stack <String> S = new Stack<String>();
        Calculator  IFP = new Calculator();
        for (int i=1; i<elementMath.length; i++){
            char c = elementMath[i].charAt(0);
            if (!IFP.laToanTu(c)) S.push(elementMath[i]);
            else{
                double num = 0f;
                double num1 = Float.parseFloat(S.pop());
                double num2 = Float.parseFloat(S.pop());
                switch (c) {
                    case '+' : num = cong(num2, num1); break;
                    case '-' : num = tru(num2, num1); break;
                    case '*' : num = nhan(num2, num1); break;
                    case '/' :
                        try {
                            num = chia(num2, num1);
                        } catch (MyException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
                S.push(Double.toString(num));
            }
        }
        return S.pop();
    }


    public static void main(String[] args) {
        Calculator a = new Calculator();
        String input = a.nhap();
        while(!a.kiemTraKiTu(input)|| !a.kiemTraNgoac(input) || !a.kiemTraToanTu(input)){
            input = a.nhap();
        }

        String elementMath[] = null;
        elementMath = a.chuanHoaChuoi(input);
        elementMath = a.hauTo(elementMath);
        String kq = a.ketQua(elementMath);
        System.out.println("ket qua la: "+ kq);
    }
}
