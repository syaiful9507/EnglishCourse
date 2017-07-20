package syaiful.finalpro.englishcourse.chat.core.registration;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by syaiful9508 on 02/06/17.
 */

public class RegisterContract {
    public interface View {
        void onRegistrationSuccess(FirebaseUser firebaseUser);

        void onRegistrationFailure(String message);
    }

    interface Presenter {
        void register(Activity activity, String email, String password);
    }

    interface Interactor {
        void performFirebaseRegistration(Activity activity, String email, String password);
    }

    interface OnRegistrationListener {
        void onSuccess(FirebaseUser firebaseUser);

        void onFailure(String message);
    }
}
