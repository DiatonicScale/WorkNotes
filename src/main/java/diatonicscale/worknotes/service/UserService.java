/**
 * User: DiatonicScale
 * Date: 25.08.2018
 */

package diatonicscale.worknotes.service;

import diatonicscale.worknotes.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    void update(User user);

    void delete(int id); // exception?

    User get(int id); // exception?

    List<User> getAll();
}
