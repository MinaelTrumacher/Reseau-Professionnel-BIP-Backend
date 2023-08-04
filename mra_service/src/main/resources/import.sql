--Equivalant de cette requete dans le ficher afpa.mdr.importation/DataImportRunner.java (Ce fait a la fin de tous les imports)
--copy geolocalisations(id,latitude,longitude,code_postal,region,ville) from '${classpath:Table_geolocalisation.csv}' WITH DELIMITER ',' CSV HEADER ENCODING 'UTF-8';

--Utilisateur
--Mot de passe : 123456aB/
insert into Utilisateurs(nom,prenom,role,email,mdp,description,etat_inscription,url_photo,url_banniere,date_creation,date_modification) values ('Chnaif','Walid','JEDI','chnaifw@gmail.com','$2a$10$Fi75AyzoPZsj48bfr4tbger5IKWHYAaCohBvMnPdbBlclaMd9Qj3.','Beta Testeur','autorisé','https://media.discordapp.net/attachments/811032745379233812/1136221736870883328/7a2ecd93588bf3ce28cc696a928a3830.png','https://media.discordapp.net/attachments/811032745379233812/1136222097354534962/26ae1241ca65ba8e8ff4a4d442c92566.png','2023-07-31 11:08:36.427','2023-07-31 11:08:36.427');
insert into Utilisateurs(nom,prenom,role,email,mdp,description,etat_inscription,url_photo,url_banniere,date_creation,date_modification) values ('Schiavon','Pierre','JEDI','pierre.schiavon@gmail.com','$2a$10$Fi75AyzoPZsj48bfr4tbger5IKWHYAaCohBvMnPdbBlclaMd9Qj3.','Beta Testeur','autorisé','https://pbs.twimg.com/profile_images/385921406/abitbol_400x400.jpg','https://img.lovepik.com/background/20211021/large/lovepik-blue-technology-banner-background-image_500362377.jpg','2023-07-31 11:08:36.427','2023-07-31 11:08:36.427');
insert into Utilisateurs(nom,prenom,role,email,mdp,description,etat_inscription,url_photo,url_banniere,date_creation,date_modification) values ('Del bario','Patrice','JEDI','patrice.delbarrio@gmail.com','$2a$10$Fi75AyzoPZsj48bfr4tbger5IKWHYAaCohBvMnPdbBlclaMd9Qj3.','Beta Testeur','autorisé','https://pbs.twimg.com/profile_images/385921406/abitbol_400x400.jpg','https://img.lovepik.com/background/20211021/large/lovepik-blue-technology-banner-background-image_500362377.jpg','2023-07-31 11:08:36.427','2023-07-31 11:08:36.427');
insert into Utilisateurs(nom,prenom,role,email,mdp,description,etat_inscription,url_photo,url_banniere,date_creation,date_modification) values ('Truong','Minh','JEDI','truong.minhtuan.tlse@gmail.com','$2a$10$Fi75AyzoPZsj48bfr4tbger5IKWHYAaCohBvMnPdbBlclaMd9Qj3.','Beta Testeur','autorisé','https://pbs.twimg.com/profile_images/385921406/abitbol_400x400.jpg','https://img.lovepik.com/background/20211021/large/lovepik-blue-technology-banner-background-image_500362377.jpg','2023-07-31 11:08:36.427','2023-07-31 11:08:36.427');

