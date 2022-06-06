### Santa App : Projet Java Info 1 Semestre 2 ###

**Auteurs : N'CHO Axel et Revel Paul**

#### Bref ####

>L'application permet aux enfants d'envoyer une commande de cadeaux au Père Noël.  

Ils créent un compte puis choisissent 5 jouets dans une liste de 20 jouets correspondant  
à leur âge.

Le père noël valide ou annule les commandes en fonction de la sagesse  
de l'enfant durant toute l'année. Cette sagesse est déterminée aléatoirement.  

Tant que la commande n'est pas validée, l'enfant peut la modifier.

Une fois validée, la commande est préparée par les lutins préparateurs puis validée avec  
la mention "prête à être livrée".  

#### Utilisateurs ####

Ils sont tous stockés dans la base de données, dans le repertoire ***UsersFiles.santaDB***. Ils se connectent avec leur mail et leur mot de passe.

##### Père Noël #####

>C'est l'utilisateur "***santa***". Il existe par défaut sous l'adresse mail ***santa@santa.com*** et le mot de passe ***hohoho!***.  
> L'adresse mail du père noël doit obligatoirement se terminer par "***@santa.com***".

##### Lutins préparateurs #####

>Nous en avons créé un seul par défaut sous l'adresse mail ***pelf1@pelf.com*** et le mot de passe ***pElf1***.  
> Pour en créer d'autres, il faut utiliser une adresse mail qui se termine par "***@pelf.com***". Les champs "nom", "prénom", "e-mail" et "mot de passe" sont obligatoires.

Les lutins préparateurs ne voient que les commandes validées par le père noël.

#### Lutins soigneurs ####

>Nous n'avons pas eu assez de temps pour les intégrer à l'application. De même pour les rennes et la gestion RH.

### Détails d'utilisation ###

Une ***JList*** a été utilisée pour la list des jouets. Il faut donc faire ***Ctrl+Click*** pour choisir plusieurs jouets.
Vous pouvez vous connecter sur le compte du Père Noël ou d'un lutin pour voir les commandes, les valider ou les annuler.

>Le programme principal se trouve dans le fichier ***Main.java***.

> Sur Linux, vous devez préciser le chemin absolu vers le repertoire du projet dans ***FileHelper.java***. Il s'agit de
> l'attribut ***appPath***.

>Les utilisateurs test1 et test2 sont les exemples que nous avons réalisé.
> Pour se connecter à leur compte, utiliser le mot de passe ***hohoho!***.
