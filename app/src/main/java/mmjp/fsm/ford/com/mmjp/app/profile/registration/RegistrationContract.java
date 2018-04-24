package mmjp.fsm.ford.com.mmjp.app.profile.registration;

public interface RegistrationContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
