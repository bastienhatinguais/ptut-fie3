package com.ptut.syllabus.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ptut.syllabus.entity.Utilisateur;
import com.ptut.syllabus.dao.UtilisateurRepository;

@Service
public class UtilisateurDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UtilisateurRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByPseudo(pseudo)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le pseudo : " + pseudo));
        return UtilisateurDetailsImpl.build(user);
    }
}