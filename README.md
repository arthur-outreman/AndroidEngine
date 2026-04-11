# AndroidEngine (reforged)

## Moteur de jeu 2D rudimentaire pour Android Studio

### A propos :

&emsp; Jusqu'ici le projet implémente le render et les collisions d'objects 2D grâce à un system de Node similaire à celui de Godot.
<br><br>
&emsp; Le moteur est séparé entre les Nodes qui contiennent les données *(un Transform2D, une liste de Node2D enfants et une liste de component)* et les Components qui implémentent la logique.
<br><br>
&emsp; Il y a un système de scènes intégré sous la forme d'une liste de pointeurs vers des Nodes racines qui contienent toute l'arborescence d'une scène.

---

### Utilisation :

&emsp; Vous êtes libre de réutiliser et de modifier le code source du moteur
<br><br>
&emsp; Pour utiliser **AndroidEngine** comme base de projet il vous suffit de créer une nouvelle EmptyViewActivity en langage JAVA sur Android Studio et de copier tous les fichiers sources dans le dossier Java.
<br><br>
&emsp; Si vous ne voulez pas avoir a changer tous les imports il est impératif que le nom du package soit  : "com.example.engine".

---

### Problèmes connus :

- Les rotations de Node ne s'appliquent pas aux SpriteComponent.
<br><br>
- Aucun moyen simple de flipH un spriteComponent.
