
insert into Utilisateurs(nom,prenom,role,email,mdp,description,etat_inscription,url_photo,url_banniere,date_creation,date_modification,geolocalisation_id) values ('Chnaif','Walid','JEDI','chnaifw@gmail.com','$2a$10$Fi75AyzoPZsj48bfr4tbger5IKWHYAaCohBvMnPdbBlclaMd9Qj3.','Beta Testeur','autorisé','https://media.discordapp.net/attachments/811032745379233812/1136221736870883328/7a2ecd93588bf3ce28cc696a928a3830.png','https://media.discordapp.net/attachments/811032745379233812/1136222097354534962/26ae1241ca65ba8e8ff4a4d442c92566.png','2023-07-31 11:08:36.427','2023-07-31 11:08:36.427',25336);

// Equivalant de cette requete dans le ficher afpa.mdr.importation/DataImportRunner.java
//copy geolocalisations(id,latitude,longitude,code_postal,region,ville) from '${classpath:Table_geolocalisation.csv}' WITH DELIMITER ',' CSV HEADER ENCODING 'UTF-8';

 