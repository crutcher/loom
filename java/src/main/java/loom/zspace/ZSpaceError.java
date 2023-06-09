package loom.zspace;

/**
 * Base class for ZSpace errors.
 *
 * <p>We throw runtime errors, rather than caught exceptions. This is inline with {@code
 * ArithmeticException}, {@code ArrayIndexOutOfBoundsException}, etc.
 */
public class ZSpaceError extends RuntimeException {
  @java.io.Serial static final long serialVersionUID = -8561235984801453952L;

  ZSpaceError(String message) {
    super(message);
  }

  public ZSpaceError(String message, Throwable cause) {
    super(message, cause);
  }

  public ZSpaceError(Throwable cause) {
    super(cause);
  }
}
