/**
 * User: DiatonicScale
 * Date: 30.08.2018
 */

package diatonicscale.worknotes.exception;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String msg) {
        super(msg);
    }

    public RepositoryException(String msg, Throwable e) {
        super(msg, e);
    }
}
