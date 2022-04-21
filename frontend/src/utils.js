/**
 * recupere l'id d'un objet a partir de son selflink
 * @param {String} selfLink
 */
export function selfLinkToId(selfLink) {
  let last = selfLink.lastIndexOf("/");
  let id = selfLink.substring(last + 1);
  return id;

}

/**
 * Retire http:localhost:8989/api à l'adresse en paramètre
 * @param {String} link
 * @returns
 */
export function trimLink(link) {
  return link.replace("http://localhost:8989/api", "");
}
