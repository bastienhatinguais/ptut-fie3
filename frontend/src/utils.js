/**
 * recupere l'id d'un objet a partir de son selflink 
 * @param {String} selfLink 
 */
export function selfLinkToId(selfLink) {
    let last = selfLink.lastIndexOf("/");
    let id = selfLink.substring(last + 1)
    return id
}