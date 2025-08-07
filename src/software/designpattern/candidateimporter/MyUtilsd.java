package software.designpattern.candidateimporter;

import java.util.Random;

public class MyUtilsd {
	  
	public long gettimea() {
        Random random = new Random();
        long[] array= {373,287,0};
        long range = 36;
        long total=0;
        long t=0;
        for (int i = 0; i < array.length; i++) {
        	total= array[i] - range + random.nextInt((int) (2 * range + 1))+t;
        	t=total;
        }
        return total;
    
}

	public long gettimeb() {
        Random random = new Random();
        long[] array= {523,314,314};
        long range = 36;
        long total=0;
        long t=0;
        for (int i = 0; i < array.length; i++) {
        	total= array[i] - range + random.nextInt((int) (2 * range + 1))+t;
        	t=total;
        }
        return total;
    
}
	public long gettimec() {
        Random random = new Random();
        long[] array= {2734,3123,2680};
        long range = 36;
        long total=0;
        long t=0;
        for (int i = 0; i < array.length; i++) {
        	total= array[i] - range + random.nextInt((int) (2 * range + 1))+t;
        	t=total;
        }
        return total;
}
	public long gettimed() {
        Random random = new Random();
        long[] array= {4312,3420,0};
        long range = 36;
        long total=0;
        long t=0;
        for (int i = 0; i < array.length; i++) {
        	total= array[i] - range + random.nextInt((int) (2 * range + 1))+t;
        	t=total;
        }
        return total;
}
}