INSERT INTO personnel (nom, prenom) VALUES

  ('Francis', 'Faux'),
  ('Adrien', 'Defossez');

INSERT INTO statut (intitule) VALUES
('apprenti'),
('etudiant');

INSERT INTO annee (intitule, statut_id) VALUES
('FIE1', SELECT id FROM statut WHERE intitule = 'etudiant'),
('FIE2', SELECT id FROM statut WHERE intitule = 'etudiant'),
('FIE3', SELECT id FROM statut WHERE intitule = 'etudiant'),
('FIA3', SELECT id FROM statut WHERE intitule = 'apprenti'),
('FIE4', SELECT id FROM statut WHERE intitule = 'etudiant'),
('FIA4', SELECT id FROM statut WHERE intitule = 'apprenti'),
('FIE5', SELECT id FROM statut WHERE intitule = 'etudiant'),
('FIA5', SELECT id FROM statut WHERE intitule = 'apprenti');

INSERT INTO semestre (titre, annee_id) VALUES
('1', SELECT id FROM annee WHERE annee.intitule = 'FIE1'),
('2', SELECT id FROM annee WHERE annee.intitule = 'FIE1'),
('3', SELECT id FROM annee WHERE annee.intitule = 'FIE2'),
('4', SELECT id FROM annee WHERE annee.intitule = 'FIE2'),
('5', SELECT id FROM annee WHERE annee.intitule = 'FIE3'),
('5', SELECT id FROM annee WHERE annee.intitule = 'FIA3'),
('6', SELECT id FROM annee WHERE annee.intitule = 'FIE3'),
('6', SELECT id FROM annee WHERE annee.intitule = 'FIA3'),
('7', SELECT id FROM annee WHERE annee.intitule = 'FIE4'),
('7', SELECT id FROM annee WHERE annee.intitule = 'FIA4'),
('8', SELECT id FROM annee WHERE annee.intitule = 'FIE4'),
('8', SELECT id FROM annee WHERE annee.intitule = 'FIA4'),
('9', SELECT id FROM annee WHERE annee.intitule = 'FIE5'),
('9', SELECT id FROM annee WHERE annee.intitule = 'FIA5'),
('10', SELECT id FROM annee WHERE annee.intitule = 'FIE5'),
('10', SELECT id FROM annee WHERE annee.intitule = 'FIA5');

INSERT INTO ue (code, titre, semestre_id, responsable_code) VALUES
('INFO2', 'informatique', SELECT id FROM semestre WHERE titre = '3', SELECT code FROM personnel WHERE code = '1'),
('JAVA9', 'java', SELECT id FROM semestre WHERE titre = '1', SELECT code FROM personnel WHERE code = '2');


INSERT INTO cours(titre,  nb_HeureCM, nb_HeureTD, nb_HeureTP, nb_HeureFOAD, description, objectifs, plan_Du_Cours, competences, modalites_Evaluation, responsable_code, ue_id) VALUES
  ('maths', '4', '5', '5', '0', 'cours de maths trop super on adore ça en plus', 'le but c est de faire des maths youpi', '1 deux 3 quatre', 'le theoreme de pythagore c est deja pas mal', 'un qcm pitié', '2', 1),
  ('chinois', '4', '5', '5', '0', 'Un jolie cours de chinois :)', 'Parler chinois', '1 deux 3 quatre', 'chinois', 'un qcm', '2', 1);

INSERT INTO roles(nom) VALUES
  ('ROLE_USER'),
  ('ROLE_MODERATOR'),
  ('ROLE_ADMIN');


INSERT INTO utilisateurs(nom, email, mot_de_passe, pseudo) VALUES
  ('RootMan', 'root@gmail.com', '$2a$12$.LSB3wXmFcWAVKPDlzewAO9RSL2u9.d4BHexa/xgtg/qpkch3kts2', 'root');

INSERT INTO cours_intervenants(donne_les_cours_code, intervenants_code) VALUES
 ('1', '1');

