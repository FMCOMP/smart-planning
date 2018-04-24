package mmjp.fsm.ford.com.mmjp.app.profile.forgetpasscode;

public interface ForgetPasscodeContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
