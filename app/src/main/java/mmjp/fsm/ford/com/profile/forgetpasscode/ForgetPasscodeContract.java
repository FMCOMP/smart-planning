package mmjp.fsm.ford.com.profile.forgetpasscode;

public interface ForgetPasscodeContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
