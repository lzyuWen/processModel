package software.designpattern.candidateimporter;

import java.util.Random;

public class MyUtilsc {
	  
	public long gettimea() {
        Random random = new Random();
        long[] array= {200,173,0};
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
        long[] array= {523,427,416};
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
        long[] array= {6234,4867,4986};
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
        long[] array= {2113,1701,0};
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