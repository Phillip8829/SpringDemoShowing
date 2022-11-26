package My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Services;

import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Model.AppUser;
import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      AppUser user =  userRepo.findByUsername(username);
      UserDetailPrinciple userDetailPrinciple = new UserDetailPrinciple(user);
      return userDetailPrinciple;
    }

    public AppUser addUser(AppUser user) {
        return userRepo.save(user);
    }
}
