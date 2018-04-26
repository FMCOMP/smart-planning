package mmjp.fsm.ford.com.profile.login;

public interface LoginContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
