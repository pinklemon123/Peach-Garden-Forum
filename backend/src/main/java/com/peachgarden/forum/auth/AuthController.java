package com.peachgarden.forum.auth;

import com.peachgarden.forum.user.User;
import com.peachgarden.forum.user.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    private final UserRepository users;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final VerificationTokenRepository tokens;

    public AuthController(UserRepository users, VerificationTokenRepository tokens) {
        this.users = users;
        this.tokens = tokens;
    }

    public record RegisterReq(@NotBlank String username, @Email String email, @NotBlank String password) {}
    public record LoginReq(@Email String email, @NotBlank String password) {}

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReq req) {
        if (users.findByEmail(req.email()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error","email_exists"));
        }
        if (users.findByUsername(req.username()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error","username_exists"));
        }
        User u = new User();
        u.setUsername(req.username());
        u.setEmail(req.email());
        u.setPasswordHash(passwordEncoder.encode(req.password()));
        users.save(u);

        // create verification token and (stub) send email
        VerificationToken vt = new VerificationToken();
        vt.setUserId(u.getId());
        vt.setToken(UUID.randomUUID().toString());
        vt.setExpiresAt(Instant.now().plusSeconds(3600 * 24));
        tokens.save(vt);

        // TODO: integrate real email provider. For now, return token for testing.
        return ResponseEntity.created(URI.create("/auth/register")).body(Map.of("verify_token", vt.getToken()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq req) {
        Optional<User> uOpt = users.findByEmail(req.email());
        if (uOpt.isEmpty()) return ResponseEntity.status(401).body(Map.of("error","invalid_credentials"));
        User u = uOpt.get();
        if (!passwordEncoder.matches(req.password(), u.getPasswordHash())) {
            return ResponseEntity.status(401).body(Map.of("error","invalid_credentials"));
        }
        if (!u.isEmailVerified()) {
            return ResponseEntity.status(403).body(Map.of("error","email_not_verified"));
        }
        // TODO: issue JWT access token
        return ResponseEntity.ok(Map.of("message","login_ok","userId", u.getId()));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam("token") String token) {
        Optional<VerificationToken> tOpt = tokens.findByToken(token);
        if (tOpt.isEmpty()) return ResponseEntity.badRequest().body(Map.of("error","invalid_token"));
        VerificationToken t = tOpt.get();
        if (t.getExpiresAt().isBefore(Instant.now())) {
            return ResponseEntity.badRequest().body(Map.of("error","expired_token"));
        }
        User u = users.findById(t.getUserId()).orElseThrow();
        u.setEmailVerified(true);
        users.save(u);
        tokens.delete(t);
        return ResponseEntity.ok(Map.of("message","verified"));
    }
}

