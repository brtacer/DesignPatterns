package com.berat.structural;

/**
 * İki uyumsuz interface’i beraber kullanmamızı sağlar.
 * Daha önce yazılmış olan kodları düzenlemek zorunda kalmayız. Sonuçta ilgili kodun daha önce çalıştığını kabul ediyoruz.
 * Adapte edeceğimiz class üzerinde değişiklik yapmadığımız için uygulama içerisinde kullanılmış olduğu herhangi bir yerde değişiklik yapmamız gerekmez.
 */
public class Adapter {
    class LegacyUser{
        int id;
        String username;
        String name;
        String lastname;
    }

    interface LegacyUserService{
        LegacyUser getUser(int id);
    }

    class User{
        int id;
        String email;
        String username;
        String name;
        String lastname;
        String location;
        Boolean isActive;
    }

    interface UserRepository{
        User getUser(String email);
    }

    interface UserService {
        User getUser(String email);
    }

    class UserServiceAdapter implements UserService {

        UserRepository userRepository;
        LegacyUserService legacyUserService;

        @Override
        public User getUser(String email) {
            User user = userRepository.getUser(email);
            LegacyUser legacyUser = legacyUserService.getUser(user.id);
            user.username = legacyUser.username;
            user.lastname = legacyUser.lastname;
            return user;
        }
    }

    public void adapterDemo(){
        UserService userService = new UserServiceAdapter();
        User user = userService.getUser("email@email.com");
        System.out.println(user);
    }

    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.adapterDemo();
    }
}