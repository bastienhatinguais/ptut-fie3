INSERT INTO personnel (nom, prenom) VALUES
  ('lala', 'coucou'),
  ('lolo', 'cocu');

INSERT INTO roles(nom) VALUES
  ('ROLE_DIRECTEUR_ETUDES'),
  ('ROLE_RESPONSABLE_ANNEE'),
  ('ROLE_RESPONSABLE_COURS');

INSERT INTO utilisateurs(nom, email, mot_de_passe, pseudo) VALUES
  ('RootMan', 'root@gmail.com', '$2a$12$.LSB3wXmFcWAVKPDlzewAO9RSL2u9.d4BHexa/xgtg/qpkch3kts2', 'root');

INSERT INTO utilisateur_roles(utilisateur_id, role_id) VALUES (1,1);