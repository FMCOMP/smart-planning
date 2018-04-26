package mmjp.fsm.ford.com.profile.home;

public interface HomeContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
