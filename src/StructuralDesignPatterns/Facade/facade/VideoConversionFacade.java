package StructuralDesignPatterns.Facade.facade;

import StructuralDesignPatterns.Facade.some_complex_media_library.*;

import java.io.File;

public class VideoConversionFacade {
    public File concertVideo(String fileName, String format){
        System.out.println("VideoConversionFacade: conversion started");
        VideoFile file = new VideoFile(fileName);
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if(format.equals("mp4")){
            destinationCodec = new MPEGCompressionCodec();
        }
        else{
            destinationCodec = new OggCompressionCodec();
        }
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = (new AudioMixer()).fix(intermediateResult);
        System.out.println("VideoConversionFacade: conversion completed.");
        return result;
    }
}
