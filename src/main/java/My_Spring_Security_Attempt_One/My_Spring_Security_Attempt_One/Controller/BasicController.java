package My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Controller;
import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Model.AppRole;
import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Model.AppUser;
import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Repo.RoleRepo;
import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Repo.UserRepo;
import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BasicController {

    @Autowired
  RoleRepo roleRepo;
    @Autowired
  UserService userService;


    @GetMapping("/home")
    public ResponseEntity<String> hello()
    {
        return new ResponseEntity<>("Hello World Home", HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin()
    {
        return new ResponseEntity<>("Hello World Admin", HttpStatus.OK);
    }

    //ADD NEW ROLE
    @PostMapping("/addrole")
    public AppRole addRole(@RequestBody AppRole role)
    {
      return roleRepo.save(role);
    }
//
    @PostMapping("/adduser")
    public AppUser addUser(@RequestBody AppUser user)
    {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userService.addUser(user);
    }


}
