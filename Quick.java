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

  public static void insertionSort(int[] ary, int lo, int hi) {
   //the sort starts at index 1 because it is the sorted side
   for (int i = 1; i < ary.length; i++) {
     //unsorted is being compared to each value in the sorted side
     int unsorted = ary[i];
     int sorted = i - 1;
     //checks if sorted side is greater than the unsorted value
     while (sorted >= 0 && ary[sorted] > unsorted ) {
       //shifts each value forward to the right if the sorted is greater
       ary[sorted+1] = ary[sorted];
       sorted--;
     }
     //if the while loop is done, means sorted side is sorted and insert the unsorted
     //into where it belongs,
     //the right of the next smallest number
     ary[sorted+1] = unsorted;
   }
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
   if (start == end) return start;
   //selecting median from start end and middle numbers
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
   //index to return for pivot
   int index = start;
   //swapping pivot with starting index
   int pivot = data[pick];
   data[pick] = data[start];
   data[start] = pivot;
   //store original pivot index
   int pindex = start;
   //offset start by 1 to start comparing
   start+=1;
   Random rand = new Random();
   while (start != end) {
     //value being compared
     int value = data[start];
     //when value is greater place at end, subtract end
     if (value > pivot) {
       data[start] = data[end];
       data[end] = value;
       end--;
     }
     //when value is less move start forward
     else if (value < pivot) {
       start++;
     }
     //to place 50% of duplicates on left and 50% on right
     else {
	     int chance = rand.nextInt(2);
	     if (chance == 0) {
         data[start] = data[end];
         data[end] = value;
         end--;
	     } else {
         start++;
	     }
     }
   }
   //if pivot is less than value at start then place it at the index before start
   if (pivot < data[start]) {
     start--;
   }
   //swap pivot with start
   data[pindex] = data[start];
   data[start] = pivot;
   index = start;
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
   if (data.length <= 35) {
     insertionSort(data,lo,hi);
   } else {
   //initial pivot
   int pivot = partition(data, lo, hi);
   //for log(n) runtime, split array in half each time to partition
   quicksort(data, lo, pivot - 1);
   quicksort(data, pivot + 1, hi);
 }
 }




}
