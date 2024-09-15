package StructuralDesignPatterns.Proxy.proxy;

import StructuralDesignPatterns.Proxy.some_cool_media_library.ThirdPartyYouTubeClass;
import StructuralDesignPatterns.Proxy.some_cool_media_library.ThirdPartyYouTubeLib;
import StructuralDesignPatterns.Proxy.some_cool_media_library.Video;

import java.util.HashMap;

public class YouTubeCacheProxy implements ThirdPartyYouTubeLib {
    private ThirdPartyYouTubeLib youttuveService;
    private HashMap<String, Video> cachePopular = new HashMap<String, Video>();
    private HashMap<String, Video> cacheAll = new HashMap<>();

    public YouTubeCacheProxy(){
        this.youttuveService = new ThirdPartyYouTubeClass();
    }

    @Override
    public HashMap<String, Video> popularVideos() {
        if(cachePopular.isEmpty()){
            cachePopular = youttuveService.popularVideos();
        }
        else{
            System.out.println("Retrieved list from cache.");
        }
        return cachePopular;
    }

    @Override
    public Video getVideo(String videoId) {
        Video video = cacheAll.get(videoId);
        if(video==null){
            video=youttuveService.getVideo(videoId);
            cacheAll.put(videoId,video);
        }
        else{
            System.out.println("Retrieved video '"+videoId+"' from cache.");
        }
        return video;
    }

    public  void reset(){
        cachePopular.clear();
        cacheAll.clear();
    }
}
