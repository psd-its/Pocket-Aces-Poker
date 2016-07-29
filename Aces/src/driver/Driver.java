package driver;

import model.facade.AcesFacade;
import model.facade.AcesModel;
import view.main.MainView;

public class Driver
{

    public Driver()
    {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        AcesFacade facade = new AcesModel();
        MainView view = new MainView(facade);
        view.setVisible(true);

    }

}
