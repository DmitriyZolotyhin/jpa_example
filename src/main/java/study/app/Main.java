package study.app;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import study.entities.ContactEntity;
import study.entities.ContactTelDetailEntity;
import study.entities.HobbyEntity;
import study.impl.ContactSummaryUntypeImpl;
import study.intf.ContactService;
import study.intf.ContactSummaryService;
import study.supportClasses.ContactSummary;

    public class Main {

        public static void main(String[] args) {
            GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
            ctx.load("classpath:spring-config.xml");
            ctx.refresh();
            simpleNativeQuery(ctx);

            untypeTutorial(ctx);

            findByID(ctx);
            /* insert delete update tutorial. Пример вставки обновления удаления */
            insertUpdateExample(ctx);
            criteriaExample(ctx);
           deleteExample(ctx);

        }


        private static void simpleNativeQuery(GenericXmlApplicationContext ctx) {
            ContactService service = ctx.getBean("jpaContactService", ContactService.class);
            List<ContactEntity> contacts = service.findAllByNativeQuery();
            List<ContactEntity> contacts2 = service.findAllByNativeQuery2();
            for (ContactEntity c : contacts) {
                System.out.println(c);
            }
            System.out.println("Contacts = Contacts2: " + contacts.equals(contacts2));

        }


        private static void insertUpdateExample(GenericXmlApplicationContext ctx) {
            ContactService service = ctx.getBean("jpaContactService", ContactService.class);

            ContactEntity contact = new ContactEntity();
            contact.addContactTelDetail(new ContactTelDetailEntity("Городской", "8-499-000-333"));
            contact.setFirstName("NameInsert");
            contact.setLastName("LastNameInsert");
            contact.setBirthDate(new java.util.Date());

            ContactTelDetailEntity contactTelDetail = new ContactTelDetailEntity("Home", "1111111111");
            contact.addContactTelDetail(contactTelDetail);

            contactTelDetail = new ContactTelDetailEntity("Городской", "8-499-000-333");
            contact.addContactTelDetail(contactTelDetail);

            contactTelDetail = new ContactTelDetailEntity("Mobile", "2222222222");
            contact.addContactTelDetail(contactTelDetail);

            service.save(contact);

            /*update example */
            ContactEntity existContact = service.findById(contact.getId()); //contact.id - insert before
            existContact.setLastName("updatedLastName");
            existContact.setFirstName("updatedNewName");
            ContactTelDetailEntity toDeleteTel = null;
            for (ContactTelDetailEntity c : existContact.getContactTelDetails()) {
                if (c.getTelType().equals("Mobile")) {
                    toDeleteTel = c;
                }
            }
            existContact.removeContactTelDetail(toDeleteTel);
            service.save(existContact);
        }

        private static void deleteExample(GenericXmlApplicationContext ctx) {
            ContactService service = ctx.getBean("jpaContactService", ContactService.class);
            ContactEntity contactEntity = service.findById(28);
            service.delete(contactEntity);
        }

        private static void untypeTutorial(GenericXmlApplicationContext ctx) {
            ContactSummaryUntypeImpl summaryUntype = ctx.getBean("contactSummaryUntype", ContactSummaryUntypeImpl.class);
            summaryUntype.displayAllContactSummary();
            ContactSummaryService contactSummaryService = ctx.getBean("contactSummaryService", ContactSummaryService.class);
            List<ContactSummary> contactSummaryList = contactSummaryService.findAllSummary();
            for (ContactSummary cs : contactSummaryList) {
                System.out.println(cs);
            }
        }

        private static void findByID(GenericXmlApplicationContext ctx) {
            ContactService contactService = ctx.getBean("jpaContactService", ContactService.class);
            List<ContactEntity> contacts = contactService.findAll();

            System.out.println("JPA . Contact");
            for (ContactEntity contact : contacts) {
                System.out.println(contact);
            }

            List<ContactEntity> cotactsWithDetail = contactService.findAllWithDetail();
            testContactDetail(cotactsWithDetail);

            ContactEntity contactById = contactService.findById(8);
            System.out.println(contactById);
        }

        private static void testContactDetail(List<ContactEntity> contacts) {
            System.out.println("Contact with detail");
            for (ContactEntity contact : contacts) {
                System.out.println("Contact: ");
                System.out.println(contact);
                if (contact.getContactTelDetails() != null) {
                    for (ContactTelDetailEntity tel : contact.getContactTelDetails()) {
                        System.out.println("Details: ");
                        System.out.println(tel);
                    }
                }

                System.out.println("Hobby: ");
                if (contact.getHobbies() != null) {
                    for (HobbyEntity hobby : contact.getHobbies()) {
                        System.out.println(hobby);
                    }
                }
            }
        }

        private static void criteriaExample(GenericXmlApplicationContext ctx) {
            ContactService service = ctx.getBean("jpaContactService", ContactService.class);
           List<ContactEntity> criteriaResult = service.findByCriteriaQuery("Name0", "LastName0");
         //   List<ContactEntity> criteriaResult = service.findByCriteriaQuery("NameInsert", "LastNameInsert");

            for (ContactEntity contact : criteriaResult) {
                System.out.println(contact);
                if (contact.getContactTelDetails() != null) {
                    for (ContactTelDetailEntity tel : contact.getContactTelDetails()) {
                        System.out.println(tel);
                    }
                }
                if (contact.getHobbies() != null) {
                    for (HobbyEntity hobby : contact.getHobbies()) {
                        System.out.println(hobby);
                    }
                }
            }

        }

    }