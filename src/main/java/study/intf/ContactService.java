package study.intf;

import java.util.List;
import study.entities.ContactEntity;

    public interface ContactService {

        // Найти все контакты.
        List<ContactEntity> findAll();
        // Найти все контакты с заданным телефоном и хобби.
        List<ContactEntity> findAllWithDetail();
        // Найти контакт со всеми деталями по идентификатору.
        ContactEntity findById(Integer id);
        // Вставить или обновить контакт.
        ContactEntity save(ContactEntity contact);
        // Удалить контакт.
        void delete(ContactEntity contact);
        // Найти все контакты с помощью собственного запроса
        List<ContactEntity> findAllByNativeQuery();
        List<ContactEntity> findAllByNativeQuery2();
// Запросы c критерием
        List<ContactEntity> findByCriteriaQuery(String firstName, String lastName);

    }


