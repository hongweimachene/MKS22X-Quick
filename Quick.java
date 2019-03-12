import java.util.Random;

public class Quick{
  public static int quickSelect(int[] data, int k) {
    int kth = 0;
    int val = partition(data, 0, data.length-1);
    if (val > k) {
      kth = data[partition(data, 0, val -1)];
    }
    if (val < k) {
      
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
   Random rand = new Random();
   int pick = rand.nextInt(data.length);
   int index = 0;
   int pivot = data[pick];
   data[pick] = data[0];
   data[0] = pivot;
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
       data[0] = data[start - 1];
       data[start - 1] = pivot;
       index = start - 1;
     } else {
       data[0] = data[start];
       data[start] = pivot;
       index = start;
     }
   }
   return index;
 }
}
