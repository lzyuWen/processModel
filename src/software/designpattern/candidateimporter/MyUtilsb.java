package software.designpattern.candidateimporter;

import java.util.Random;

public class MyUtilsb {
	  
	public long gettimea() {
        Random random = new Random();
        long[] array= {1000,1305,0};
        long range = 36;
        long total=0;
        long t=0;
        for (int i = 0; i < array.length-1; i++) {
        	total= array[i] - range + random.nextInt((int) (2 * range + 1))+t;
        	t=total;
        }
        return total;
    
}

	public long gettimeb() {
        Random random = new Random();
        long[] array= {1718,1896,0};
        long range = 36;
        long total=0;
        long t=0;
        for (int i = 0; i < array.length-1; i++) {
        	total= array[i] - range + random.nextInt((int) (2 * range + 1))+t;
        	t=total;
        }
        return total;
    
}
	public long gettimec() {
        Random random = new Random();
        long[] array= {4325,2674,0};
        long range = 36;
        long total=0;
        long t=0;
        for (int i = 0; i < array.length-1; i++) {
        	total= array[i] - range + random.nextInt((int) (2 * range + 1))+t;
        	t=total;
        }
        return total;
}
	public long gettimed() {
        Random random = new Random();
        long[] array= {4576,3434,0};
        long range = 36;
        long total=0;
        long t=0;
        for (int i = 0; i < array.length-1; i++) {
        	total= array[i] - range + random.nextInt((int) (2 * range + 1))+t;
        	t=total;
        }
        return total;
}
}