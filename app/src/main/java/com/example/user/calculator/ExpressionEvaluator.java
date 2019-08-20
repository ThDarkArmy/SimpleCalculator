package com.example.user.calculator;
import java.util.*;
import java.io.*;
public class ExpressionEvaluator {

//    public static void main(String[] args){
//        Scanner scan  = new Scanner(System.in);
//        String str = scan.nextLine();
//        //System.out.println(toPostfix(str));
//        try {
//            System.out.println(evaluate(toPostfix(str)));
//        } catch (Exception e) {
//            System.out.println("Error Occured!");
//        }
//    }

    String evaluate(String str){
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' ')
                continue;
            else if(str.charAt(i)>='0' && str.charAt(i)<='9'){
                int n=0;
                while(i<str.length() && str.charAt(i)>='0' && str.charAt(i)<='9'){
                    n=n*10+(str.charAt(i)-'0');
                    i++;
                }

                stack.push(n);
            }else{
                int op1 = stack.pop();
                int op2 = stack.pop();
                stack.push(calculate(op2,op1,str.charAt(i)));
            }
        }

        return stack.pop().toString();
    }

    int calculate(int op2,int op1, char o){
        switch(o){
            case '+': return op1+op2;
            case '-': return op2-op1;
            case '*': return op1*op2;
            case '/': return op2/op1;
        }
        return 0;
    }

    String toPostfix(String st){
        char ar[] = st.toCharArray();
        String str = "";
        Stack<Character> op = new Stack<>();

        for(int i=0;i<st.length();i++){
            if(ar[i]==' '){
                continue;
            }else if(ar[i]=='-' || ar[i]=='+' || ar[i]=='*' || ar[i]=='/'){
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
                while(i<st.length() && ar[i]>='0' && ar[i]<='9'){
                    str = str + Character.toString(ar[i]);
                    i++;
                }
                i--;
            }
        }str = str +" "+ Character.toString(op.peek());
        return str;
    }
    boolean hasPrecedence(char op1, char op2){
        //if (op2 == '(' || op2 == ')')
        //return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

}
