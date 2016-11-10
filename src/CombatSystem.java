public abstract class CombatSystem {
  abstract WarAvg battle(int ss, int attack, int defend);

  abstract String type();

  WarAvg[][] percents;
  int pscale = 3;

  WarAvg[][] calcGrid(int atk, int def) {
    WarAvg[][] res = new WarAvg[atk][def];
    for (int a = 0; a < atk; a++) {
      for (int d = 0; d < def; d++) {
        res[a][d] = battle(1000, pscale * (a + 1), pscale * (d + 1));
        // res[a][d] = battle(10000, (int) Math.pow(a + 1, 2),
        // (int) Math.pow(d + 1, 2)).atkWin;
      }
    }
    percents = res;
    return res;
  }

  void printEverything() {
    printSideBySide();
    // printGrid();
    // printSurvivability();
    // printKDR();
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
        System.out.printf("  %.2f", percents[a][d].survivability());
      }
      System.out.println();
    }
  }

  void printSideBySide() {
    System.out.printf("%n%-10s x%d%n", type(), pscale);
    for (int a = 0; a < percents.length; a++) {
      System.out.print(" |");
      for (int d = 0; d < percents[a].length; d++) {
        System.out.print(percents[a][d].atkWinChar());
      }
      System.out.print("|    |");
      for (int d = 0; d < percents[a].length; d++) {
        System.out.print(percents[a][d].stdDevRatioChar());
      }
      System.out.print("|    |");
      for (int d = 0; d < percents[a].length; d++) {
        System.out.print(percents[a][d].survivabilityChar());
      }
      System.out.print("|    |");
      for (int d = 0; d < percents[a].length; d++) {
        System.out.print(percents[a][d].kdrChar());
      }
      System.out.println("|");
    }
  }

  void printAtkWin() {
    System.out.printf("%n%10s x%d atkWin%n", type(), pscale);
    for (int a = 0; a < percents.length; a++) {
      for (int d = 0; d < percents[a].length; d++) {
        System.out.print(percents[a][d].atkWinChar());
      }
      System.out.println();
    }
  }

  void printStdDevRatio() {
    System.out.printf("%n%10s x%d stdDevRatio%n", type(), pscale);
    for (int a = 0; a < percents.length; a++) {
      for (int d = 0; d < percents[a].length; d++) {
        System.out.print(percents[a][d].stdDevRatioChar());
      }
      System.out.println();
    }
  }

  void printSurvivability() {
    System.out.printf("%n%10s x%d survivability%n", type(), pscale);
    for (int a = 0; a < percents.length; a++) {
      for (int d = 0; d < percents[a].length; d++) {
        System.out.print(percents[a][d].survivabilityChar());
      }
      System.out.println();
    }
  }

  void printKDR() {
    System.out.printf("%n%10s x%d KDR%n", type(), pscale);
    for (int a = 0; a < percents.length; a++) {
      for (int d = 0; d < percents[a].length; d++) {
        System.out.print(percents[a][d].kdrChar());
      }
      System.out.println();
    }
  }
}
