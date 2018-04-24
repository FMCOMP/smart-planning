package mmjp.fsm.ford.com.mmjp.app.profile.login;

public interface LoginContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
