import { axiosApi } from "./api/api";
import router from "@/router";

export default class Auth {
  m_estConnecté;
  m_utilisateur;

  constructor() {
    let localUtilisateur = JSON.parse(localStorage.getItem("utilisateur"));
    //utilisateur stocké non vide ?
    if (localUtilisateur && Object.keys(localUtilisateur).length !== 0) {
      this.m_estConnecté = true;
      this.m_utilisateur = localUtilisateur;
    } else {
      this.m_estConnecté = false;
      this.m_utilisateur = {};
    }
  }

  getEstConnecté() {
    return this.m_estConnecté;
  }

  getUtilisateur() {
    return this.m_utilisateur;
  }

  setEstConnecté(estConnecté) {
    this.m_estConnecté = estConnecté;
    console.log({
      utilisateur: this.getUtilisateur(),
      estConnecté: this.getEstConnecté(),
    });
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
    this.setUtilisateur({});
    this.setEstConnecté(false);

    //redirection à l'accueil
    router.push("/");
  }

  inscription(utilisateur) {
    return axiosApi
      .post("auth/inscription", {
        email: utilisateur.email,
        nom: utilisateur.nom,
        prenom: utilisateur.prenom,
        pseudo: utilisateur.pseudo,
        motDePasse: utilisateur.motDePasse,
        roles: utilisateur.roles,
      })
      .then((response) => {
        console.log(response);
        return response.data;
      })
      .catch((e) => console.log(e.response));
  }
}
