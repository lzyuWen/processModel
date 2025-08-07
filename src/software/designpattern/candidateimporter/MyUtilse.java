
package software.designpattern.candidateimporter;

import java.util.Random;

public class MyUtilse {
	  
	public long gettimea() {
        Random random = new Random();
        long[] array= {330,330,0};
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
        long[] array= {423,383,345};
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
        long[] array= {3245,2132,3160};
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
        long[] array= {3299,4433,0};
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