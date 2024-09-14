package StructuralDesignPatterns.Facade.some_complex_media_library;

public class OggCompressionCodec implements Codec{
    private String type = "ogg";

    @Override
    public String getType() {
        return type;
    }
}
