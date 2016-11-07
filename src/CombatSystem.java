public abstract class CombatSystem {
  abstract WarAvg battle(int ss, int attack, int defend);

  abstract String type();

  WarAvg[][] percents;
  int pscale = 3;

  WarAvg[][] calcGrid(int atk, int def) {
    WarAvg[][] res = new WarAvg[atk][def];
    for (int a = 0; a < atk; a++) {
      for (int d = 0; d < def; d++) {
        res[a][d] = battle(10000, pscale * (a + 1), pscale * (d + 1));
        // res[a][d] = battle(10000, (int) Math.pow(a + 1, 2),
        // (int) Math.pow(d + 1, 2)).atkWin;
      }
    }
    percents = res;
    return res;
  }

  void printGrid() {
    System.out.println();
    System.out.printf("%10s", type());
    for (int d = 0; d < percents[0].length; d++) {
      System.out.printf("%6d", pscale * (d + 1));
    }
    System.out.println();
    for (int a = 0; a < percents.length; a++) {
      System.out.printf("%10d", pscale * (a + 1));
      for (int d = 0; d < percents[a].length; d++) {
        System.out.printf("  %.2f", percents[a][d].atkWin);
      }
      System.out.println();
    }
  }

  void printAtkWin() {
    System.out.printf("%n%10s x%d atkWin%n", type(), pscale);
    for (int a = 0; a < percents.length; a++) {
      for (int d = 0; d < percents[a].length; d++) {
        double p = percents[a][d].atkWin;
        if (p < 0.1 || p > 0.9) {
          System.out.print(" ");
        } else if (p < 0.3 || p > 0.7) {
          System.out.print(".");
        } else if (p < 0.5) {
          System.out.print("o");
        } else {
          System.out.print("x");
        }
      }
      System.out.println();
    }
  }

  void printStdDevRatio() {
    System.out.printf("%n%10s x%d stdDevRatio%n", type(), pscale);
    for (int a = 0; a < percents.length; a++) {
      for (int d = 0; d < percents[a].length; d++) {
        double p = percents[a][d].stdDevRatio();
        if (p < 0.05) {
          System.out.print(" ");
        } else if (p < 0.1) {
          System.out.print(".");
        } else if (p < 0.2) {
          System.out.print(":");
        } else if (p < 0.3) {
          System.out.print("o");
        } else if (p < 0.5) {
          System.out.print("O");
        } else {
          System.out.print("@");
        }
      }
      System.out.println();
    }
  }
}
