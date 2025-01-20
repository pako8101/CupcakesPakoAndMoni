package kamenov.cupcakespakoandmoni.services;

import kamenov.cupcakespakoandmoni.models.UserEntity;
import kamenov.cupcakespakoandmoni.models.dtos.UserRegisterBindingModel;
import kamenov.cupcakespakoandmoni.models.user.AppUserDetails;
import kamenov.cupcakespakoandmoni.models.view.UserViewModel;
import org.springframework.security.core.Authentication;

import java.util.Optional;
import java.util.function.Consumer;

public interface UserService {

    public UserEntity registerUser(UserRegisterBindingModel userRegisterBindingModel,
                                   Consumer<Authentication> successfulRegister);

    //public UserViewModel getUserProfile();

    public UserViewModel findBId(Long id);

    UserEntity findByName(String username);

    Optional<AppUserDetails> getCurrentUser();


}
