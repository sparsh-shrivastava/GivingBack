package customs;

import android.widget.Toast;

/**
 * Created by sparsh on 19/3/17.
 */

public class MethodsClass {
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


}
