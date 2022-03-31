package br.com.goodpractices.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Setter
public class Person extends AuditObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person")
    @SequenceGenerator(name = "seq_person", sequenceName = "seq_person", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
}
