public class Annihilate extends CombatSystem {

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
      int curAtk = attack - Math.min(attack, defend);
      int curDef = defend - Math.min(attack, defend);
      if (curAtk > 0) {
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
    return "annihilate";
  }

}
