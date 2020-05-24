# Logiciel de Dessin
Ce logiciel de dessin permet de créer des formes, de les regrouper dans un dessin et dans des sous-dessins, ainsi que de sauvegarder et charger des dessins entiers.

Chaque commande admet les caractères blans entre les différents mots et parenthèses. Les mots clés de fonction doivent eux respecter la casse, et sont toujours suivies de parenthèses, avec ou sans arguments. Cela permet de les distinguer d'un nom de variable. Un nom de variable commence par une lettre et peut contenir lettres, chiffres et tirets bas. Les nombres sont des entiers, positifs et négatifs.

## Formes
Plusieurs types de formes peuvent être créées : Cercle, Triangle, Rectangle et Carré.
Elle seront intégrées dans le dessin courant.
- Pour un cercle c de centre (0,1) et de rayon 3, la commande de création est : `c = Cercle((0,1),3)`. Le rayon doit être positif.
- Pour un triangle t de points (0,1), (1,2) et (2,3), la commande de création est : `t = Triangle((0,1),(1,2),(2,3))`.
- Pour un rectangle r de point haut-gauche (0,1) et de point bas-droit (1,2), la commande de création est : `r = Rectangle((0,1),(1,2))`.
- Pour un carré c de point haut-gauche (0,1) et de longueur 3, la commande de création est : `c = Carre((0,1),3)`. La longueur doit être positive.

## Dessin
Les formes peuvent être réunies en dessin. Une forme est toujours crée dans le dessin courant. Le dessin courant peut-être celui Global, un autre dessin ou un sous-dessin. Un dessin ne peut contenir deux variables, c'est-à-dire forme ou sous-dessin, du même nom. Mais deux dessins différents peuvent tout à fait contenir des variables ayant des noms identiques.
Pour créer un dessin nommé D, on utlise `D = Dessin()`.                                           
On peut déplacer les dessins entre le dessin courant et un sous-dessin de celui-ci.
Pour déplacer une forme ou un dessin f du dessin courant au sous-dessin ssD, on a la commande `ajoute(f,ssD)`.
Pour déplacer une forme ou dessin f d'un sous-dessu=in ssD au dessin courant, on a la commande `retire(f,ssD)`.

La commande `voir(ssD)` permet de changer le dessin courant pour le sous-dessin ssD de ce dernier.
La commande `sortir()` permet de changer le dessin courant pour le dessin parent de ce dernier.

Le dessin Global est le dessin contenant tous les autres. Il ne peut ni être sauvegardé, ni être supprimé, ni être déplacé.

## Traitement
Chaque forme peut être déplacée, et un ensemble de forme, un dessin, peut l'être aussi.
La commande pour cela est la même : `deplacer(f,(1,1))` permet de déplacer une forme ou un dessin f par un vecteur de déplacement (1,1).

Toutes varaibles peuvent être supprimées, ou copiées.
`c = copier(a)` permet de copier à l'identique une variable a, et de l'insérer dans le dessin courant sous le nom c.
`supprime(a)` permet de supprimer une variable nommée a.

## Affichage
Il existe deux sortes d'affichage, commandés par deux commandes différentes.
Tout d'abord, avec `afficheTout()`, le dessin Global, et donc tous les dessins, est affichées avec toutes ces composants.
Ensuite, avec `affiche()`, seul le dessin sur lequel on travaille est affiché. Ce dessin sera un fils direct de Global, égal au ou qui contient le dessin courant. C'est cet affichage qui est fait après chaque commande.

## Sauvegarde
Il faut tout d'abord savoir que seul un dessin fils direct de Golbal peut-être sauvegardé. On ne peut pas sauvegarder un sous-dessin indépendemment de son parent.
La commande `sauvegarde()` permet de sauvegarder le dessin sur lequel on est en train de travailler, à la condition, bien sûr, qu'il soit différent de Global. Si un dessin du même nom avait déjà été sauvegardé, une confirmation est demandée pour écraser l'ancienne sauvegarde.
Pour charger une sauvegarde d'un dessin nommé D, on utilise la commande `charger(D)`. Le dessin nommé D sera donc chargé dans le logicel, en tant que fils de Global, à la condition qu'un autre dessin nommé D n'y soit pas déjà.
`supprimeSauvegarde(D)` supprime la sauvegarde du dessin D, mais ne supprime le potentiel dessin D ouvert dans le logiciel.

## Annuler et quitter
Pour toutes les commandes ennoncées précédemments, sauf sauvegarde et supprimeSauvegarde, un historique est gardé est permet d'annuler les commandes dans l'ordre inverse d'execution. L'état du logiciel deviendra alors celui d'avant l'execution de la commande annulée.
Pour l'annuler, on tape la commande `annule()`.
Le logicel de dessin se fermera après l'execution de la commande `quitter()`.