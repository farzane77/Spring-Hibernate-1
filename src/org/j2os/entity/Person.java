package org.j2os.entity;

import javax.persistence.*;

@Table(name = "person")
@Entity(name = "person")
public class Person {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //@Column(columnDefinition = "VARCHAR2(20)",unique = true,nullable = false)
    private String name;
    private String family;

    public Person() {
    }

    public Person(String name, String family) {
        this.name = name;
        this.family = family;
    }

    @Version
    private long recordVersion;

    public long getId() {
        return id;
    }

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Person setFamily(String family) {
        this.family = family;
        return this;
    }

    public long getRecordVersion() {
        return recordVersion;
    }

    public Person setRecordVersion(long recordVersion) {
        this.recordVersion = recordVersion;
        return this;
    }
}
