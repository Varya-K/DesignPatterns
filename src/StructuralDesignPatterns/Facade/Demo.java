package StructuralDesignPatterns.Facade;

import StructuralDesignPatterns.Facade.facade.VideoConversionFacade;

import java.io.File;

public class Demo {
    public static void main(String[] args){
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.concertVideo("youtubevideo.ogg","mp4");
    }
}
