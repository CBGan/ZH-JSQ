import java.math.*;
import java.text.*;
import org.xidea.el.*;

class RealNumber //实数
{

}

class RationalNumber extends RealNumber//有理数 包括小数和整数
{

    //常量，用来表示有理数加减乘除
    public static final int ADD = 0;
    public static final int MINUS = 1;
    public static final int MULTIOLY = 2;
    public static final int DEVIDE = 3;

    int numerator;//分子
    int denominator;//分母

    RationalNumber( int numerator,int denominator) {
        this.denominator = denominator;
        this.numerator = numerator;
    }

    boolean isInteger()//判断有理数是否是整数
    {
        this.simplificate();
        return this.denominator == 1;
    }

    public static boolean isInteger(RationalNumber rational_number) {
        return rational_number.isInteger();
    }

    public String toString()//重写toString方法
    {
        return String.valueOf(this.numerator) + "/" + String.valueOf(this.denominator);
    }

    void simplificate()//化简有理数
    {
        RationalNumber rn=this.simplificate(this);
        this.numerator=rn.numerator;
        this.denominator=rn.denominator;
    }

    public static RationalNumber simplificate(RationalNumber rational_number)//化简有理数
    {
        /*hxp备用算法,写于2015年8月17日
        /*原理：通过循环寻找公约数，在1000以内质数里匹配，找到就分子分母同时
        *除以公约数，直到找不到为止
        final int[] less_than_1000_prime_numbers={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,
                                                  53,59,61,67,71,73,79,83,89,97,101,103,107,
                                                  109,113,127,131,137,139,149,151,157,163,167,
                                                  173,179,181,191,193,197,199,211,223,227,229,
                                                  233,239,241,251,257,263,269,271,277,281,283,
                                                  293,307,311,313,317,331,337,347,349,353,359,
                                                  367,373,379,383,389,397,401,409,419,421,431,
                                                  433,439,443,449,457,461,463,467,479,487,491,
                                                  499,503,509,521,523,541,547,557,563,569,571,
                                                  577,587,593,599,601,607,613,617,619,631,641,
                                                  643,647,653,659,661,673,677,683,691,701,709,
                                                  719,727,733,739,743,751,757,761,769,773,787,
                                                  797,809,811,821,823,827,829,839,853,857,859,
                                                  863,877,881,883,887,907,911,919,929,937,941,
                                                  947,953,967,971,977,983,991,997};//1000以内质数，共168个
        boolean isSimplest = false;
        while(isSimplest==false)
        {
            for(int i=0;i<less_than_1000_prime_numbers.length;i++)
            {
                if(rational_number.numerator%less_than_1000_prime_numbers[i]==0 && rational_number.denominator%less_than_1000_prime_numbers[i]==0)
                {
                    rational_number.numerator=rational_number.numerator/less_than_1000_prime_numbers[i];
                    rational_number.denominator=rational_number.denominator/less_than_1000_prime_numbers[i];
                    break;
                }else if(i==less_than_1000_prime_numbers.length-1)
                {
                    isSimplest=true;
                    break;
                }
            }
        }
        */
        //ybr的算法,忘了原理是什么了,有bug也没办法了

        int a=2;
        boolean set = true;
        double a1 = rational_number.numerator, a2 = rational_number.denominator;
        while (set) {
            if (rational_number.numerator% a == 0 && rational_number.denominator % a == 0) {
                rational_number.numerator = rational_number.numerator / a;
                rational_number.denominator = rational_number.denominator / a;
            } else {
                if (a == a1 + 1 || a == a2 + 1 || 0 - a == a1 - 1 || 0 - a == a2 - 1) {
                    set = false;
                } else a++;
            }
        }

        //然后化简符号，分子分母都是负的就把负号都削掉，一正一负，负号放分子上
        if (rational_number.numerator < 0 && rational_number.denominator < 0) {
            rational_number.numerator = -rational_number.numerator;
            rational_number.denominator = -rational_number.denominator;
        }
        if (rational_number.numerator > 0 && rational_number.denominator < 0) {
            rational_number.numerator = -rational_number.numerator;
            rational_number.denominator = -rational_number.denominator;
        }
        return rational_number;
    }

