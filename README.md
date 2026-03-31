# AndroidEngine (reforged) - Documentation

## MainActivity

### void onCreate(Bundle savedInstanceState)
Initialise l’activité Android et lance le moteur via GameView.

---

## GameView

### void update(float deltaTime)
Met à jour la logique du moteur (scène active + nodes).

**Paramètres :**
- `deltaTime` : temps écoulé depuis la dernière frame (en secondes)

---

### void render(Canvas canvas)
Rend la scène active sur le canvas.

**Paramètres :**
- `canvas` : surface de dessin Android

---

### boolean onTouchEvent(MotionEvent event)
Transmet les événements tactiles au moteur.

**Paramètres :**
- `event` : événement tactile Android

**Retour :**
- `true` si l’événement est consommé, sinon `false`

---

### void surfaceCreated(SurfaceHolder holder)
Démarre la boucle de jeu.

---

### void surfaceDestroyed(SurfaceHolder holder)
Arrête proprement la boucle de jeu.

---

### void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
(Non utilisé mais nécéssaire à l'implementation)

---

## MainThread

### void setRunning(boolean running)
Démarre ou arrête la boucle de jeu.

**Paramètres :**
- `running` : état du thread

---

### void run()
Boucle principale du moteur :
- calcule deltaTime
- appelle update()
- appelle render()
- limite les FPS

---

## EngineCore

### void update(float deltaTime)
Gestion de l'actualisation.

**Paramètres :**
- `deltaTime` : temps entre deux frames (en secondes)

---

### void render(Canvas canvas)
Gestion de l'affichage.

**Paramètres :**
- `canvas` : surface de rendu Android

---

### void onTouchEvent(MotionEvent event)
Gestion des évènements.

**Paramètres :**
- `event` : événement tactile Android

