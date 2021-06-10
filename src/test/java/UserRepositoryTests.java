import com.kiran.crud.users.User;
import com.kiran.crud.users.UserRepository;
import org.assertj.core.internal.Classes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//You can use the @DataJpaTest annotation to test JPA applications.
// By default, it scans for @Entity classes and configures Spring Data JPA repositories.
@DataJpaTest

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)とする
// ことでアプリケーションで設定されているDBを使うことができる
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

/*
@Rollback is a test annotation that is used to indicate
whether a test-managed transaction should be rolled back after the test method has completed.
i.e changes would be committed
 */
@Rollback(false)
public class UserRepositoryTests {

    //また、テストクラス内でテストデータを操作するためにTestEntityManagerが用意されている。
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("kiranpaudyal@gmail.com");
        user.setPassword("kiran123");
        user.setFirstName("Kiran");
        user.setLastName("Paudyal");

        /*
        UserRepository extends CrudRepository.class which contains 'save' method.
         */
        User savedUser = repo.save(user);

        //Find by primary key. Search for an entity of the specified class and primary key.
        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

}
