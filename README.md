# CountriesApp
MVVM, Hilt, Retrofit ve Room kullanımlarını pekiştirmek için gerçekleştirilen basit uygulama.

## Description
Bu android uygulaması, Hilt, Retrofit ve Room gibi güncel teknolojileri kullanılarak MVVM'e uygun proje gerçekleştirilmesi konusunda deneyim elde edilmesi adına geliştirilmiştir. Uygulama, temel olarak 2 adet
fragment üzerinde çalışmaktadır. Veriler Retrofit kullanılarak internetten getirildikten sonra Room ile veritabanına kaydedilmektedir. Kullanıcı uygulamayı 10 saniye içinde tekrar açarsa Room ile veritabanına
kaydedilmiş veriler kullanılır. Ancak kullanıcı 10 saniyeden daha fazla bir süre sonra uygulamayı tekrar açarsa, Retrofit kullanılarak veriler internetten tekrar getirilir. Ayrıca SwipeRefreshLayout sayesinde
kullanıcı ekranını yenileyebilir ve uygulama içindeyken verilerin internetten getirilmesini sağlayabilir.

## App Display Image
| HomeFragment | DetailFragment |
|--------------|----------------|
| ![HomeFragment](https://github.com/kursatmemis/CountriesApp/blob/main/images/img_1.jpg) | ![DetailFragment](https://github.com/kursatmemis/CountriesApp/blob/main/images/img_2.jpg) |
