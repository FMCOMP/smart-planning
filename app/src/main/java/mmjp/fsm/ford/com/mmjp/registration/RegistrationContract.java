package mmjp.fsm.ford.com.mmjp.app.registration;

public interface RegistrationContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
