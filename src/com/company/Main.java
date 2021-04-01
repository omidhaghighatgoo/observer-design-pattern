package com.company;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main implements PropertyChangeListener {

    private static String result = "";
    private static int i =0;

    public static void main(String[] args) {
        Main observer = new Main();
        RestCaller observable = new RestCaller("rest Caller Thread");
        observable.addPropertyChangeListener(observer);

        while(result.equals("")){
            System.out.println(i++);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.result = (String) evt.getNewValue() ;
        System.out.println(this.result);
    }

}
