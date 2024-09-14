package StructuralDesignPatterns.Facade.some_complex_media_library;

public class MPEGCompressionCodec implements Codec{
    private String type = "mp4";

    @Override
    public String getType() {
        return type;
    }
}
