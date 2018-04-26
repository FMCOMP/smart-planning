package mmjp.fsm.ford.com.profile.registration;

public interface RegistrationContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
