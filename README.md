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
- gestion de l'alternance

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

Pour compiler le programme et exécuter l'Environnement 1 :
	
	ant run-env1

Pour compiler le programme et exécuter l'Environnement 2 :

	ant run-env2
	
Pour compiler le programme et exécuter l'Environnement 3 :

	ant run-env3

Pour compiler le programme et exécuter l'Environnement 4 :

	ant run-env4
	
## Traces & Résultats

### Environnement 1

| Itération  | Action   | Resultat | Valeur |
| ---------- | -------- | -------- | ------ |
|          1 | TRIANGLE | GREEN    |      1 |
|          2 | CIRCLE   | WHITE    |     -1 |
|          3 | TRIANGLE | GREEN    |      1 |
|          4 | TRIANGLE | GREEN    |      1 |
|          5 | TRIANGLE | GREEN    |      1 |
|          6 | TRIANGLE | GREEN    |      1 |
|          7 | TRIANGLE | GREEN    |      1 |
|          8 | TRIANGLE | GREEN    |      1 |
|          9 | TRIANGLE | GREEN    |      1 |
|         10 | TRIANGLE | GREEN    |      1 |

### Environnement 2

| Itération  | Action   | Resultat | Valeur |
| ---------- | -------- | -------- | ------ |
|          1 | TRIANGLE | WHITE    |     -1 |
|          2 | CIRCLE   | GREEN    |      1 |
|          3 | TRIANGLE | WHITE    |     -1 |
|          4 | CIRCLE   | GREEN    |      1 |
|          5 | CIRCLE   | GREEN    |      1 |
|          6 | CIRCLE   | GREEN    |      1 |
|          7 | CIRCLE   | GREEN    |      1 |
|          8 | CIRCLE   | GREEN    |      1 |
|          9 | CIRCLE   | GREEN    |      1 |
|         10 | CIRCLE   | GREEN    |      1 |

### Environnement 3

| Itération  | Action   | Resultat | Valeur | Memorisation |
| ---------- | -------- | -------- | ------ | ------------ |
|          1 | TRIANGLE | GREEN    |      1 |              |
|          2 | CIRCLE   | GREEN    |      1 | { i12, i21 } |
|          3 | TRIANGLE | GREEN    |      1 | { i21, i12 } |
|          4 | CIRCLE   | GREEN    |      1 |              |
|          5 | TRIANGLE | GREEN    |      1 |              |
|          6 | CIRCLE   | GREEN    |      1 |              |
|          7 | TRIANGLE | GREEN    |      1 |              |
|          8 | CIRCLE   | GREEN    |      1 |              |
|          9 | TRIANGLE | GREEN    |      1 |              |
|         10 | CIRCLE   | GREEN    |      1 |              |

### Environnement 4

| Itération  | Action   | Resultat | Valeur | Memorisation |
| ---------- | -------- | -------- | ------ | ------------ |
|          1 | TRIANGLE | GREEN    |      1 |              |
|          2 | CIRCLE   | WHITE    |      0 | { i12, i21 } |
|          3 | TRIANGLE | GREEN    |      1 | { i21, i12 } |
|          4 | TRIANGLE | GREEN    |      1 | { i12, i12 } |
|          5 | TRIANGLE | GREEN    |      1 | { i12, i12 } |
|          6 | TRIANGLE | GREEN    |      1 | { i12, i12 } |
|          7 | TRIANGLE | GREEN    |      1 | { i12, i12 } |
|          8 | TRIANGLE | GREEN    |      1 | { i12, i12 } |
|          9 | TRIANGLE | GREEN    |      1 | { i12, i12 } |
|         10 | TRIANGLE | GREEN    |      1 | { i12, i12 } |
|         11 | TRIANGLE | WHITE    |      0 | { i12, i11 } |
|         12 | CIRCLE   | GREEN    |      1 | { i11, i22 } |
|         13 | CIRCLE   | GREEN    |      1 | { i11, i22 } |
|         14 | CIRCLE   | GREEN    |      1 | { i11, i22 } |
|         15 | CIRCLE   | GREEN    |      1 | { i11, i22 } |
|         16 | CIRCLE   | GREEN    |      1 | { i11, i22 } |
|         17 | CIRCLE   | GREEN    |      1 | { i11, i22 } |
|         18 | CIRCLE   | GREEN    |      1 | { i11, i22 } |
|         19 | CIRCLE   | GREEN    |      1 | { i11, i22 } |
|         20 | CIRCLE   | GREEN    |      1 | { i11, i22 } |

