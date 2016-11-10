public class WarAvg {
  String type;
  int samples;
  double atkWin;
  int initAtk;
  double resAtk;
  int initDef;
  double resDef;

  public String toString() {
    if (atkWin >= 1) {
      resDef = 0;
    }
    if (atkWin <= 0) {
      resAtk = 0;
    }
    return String.format("%-10s", type)
        + " "
        + samples
        + " ["
        + initAtk
        + ","
        + initDef
        + "] "
        + String.format("%.3f [%.3f,%.3f] sdr%.3f srv%.3f kdr%.3f", atkWin,
            resAtk, resDef, stdDevRatio(), survivability(), kdr());
  }

  public double stdDev() {
    // double mean = (resAtk + resDef) * atkWin - resDef;
    // return Math.sqrt(Math.pow(resAtk - mean, 2) * atkWin
    // + Math.pow(resDef + mean, 2) * (1 - atkWin));
    if (atkWin <= 0 || atkWin >= 1) {
      return 0;
    }
    return (resAtk + resDef) * Math.sqrt((1 - atkWin) * atkWin);
  }

  public double stdDevRatio() {
    return stdDev() / (initAtk + initDef);
  }

  public double survivability() {
    if (atkWin >= 1) {
      resDef = 0;
    }
    if (atkWin <= 0) {
      resAtk = 0;
    }
    return resAtk * atkWin / (initAtk + initDef) + resDef * (1 - atkWin)
        / (initAtk + initDef);
  }

  public double kdr() {
    // if (atkWin >= 1) {
    // return (initAtk - resAtk) / initDef;
    // }
    // if (atkWin <= 0) {
    // return (initDef - resDef) / initAtk;
    // }
    // return (initAtk - resAtk) / initDef * atkWin + (initDef - resDef)
    // / initAtk * (1 - atkWin);
    if (atkWin >= 1) {
      return initDef / (initAtk - resAtk);
    }
    if (atkWin <= 0) {
      return (initDef - resDef) / initAtk;
    }
    return initDef / (initAtk - resAtk) * atkWin + (initDef - resDef) / initAtk
        * (1 - atkWin);
  }

  public static void printDesc() {
    System.out.println();
    System.out.println("KEY:");
    // System.out
    // .println("  stdDev = (resAtk + resDef) * Math.sqrt((1 - atkWin) * atkWin)");
    System.out.println(" 1. atkWin");
    System.out
        .println(" 2. stdDevRatio = (resAtk + resDef) * Math.sqrt((1 - atkWin) * atkWin) / (initAtk + initDef)");
    System.out
        .println(" 3. survivability = resAtk * atkWin / (initAtk + initDef) + resDef * (1 - atkWin) / (initAtk + initDef)");
    System.out
        .println(" 4. kdr = initDef / (initAtk - resAtk) * atkWin + (initDef - resDef) / initAtk * (1 - atkWin)");
  }

  public char atkWinChar() {
    double p = atkWin;
    if (p <= 0.001) {
      return 'O';
    } else if (p >= 0.999) {
      return 'X';
    } else if (p < 0.1 || p > 0.9) {
      return ' ';
    } else if (p < 0.3 || p > 0.7) {
      return '.';
    } else if (p < 0.5) {
      return 'o';
    } else {
      return 'x';
    }
  }

  public char stdDevRatioChar() {
    double p = stdDevRatio();
    if (p < 0.05) {
      return ' ';
    } else if (p < 0.1) {
      return '.';
    } else if (p < 0.2) {
      return ':';
    } else if (p < 0.3) {
      return 'o';
    } else if (p < 0.5) {
      return 'O';
    } else {
      return '@';
    }
  }

  public char survivabilityChar() {
    double p = survivability();
    if (p < 0.2) {
      return ' ';
    } else if (p < 0.3) {
      return '.';
    } else if (p < 0.4) {
      return ':';
    } else if (p < 0.5) {
      return 'o';
    } else if (p < 0.6) {
      return 'O';
    } else {
      return '@';
    }
  }

  public char kdrChar() {
    double p = kdr();
    if (p < 0.2) {
      return '%';
    } else if (p < 0.5) {
      return 'O';
    } else if (p < 0.8) {
      return 'o';
    } else if (p < 0.9) {
      return '.';
    } else if (p < 1.111) {
      return ' ';
    } else if (p < 1.25) {
      return ',';
    } else if (p < 2) {
      return 'x';
    } else if (p < 5) {
      return 'X';
    } else {
      return '&';
    }
  }
}
