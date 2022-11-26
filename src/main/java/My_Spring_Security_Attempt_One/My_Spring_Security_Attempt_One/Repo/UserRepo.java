package My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Repo;

import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Model.AppRole;
import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Integer> {

    AppUser findByUsername(String username);


}
