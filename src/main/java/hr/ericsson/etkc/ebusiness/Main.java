package hr.ericsson.etkc.ebusiness;

import io.quarkus.runtime.annotations.QuarkusMain;

import org.opencv.core.Core;

import io.quarkus.runtime.Quarkus;

@QuarkusMain  
public class Main {

    public static void main(String ... args) {
        System.out.println("Running main method");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Quarkus.run(args); 
    }
}
