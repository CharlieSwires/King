package charlie.com;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {

    public static void main(String args[]) {
        Integer[] unsorted = {1,2,3,4,5,6,7,8,9,0,3,6,2,1};
        List<Object> unsortedList = Arrays.asList(unsorted);
        Collections.sort(unsortedList, new Comparator(){

            @Override
            public int compare(java.lang.Object o1, java.lang.Object o2) {
                Integer a = (Integer)o1;
                Integer b = (Integer)o2;
                if (a == null || b == null) return 0;
                return b-a;
            }

        });
        System.out.println(unsortedList);
    }
}
