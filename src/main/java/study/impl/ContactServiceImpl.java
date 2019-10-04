package study.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.entities.ContactEntity;
import study.entities.ContactEntity_;
import study.intf.ContactService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

    @Repository
    @Service("jpaContactService")
    @Transactional
    public class ContactServiceImpl implements ContactService {

        @PersistenceContext
        private EntityManager em;

        public List<ContactEntity> findAll() {
            return em.createNamedQuery("ContactEntity.findAll", ContactEntity.class).getResultList();
        }

        public List<ContactEntity> findAllWithDetail() {
            return em.createNamedQuery("ContactEntity.findAllWithDetail", ContactEntity.class).getResultList();
        }

        public ContactEntity findById(Integer id) {
            TypedQuery<ContactEntity> query = em.createNamedQuery("ContactEntity.findById", ContactEntity.class);
            query.setParameter("id", id);
            return query.getSingleResult();

        }

        public ContactEntity save(ContactEntity contact) {
            if (contact.getId() == null) {
                em.persist(contact);
            } else {
                em.merge(contact);
            }
            System.out.println("Contact saved with id: " + contact.getId());
            return contact;
        }

        public void delete(ContactEntity contact) {
            ContactEntity mergedContact = em.merge(contact);
            em.remove(mergedContact);
            System.out.println("Contact with id: " + mergedContact.getId() + " deleted successfully");
        }

        /* names from columns! not ContactEntity properties */
        final static String ALL_CONTACT_NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from contact";
        public List<ContactEntity> findAllByNativeQuery() {
            return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, ContactEntity.class).getResultList();
        }

       public List<ContactEntity> findAllByNativeQuery2() {
            return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, "nativeSqlResult").getResultList();
      }
        @Transactional(readOnly = true)
        public List<ContactEntity> findByCriteriaQuery(String firstName, String lastName) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            javax.persistence.criteria.CriteriaQuery<ContactEntity> criteriaQuery = cb.createQuery(ContactEntity.class);
            Root<ContactEntity> contactEntityRoot = criteriaQuery.from(ContactEntity.class);
            contactEntityRoot.fetch(ContactEntity_.contactTelDetails, JoinType.LEFT);
            contactEntityRoot.fetch(ContactEntity_.hobbies, JoinType.LEFT);

            criteriaQuery.select(contactEntityRoot).distinct(true);

            Predicate criteria = cb.conjunction();
            //firstName
            if (firstName != null) {
                Predicate p = cb.equal(contactEntityRoot.get(ContactEntity_.firstName), firstName);
                criteria = cb.and(criteria, p);
            }
            //lastName
            if (lastName != null) {
                Predicate p = cb.equal(contactEntityRoot.get(ContactEntity_.lastName), lastName);
                criteria = cb.and(criteria, p);
            }
            criteriaQuery.where(criteria);
            List<ContactEntity> result = em.createQuery(criteriaQuery).getResultList();
            return result;
        }
    }







