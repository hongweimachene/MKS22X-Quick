import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Quick{
  public static int quickSelect(int[] data, int k) {
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
     if (pivot <= data[start]) {
       data[pindex] = data[start - 1];
       data[start - 1] = pivot;
       index = start - 1;
     } else {
       data[pindex] = data[start];
       data[start] = pivot;
       index = start;
     }
   }
   return index;
 }

 public static void quickSort(int[] data) {
   quickSort(data, 0, data.length-1);
 }

 private static void quickSort(int[] data, int lo, int hi) {
   if (lo >= hi) {
     return;
   }
   int pivot = partition(data, lo, hi);
   quickSort(data, lo, pivot - 1);
   quickSort(data, pivot + 1, hi);
 }

 public static void main(String[] args) {
   int[] ary1 = new int[] {8, 6 , 7 , 5, 3 ,0, 9};
   // System.out.println(partition(ary1, 0, ary1.length-1));
   // for (int i = 0; i < ary1.length; i++) {
   //   System.out.print(ary1[i]+ " ");
   // }
   int[] ary = new int[] { 2, 10, 15, 23, 0,  5, 5, 7, 142, 32, 45, 142, 23 , 34, 82, 45, 76, 142, 249, 249};
   System.out.println(quickSelect(ary, 19));
   quickSort(ary1);
   quickSort(ary);
   for (int i = 0; i < ary1.length; i++) {
     System.out.print(ary1[i]+ " ");
   }
   System.out.println("");
   for (int i = 0; i < ary.length; i++) {
     System.out.print(ary[i]+ " ");
   }
 }
}