insert into Utilisateurs(nom,prenom,role,email,mdp,description,etat_inscription,url_photo,url_banniere,date_creation,date_modification) values ('Ghoufa','Karim','JEDI','ghoufaabdelkarim@gmail.com','$2a$10$Fi75AyzoPZsj48bfr4tbger5IKWHYAaCohBvMnPdbBlclaMd9Qj3.','Testé','autorisé','https://media.discordapp.net/attachments/811032745379233812/1136221736870883328/7a2ecd93588bf3ce28cc696a928a3830.png','https://media.discordapp.net/attachments/811032745379233812/1136222097354534962/26ae1241ca65ba8e8ff4a4d442c92566.png','2023-07-31 11:08:36.427','2023-07-31 11:08:36.427');
insert into Utilisateurs(nom,prenom,role,email,mdp,description,etat_inscription,url_photo,url_banniere,date_creation,date_modification) values ('Massolin','Kevin','JEDI','kevinmassolin@yahoo.fr','$2a$10$Fi75AyzoPZsj48bfr4tbger5IKWHYAaCohBvMnPdbBlclaMd9Qj3.','Testé','autorisé','https://pbs.twimg.com/profile_images/385921406/abitbol_400x400.jpg','https://img.lovepik.com/background/20211021/large/lovepik-blue-technology-banner-background-image_500362377.jpg','2023-07-31 11:08:36.427','2023-07-31 11:08:36.427');
insert into Utilisateurs(nom,prenom,role,email,mdp,description,etat_inscription,url_photo,url_banniere,date_creation,date_modification) values ('Lakhmes','Youba','JEDI','lakhmesyouba@hotmail.fr','$2a$10$Fi75AyzoPZsj48bfr4tbger5IKWHYAaCohBvMnPdbBlclaMd9Qj3.','Testé','autorisé','https://pbs.twimg.com/profile_images/385921406/abitbol_400x400.jpg','https://img.lovepik.com/background/20211021/large/lovepik-blue-technology-banner-background-image_500362377.jpg','2023-07-31 11:08:36.427','2023-07-31 11:08:36.427');
insert into Utilisateurs(nom,prenom,role,email,mdp,description,etat_inscription,url_photo,url_banniere,date_creation,date_modification) values ('Bonnin','Laura','JEDI','bonnin.laura52@gmail.com','$2a$10$Fi75AyzoPZsj48bfr4tbger5IKWHYAaCohBvMnPdbBlclaMd9Qj3.','Testé','autorisé','https://pbs.twimg.com/profile_images/385921406/abitbol_400x400.jpg','https://img.lovepik.com/background/20211021/large/lovepik-blue-technology-banner-background-image_500362377.jpg','2023-07-31 11:08:36.427','2023-07-31 11:08:36.427');
insert into Utilisateurs(nom,prenom,role,email,mdp,description,etat_inscription,url_photo,url_banniere,date_creation,date_modification) values ('Schaedker','Minh','JEDI','bahia.gsa@gmail.com','$2a$10$Fi75AyzoPZsj48bfr4tbger5IKWHYAaCohBvMnPdbBlclaMd9Qj3.','Testé','autorisé','https://pbs.twimg.com/profile_images/385921406/abitbol_400x400.jpg','https://img.lovepik.com/background/20211021/large/lovepik-blue-technology-banner-background-image_500362377.jpg','2023-07-31 11:08:36.427','2023-07-31 11:08:36.427');

