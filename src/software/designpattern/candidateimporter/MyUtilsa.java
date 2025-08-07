package software.designpattern.candidateimporter;

import java.util.Random;

public class MyUtilsa {
  
	public long gettimea() {
        Random random = new Random();
        long[] array= {534 , 534 ,0};
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
        long[] array= {832,500,500};
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
        long[] array= {6869,5000,5000};
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
        long[] array= {4876,4433,0};
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
