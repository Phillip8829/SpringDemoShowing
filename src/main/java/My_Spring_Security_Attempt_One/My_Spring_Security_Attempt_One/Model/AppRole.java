package My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Model;


import javax.persistence.*;

@Entity
@Table(name="roles")
public class AppRole {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rolename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
