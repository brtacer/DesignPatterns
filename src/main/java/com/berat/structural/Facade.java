package com.berat.structural;

/**
 * Bir alt sistemin parçalarını oluşturan classları istemciden soyutlayarak kullanımı daha da kolaylaştırmak için tasarlanmış tasarım kalıbıdır.
 * Mimarisel açıdan ise, karmaşık ve detaylı bir sistemi organize eden ve bir bütün olarak clientlara(istemcilere) sunan yapıdır.
 * Anlayacağınız, karmaşık ve detaylı olarak nitelendirdiğimiz bu sistemi bir alt sistem olarak varsayarsak eğer bu sistemi kullanacak
 * clientlara daha basit bir arayüz sağlamak ve alt sistemleri bu arayüze organize bir şekilde dahil etmek ve bu alt sistemlerin sağlıklı
 * çalışabilmesi için bu arayüz çatısı altında işin algoritmasına uygun işlev sergilemek istersek Facade Design Pattern’i kullanmaktayız.
 */
public class Facade {
    class Video {
        String name;
        String codecType;

        public Video(String name) {
            this.name = name;
            this.codecType = name.substring(name.indexOf(".") + 1);
        }
    }

    abstract class Codec {
        String type;
        abstract void decode(Video video);
    }

    class Mp4Codec extends Codec {

        String type = "mp4";

        @Override
        void decode(Video video) {
            System.out.println("Decoding Mp4");
        }
    }

    class VlcCodec extends Codec {

        String type = "vlc";

        @Override
        void decode(Video video) {
            System.out.println("Decoding vlc");
        }
    }

    class CodecFactory {
        Codec getCodec(String type){
            switch (type){
                case "mp4":
                    return new Mp4Codec();
                case "vlc":
                    return new VlcCodec();
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    class BitReader{
        Video convert(Video video, Codec codec){
            System.out.println("Converting video");
            codec.decode(video);
            return video;
        }
    }

    class AudioFixer {
        Video fix(Video video){
            System.out.println("Fixing audio");
            return video;
        }
    }

    public void convertVideo(String filename){
        Video video = new Video(filename);
        CodecFactory factory = new CodecFactory();
        Codec codec = factory.getCodec(video.codecType);
        BitReader reader = new BitReader();
        Video convertedVideo = reader.convert(video, codec);

        AudioFixer audioFixer = new AudioFixer();
        Video finalVideo = audioFixer.fix(convertedVideo);

    }
}
