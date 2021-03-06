public class MaxBag extends CombatSystem {

  @Override
  WarAvg battle(int samples, int attack, int defend) {
    WarAvg res = new WarAvg();
    res.type = type();
    res.samples = samples;
    res.initAtk = attack;
    res.initDef = defend;
    int atkWins = 0;
    int resAtk = 0;
    int resDef = 0;
    for (int sample = 0; sample < samples; sample++) {
      int curAtk = 0;
      int curDef = 0;
      for (int i = 0; i < Math.max(attack, defend); i++) {
        int rand = Sim.r.nextInt(attack + defend - curAtk - curDef);
        if (rand < attack - curAtk) {
          curAtk++;
        } else {
          curDef++;
        }
      }
      if (curAtk > curDef) {
        atkWins++;
        resAtk += curAtk;
      } else {
        resDef += curDef;
      }
    }
    res.atkWin = atkWins * 1.0 / samples;
    res.resAtk = resAtk * 1.0 / atkWins;
    res.resDef = resDef * 1.0 / (samples - atkWins);
    return res;
  }

  @Override
  String type() {
    return "maxBag";
  }

}
