/**
 * User: DiatonicScale
 * Date: 27.08.2018
 */

package diatonicscale.worknotes.repository;

import diatonicscale.worknotes.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MockUserRepository implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
