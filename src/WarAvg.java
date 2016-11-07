public class WarAvg {
  String type;
  int samples;
  double atkWin;
  int initAtk;
  double resAtk;
  int initDef;
  double resDef;

  public String toString() {
    return String.format("%-10s", type)
        + " "
        + samples
        + " ["
        + initAtk
        + ","
        + initDef
        + "] "
        + String.format("%.3f [%.3f,%.3f] %.3f", atkWin, resAtk, resDef,
            stdDevRatio());
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
}
