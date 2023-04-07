package com.berat.structural;

import java.util.List;

/**
 * Flyweight pattern, nesne üretiminden kaynaklı bellek kullanımını minimize etmemizi sağlayan bir tasarım kalıbıdır.
 * Eğer bellek tüketimi, çok fazla nesnenin bir arada ele alınmasından kaynaklı ortaya çıkıyorsa burada flyweight tasarım kalıbını kullanabiliriz.
 *
 * Flyweight tasarım kalıbının yapısına değinecek olursak, bu patternde FlyweightFactory dediğimiz bir classımız vardır.
 * Bu class içerisinde Flyweight classının kalıtımını alan classların yani tekrar tekrar üreteceğimiz classların listesini tutar
 * ve bir metot ile clienta bu listeye erişim verir. Client bu metot üzerinden bir nesne üretmek istediği zaman, metot önce
 * bu nesne elindeki listede yani nesne havuzunda var mı diye bakar. Var ise bu listeden verir. Yok ise de önce bu listeye ekler
 * sonra da eklediğini verir.
 */
public class Flyweight {
    class Skin {}

    class Theme {}

    class Particle {
        Skin skin;
        Theme theme;

        public Particle(Skin skin, Theme theme) {
            this.skin = skin;
            this.theme = theme;
        }
    }

    class Coordinate {}

    class Vector {}

    class MovingParticle extends Particle {
        Coordinate coordinate;
        Vector vector;

        public MovingParticle(Skin skin, Theme theme, Coordinate coordinate, Vector vector) {
            super(skin, theme);
            this.coordinate = coordinate;
            this.vector = vector;
        }
    }

    class ParticleFactory {
        List<Particle> particles;

        MovingParticle getParticle(Skin skin,
                                   Theme theme,
                                   Coordinate coordinate,
                                   Vector vector) {


            Particle particle = particles.stream().filter(p -> p.skin == skin && p.theme == theme)
                    .findAny()
                    .orElseGet(() -> {
                        Particle p = new Particle(skin, theme);
                        particles.add(p);
                        return p;
                    });
            return new MovingParticle(particle.skin,
                    particle.theme,
                    coordinate,
                    vector);

        }

        class Game {
            List<MovingParticle> movingParticles;
            ParticleFactory particleFactory;

            void shoot(Coordinate coordinate, Vector vector) {
                MovingParticle movingParticle = particleFactory.getParticle(new Skin(), new Theme(), coordinate, vector);
                movingParticles.add(movingParticle);
            }
        }

    }
}
