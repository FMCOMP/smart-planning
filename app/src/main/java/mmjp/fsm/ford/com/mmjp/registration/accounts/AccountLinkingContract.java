package mmjp.fsm.ford.com.mmjp.app.registration.accounts;

public interface AccountLinkingContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
