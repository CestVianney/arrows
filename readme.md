- - -
#Read me !

- - -

Les exercices ont été tous effectués suivant l'énoncé fourni dans le mail.

Etant donné le caractère non éliminatoire de l'épreuve, je me suis permis quelques libertés dans le rendu :
- Je n'ai appliqué la gestion des erreurs (throw exception) que dans l'API Post /arrows ; dans le cas du fichier .txt, j'ai préféré afficher 0 points (comme dans les documents en exemple)
- Pour transformer le .txt en csv (en y appliquant le traitement pour calculer les points), il était possible de s'y prendre de plusieurs manières, notamment via une API... cependant, étant donné qu'on a déjà une API Post pour le json, j'ai choisi de faire un batch
- - Etant donné l'exercice, j'ai évité de m'imposer certaines contraintes "inutiles", donc il suffit de déposer un fichier texte type de l'exemple donné par mail dans resources/batch/input
pour qu'il soit transformé dans ..../output/csv (.csv) et déplacé dans ..../output/done (.txt) afin de garder sa trace
- - __Les output sont générés dans le dossier target. Selon la sécurité du disque, le programme n'aurait potentiellement pas eu de droit d'écriture, et ça aurait été embêtant pour tester l'ex.2__
- - A ne pas trop brusquer, la gestion des erreurs est 'très simple', l'idée était surtout d'ajouter un scheduler à l'exercice histoire de ne pas faire 2 fois une API similaire (qui aurait pris un MultipartFile en parametre, etcetc). 
- - J'ai choisi de faire un batch (il tourne au lancement, et chaque minute), mais je l'ai initialement codé pour l'API, d'où les deux TUs commentés dans ArrowsApiRestControllerTest

- J'ai gardé une architecture hexagonale car je trouve ça très simple à lire
- J'ai essayé d'appliquer quelques notions de clean code, l'algo de base de l'exercice 1 un peu casse-tête (ou bien je suis passé à côté d'une solution bien plus simple) ne permettant pas d'avoir 100% de recul sur le code écrit (et pas de chance, 0 reviewer !)
- En dehors de l'exercice, j'aurais sans doutes aimé (si le temps me l'avait permis, mais la vie en dehors du boulot m'en a empêché) faire un petit front-end pour jouer avec l'API, charger des fichiers pour le batch, "modifier" le body du Json de sortie de Post /arrows de manière à identifier chaque flèche & caractère interdit afin de visuellement attribuer la valeur de chaque flèche constituant le chaîne de caractère en input (ainsi l'utilisateur voit que <= rapporte -4, <=>>> 0, ... )
---
L'exercice a __globalement__ été effectué selon
les règles énoncées dans le fichier readme.md fourni.

Globalement, car il présentait quelques petites erreurs / incertitudes :
- "Une flèche ne peut débuter que par < ou >" : opposition directe avec certains exemples de calculs de points ("-> compte pour 2"); or cette flèche ne commence pas par une balise. Bien évidemment je me doute que c'était à but illustratif :)
- Exemples : "arrowCount('->><=>><') #8" : égal à 3, pas à 8 !
- Etant donné que les résultats sont des int : pas de virgules, et arrondi. J'ai fait en sorte d'arrondir le résultat le plus tard possible de manière à perdre le moins de proximité possible avec le 'vrai' résultat