    public static RationalNumber rationalNumberOperation(RationalNumber rational_number1,RationalNumber rational_number2,int operation_symbol_id)//有理数四则运算，加减乘除的id是0，1，2，3。
    {
        RationalNumber result=new RationalNumber(0,1);
        switch (operation_symbol_id)
        {
            case 0:
                result.numerator=rational_number1.numerator*rational_number2.denominator+rational_number2.numerator*rational_number1.denominator;
                result.denominator=rational_number1.denominator*rational_number2.denominator;
                result.simplificate();
                break;
            case 1:
                result.numerator=rational_number1.numerator*rational_number2.denominator-rational_number2.numerator*rational_number1.denominator;
                result.denominator=rational_number1.denominator*rational_number2.denominator;
                result.simplificate();
                break;
            case 2:
                result.numerator=rational_number1.numerator*rational_number2.numerator;
                result.denominator=rational_number1.denominator*rational_number2.denominator;
                result.simplificate();
                break;
            case 3:
                result.numerator=rational_number1.numerator*rational_number2.denominator;
                result.denominator=rational_number2.numerator*rational_number1.denominator;
                result.simplificate();
                break;
        }
        return result;
    }


    //private RealNumber add(RealNumber real_number_1, RealNumber real_number_2) {

    //}

}

class Radical extends RealNumber//根式
{
    //根式就是一个有理数与一个被开方数相乘的形式,例如 (2/3)*√(5)
    RationalNumber the_number_before_radical_sign = new RationalNumber(0, 1);//根号前面的那个有理数，例如刚刚那个2/3
    RationalNumber the_number_under_radical_sign = new RationalNumber(0, 1);//根号下被开方数

    Radical(RationalNumber the_number_before_radical_sign, RationalNumber the_number_under_radical_sign) {
        this.the_number_before_radical_sign = the_number_before_radical_sign;
        this.the_number_under_radical_sign = the_number_under_radical_sign;
    }

    public String toString() {
        return String.valueOf("(" + this.the_number_before_radical_sign.toString() + ")*√(" + this.the_number_under_radical_sign.toString() + ")");
    }

    public static Radical simplificate(Radical radical) {
        //先把复杂的根式搞成一个被开方数除以一个整数的形式
        radical = new Radical(new RationalNumber(1, radical.the_number_under_radical_sign.denominator * radical.the_number_before_radical_sign.denominator * radical.the_number_before_radical_sign.denominator), new RationalNumber(radical.the_number_under_radical_sign.numerator * radical.the_number_before_radical_sign.numerator * radical.the_number_before_radical_sign.numerator * radical.the_number_under_radical_sign.denominator * radical.the_number_before_radical_sign.denominator * radical.the_number_before_radical_sign.denominator, 1));
        //再化简分数线上面被开方数搞成几倍根好几的形式
        int possible_number_before_radical_sign = radical.the_number_under_radical_sign.numerator + 1;
        //原理：一个一个从上往下试，找根号前面的数
        while (true) {
            possible_number_before_radical_sign--;
            if (radical.the_number_under_radical_sign.numerator % (possible_number_before_radical_sign * possible_number_before_radical_sign) == 0) {
                radical.the_number_before_radical_sign.numerator = possible_number_before_radical_sign;
                radical.the_number_under_radical_sign.numerator = radical.the_number_under_radical_sign.numerator / (possible_number_before_radical_sign * possible_number_before_radical_sign);
                break;
            }
        }
        //最后化简根号前面的有理数
        radical.the_number_before_radical_sign.simplificate();

        return radical;
    }
}

public class SDTMath {
    public static double Expression(String A) {
        /*
		 *此为JSEL的算法简化函数
		 *作用:将近五行代码才能调用JSEL的代码化简为只需要一行代码
		 *调用方法:Expression("我是解析式");
		 *输入为一个String的算式
		 *输出为double的结果
		 */
        ExpressionFactory factory = ExpressionFactory.getInstance();
        Expression el = factory.create(A);
        double result = Double.parseDouble(el.evaluate() + "");
        return result;
    }

//    public static Double[] fractionroot(Integer A1, Integer A2) {  /*
//		*[分数开根函数1.0]
//		*注：必须和root和ST函数一起使用
//	    */
//        Double[] re = new Double[3];
//        double[] root = new double[2];
//        Double[] ST = new Double[2];
//        double n1 = 0, n2 = 0, N1 = Double.parseDouble(A1 + ""), N2 = Double.parseDouble(A2 + "");
//        n1 = N1 * N2;
//        root = root((int) n1);
//        n2 = root[0];
//        n1 = root[1];
//        //ST = ST(n2, N2);
//        re[0] = ST[0];
//        re[1] = n1;
//        re[2] = ST[1];
//        return re;
//    }

