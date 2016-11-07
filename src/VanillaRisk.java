public class VanillaRisk extends CombatSystem {
  String type() {
    return "vanilla";
  }

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
      int curAtk = attack;
      int curDef = defend;
      while (curAtk > 0 && curDef > 0) {
        int[] roundRes = riskRound(curAtk, curDef);
        curAtk = roundRes[0];
        curDef = roundRes[1];
      }
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

  static int[] riskRound(int attack, int defend) {
    int[] attackDice = new int[Math.min(attack, 3)];
    for (int i = 0; i < attackDice.length; i++) {
      attackDice[i] = Sim.r.nextInt(6);
    }
    int[] defendDice = new int[Math.min(defend, 2)];
    for (int i = 0; i < defendDice.length; i++) {
      defendDice[i] = Sim.r.nextInt(6);
    }
    Sim.sort(attackDice, true);
    Sim.sort(defendDice, true);

    int[] res = { attack, defend };
    for (int i = 0; i < attackDice.length && i < defendDice.length; i++) {
      if (attackDice[i] > defendDice[i]) {
        res[1]--;
      } else {
        res[0]--;
      }
    }
    return res;
  };
}
