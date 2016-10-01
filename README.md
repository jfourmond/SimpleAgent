# Simple Agent

## TD d'IA développementale

Le but du TD est d'implémenter un agent qui apprenne à effectuer les interactions positives sans connaître à priori son système motivationnel ni son environnement.

### Actions

Deux [actions](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/action/Action.java) possibles E = {a1, a2}, ici représentées par :
- **TRIANGLE**
- **CIRCLE**.

### Résultats

Deux [resultats](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/result/Result.java) possibles R = {r1, r2}, ici représentés par :
- **WHITE**
- **GREEN**.


### Interactions

Quatres [interactions](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/interaction/Interaction.java) possibles E x R = {i11, i12, i21, i22}, ici représentés par :
- **TRIANGLE WHITE**
- **TRIANGLE GREEN**
- **CIRCLE WHITE**
- **CIRCLE GREEN**

### Environnement

[Environnement 1](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/environment/Env1.java) : 
- e1 -> r1 , e2 -> r2   (i12 et i21 ne se produisent jamais)

[Environnement 2](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/environment/Env2.java) :
- e1 -> r2 , e2 -> r1   (i11 et i22 ne se produisent jamais)

[Environnement 3](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/environment/Env3.java) :
- Retourne résultat r2 uniquement si l’agent alterne les actioons.
-  Agent motivé pour obtenir r2: (i12 > 0, i22 > 0)
- a1 -> r2, a1 -> r1, … a1 -> r1, a2-> r2, … a2->r1, … a2 -> r1, a1->r2, a2 -> r2, a1 -> r2, a2 -> r2, …

### Système motivationnel

[Coupling 1](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/coupling/Coupling1.java) :
- v(i11) = v(i12) = 1, v(i21) = v(i22) = -1

[Coupling 2](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/coupling/Coupling2.java) :
- v(i11) = v(i12) = -1, v(i21) = v(i22) = 1

[Coupling 3](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/coupling/Coupling3.java) :
- gestion de l'alternance