    /*public static Double[] FM(Integer A1, Integer A2, Integer K, Integer B1, Integer B2) {
		 *[分数基本计算函数2.0]
		 *注：必须和ST函数一起使用

        double n1 = 0, n2 = 0;
        Double[] re = new Double[2];
        if (K == 0) {//A+B
            n1 = A1 * B2 + B1 * A2;
            n2 = B2 * A2;
            re = ST(n1, n2);
        }
        if (K == 1) {//A-B
            n1 = A1 * B2 - B1 * A2;
            n2 = B2 * A2;
            re = ST(n1, n2);
        }
        if (K == 2) {//AxB
            n1 = A1 * B1;
            n2 = B2 * A2;
            re = ST(n1, n2);
        }
        if (K == 3) {//A÷B
            n1 = A1 * B2;
            n2 = B1 * A2;
            re = ST(n1, n2);
        }
        return re;
    }
    */

//    public static Double[] ST(Double n1, Double n2) {	/*
//		 *[分数化简函数4.0]
//		 *n1分子，n2分母
//		 *含有质数的分数初步化简
//		 */
//        Double[] re = new Double[2];
//        String N1 = "" + n1, N2 = "" + n2;
//        int a = 2;
//        boolean set = true, T2 = true, T3 = true, T5 = true, T7 = true;
//        double a1 = n1, a2 = n2;
//        while (set) {
//            if (n2 % a == 0 && n1 % a == 0) {
//                n1 = n1 / a;
//                n2 = n2 / a;
//            } else {
//                if (a == a1 + 1 || a == a2 + 1 || 0 - a == a1 - 1 || 0 - a == a2 - 1) {
//                    set = false;
//                } else a++;
//            }
//        }
//        re[0] = n1;
//        re[1] = n2;
//        return re;
//    }

    public static double[] FunctionInterpreter(String equation) {
		/*说明：
		 *FunctionInterpreter用来分析函数解析式
		 *用方法FunctionInterpreter
		 *返回double[]
		 *[0,1,2]分别为A,B,C
		 *[3]为状态值,0=正常,1=出错
		 *需要JSEL库支持以及附带的JSEL简化函数
		 *算法:
		 *先把x，x²都换成0，计算出所有常数的和
		 *再把x换成1，计算并与第一次结果相减，得出x，x²的总数
		 *把x换成一个数n，计算并与第一次结果相减，出这次所有x和x²的和
		 *用第三次计算结果减去第二次计算结果的n倍再除以(n-1)计算x²个数
		 *@author hxp
		 */
        String expression = equation.replace("=", "-(") + "+0)";
        //移项，把等式右边的移到左边
        //我们之所以要在后面加上个"+0"是因为如果方程是x+1=0的话，移项移过去是x+1-(0)，会错误。而x+1-(0+0)不会
        double[] result = new double[6];
        try {
            Integer n = 800011;
            double value_without_x = SDTMath.Expression(expression.replace("x", "0"));
            double value_with_x_1 = SDTMath.Expression(expression.replace("x", "1"));
            double value_with_x_n = SDTMath.Expression(expression.replace("x", String.valueOf(n)));
            double a = ((value_with_x_n - n * value_with_x_1) / (n - 1)) / n;
            double b = value_with_x_1 - a - value_without_x;//这行代码有错误
            double c = value_without_x;
            //四舍五入 网上抄的代码，弥补CPU的浮点计算先天缺陷造成的极其微小的误差
            DecimalFormat df = new DecimalFormat("#.####");
            a = Double.parseDouble(df.format(a));
            b = Double.parseDouble(df.format(b));
            c = Double.parseDouble(df.format(c));
            result[0] = a;
            result[1] = b;
            result[2] = c;
            result[3] = 0;
        } catch (Exception e) {
            result[3] = 1;
        }
        return result;
    }

