package kamenov.cupcakespakoandmoni.services.impl;

import kamenov.cupcakespakoandmoni.models.UserEntity;
import kamenov.cupcakespakoandmoni.models.dtos.UserRegisterBindingModel;
import kamenov.cupcakespakoandmoni.models.user.AppUserDetails;
import kamenov.cupcakespakoandmoni.models.user.UserSession;
import kamenov.cupcakespakoandmoni.models.view.UserViewModel;
import kamenov.cupcakespakoandmoni.repos.UserRepository;
import kamenov.cupcakespakoandmoni.services.AppUserDetailsService;
import kamenov.cupcakespakoandmoni.services.RoleService;
import kamenov.cupcakespakoandmoni.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserSession loggedUser;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, UserDetailsService userDetailsService,
                           PasswordEncoder passwordEncoder, ModelMapper modelMapper,
                           UserSession loggedUser, RoleService roleService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;

        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.roleService = roleService;
    }

    @Override
    public UserEntity registerUser(UserRegisterBindingModel userRegisterBindingModel,
                                   Consumer<Authentication> successfulRegister) {

        UserEntity user = modelMapper.map(userRegisterBindingModel, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        // user.getRoles().add(roleService.findByName(UserRoleEnum.USER));
//        if (userRegisterBindingModel.getImage() == null || Objects.equals(userRegisterBindingModel.getImage().getOriginalFilename(), "")) {
//            user.setImage(profileImageService.getDefaultProfileImage());
//
//        }
//        user.setImage(profileImageService.saveProfileImage(userRegisterBindingModel.
//                getImage(), user));


        user. setFullName(userRegisterBindingModel.getFullName()).
                setEmail(userRegisterBindingModel.getEmail()).
                setUsername(userRegisterBindingModel.getUsername()).
                setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword())
                );

        userRepository.save(user);

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userRegisterBindingModel.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        successfulRegister.accept(authentication);
        return user;
    }

    @Override
    public UserViewModel findBId(Long id) {
        return userRepository.findById(id)
                .map(userEntity -> modelMapper.map(userEntity
                , UserViewModel.class
                )).orElse(null);
    }

    @Override
    public UserEntity findByName(String username) {
        return userRepository.findUserEntityByUsername(username)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Optional<AppUserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication != null &&
                authentication.getPrincipal()
                        instanceof AppUserDetails appUserDetails) {
            return Optional.of(appUserDetails);
        }

        return Optional.empty();
    }
    }

