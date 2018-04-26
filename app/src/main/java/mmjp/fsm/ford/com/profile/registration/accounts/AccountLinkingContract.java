package mmjp.fsm.ford.com.profile.registration.accounts;

public interface AccountLinkingContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
