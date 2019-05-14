Info Utiles dans l'input

Comments
ts , comment_id ,  comment

Friendship
ts, user_id_1, user_id_2

Likes
ts, user_id, comment_id


1. timestamp Like > timestamp comment
2. Score peut qu'augmenter (atomic / synchro ?)
3. Comments peut exister sans avoir été lu

Faire Attention !
1. Amitié qui vient après le like
2. Debut des ts des fichiers (d secondes)
3. faire attention D (timeline générale / séparées likes ou comment)
4. Le score peut augmenter de plus de 1 à la fois.
5. Si le score est égal, le commentaire prenant la place la plus haute est celui le plus petit lexicalement (aaa > aab).

Idées
1. Classe comment (id(constructeur) , score(initialisé) , string(peut venir après) 
2. Graphe (à partir de frienship)

3. Lire un seul commentaire à la fois
4. Lire le plus vieux like / friendship à chaque itération

