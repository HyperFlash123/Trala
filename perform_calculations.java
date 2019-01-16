package hello;

import java.util.Stack;

public class perform_calculations 
{
	int a;
	int b;
	String exp[];
	String converted[];
	private int l1,l2;
	public int add()
	{
		return a+b;
	}
	public int sub()
	{
		return a-b;
	}
	public int mul()
	{
		return a*b;
	}
	public int div()
	{
		return a/b;
	}
	public int Prec(char ch)
    {
        switch (ch)
        {
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
            return 2;
      
        case '^':
            return 3;
        }
        return -1;
    }
      
   
    public String infixToPostfix()
    {
         
         int s=0;
        Stack<Character> stack = new Stack<>();
         
        for (int i = 0; i<l1; ++i)
        {
           
             
              if (exp[i].charAt(0)>=48&&exp[i].charAt(0)<=57)
                {converted[s++] = exp[i];}
              
              else if (exp[i].charAt(0) == '(')
                stack.push(exp[i].charAt(0));
             
             else if (exp[i].charAt(0) == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                { converted[s]="";converted[s++] += stack.pop();}
                 
                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression";      
                else
                    stack.pop();
            }
            else 
            {
                while (!stack.isEmpty() && Prec(exp[i].charAt(0)) <= Prec(stack.peek()))
                { converted[s]="";converted[s++] += stack.pop();}
                stack.push(exp[i].charAt(0));
            }
      
        }
      
        
        while (!stack.isEmpty())
        { converted[s]="";converted[s++] += stack.pop();}
         l2=s;
        return "";
    }
    public int evaluatePostfix()
    {
        //create a stack
        Stack<Integer> stack=new Stack<>();
         
        
        for(int i=0;i<l2;i++)
        {
           
            if(converted[i].charAt(0)>=48&&converted[i].charAt(0)<=57)
            {int m = Integer.parseInt(converted[i]);stack.push(m);}
             
            //  If the scanned character is an operator, pop two
            // elements from stack apply the operator
            else
            {
                int val1 = stack.pop();
                int val2 = stack.pop();
                 char c = converted[i].charAt(0);
                switch(c)
                {
                    case '+':
                    stack.push(val2+val1);
                    break;
                     
                    case '-':
                    stack.push(val2- val1);
                    break;
                     
                    case '/':
                    stack.push(val2/val1);
                    break;
                     
                    case '*':
                    stack.push(val2*val1);
                    break;
              }
            }
        }
        return stack.pop();    
    }
    public void convert(String str)
    {
    	int i;
    	int s=0;
    	char ch;
    	for(i=0;i<str.length();i++)
    	{
    		ch = str.charAt(i);
    		if(ch=='('||ch==')'||ch=='+'||ch=='-'||ch=='/'||ch=='*')
    		{
    			exp[s]="";
    			exp[s]+=ch;s++;
    		}
    		else
    		{
    			exp[s]="";
    			while(ch>=48&&ch<=57&&i<str.length())
    			{
    				exp[s]+=ch;
    				i++;
    				if(i<str.length())
    				ch=str.charAt(i);
    			}
    			s++;i--;
    		}
    	}
    	l1=s;
    }
    public String isError(String expression)
    {
    	
    	int i;
    	char b1=0,b2=0;
    	for(i=0;i<expression.length();i++)
    	{
    		char ch = expression.charAt(i);
    		char ch1=' ';char ch2=' ';
    		if(i!=0)
    		ch1=expression.charAt(i-1);
    		if(i!=expression.length()-1)
    		ch2=expression.charAt(i+1);
    		
    		if(ch=='('||ch==')'||ch=='+'||ch=='-'||ch=='*'||ch=='/'||(ch1>=48&&ch1<=57))
    		{}
    		else
    			return "invalid";
    		if(ch=='+'||ch=='-'||ch=='*'||ch=='/')
    		{
    			if(i==0||i==expression.length()-1) return "invalid";
    			if(((expression.charAt(i-1)>=48&&expression.charAt(i-1)<=57)&&(expression.charAt(i+1)>=48&&expression.charAt(i+1)<=57))||(ch1=='('&&ch2==')'))
    			{}
    			else return "invalid";
    		}
    		if(ch=='(')
    		{
    			b1++;
    			if((ch1=='+'||ch1=='-'||ch1=='*'||ch1=='/')&&(ch2>=48&&ch2<=57))
    			{}
    			return  "invalid";
    		}
    		if(ch==')')
    		{
    			b2++;
    			if((ch2=='+'||ch2=='-'||ch2=='*'||ch2=='/')&&(ch1>=48&&ch1<=57))
    			{}
    			return  "invalid";
    		}
    		
    		
    	}
    	if(b1==b2){}
    	else return "invalid";  
    	
    	exp= new String [100];
    	converted = new String [100];
    	convert(expression);
    	String str = infixToPostfix();
    	//if(str.length()!=0)
    	return str;
    }
    public int result(String expression)
    {
    	exp= new String [100];
    	converted = new String [100];
    	convert(expression);
    	String str = infixToPostfix();
    	return evaluatePostfix();
    }
	
}