--Publication
INSERT INTO publications (title, categorie, contenu, date_creation, date_modification, utilisateur_id) VALUES ('C# Job dating', 'job_dating', E'C# Job dating\n\nNous organisons un job dating pour les développeurs C# expérimentés. Si vous êtes passionné par la programmation C# et que vous recherchez de nouvelles opportunités professionnelles, c''est l''occasion parfaite pour rencontrer des recruteurs de diverses entreprises. Venez avec votre CV et vos réalisations pour discuter de votre parcours professionnel et découvrir les opportunités qui vous attendent.\nDate : 15 septembre 2023\nLieu : Centre des congrès, 123 rue de la République, Paris\n\nInscrivez-vous dès maintenant pour réserver votre place !\n\n#jobdating #CSharp #développeur #opportunités', '2023-08-03 12:34:56', '2023-08-03 12:34:56', 1);
INSERT INTO publications (title, categorie, contenu, date_creation, date_modification, utilisateur_id) VALUES ('Stage JEE Spring boot', 'offre_stage', E'Stage JEE Spring boot\n\nNous offrons un stage passionnant pour un développeur JEE Spring Boot talentueux. Rejoignez notre équipe dynamique pour travailler sur des projets innovants et gagner en expérience pratique dans le développement d''applications Web. Si vous êtes enthousiaste à l''idée de relever des défis techniques et de développer des solutions de pointe, postulez dès aujourd''hui !\n\nProfil recherché :\n- Maîtrise de JEE et Spring Boot\n- Connaissance des bases de données relationnelles\n- Capacité à travailler en équipe et à communiquer efficacement\n\nDurée du stage : 6 mois\nLocalisation : Lyon\n\nEnvoyez-nous votre CV et lettre de motivation pour postuler !\n\n#stage #JEE #SpringBoot #développementWeb', '2023-08-03 12:34:56', '2023-08-03 12:34:56', 2);
INSERT INTO publications (title, categorie, contenu, date_creation, date_modification, utilisateur_id) VALUES ('Poste développeur concepteur d''application', 'offre_emploi', E'Poste développeur concepteur d''application\n\nNous recrutons un développeur concepteur d''application pour rejoindre notre équipe technique. Vous travaillerez sur la conception et le développement d''applications innovantes, en collaborant avec des équipes multidisciplinaires pour offrir des solutions logicielles de haute qualité.\n\nExigences :\n- Expérience avérée dans le développement d''applications\n- Maîtrise d''au moins un langage de programmation (Java, C#, Python, etc.)\n- Connaissance des principes de conception logicielle et des bonnes pratiques de codage\n\nNous offrons un environnement de travail stimulant et des opportunités d''évolution professionnelle.\n\nLieu : Toulouse\nType de contrat : CDI\n\nPostulez dès maintenant avec votre CV et une brève présentation de vos réalisations professionnelles.\n\n#emploi #développeur #conception #recrutement', '2023-08-03 12:34:56', '2023-08-03 12:34:56', 3);
INSERT INTO publications (title, categorie, contenu, date_creation, date_modification, utilisateur_id) VALUES ('Afterwork au bistot', 'afterwork', E'Afterwork au bistrot\n\nVenez nous rejoindre pour un afterwork convivial au bistrot "La Bonne Adresse". C''est l''occasion parfaite pour rencontrer des professionnels du secteur, élargir votre réseau et partager des expériences intéressantes autour d''un verre.\n\nDate : 20 septembre 2023\nHeure : 18h30 - 21h00\nLieu : La Bonne Adresse, 456 avenue du Plaisir, Lille\n\nQue vous soyez étudiant, jeune professionnel ou expérimenté, tout le monde est le bienvenu ! Profitez de cette opportunité pour discuter de vos projets, échanger des idées et passer un bon moment ensemble.\n\nVeuillez confirmer votre présence pour que nous puissions organiser au mieux cet événement.\n\n#afterwork #réseautage #rencontres #convivialité', '2023-08-03 12:34:56', '2023-08-03 12:34:56', 4);

--Message
-- Messages entre l'utilisateur 1 et les autres utilisateurs
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Bonjour à tous !', FALSE, NULL, 1, 2, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salut ! Comment ça va ?', TRUE, 4, 2, 1, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Coucou !', FALSE, NULL, 1, 3, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Hey, ça va super bien !', TRUE, 3, 3, 1, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salutations !', FALSE, NULL, 1, 4, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Bonjour ! Tout va bien ?', TRUE, 2, 4, 1, NOW());

-- Messages entre l'utilisateur 2 et les autres utilisateurs
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Hello à tous !', FALSE, NULL, 2, 1, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salut, ça va bien, merci !', TRUE, 3, 1, 2, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Coucou les amis !', FALSE, NULL, 2, 3, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salut ! Ça roule !', TRUE, 4, 3, 2, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salutations à tous !', FALSE, NULL, 2, 4, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Bonjour ! Comment ça va ?', TRUE, 1, 4, 2, NOW());

