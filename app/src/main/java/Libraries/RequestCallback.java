package Libraries;

/**
 * Created by Kazal on 05-Mar-16.
 */
public interface RequestCallback<T> {
    void onSuccess(T data);
}
