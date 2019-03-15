import java.util.Random;
import java.util.Arrays;

public class Quick{
  public static int quickselect(int[] data, int k) {
    int kth = 0;
    int val = partition(data, 0, data.length-1);
    while (val != k) {
      if (val > k) {
        val = partition(data, 0, val -1);
      }
      if (val < k) {
        val = partition(data, val + 1, data.length -1);
      }
    }
    kth = data[val];
    return kth;
  }

  /*Modify the array such that:
 *1. Only the indices from start to end inclusive are considered in range
 *2. A random index from start to end inclusive is chosen, the corresponding
 *   element is designated the pivot element.
 *3. all elements in range that are smaller than the pivot element are placed before the pivot element.
 *4. all elements in range that are larger than the pivot element are placed after the pivot element.
 *@return the index of the final position of the pivot element.
 */
 public static int partition (int [] data, int start, int end){
   // Random rand = new Random();
   // int pick = rand.nextInt(end - start + 1) + start;
   int pick = 0;
   int middle = (start + end) / 2;
   if ((start >= middle && middle >= end) || (end >= middle && middle >= start)) {
     pick = middle;
   }
   else if ((middle >= start && start >= end) || (end >= start && start >= middle)) {
     pick = start;
   }
   else if ((start >= end && end >= middle) || (middle >= end && end >= start)) {
     pick = end;
   }
   int index = start;
   int pivot = data[pick];
   data[pick] = data[start];
   data[start] = pivot;
   int pindex = start;
   while (start != end) {
     int value = data[start];
     if (value > pivot) {
       data[start] = data[end];
       data[end] = value;
       end--;
     }
     if (value <= pivot) {
       start++;
     }
   }
   if (start == 0 && end == 0) {
     return index;
   }
   if (start == end) {
     if (pivot < data[start]) {
       data[pindex] = data[start - 1];
       data[start - 1] = pivot;
       index = start - 1;
     }
     else if (pivot > data[start]) {
       data[pindex] = data[start];
       data[start] = pivot;
       index = start;
     }
     else if (pivot == data[start]){
       Random rand = new Random();
	     int chance = rand.nextInt(2);
	     if (chance == 0) {
         data[pindex] = data[start - 1];
         data[start - 1] = pivot;
         index = start - 1;
	     } else {
         data[pindex] = data[start];
         data[start] = pivot;
         index = start;
	     }
     }
   }
   return index;
 }

 

 public static void quicksort(int[] data) {
   quicksort(data, 0, data.length-1);
 }

 private static void quicksort(int[] data, int lo, int hi) {
   //base case, end call
   if (lo >= hi) {
     return;
   }
   //initial pivot
   int pivot = partition(data, lo, hi);
   //for log(n) runtime, split array in half each time to partition
   quicksort(data, lo, pivot - 1);
   quicksort(data, pivot + 1, hi);
 }

 public static void main(String[]args){
  System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 2000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        Quick.quicksort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }
}


}