-- Messages entre l'utilisateur 3 et les autres utilisateurs
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salut tout le monde !', FALSE, NULL, 3, 1, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Bonjour ! Ça va très bien !', TRUE, 1, 1, 3, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salut les amis !', FALSE, NULL, 3, 2, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Hey ! Tout va super bien !', TRUE, 2, 2, 3, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Coucou à tous !', FALSE, NULL, 3, 4, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Bonjour ! Comment ça se passe ?', TRUE, 4, 4, 3, NOW());

-- Messages entre l'utilisateur 4 et les autres utilisateurs
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Hey ! Comment allez-vous ?', FALSE, NULL, 4, 1, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salut ! Tout va très bien !', TRUE, 1, 1, 4, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Coucou tout le monde !', FALSE, NULL, 4, 2, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salutations ! Tout est super !', TRUE, 3, 2, 4, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Bonjour à tous !', FALSE, NULL, 4, 3, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Hey ! Comment ça va ?', TRUE, 2, 3, 4, NOW());

-- Messages concernant la publication sur un recrutement
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Bonjour à tous ! Notre entreprise est actuellement à la recherche de développeurs talentueux. Si vous êtes intéressés par des opportunités de travail, n''hésitez pas à me contacter !', FALSE, NULL, 1, 2, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salut ! Je suis intéressé par le recrutement. Peux-tu m''en dire plus ?', TRUE, 4, 2, 1, NOW());

-- Messages concernant la publication sur un afterwork au bistrot
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Coucou ! Ce vendredi, nous organisons un afterwork au bistrot du coin. Soyez tous les bienvenus pour une soirée détente et conviviale !', FALSE, NULL, 1, 3, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Hey, ça a l''air sympa ! Je serai présent vendredi.', TRUE, 3, 3, 1, NOW());

-- Messages concernant la publication sur une offre de stage
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Salut tout le monde ! Notre entreprise propose actuellement des offres de stage dans le domaine du marketing digital. Si vous connaissez des étudiants intéressés, faites-leur passer l''information !', FALSE, NULL, 2, 4, NOW());
INSERT INTO messages (contenu, vu, supprimer_par_user_id, expediteur_id, destinataire_id, date_envoi) VALUES ('Bonjour ! Je connais des étudiants qui pourraient être intéressés. Je leur en parlerai.', TRUE, 1, 4, 2, NOW());


-- FORMATION
INSERT INTO formations (titre, code_rncp) VALUES ('DESIGNER WEB', 'RNCP26602');
INSERT INTO formations (titre, code_rncp) VALUES ('CONCEPTEUR DESIGNER USER INTERFACE', 'RNCP35634');
INSERT INTO formations (titre, code_rncp) VALUES ('DEVELOPPEUR WEB ET WEB MOBILE', 'RNCP37674');
INSERT INTO formations (titre, code_rncp) VALUES ('CONCEPTEUR DEVELOPPEUR D''APPLICATIONS', 'RNCP31678');
INSERT INTO formations (titre, code_rncp) VALUES ('MONTEUR AUDIOVISUEL', 'RNCP28280');
INSERT INTO formations (titre, code_rncp) VALUES ('TECHNICIEN ASSISTANCE INFO', 'RNCP37681');
INSERT INTO formations (titre, code_rncp) VALUES ('TECHNICIEN SUPERIEUR SYSTEMES ET RESEAUX', 'RNCP37682');
INSERT INTO formations (titre, code_rncp) VALUES ('AIS', 'RNCP37680');

-- ENTREPRISE
INSERT INTO entreprises (raison_sociale, siret) VALUES ('ATOS FRANCE (ATOS ORIGIN)', 408024719);
INSERT INTO entreprises (raison_sociale, siret) VALUES ('AIRBUS', 383474814);
INSERT INTO entreprises (raison_sociale, siret) VALUES ('LYRA FRANCE', 799960067);
INSERT INTO entreprises (raison_sociale, siret) VALUES ('KONCEPT', 799808761);
INSERT INTO entreprises (raison_sociale, siret) VALUES ('CS GROUP - FRANCE', 393135298);

