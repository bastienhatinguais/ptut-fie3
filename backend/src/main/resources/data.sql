INSERT INTO personnel (nom, prenom) VALUES
  ('lala', 'coucou'),
  ('lolo', 'cocu');

INSERT INTO statut (intitule) VALUES
('Apprenti'),
('Etudiant');

INSERT INTO annee (intitule, statut_id) VALUES
('FIE1', SELECT id FROM statut WHERE intitule = 'Etudiant'),
('FIE2', SELECT id FROM statut WHERE intitule = 'Etudiant');

INSERT INTO semestre (titre, annee_id) VALUES
('1', SELECT id FROM annee WHERE annee.intitule = 'FIE1'),
('2', SELECT id FROM annee WHERE annee.intitule = 'FIE1'),
('3', SELECT id FROM annee WHERE annee.intitule = 'FIE2'),
('4', SELECT id FROM annee WHERE annee.intitule = 'FIE2');

INSERT INTO ue (code, titre, semestre_id) VALUES
('1', 'xxx', SELECT id FROM semestre WHERE titre = '1');
INSERT INTO roles(nom) VALUES
  ('ROLE_USER'),
  ('ROLE_MODERATOR'),
  ('ROLE_ADMIN');

INSERT INTO utilisateurs(nom, email, mot_de_passe, pseudo) VALUES
  ('RootMan', 'root@gmail.com', '$2a$12$.LSB3wXmFcWAVKPDlzewAO9RSL2u9.d4BHexa/xgtg/qpkch3kts2', 'root');
