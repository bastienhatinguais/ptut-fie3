import { axiosApi, setToken, deleteToken } from "./api/api";

export default class Auth {
  m_estConnecté;
  m_utilisateur;

  constructor() {
    let localUtilisateur = JSON.parse(localStorage.getItem("utilisateur"));
    this.m_estConnecté = localUtilisateur ? true : false;
    this.m_utilisateur = localUtilisateur ? localUtilisateur : {};
  }

  getEstConnecté() {
    return this.m_estConnecté;
  }

  getUtilisateur() {
    return this.m_utilisateur;
  }

  setEstConnecté(estConnecté) {
    this.m_estConnecté = estConnecté;
  }

  setUtilisateur(utilisateur) {
    localStorage.setItem("utilisateur", JSON.stringify(utilisateur));
    this.m_utilisateur = utilisateur;
  }

  /**
   * Permet de se connecter au compte
   * @param {Object} utilisateur {"pseudo" : ..., "motDePasse" : ...}
   * @returns
   */
  connexion(utilisateur) {
    const self = this;
    return axiosApi
      .post("auth/connexion", {
        pseudo: utilisateur.pseudo,
        motDePasse: utilisateur.motDePasse,
      })
      .then((response) => {
        console.log("coucou" + response);
        if (response.data.token) {
          self.setUtilisateur(response.data);
          self.setEstConnecté(true);
        }
        return response.data;
      })
      .catch((e) => console.log(e));
  }

  /**
   * Permet de se déconnecter du compte
   */
  deconnexion() {
    this.m_utilisateur = null;
    this.m_estConnecté = false;
  }

  inscription(email, pseudo, motDePasse, roles) {
    return axiosApi
      .post("auth/inscription", {
        email: email,
        pseudo: pseudo,
        motDePasse: motDePasse,
        roles: roles,
      })
      .then((response) => console.log(response))
      .catch((e) => console.log(e));
  }
}
