package com.company;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestCaller implements Runnable {

    private PropertyChangeSupport support ;

    private String oldValue ="";

    RestCaller(String name){
        support = new PropertyChangeSupport(this);
        Thread t = new Thread(this , name);
        t.start();
    }

    @Override
    public void run() {
        sendHttpRequest();
    }

    public void sendHttpRequest(){
        try{

            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build() ;
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(
                    URI.create("https://gorest.co.in/public-api/users")).GET().build();

            HttpResponse<String> response = httpClient.send(httpRequest , HttpResponse.BodyHandlers.ofString());
            setResult(response.body());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void setResult(String newValue){
        support.firePropertyChange("CS" , oldValue , newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }
    public void removePropertyListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }
}
