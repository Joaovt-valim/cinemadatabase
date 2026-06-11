package com.Senai.Filmes.Service;


import com.Senai.Filmes.DTO.Request.CadastroRequest;
import com.Senai.Filmes.DTO.Request.LoginRequest;
import com.Senai.Filmes.DTO.Response.AuthResponse;
import com.Senai.Filmes.Model.Usuario;
import com.Senai.Filmes.Repository.IUsuarioRepository;
import com.Senai.Filmes.Security.JwtUtil;
import com.Senai.Filmes.Security.UserDetailsServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UserDetailsServiceIml userDetailsServiceIml;

    @Autowired
    private IUsuarioRepository usuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUsuarioRepository userDetailsServiceImpl;


    //CAdastrear novo usuario

    public AuthResponse cadastrarUsuario(CadastroRequest request){
        if(usuarioRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException("Email ja está Cadastrado");
        }
        Usuario usuario=new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());


        usuarioRepository.save(usuario);

        UserDetails userDetails=userDetailsServiceIml.loadUserByUsername(request.email());
        String token = jwtUtil.gerarToken(userDetails);

        return new AuthResponse(token, usuario.getNome(), usuario.getCargo().name());
    }
    //Login
    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha())
        );

        Usuario usuario= usuarioRepository.findByEmail(loginRequest.email()).orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado"));

        UserDetails userDetails = userDetailsServiceIml.loadUserByUsername(loginRequest.email());
        String token = jwtUtil.gerarToken(userDetails);

        return new AuthResponse(token, usuario.getNome(), usuario.getEmail());
    }
}
