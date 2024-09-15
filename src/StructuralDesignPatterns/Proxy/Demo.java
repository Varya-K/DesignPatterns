package StructuralDesignPatterns.Proxy;


import StructuralDesignPatterns.Proxy.downloader.YouTubeDownloader;
import StructuralDesignPatterns.Proxy.proxy.YouTubeCacheProxy;
import StructuralDesignPatterns.Proxy.some_cool_media_library.ThirdPartyYouTubeClass;

public class Demo {
    public static void main(String[] args){
        YouTubeDownloader naiveDownloader = new YouTubeDownloader(new ThirdPartyYouTubeClass());
        YouTubeDownloader smartDownlouder = new YouTubeDownloader(new YouTubeCacheProxy());

        long naive = test(naiveDownloader);
        long smart = test(smartDownlouder);
        System.out.println("Time saved by caching proxy: "+(naive-smart)+"ms");
    }

    private static long test(YouTubeDownloader downloader){
        long startTime = System.currentTimeMillis();

        //User behaviour in our app:
        downloader.renderPopularVideos();
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderPopularVideos();
        downloader.renderVideoPage("dancesvideoo");
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderVideoPage("someothervid");

        long estimatedTime = System.currentTimeMillis()-startTime;
        System.out.println("Time elapsed: "+estimatedTime+"ms/n");
        return estimatedTime;
    }
}
