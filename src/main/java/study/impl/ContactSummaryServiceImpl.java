package study.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;




import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.intf.ContactSummaryService;
import study.supportClasses.ContactSummary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
    /*
    Запрос со специальным типом результата и конструирующим выражением
     */
    @Service("contactSummaryService")
    @Repository
    @Transactional
    public class ContactSummaryServiceImpl implements ContactSummaryService {

        @PersistenceContext
        private EntityManager em;

        public List<ContactSummary> findAllSummary() {
            List<ContactSummary> contactSummaryList = em.createQuery(
                    "select new study.supportClasses.ContactSummary(c.firstName, c.lastName, t.telNumber)"
                            +" from ContactEntity c left join c.contactTelDetails t where t.telType='Домашний'", ContactSummary.class).getResultList();
            return contactSummaryList;
        }
    }


