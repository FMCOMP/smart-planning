package mmjp.fsm.ford.com.mmjp.app.login;

public interface LoginContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
