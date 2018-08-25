/**
 * User: DiatonicScale
 * Date: 25.08.2018
 */

package diatonicscale.worknotes.repository;

import diatonicscale.worknotes.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user); // null, if not saved

    boolean delete(int id);

    User get(int id);

    List<User> getAll();
}
