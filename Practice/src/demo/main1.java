package demo;

import java.util.List;
import java.util.stream.Collectors;

public class main1 {

	public static void main(String[] args) {
		List<Integer> list = List.of(1,2,3,3,4,5,6,7,7,8);
        List<Integer> finalList = list.stream().filter((a) -> a > 3).collect(Collectors.toList());
        
        int result = finalList.stream().reduce(0, (a, b) -> a + b);
        
        List<Integer> intLst = finalList.stream().filter((a) -> a <= 7).collect(Collectors.toList());

        System.out.println(result);
        System.out.println(intLst);
        System.out.println(finalList);
        

	}
}
