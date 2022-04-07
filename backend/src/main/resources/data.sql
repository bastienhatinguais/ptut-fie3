INSERT INTO personnel (nom, prenom) VALUES
  ('lala', 'coucou'),
  ('lolo', 'cocu');

INSERT INTO roles(nom) VALUES
  ('ROLE_USER'),
  ('ROLE_MODERATOR'),
  ('ROLE_ADMIN');

INSERT INTO utilisateurs(nom, email, mot_de_passe, pseudo) VALUES
  ('RootMan', 'root@gmail.com', '$2a$12$.LSB3wXmFcWAVKPDlzewAO9RSL2u9.d4BHexa/xgtg/qpkch3kts2', 'root')