--INTERACTION
INSERT INTO interactions (type_interaction, date_interaction, utilisateur_id, publication_id) VALUES ('favoris', NOW(), 1, 1);
INSERT INTO interactions (type_interaction, date_interaction, utilisateur_id, publication_id) VALUES ('signaler', NOW(), 2, 1);
INSERT INTO interactions (type_interaction, date_interaction, utilisateur_id, publication_id) VALUES ('postuler', NOW(), 3, 1);
INSERT INTO interactions (type_interaction, date_interaction, utilisateur_id, publication_id) VALUES ('favoris', NOW(), 4, 2);
INSERT INTO interactions (type_interaction, date_interaction, utilisateur_id, publication_id) VALUES ('signaler', NOW(), 1, 2);
INSERT INTO interactions (type_interaction, date_interaction, utilisateur_id, publication_id) VALUES ('postuler', NOW(), 2, 2);
INSERT INTO interactions (type_interaction, date_interaction, utilisateur_id, publication_id) VALUES ('favoris', NOW(), 3, 3);
INSERT INTO interactions (type_interaction, date_interaction, utilisateur_id, publication_id) VALUES ('signaler', NOW(), 4, 3);
INSERT INTO interactions (type_interaction, date_interaction, utilisateur_id, publication_id) VALUES ('postuler', NOW(), 1, 3);
INSERT INTO interactions (type_interaction, date_interaction, utilisateur_id, publication_id) VALUES ('favoris', NOW(), 2, 4);

--SESSION 
-- Insertion pour la formation 'DESIGNER WEB'
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('DW1', 'AFPA - Centre de Toulouse/ Balma', '2023-08-01', '2023-12-31', 1);
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('DW2', 'AFPA - Centre de Toulouse/ Balma', '2024-01-01', '2024-06-30', 1);

-- Insertion pour la formation 'CONCEPTEUR DESIGNER USER INTERFACE'
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('CDUI1', 'AFPA - Centre de Toulouse/ Balma', '2023-08-01', '2023-12-31', 2);
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('CDUI2', 'AFPA - Centre de Toulouse/ Balma', '2024-01-01', '2024-06-30', 2);

-- Insertion pour la formation 'DEVELOPPEUR WEB ET WEB MOBILE'
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('DWW1', 'AFPA - Centre de Toulouse/ Balma', '2023-08-01', '2023-12-31', 3);
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('DWW2', 'AFPA - Centre de Toulouse/ Balma', '2024-01-01', '2024-06-30', 3);

-- Insertion pour la formation 'CONCEPTEUR DEVELOPPEUR D APPLICATIONS'
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('CDA10', 'AFPA - Centre de Toulouse/ Balma', '2023-08-01', '2023-12-31', 4);
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('CDA11', 'AFPA - Centre de Toulouse/ Balma', '2024-01-01', '2024-06-30', 4);

-- Insertion pour la formation 'MONTEUR AUDIOVISUEL'
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('MA1', 'AFPA - Centre de Toulouse/ Balma', '2023-08-01', '2023-12-31', 5);
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('MA2', 'AFPA - Centre de Toulouse/ Balma', '2024-01-01', '2024-06-30', 5);

-- Insertion pour la formation 'TECHNICIEN ASSISTANCE INFO'
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('TAI1', 'AFPA - Centre de Toulouse/ Balma', '2023-08-01', '2023-12-31', 6);
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('TAI2', 'AFPA - Centre de Toulouse/ Balma', '2024-01-01', '2024-06-30', 6);

