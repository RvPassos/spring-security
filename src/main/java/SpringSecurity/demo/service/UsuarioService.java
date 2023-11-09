package SpringSecurity.demo.service;

import SpringSecurity.demo.entity.Usuario;
import SpringSecurity.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByUsername((username));

        if(usuario != null){
            return (UserDetails) usuario;
        }

        throw new UsernameNotFoundException("Não encontrado");
    }

    public String criarUsuario(Usuario user) throws Exception {

        Usuario username = usuarioRepository.findByUsername(user.getUsername());

        if(user.getUsername().equals(username)){
            throw new Exception("Username está em uso");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(user.getRole().toUpperCase());

        var usersalvo = usuarioRepository.save(user);


        return "Usuario cadastrado";
    }

}
