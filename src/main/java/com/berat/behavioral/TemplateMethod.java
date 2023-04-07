package com.berat.behavioral;

/**
 * Şablon metot kalıbı davranışsal tasarım kalıpları grubunda yer alır.
 * Şablon metot kalıbı bir işlem için gerekli adımları soyut olarak tanımlar ve bir şablon metot ile algoritmanın nasıl çalışacağını belirler.
 * Alt sınıflar algoritma için gerekli bir ya da birden fazla metodu kendi bünyelerinde gerçekleyerek kullanılan algoritmanın
 * kendi istekleri doğrultusunda çalışmasını sağlarlar.
 * Böylece hem kod tekrarının önüne geçilerek kodun tekrar kullanılabilirliği
 * hem de algoritma iskeletinde yapılacak bir düzenlemenin tek bir yerden yapılması sağlanır.
 * Örneğin ata sınıfa (parent class) konulacak ve tanımlaması yapılmış bir yöntemle, alt sınıfın akışına müdahale etmesi sağlanabilir.
 */
public class TemplateMethod {
    abstract class Game {
        abstract void initialize();
        abstract void startPlay();
        abstract void endPlay();

        public final void play(){
            initialize();
            startPlay();
            endPlay();
        }
    }

    class Cricket extends Game {

        @Override
        void endPlay() {
            System.out.println("Cricket Game Finished!");
        }

        @Override
        void initialize() {
            System.out.println("Cricket Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Cricket Game Started. Enjoy the game!");
        }
    }

    class Football extends Game {

        @Override
        void endPlay() {
            System.out.println("Football Game Finished!");
        }

        @Override
        void initialize() {
            System.out.println("Football Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Football Game Started. Enjoy the game!");
        }
    }

    void templateDemo(){
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }

    public static void main(String[] args) {
        TemplateMethod templateMethod = new TemplateMethod();
        templateMethod.templateDemo();
    }
}