-- Insertion pour la formation 'TECHNICIEN SUPERIEUR SYSTEMES ET RESEAUX'
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('TSSR1', 'AFPA - Centre de Toulouse/ Balma', '2023-08-01', '2023-12-31', 7);
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('TSSR2', 'AFPA - Centre de Toulouse/ Balma', '2024-01-01', '2024-06-30', 7);

-- Insertion pour la formation 'AIS'
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('AIS1', 'AFPA - Centre de Toulouse/ Balma', '2023-08-01', '2023-12-31', 8);
INSERT INTO sessions (nom_promo, centre, date_debut, date_fin, formation_id) VALUES ('AIS2', 'AFPA - Centre de Toulouse/ Balma', '2024-01-01', '2024-06-30', 8);


--SUIVI
INSERT INTO suivis (utilisateur_id, session_id) VALUES (1, 7);
INSERT INTO suivis (utilisateur_id, session_id) VALUES (2, 7);
INSERT INTO suivis (utilisateur_id, session_id) VALUES (3, 7);
INSERT INTO suivis (utilisateur_id, session_id) VALUES (4, 8);

--STAGE
INSERT INTO stages (type, date_debut, date_fin, utilisateur_id, formation_id, entreprise_id) VALUES ('PAE', '2023-08-01', '2023-12-31', 1, 4, 1);
INSERT INTO stages (type, date_debut, date_fin, utilisateur_id, formation_id, entreprise_id) VALUES ('ALTERNANCE', '2024-01-01', '2024-06-30', 1, 4, 2);
INSERT INTO stages (type, date_debut, date_fin, utilisateur_id, formation_id, entreprise_id) VALUES ('PAE', '2023-08-01', '2023-12-31', 2, 4, 3);
INSERT INTO stages (type, date_debut, date_fin, utilisateur_id, formation_id, entreprise_id) VALUES ('ALTERNANCE', '2024-01-01', '2024-06-30', 2, 4, 4);
INSERT INTO stages (type, date_debut, date_fin, utilisateur_id, formation_id, entreprise_id) VALUES ('PAE', '2023-08-01', '2023-12-31', 3, 4, 5);
INSERT INTO stages (type, date_debut, date_fin, utilisateur_id, formation_id, entreprise_id) VALUES ('ALTERNANCE', '2024-01-01', '2024-06-30', 3, 4, 1);
INSERT INTO stages (type, date_debut, date_fin, utilisateur_id, formation_id, entreprise_id) VALUES ('PAE', '2023-08-01', '2023-12-31', 4, 4, 2);
INSERT INTO stages (type, date_debut, date_fin, utilisateur_id, formation_id, entreprise_id) VALUES ('ALTERNANCE', '2024-01-01', '2024-06-30', 4, 4, 3);
INSERT INTO stages (type, date_debut, date_fin, utilisateur_id, formation_id, entreprise_id) VALUES ('PAE', '2023-08-01', '2023-12-31', 1, 4, 4);
INSERT INTO stages (type, date_debut, date_fin, utilisateur_id, formation_id, entreprise_id) VALUES ('ALTERNANCE', '2024-01-01', '2024-06-30', 2, 4, 5);


--EMBAUCHE
INSERT INTO embauches (date_debut, utilisateur_id, entreprise_id) VALUES ('2023-07-01', 1, 1);
INSERT INTO embauches (date_debut,date_fin, utilisateur_id, entreprise_id) VALUES ('2023-07-01','2024-07-01', 2, 2);
INSERT INTO embauches (date_debut,date_fin, utilisateur_id, entreprise_id) VALUES ('2023-07-01','2024-07-01', 3, 3);
INSERT INTO embauches (date_debut,date_fin, utilisateur_id, entreprise_id) VALUES ('2023-07-01','2024-07-01', 4, 4);
INSERT INTO embauches (date_debut, utilisateur_id, entreprise_id) VALUES ('2023-07-01', 1, 5);
INSERT INTO embauches (date_debut, utilisateur_id, entreprise_id) VALUES ('2023-07-01', 2, 1);
