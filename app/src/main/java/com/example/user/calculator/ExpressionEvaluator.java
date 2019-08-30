package com.example.user.calculator;

import java.util.*;
import java.io.*;

class ExpressionEvaluator{
//    public static void main(String[] args)throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter the expression!");
//        String str = br.readLine();
//        System.out.println("Postfix: "+toPostfix(str));
//        try {
//            System.out.println("Result: "+evaluate(toPostfix(str)));
//        } catch (Exception e) {
//            System.out.println("Error Occured!");
//        }
//        br.close();
//    }


    //Evaluation of postfix erxpression
    static Double evaluate(String str){
        Stack<Double> stack = new Stack<>();

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' ')
                continue;
            else if((str.charAt(i)>='0' && str.charAt(i)<='9') || str.charAt(i)=='.'){
                String n="";
                while(i<str.length() && str.charAt(i)>='0' && str.charAt(i)<='9'){
                    n=n+Character.toString(str.charAt(i));
                    i++;
                }
                String dc="";
                if(i<str.length() && str.charAt(i)=='.'){
                    i++;
                    while(i<str.length() && str.charAt(i)>='0' && str.charAt(i)<='9'){
                        dc=dc+Character.toString(str.charAt(i));
                        i++;
                    }

                }
                if(dc.length()>0){
                    n=n+dc;
                }
                double num=Double.parseDouble(n);
                double number = num/(dc.length()>0?(dc.length())*10:1);
                System.out.println("NUM: "+num);
                System.out.println("Pushed: "+number);
                stack.push(number);
            }else{
                double op1 = stack.pop();
                double op2 = stack.pop();
                stack.push(calculate(op2,op1,str.charAt(i)));
            }
        }

        return stack.pop();
    }

    static double calculate(double op2,double op1, char o){
        switch(o){
            case '+': return op1+op2;
            case '-': return op2-op1;
            case '*': return op1*op2;
            case '/': return op2/op1;
            case '^': return Math.pow(op2,op1);
        }
        return 0;
    }

    //infix to postfix expression 

    static String toPostfix(String st){
        char ar[] = st.toCharArray();
        String str = "";
        Stack<Character> op = new Stack<>();

        for(int i=0;i<st.length();i++){
            if(ar[i]==' '){
                continue;
            }else if(ar[i]=='-' || ar[i]=='+' || ar[i]=='*' || ar[i]=='/' || ar[i]=='^'){
                while(!op.empty()  && op.peek()!='(' && hasPrecedence(ar[i],op.peek())){
                    str = str +" "+ Character.toString(op.peek());
                    op.pop();
                }
                op.push(ar[i]);
            }else if(ar[i]=='('){
                op.push(ar[i]);
            }else if(ar[i]==')'){
                while(!op.empty() && op.peek()!='('){
                    str = str +" "+ Character.toString(op.peek());
                    op.pop();
                }
                op.pop();
            }else{
                str = str +" "+ Character.toString(ar[i]);
                i++;
                String dc="";
                while(i<st.length() && (ar[i]>='0' && ar[i]<='9')){
                    str = str + Character.toString(ar[i]);
                    i++;
                }

                if(i<st.length() && ar[i]=='.'){
                    i++;
                    while(i<st.length() && ar[i]>='0' && ar[i]<='9'){
                        dc = dc + Character.toString(ar[i]);
                        i++;
                    }

                    if(dc.length()>0){
                        str=str+"."+dc;
                    }
                }
                i--;
            }

        }
        while(!op.empty()){
            str = str +" "+ Character.toString(op.peek());
            op.pop();
        }
        return str;
    }

    static boolean hasPrecedence(char op1, char op2){
        if(op1=='^')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
}