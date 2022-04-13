INSERT INTO personnel (nom, prenom) VALUES
  ('lala', 'coucou'),
  ('lolo', 'cocu');

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

INSERT INTO ue (code, titre, semestre_id) VALUES
('INFO2', 'informatique', SELECT id FROM semestre WHERE titre = '3'),
('JAVA9', 'java', SELECT id FROM semestre WHERE titre = '1');

INSERT INTO roles(nom) VALUES
  ('ROLE_USER'),
  ('ROLE_MODERATOR'),
  ('ROLE_ADMIN');

INSERT INTO utilisateurs(nom, email, mot_de_passe, pseudo) VALUES
  ('RootMan', 'root@gmail.com', '$2a$12$.LSB3wXmFcWAVKPDlzewAO9RSL2u9.d4BHexa/xgtg/qpkch3kts2', 'root');
