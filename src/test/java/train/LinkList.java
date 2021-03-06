package train;

import org.testng.annotations.Ignore;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Ignore
public class LinkList {


    public int[] fibo(int count){
        List<Integer> fiboArr = new ArrayList<>();
        fiboArr.add(1);
        fiboArr.add(1);
        for (int i = 2; i <count; i++) {
            fiboArr.add((fiboArr.get(i-2) + fiboArr.get(i-1)));

        }
            return fiboArr.stream().mapToInt(x-> x).toArray();
    }

    public BigInteger find(int let){
        BigInteger result = BigInteger.valueOf(1);
        for (int i = let; i >=1 ; i--) {
            result=BigInteger.valueOf(i).multiply(result);

        }
        return let>0? result : BigInteger.valueOf(0);
    }
    public int sumOfDigits(int digit){
        int result = 0;
        String digits = String.valueOf(digit);
        char[] digs = digits.toCharArray();
        for (char vel:digs){
            result+=Integer.parseInt(String.valueOf(vel));
        }
        return result;
    }

    public static void main(String[] args) {
        LinkList list = new LinkList();
        System.out.println(Arrays.toString(list.fibo(4)));
        System.out.println("sum = ".concat(String.valueOf(list.sumOfDigits(3457))));
    }
}
