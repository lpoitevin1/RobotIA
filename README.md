# IDEFIX

Mot de passe wifi raspberry :
    oasisTD8
    @Deay2dh&
    
Connection aux robots :
	ssh robot@xxx.xxx.xxx.xxx
	maker

scp ev3dev:/home/robot /media/chris/Data/chris/Documents/school/M1/IDEFIX/idefix/src/avancer.py

Envoyer le fichier au robot (Sur terminal de machine):

    cd `path/to/src`
    sftp robot@`adress ip`
    put `localFile` (pour transfert localFile à la répertoire du robot /home/robot/localFile)
    
## Partie physique
La partie logique se trouve à la racine du projet.


## Partie logique
La partie logique se trouve dans le repertoire TSP.  
Il est possible de trouver des exemples de résolutions du jeu dans la classe PlateauTest et dans le main.

    Plateau p = new Plateau("node_grille_mur5x5", "link_grille_mur5x5");
    Noeud r1 = g.getNodes().get(0);
    Noeud r2 = g.getNodes().get(1);
    Noeud r3 = g.getNodes().get(2);
    Noeud obj = g.getNodes().get(17);
    p.setRobots(r1, r2, r3);
    p.setObjectif(obj);
    
Les parametres à definir sont les suivants :  

*     Les noms des fichiers contenant les informations du graphe (noeud et aretes).  
*    La position du premier robot dans le graphe.  
*     La position du second robot dans le graphe.  
*     La position du troisieme robot dans le graphe.  
*     L'objectif à atteindre pour le premier robot.  


Il est possible de d'affecter la valeur d'un noeud par sa valeur en abscisse et ordonnée (mais celui ci doit exister dans le graphe).  
Voici un exemple pour affecter l'objectif à X=2 Y=3.

    Noeud obj = g.findNode(2,3);

    