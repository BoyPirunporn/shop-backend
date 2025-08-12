package com.backend.shop.applications.services.authen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.auth.AuthDTO;
import com.backend.shop.applications.dto.auth.SignInDTO;
import com.backend.shop.applications.dto.auth.TokenDTO;
import com.backend.shop.applications.interfaces.IAuthService;
import com.backend.shop.applications.interfaces.IMenuService;
import com.backend.shop.applications.mapper.UserModelMapper;
import com.backend.shop.domains.models.Permissions;
import com.backend.shop.domains.models.User;
import com.backend.shop.domains.usecase.IAuthUseCase;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.backend.shop.infrastructure.jwt.interfaces.IJwtService;

@Service
public class AuthServiceImpl implements IAuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserModelMapper userModelMapper;
    private final IAuthUseCase authUseCase;
    private final IJwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final IMenuService menuService;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserModelMapper userModelMapper, IAuthUseCase authUseCase, IJwtService jwtService,
            UserDetailsService userDetailsService, IMenuService menuService, PasswordEncoder passwordEncoder) {
        this.userModelMapper = userModelMapper;
        this.authUseCase = authUseCase;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.menuService = menuService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenDTO signUp(AuthDTO auth) {
        User user = userModelMapper.toModel(auth);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("USER -> {}", user.toString());
        // if (authUsecase.existingByEmail(auth.getEmail())) {
        // throw new BaseException("Email already exists", HttpStatus.BAD_REQUEST);
        // }
        // User saveUser = authUsecase.createUser(user);
        // String token = jwtService.generateToken(saveUser.getEmail());
        // String refresnToken = jwtService.generateRefreshToken(user.getEmail());
        return new TokenDTO("token", "refresnToken", null);

    }

    @Override
    public TokenDTO signIn(SignInDTO sign) {
        User user = authUseCase.findByEmail(sign.getEmail());
        System.out.println("PASS -> " + passwordEncoder.encode(sign.getPassword()));
        if (user == null || !passwordEncoder.matches(sign.getPassword(), user.getPassword())) {
            throw new BaseException("Email or password incorrect", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtService.generateToken(user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());
        // List<MenuItemDTO> menus = menuService.getMenu().getPayload();
        List<String> authorities = new ArrayList<>();
        // ดึง ROLE_<NAME>
        authorities.addAll(
                user.getRoles().stream()
                        .map(role -> "ROLE_" + role.getName())
                        .collect(Collectors.toSet()));

        // // ดึง Permission ของแต่ละ Role
        // authorities.addAll(
        //         user.getRoles().stream()
        //                 .flatMap(role -> role.getPermissions().stream())
        //                 .map(Permissions::getName)
        //                 .collect(Collectors.toSet()));

        return new TokenDTO(
                token,
                refreshToken,
                authorities,
                user.getFirstName(),
                user.getLastName(),
                user.getImage(),
                new ArrayList<>());
    }

    @Override
    public TokenDTO refreshToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!jwtService.isTokenValid(refreshToken, userDetails)) {
            throw new BaseException("Invalid Refresh Token", HttpStatus.UNAUTHORIZED);
        }
        String newAccessToken = jwtService.generateToken(userDetails.getUsername());
        return new TokenDTO(newAccessToken, refreshToken);
    }

    @Override
    public User getByEmail(String email) {
        return authUseCase.findByEmail(email);
    }

    @Override
    public DataTablesOutput<AuthDTO> dataTable(DataTablesInput input) {
        logger.info("INITIAL DATATABLE");
        DataTablesOutput<User> output = authUseCase.dataTable(input);
        logger.info("DATATABLE OUTPUT -> {}",output.getDraw());
        DataTablesOutput<AuthDTO> result = new DataTablesOutput<>();
        result.setDraw(output.getDraw());
        result.setError(output.getError());
        result.setRecordsFiltered(output.getRecordsFiltered());
        result.setRecordsTotal(output.getRecordsTotal());
        result.setSearchPanes(output.getSearchPanes());
        result.setData(output.getData().stream().map(userModelMapper::toDTO).collect(Collectors.toList()));

        return result;
    }

}
