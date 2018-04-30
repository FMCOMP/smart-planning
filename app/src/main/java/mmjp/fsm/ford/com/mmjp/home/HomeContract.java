package mmjp.fsm.ford.com.mmjp.app.home;

public interface HomeContract {

    interface Presenter {
        void onCreate();
    }

    interface View {
        void showLoading();
        void hideLoading();
    }
}
