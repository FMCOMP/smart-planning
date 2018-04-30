package mmjp.fsm.ford.com.mmjp.app.registration.accounts;

/**
 * Created by userpmp on 2/15/18.
 */

public class AccountLinkingPresenter implements AccountLinkingContract.Presenter {

    private AccountLinkingContract.View view ;

    public AccountLinkingPresenter(AccountLinkingContract.View view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
       //Add Service Call logic Here
    }
}
