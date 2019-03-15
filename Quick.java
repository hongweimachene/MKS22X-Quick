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
 public static int partition(int[] data, int start, int end) {
   //selecting median between start, end, and middle value
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

   //value of pivot
   int pivot = data[pick];

   //swap start with pivot
   data[pick] = data[start];
   data[start] = pivot;

   //stores original start
   int pindex = start;

   //offset start by 1 to start swapping
   start += 1;

   Random rand = new Random();
   while (start != end) {
     int value = data[start];
     if (value > pivot) {
       data[start] = data[end];
       data[end] = value;
       end--;
     }
     else if (value < pivot) {
       start++;
     }
   }
   if (pivot < data[start]) {
     data[pindex] = data[start-1];
     data[start-1] = pivot;
   }
   return start-1;

 }

 public static void quicksort(int[] data) {
   quicksort(data, 0, data.length-1);
 }

 private static void quicksort(int[] data, int lo, int hi) {
   if (lo >= hi) {
     return;
   }
   int pivot = partition(data, lo, hi);
   quicksort(data, lo, pivot - 1);
   quicksort(data, pivot + 1, hi);
 }


}
