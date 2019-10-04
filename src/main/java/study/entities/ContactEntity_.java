package study.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

    @Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
    @StaticMetamodel(ContactEntity.class)
    public abstract class ContactEntity_ {

        public static volatile SingularAttribute<ContactEntity, String> firstName;
        public static volatile SingularAttribute<ContactEntity, String> lastName;
        public static volatile SetAttribute<ContactEntity, HobbyEntity> hobbies;
        public static volatile SingularAttribute<ContactEntity, Integer> id;
        public static volatile SingularAttribute<ContactEntity, Date> birthDate;
        public static volatile SingularAttribute<ContactEntity, Integer> version;
        public static volatile SetAttribute<ContactEntity, ContactTelDetailEntity> contactTelDetails;

    }