    public static double[] hackEquation(String equation) {
		/*说明：
		 *EquationHacker用来解方程
		 *用方法hackEuqation(方程)
		 *返回double[]
		 *一元一次返回的double[0]为解
		 *一元二次返回的double[0,1]为方程两个解,[2]为∆
		 *[3]为状态值，出错=0，一次方程=1，二次方程=2
		 *[4,5]为LR值
		 *需要JSEL库以及附带的JSEL简化函数支持
		 *算法:
		 *先把x，x²都换成0，计算出所有常数的和
		 *再把x换成1，计算并与第一次结果相减，得出x，x²的总数
		 *把x换成一个数n，计算并与第一次结果相减，出这次所有x和x²的和
		 *用第三次计算结果减去第二次计算结果的n倍再除以(n-1)计算x²个数
		 *@author hxp
		 */
        String expression = equation.replace("=", "-(") + "+0)";
        //移项，把等式右边的移到左边
        //我们之所以要在后面加上个"+0"是因为如果方程是x+1=0的话，移项移过去是x+1-(0)，会错误。而x+1-(0+0)不会
        double[] result = new double[10];
        try {
            Integer n = 800011;
            double value_without_x = SDTMath.Expression(expression.replace("x", "0"));
            double value_with_x_1 = SDTMath.Expression(expression.replace("x", "1"));
            double value_with_x_n = SDTMath.Expression(expression.replace("x", String.valueOf(n)));
            double a = ((value_with_x_n - n * value_with_x_1) / (n - 1)) / n;
            double b = value_with_x_1 - a - value_without_x;//这行代码有错误
            double c = value_without_x;
            //四舍五入 网上抄的代码，弥补CPU的浮点计算先天缺陷造成的极其微小的误差
            DecimalFormat df = new DecimalFormat("#.####");
            a = Double.parseDouble(df.format(a));
            b = Double.parseDouble(df.format(b));
            c = Double.parseDouble(df.format(c));
            if (a == 0) {
                //一元一次
                result[0] = -c / b;
                result[1] = 0;
                result[2] = 0;
                result[3] = 1;
                result[4] = 0 - b * result[0] - c;
                result[5] = 0;
            } else {
                //一元二次
                int delta = (int) (b * b - 4 * a * c);
                if (delta >= 0) {
                    int K = 0, c1 = delta + 1, e = 0;
                    try {
                        while (K == 0) {
                            c1--;
                            if (delta % (c1 * c1) == 0) {
                                K = 1;
                            }
                        }
                        int d = c1 * c1;
                        e = delta / d;
                        if (e == 1) {
                            e = 0;
                        }
                        int T = 2;
                        boolean set = true;
                        double a1 = -b, a2 = c1, a3 = 2 * a, B = -b, A = 2 * a;
                        while (set) {
                            if (B % T == 0 && c1 % T == 0 && A % T == 0) {
                                A = A / a;
                                c1 = c1 / Integer.parseInt("" + a);
                                B = B / a;

                            } else {
                                if (T == a1 + 1 || T == a2 + 1 || T == a3 + 1 || 0 - T == a3 - 1 || 0 - T == a1 - 1 || 0 - T == a2 - 1) {
                                    set = false;
                                } else a++;
                            }
                        }
                    } catch (Exception k) {
                    }
                    result[0] = (-b + Math.sqrt(delta)) / (2 * a);
                    result[1] = (-b - Math.sqrt(delta)) / (2 * a);
                    result[2] = delta;
                    result[4] = 0 - a * result[0] * result[0] - b * result[0] - c;
                    result[5] = 0 - a * result[1] * result[1] - b * result[1] - c;
                    result[6] = -b;
                    result[7] = c1;
                    result[8] = e;
                    result[9] = 2 * a;
                } else {
                    result[0] = 0;
                    result[1] = 0;
                    result[2] = delta;
                    result[4] = 0;
                    result[5] = 0;
                    result[6] = 0;
                    result[7] = 0;
                    result[8] = 0;
                    result[9] = 0;
                }
                result[3] = 2;
            }
        } catch (Exception e) {
            result[3] = 0;
        }
        return result;
    }

//    public static double[] root(Integer N) {	/*
//		 *[开根函数1.0]
//		 *[0]为根号前数字
//		 *[1]为根号后数字
//		 */
//        double[] re = new double[2];
//        int K = 0, c = N + 1;
//        while (K == 0) {
//            c--;
//            if (N % (c * c) == 0) {
//                K = 1;
//            }
//        }
//        int d = c * c;
//        double e = N / d;
//        if (e == 1) {
//            e = 0;
//        }
//        re[0] = Double.parseDouble("" + c);
//        re[1] = Double.parseDouble("" + e);
//        return re;
//    }
}
