# Simple Agent

Travail Dirigé d'IA développementale sous la direction de :
- [Dr. GEORGEON Olivier](http://oliviergeorgeon.com/),

Par : 
- [FOURMOND Jérôme](https://github.com/jfourmond/)

Dans le cadre de l'Unité d'Enseignement **Artifical Intelligence and Cognition** du [Master 2 Informatique - Parcours Intelligence Artificielle](http://master-info.univ-lyon1.fr/IA/) de l'[Université Claude Bernard Lyon 1](http://www.univ-lyon1.fr/).

---

## Objectifs
Le but du Travail Dirigé est d'implémenter un agent qui apprenne à effectuer les interactions positives sans connaître à priori son système motivationnel ni son environnement.

## Présentation

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

Quatre environnements :

[Environnement 1](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/environment/Env1.java) : 
- e1 -> r1 , e2 -> r2   (i12 et i21 ne se produisent jamais)

[Environnement 2](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/environment/Env2.java) :
- e1 -> r2 , e2 -> r1   (i11 et i22 ne se produisent jamais)

[Environnement 3](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/environment/Env3.java) :
- Retourne résultat r2 uniquement si l’agent alterne les actioons.
-  Agent motivé pour obtenir r2: (i12 > 0, i22 > 0)
- a1 -> r2, a1 -> r1, … a1 -> r1, a2-> r2, … a2->r1, … a2 -> r1, a1->r2, a2 -> r2, a1 -> r2, a2 -> r2, …

[Environnement 4](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/environment/Env4.java) :
- Se comporte comme [Environnement 1](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/environment/Env1.java) pendant les 10 premiers cycles, puis comme [Environnement 2](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/environment/Env2.java).

### Système motivationnel

[Coupling 1](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/coupling/Coupling1.java) :
- v(i11) = v(i12) = 1, v(i21) = v(i22) = -1

[Coupling 2](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/coupling/Coupling2.java) :
- v(i11) = v(i12) = -1, v(i21) = v(i22) = 1

[Coupling 3](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/coupling/Coupling3.java) :
- v(i12) = v(22) = 1, v(i11) = v(i21) = -1

### Agent

Deux agents :

[Agent 1](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/agent/Agent1.java) : premier agent basique utile jusqu'au troisième environnement.

[Agent 2](https://github.com/jfourmond/SimpleAgent/blob/master/SimpleAgent/src/environment/Agent2.java) : second agent pour le quatrièment environnement.

## Compilation & Exécution

Le répertoire dispose d'un fichier ant pour la compilation et l'exécution du programme en ligne de commande.

### Compilation

Pour compiler, uniquement, le programme :

	ant build

### Exécution

Le programme s'exécute avec trois arguments (entiers) nécessaires, et 1 optionnel, en ligne de commande :
- premier : le numéro d'Agent (**1** ou **2**)
- second : le numéro d'Environnement (**1**, **2**, **3** ou **4**)
- troisième : le numéro du Système Motivationnel (**1**, **2** ou **3**)
- quatrième : le nombre d'itération à effectuer

Avec **Ant**,

	ant run -Darg0=[numAgent] -Darg1=[numEnvironment] -Darg2=[numCoupling] (-Darg3=[nb_iteration])