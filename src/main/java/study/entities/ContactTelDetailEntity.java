package study.entities;

import javax.persistence.*;




    @Entity
    @Table(name = "contact_tel_detail", schema = "study", catalog = "")
    public class ContactTelDetailEntity {
        private Integer id;
        private String telType;
        private String telNumber;
        private int version;
        private ContactEntity contactByContactId;

        public ContactTelDetailEntity() {
        }

        public ContactTelDetailEntity(String telType, String telNumber) {
            this.telType = telType;
            this.telNumber = telNumber;
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
        @Column(name = "tel_type", nullable = false, insertable = true, updatable = true, length = 20)
        public String getTelType() {
            return telType;
        }

        public void setTelType(String telType) {
            this.telType = telType;
        }

        @Basic
        @Column(name = "tel_number", nullable = false, insertable = true, updatable = true, length = 20)
        public String getTelNumber() {
            return telNumber;
        }

        public void setTelNumber(String telNumber) {
            this.telNumber = telNumber;
        }

        @Basic
        @Column(name = "version", nullable = false, insertable = true, updatable = true)
        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        private ContactEntity contact;

        @ManyToOne
        @JoinColumn(name = "contact_id")
        public ContactEntity getContact() {
            return this.contact;
        }

        public void setContact(ContactEntity contact) {
            this.contact = contact;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ContactTelDetailEntity that = (ContactTelDetailEntity) o;

            if (id != null ? !id.equals(that.id) : that.id != null) return false;
            if (telType != null ? !telType.equals(that.telType) : that.telType != null) return false;
            return !(telNumber != null ? !telNumber.equals(that.telNumber) : that.telNumber != null);

        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (telType != null ? telType.hashCode() : 0);
            result = 31 * result + (telNumber != null ? telNumber.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "ContactTelDetailEntity{" +
                    "version=" + version +
                    ", telNumber='" + telNumber + '\'' +
                    ", telType='" + telType + '\'' +
                    ", id=" + id +
                    '}';
        }
    }


