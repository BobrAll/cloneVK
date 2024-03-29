package bobr.cloneVK.security.auth;

import bobr.cloneVK.security.jwt.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/signUp")
    @Operation(summary = "Register new user")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/signIn")
    @Operation(summary = "Log in to account")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @Transactional
    @GetMapping("/logout")
    @Operation(summary = "Log out of account")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorization) {
        String jwt = jwtService.extractJwtFromHeader(authorization);
        jwtService.delete(jwt);

        return ResponseEntity.ok("Token has been removed successfully");
    }
}
