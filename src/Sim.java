import java.util.Random;

public class Sim {

  static Random r = new Random();

  public static void main(String[] args) {
    CombatSystem r1 = new VanillaRisk();
    CombatSystem r2 = new ExtendedRisk();
    CombatSystem r3 = new StableRisk();
    CombatSystem r4 = new MaxBag();
    CombatSystem r5 = new Annihilate();
    System.out.println("Samples:");
    System.out.println(r1.battle(100000, 10, 10));
    System.out.println(r2.battle(100000, 10, 10));
    System.out.println(r3.battle(100000, 10, 10));
    System.out.println(r4.battle(100000, 10, 10));
    System.out.println(r5.battle(100000, 11, 10));
    WarAvg.printDesc();
    r1.calcGrid(20, 20);
    r1.printEverything();
    r2.calcGrid(20, 20);
    r2.printEverything();
    r3.calcGrid(20, 20);
    r3.printEverything();
    r4.calcGrid(20, 20);
    r4.printEverything();
    r5.calcGrid(20, 20);
    r5.printEverything();
  }

  static void sort(int[] arr, boolean rev) {
    int max = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
    }
    int[] buckets = new int[max + 1];
    for (int i = 0; i < arr.length; i++) {
      buckets[arr[i]]++;
    }
    int ind = 0;
    if (rev) {
      ind = arr.length - 1;
    }
    for (int val = 0; val < buckets.length; val++) {
      for (int ct = 0; ct < buckets[val]; ct++) {
        arr[ind] = val;
        if (rev) {
          ind--;
        } else {
          ind++;
        }
      }
    }
  }

  static void bblsort(int[] arr, boolean rev) {
    boolean sorted = false;
    while (!sorted) {
      sorted = true;
      for (int i = 0; i < arr.length - 1; i++) {
        if (arr[i] > arr[i + 1] && !rev || arr[i] < arr[i + 1] && rev) {
          int temp = arr[i];
          arr[i] = arr[i + 1];
          arr[i + 1] = temp;
          sorted = false;
        }
      }
    }
  }

  static String arrToStr(int[] arr) {
    String res = "[";
    for (int i = 0; i < arr.length; i++) {
      if (i > 0) {
        res += ",";
      }
      res += arr[i];
    }
    return res + "]";
  }

}
