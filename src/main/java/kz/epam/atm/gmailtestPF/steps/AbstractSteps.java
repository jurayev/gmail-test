package kz.epam.atm.gmailtestPF.steps;

import kz.epam.atm.gmailtestPF.driver.FactoryDriver;

public class AbstractSteps {

    void openPage(String url){
        FactoryDriver.getInstance().get(url);
    }
}
