ALTER SEQUENCE public.hibernate_sequence RESTART WITH 2;

INSERT INTO "tp_profil" ("pro_id", "pro_code", "pro_libelle") VALUES (0, 'SAD', 'Super Administrateur');
INSERT INTO "tp_profil" ("pro_id", "pro_code", "pro_libelle") VALUES (1, 'ADM', 'Administrateur');
INSERT INTO "tp_profil" ("pro_id", "pro_code", "pro_libelle") VALUES (2, 'SUP', 'Superviseur');

INSERT INTO "td_utilisateur" ("uti_id", "created_by", "created_date", "modified_by", "modified_date", "uti_email",
                              "uti_first_log", "uti_nom", "uti_password", "uti_prenom", "uti_status", "uti_telephone",
                              "uti_pro_id")
VALUES (1, NULL, '2021-12-04 16:17:29', NULL, '2021-12-04 16:17:29', 'super.admin@yopmail.com', 'false', 'ADMIN',
        '$2a$12$qgGJJe8zDQKPVAFk5vI4GO4DHolbXp5YoQ8V5YjFZAZJvT3vSeubS', 'Super', 'true', '777777777 ', 0);

/*mot de passe = P@sser1234*/

INSERT INTO "td_disposableemail" ("dis_id", "dis_domain") VALUES (1, 'yopmail.com');
