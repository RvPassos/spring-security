package SpringSecurity.demo.controlers;

import SpringSecurity.demo.entity.Usuario;
import SpringSecurity.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario user) throws Exception {
        try {
            return ResponseEntity.ok(usuarioService.criarUsuario(user));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}
