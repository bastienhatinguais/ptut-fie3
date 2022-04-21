INSERT INTO personnel (nom, prenom) VALUES

  ('Faux', 'Francis'),
  ('Defossez', 'Adrien'), 
  ('Bastide', 'Rémi'), 
  ('Dalce', 'Rejane');

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
('INFO2', 'Informatique', SELECT id FROM semestre WHERE titre = '3', SELECT code FROM personnel WHERE code = '1'),
('COMM3', 'Communication & Santé', SELECT id FROM semestre WHERE titre = '1', SELECT code FROM personnel WHERE code = '2');


INSERT INTO cours(titre,  nb_HeureCM, nb_HeureTD, nb_HeureTP, nb_HeureFOAD, description, objectifs, plan_Du_Cours, competences, modalites_Evaluation, responsable_code, ue_id) VALUES
   ('Traitement du signal', '8', '10', '8', '0', 
  'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod neque ligula, eget mattis dolor hendrerit vel. Donec eleifend, diam et placerat lacinia, nibh dolor porta lacus, ac auctor nulla sapien ac diam.',
  'Nullam eros libero, ornare non elit aliquet, tempus suscipit metus. Morbi ut massa velit. Praesent in placerat est.',
  '1. Lorem, 2.Ipsum, 3.Dolor, 4.Sit', 
  'Nullam eget mollis lacus, quis lobortis quam. Cras id turpis dapibus, tempor ante in, mollis enim. Praesent hendrerit urna at rutrum tempor.', 
  'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...',
   '1', 1),
    ('Introduction à la sociologie de la santé', '8', '10', '8', '0', 
  'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod neque ligula, eget mattis dolor hendrerit vel. Donec eleifend, diam et placerat lacinia, nibh dolor porta lacus, ac auctor nulla sapien ac diam.',
  'Nullam eros libero, ornare non elit aliquet, tempus suscipit metus. Morbi ut massa velit. Praesent in placerat est.',
  '1. Lorem, 2.Ipsum, 3.Dolor, 4.Sit', 
  'Nullam eget mollis lacus, quis lobortis quam. Cras id turpis dapibus, tempor ante in, mollis enim. Praesent hendrerit urna at rutrum tempor.', 
  'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...',
   '2', 2),
   ('Technologies Web', '8', '10', '8', '0', 
  'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod neque ligula, eget mattis dolor hendrerit vel. Donec eleifend, diam et placerat lacinia, nibh dolor porta lacus, ac auctor nulla sapien ac diam.',
  'Nullam eros libero, ornare non elit aliquet, tempus suscipit metus. Morbi ut massa velit. Praesent in placerat est.',
  '1. Lorem, 2.Ipsum, 3.Dolor, 4.Sit', 
  'Nullam eget mollis lacus, quis lobortis quam. Cras id turpis dapibus, tempor ante in, mollis enim. Praesent hendrerit urna at rutrum tempor.', 
  'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...',
   '3', 1),
   ('Réseaux', '12', '2', '16', '0', 
  'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod neque ligula, eget mattis dolor hendrerit vel. Donec eleifend, diam et placerat lacinia, nibh dolor porta lacus, ac auctor nulla sapien ac diam.',
  'Nullam eros libero, ornare non elit aliquet, tempus suscipit metus. Morbi ut massa velit. Praesent in placerat est.',
  '1. Lorem, 2.Ipsum, 3.Dolor, 4.Sit', 
  'Nullam eget mollis lacus, quis lobortis quam. Cras id turpis dapibus, tempor ante in, mollis enim. Praesent hendrerit urna at rutrum tempor.', 
  'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...',
   '4', 1);

INSERT INTO roles(nom) VALUES
  ('ROLE_USER'),
  ('ROLE_MODERATOR'),
  ('ROLE_ADMIN');


INSERT INTO utilisateurs(nom, email, mot_de_passe, pseudo) VALUES
  ('RootMan', 'root@gmail.com', '$2a$12$.LSB3wXmFcWAVKPDlzewAO9RSL2u9.d4BHexa/xgtg/qpkch3kts2', 'root');

INSERT INTO cours_intervenants(donne_les_cours_code, intervenants_code) VALUES
 ('1', '1');

