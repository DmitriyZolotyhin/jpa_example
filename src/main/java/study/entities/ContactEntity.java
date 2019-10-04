package study.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "contact", schema = "study", catalog = "")
@NamedQueries({
        @NamedQuery(name = "ContactEntity.findAll", query = "select c from ContactEntity c"),
        @NamedQuery(name = "ContactEntity.findById",
                query = "select distinct c from ContactEntity c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),
        @NamedQuery(name = "ContactEntity.findAllWithDetail",
                query = "select distinct c from ContactEntity c left join fetch c.contactTelDetails t left join fetch c.hobbies h")
})
@SqlResultSetMapping(
        name = "nativeSqlResult",
        entities = @EntityResult(entityClass = ContactEntity.class)
)
public class ContactEntity {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int version;

    public ContactEntity() {
    }

    public ContactEntity(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 60)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 40)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = true, insertable = true, updatable = true)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "version", nullable = false, insertable = true, updatable = true)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    private Set<ContactTelDetailEntity> contactTelDetails = new HashSet<ContactTelDetailEntity>();


    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<ContactTelDetailEntity> getContactTelDetails() {
        return this.contactTelDetails;
    }

    public void setContactTelDetails(Set<ContactTelDetailEntity> contactTelDetails) {
        this.contactTelDetails = contactTelDetails;
    }

    public void addContactTelDetail(ContactTelDetailEntity contactTelDetail) {
        contactTelDetail.setContact(this);
        getContactTelDetails().add(contactTelDetail);
    }

    public void removeContactTelDetail(ContactTelDetailEntity contactTelDetai) {
        getContactTelDetails().remove(contactTelDetai);
    }

    private Set<HobbyEntity> hobbies = new HashSet<HobbyEntity>();

    @ManyToMany(fetch = FetchType.EAGER) //если попытаться вызвать getContactTelDetails() in findAll() запрос
    // @ManyToMany
    @JoinTable(name = "contact_hobby_detail",
            //foreign key для ContactEntity  в  contact_hobby_detail таблице
            joinColumns = @JoinColumn(name = "contact_id"),
            //key для - hobby таблицы
            inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    public Set<HobbyEntity> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<HobbyEntity> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactEntity that = (ContactEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        return !(birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactEntity{" +
                "id=" + id +
                ", " + firstName + '\'' +
                ", " + lastName + '\'' +
                ", " + birthDate +
                ", " + version +
                '}';
    }
}


