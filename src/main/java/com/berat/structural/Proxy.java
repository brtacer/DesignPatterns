package com.berat.structural;

import java.util.HashMap;
import java.util.Map;

/**
 * Proxy tasarım deseni, structural grubuna ait oluşturulması karmaşık veya zaman alan işlemlerin kontrolünü sağlar.
 * Dofactory.com a göre kullanım sıklığı %80 civarındadır ve yapı bakımından basittir.
 *
 * Proxy tasarım deseni çalışma maliyeti yüksek işlemlerin olduğu yapılarda, web servisi kullanılan yapılarda,
 * remoting uygulamalarında, operasyonun gerçekleştirilmesinden önce hazırlık yapılması veya ön işlem yapılması durumlarında kullanılır.
 * Uygulanışı basit bir tasarım desenidir.
 */
public class Proxy {
    class Video {

    }

    interface YoutubeService {
        Video getVideo(String url);
    }

    class YoutubeServiceImp implements YoutubeService {
        @Override
        public Video getVideo(String url) {
            return new Video();
        }
    }

    class CachedYoutubeService implements YoutubeService{
        YoutubeService youtubeService;
        Map<String, Video> cache = new HashMap<>();

        public CachedYoutubeService(YoutubeService youtubeService) {
            this.youtubeService = youtubeService;
        }

        @Override
        public Video getVideo(String url) {
            if(cache.containsKey(url)){
                return cache.get(url);
            }

            Video video = youtubeService.getVideo(url);
            cache.put(url, video);
            return video;
        }
    }

    public void ProxyDemo(String url){
        YoutubeService youtubeService = new YoutubeServiceImp();
        YoutubeService cachedYoutubeService = new CachedYoutubeService(youtubeService);

        cachedYoutubeService.getVideo(url);
    }
}
