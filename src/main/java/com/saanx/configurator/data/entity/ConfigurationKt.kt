package com.saanx.configurator.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.validator.constraints.NotBlank
import org.springframework.data.rest.core.annotation.RestResource
import javax.persistence.*

/**
 * @author sandjelkovic
 * @date 6.6.17.
 */
@Entity
data class ConfigurationKt(
        @Id @GeneratedValue val id: Long = 0,
        @Version @JsonIgnore val version: Long = 0,
        @NotBlank val name: String = "",
        @Enumerated(value = EnumType.STRING) val visibility: Visibility = Visibility.PRIVATE,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuration", orphanRemoval = true) @RestResource(path = "slots", rel = "slots") val slots: List<SlotKt> = listOf(),
        @JsonIgnore @ManyToOne @JoinColumn(name = "userId") @RestResource(exported = false) var user: User = User())
