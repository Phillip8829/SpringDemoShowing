package My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Repo;

import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<AppRole, Integer> {

}
