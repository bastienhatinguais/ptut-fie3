INSERT INTO personnel (nom, prenom) VALUES
  ('Francis', 'Faux'),
  ('Adrien', 'Defossez');

INSERT INTO roles(nom) VALUES
  ('ROLE_USER'),
  ('ROLE_MODERATOR'),
  ('ROLE_ADMIN');


INSERT INTO utilisateurs(nom, email, mot_de_passe, pseudo) VALUES
  ('RootMan', 'root@gmail.com', '$2a$12$.LSB3wXmFcWAVKPDlzewAO9RSL2u9.d4BHexa/xgtg/qpkch3kts2', 'root');

INSERT INTO cours(titre, credits_ects, nb_HeureCM, nb_HeureTD, nb_HeureTP, nb_HeureFOAD, description, objectifs, plan_Du_Cours, competences, modalites_Evaluation, responsable_code) VALUES
  ('maths', '4', '5', '5', '5', '0', 'cours de maths trop super on adore ça en plus', 'le but c est de faire des maths youpi', '1 deux 3 quatre', 'le theoreme de pythagore c est deja pas mal', 'un qcm pitié', '2');

INSERT INTO cours_intervenants(donne_les_cours_code, intervenants_code) VALUES
 ('1', '1');